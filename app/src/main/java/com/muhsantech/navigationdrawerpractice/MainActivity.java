package com.muhsantech.navigationdrawerpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.muhsantech.navigationdrawerpractice.fragments.HomeFragment;
import com.muhsantech.navigationdrawerpractice.fragments.NotesFragment;
import com.muhsantech.navigationdrawerpractice.fragments.SearchFragment;
import com.muhsantech.navigationdrawerpractice.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.optNotes) {
                    loadFragment(new NotesFragment(), false);
                } else if (id == R.id.optHome) {
                    loadFragment(HomeFragment.getInstance("Muhsan", 11), true);

                } else if (id == R.id.optSearch) {
                    loadFragment(new SearchFragment(), false);
                } else {// Setting
                    loadFragment(SettingFragment.newInstance("Muhsan", "Javed"), false);
                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment, boolean flag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        if (flag) {
            fragmentTransaction.add(R.id.container, fragment);
            manager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.addToBackStack(String.valueOf(0));

        } else {
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

        // SEND DATA Activity into Fragment
//        Bundle bundle = new Bundle();
//        bundle.putString("name","Muhsan Javed");
//        bundle.putInt("age",15);
//
//        fragment.setArguments(bundle);
    }

    public void CallFromFragment() {
        Toast.makeText(MainActivity.this, "HELLO MUHSAN", Toast.LENGTH_SHORT).show();
    }
}
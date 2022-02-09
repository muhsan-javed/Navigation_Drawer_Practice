package com.muhsantech.navigationdrawerpractice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muhsantech.navigationdrawerpractice.MainActivity;
import com.muhsantech.navigationdrawerpractice.R;

public class HomeFragment extends Fragment {

    private static final String NAME = "name";
    private static final int AGE =  20;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static HomeFragment getInstance(String name, int age) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NAME, name);
        bundle.putInt(String.valueOf(AGE), age);

        homeFragment.setArguments(bundle);

        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = view.findViewById(R.id.myHome);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvAge = view.findViewById(R.id.tvAge);

        // Get DATA
        if (getArguments() != null) {
            String name = getArguments().getString(NAME);
            int age = getArguments().getInt(String.valueOf(AGE));

            textView.setText("Name: "+ name + ",  Age: " + age);
           // Log.d("Values From act ", "name is :" + name + ", age: " + age);

//            tvName.setText(name);
//            tvAge.setText(age);

            ((MainActivity)getActivity()).CallFromFragment();

        }


        return view;
    }
}
package com.appdeb.socialmedia.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appdeb.socialmedia.R;

public class UsersPostTab extends Fragment {
    private TextView textView;
    public static final String TITLE = "Home";

    public UsersPostTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_post_tab, container, false);
    }
}
package com.appdeb.socialmedia.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.appdeb.socialmedia.Fragments.ChattingTab;
import com.appdeb.socialmedia.Fragments.ProfileTab;
import com.appdeb.socialmedia.Fragments.PostTab;
import com.appdeb.socialmedia.Fragments.UsersPostTab;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle args = new Bundle();
        switch(position){

            case 0:
                Fragment fragment = new UsersPostTab();
                args.putInt(ProfileTab.TITLE, position + 1);
                fragment.setArguments(args);
                return fragment;

            case 1:
                Fragment fragment1 = new PostTab();
                args.putInt(ProfileTab.TITLE, position + 1);
                fragment1.setArguments(args);
                return fragment1;

            case 2:
                Fragment fragment2 = new ChattingTab();
                args.putInt(ProfileTab.TITLE, position + 1);
                fragment2.setArguments(args);
                return fragment2;

            case 3:
                Fragment fragment3 = new ProfileTab();
                args.putInt(ProfileTab.TITLE, position + 1);
                fragment3.setArguments(args);
                return fragment3;

            default:
                return null;
        }


    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

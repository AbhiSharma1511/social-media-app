package com.appdeb.socialmedia.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.appdeb.socialmedia.Fragments.ChattingTab;
import com.appdeb.socialmedia.Fragments.ProfileTab;
import com.appdeb.socialmedia.Fragments.PostTab;
import com.appdeb.socialmedia.Fragments.UsersPostTab;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {

        switch (tabPosition){

            case 0:
                UsersPostTab usersTab = new UsersPostTab();
                return usersTab;
            case 1:
                return new PostTab();
            case 2:
                return new ChattingTab();
            case 3:
                return new ProfileTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "Posts";
            case 1:
                return "➕";
            case 2:
                return "Messages";
            case 3:
                return "Profile";
            default:
                return null;

        }    }
}

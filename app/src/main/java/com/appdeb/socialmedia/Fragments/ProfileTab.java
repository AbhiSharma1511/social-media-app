package com.appdeb.socialmedia.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appdeb.socialmedia.R;
import com.appdeb.socialmedia.databinding.FragmentProfileTab2Binding;
import com.appdeb.socialmedia.databinding.FragmentProfileTabBinding;
import com.parse.ParseUser;

public class ProfileTab extends Fragment {
    public static final String TITLE = "object";
//    private FragmentProfileTabBinding binding;
    private FragmentProfileTab2Binding binding;

    final ParseUser parseUser = ParseUser.getCurrentUser();

    public ProfileTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileTab2Binding.inflate(inflater,container,false);
        View view =  binding.getRoot();

        userProfileShower();

        userPostShower();



        return view;
    }



    public void userProfileShower(){
        binding.tvUserProfileName.setText((CharSequence) parseUser.get("profileName"));
        binding.tvUserBio.setText((CharSequence) parseUser.get("profileBio"));
        binding.tvUserProfession.setText( (CharSequence) parseUser.get("profileProfession"));
        binding.tvUserHobbies.setText((CharSequence) parseUser.get("profileHobbies"));
        binding.tvUserFavSports.setText((CharSequence) parseUser.get("profileFavSports"));
    }

    public void userPostShower(){

    }

}
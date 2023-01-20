package com.appdeb.socialmedia.Fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appdeb.socialmedia.Adapters.CurrentUserPostAdapter;
import com.appdeb.socialmedia.Models.CurrentUserPostModel;
import com.appdeb.socialmedia.databinding.FragmentProfileTab2Binding;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ProfileTab extends Fragment {
    public static final String TITLE = "object";
    private FragmentProfileTab2Binding binding;
    final ParseUser parseUser = ParseUser.getCurrentUser();
    public ArrayList<CurrentUserPostModel> arrayList;
    public ProgressDialog progressDialog;

    public ProfileTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater   inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileTab2Binding.inflate(inflater,container,false);
        View view =  binding.getRoot();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");

        getUserProfile();
        getUserProfileImage();
        getUserPosts();

        return view;
    }

    public void getUserProfileImage(){
        ParseFile profileImage = parseUser.getParseFile("profilePicture");
        Objects.requireNonNull(profileImage).getDataInBackground((data, e) -> {
            if (data!=null && e==null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(data,0, data.length);
                binding.imgProfile.setImageBitmap(bitmap);
            }
        });

    }


    public void getUserProfile(){
        binding.tvUserProfileName.setText((CharSequence) parseUser.get("profileName"));
        binding.tvUserBio.setText((CharSequence) parseUser.get("profileBio"));
        binding.tvUserProfession.setText( (CharSequence) parseUser.get("profileProfession"));
        binding.tvUserHobbies.setText((CharSequence) parseUser.get("profileHobbies"));
        binding.tvUserFavSports.setText((CharSequence) parseUser.get("profileFavSports"));
    }


    public void getUserPosts(){

        progressDialog.show();
        ParseQuery<ParseObject> parseQuery = new ParseQuery<>("Photos");
        parseQuery.whereEqualTo("username",parseUser.getUsername());
        parseQuery.orderByDescending("createdAt");

        arrayList = new ArrayList<>();
        parseQuery.findInBackground((List<ParseObject> objects, ParseException e) -> {
            if (objects.size()>0 && e == null) {
                progressDialog.dismiss();
                for (ParseObject post : objects) {
                    ParseFile postPicture = (ParseFile) post.get("picture");
                    Objects.requireNonNull(postPicture).getDataInBackground((byte[] data, ParseException e1) -> {
                        if (data != null && e1 == null) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            CurrentUserPostModel model = new CurrentUserPostModel(bitmap) ;
                            arrayList.add(model);
                            CurrentUserPostAdapter adapter = new CurrentUserPostAdapter(getContext(),arrayList);
                            GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
                            binding.rvCurrentUserPost.setLayoutManager(layoutManager);
                            binding.rvCurrentUserPost.setAdapter(adapter);
                        }
                    });
                }
            }
            else if (objects.size()<=0){
                binding.tvUserPostInfo.setVisibility(View.VISIBLE);
                binding.rvCurrentUserPost.setVisibility(View.INVISIBLE);
            }
        });
    }
}
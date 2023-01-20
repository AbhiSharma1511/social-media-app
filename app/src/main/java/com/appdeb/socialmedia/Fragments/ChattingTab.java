package com.appdeb.socialmedia.Fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appdeb.socialmedia.Adapters.UserChatTabAdapter;
import com.appdeb.socialmedia.Models.UserChatModel;
import com.appdeb.socialmedia.R;
import com.appdeb.socialmedia.databinding.FragmentChattingTabBinding;
import com.appdeb.socialmedia.databinding.FragmentProfileTab2Binding;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ChattingTab extends Fragment {

    public static final String TITLE = "Chats";

    private ArrayList<UserChatModel> userChatModelArrayList;
    private FragmentChattingTabBinding binding;
    private ProgressDialog progressDialog;

    public ChattingTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChattingTabBinding.inflate(inflater,container,false);
        View view =  binding.getRoot();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading users, please wait...");

        getUsersList();

        return view;
    }

    public void getUsersList(){

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        userChatModelArrayList = new ArrayList<>();

        progressDialog.show();
        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (users.size()>0){
                    for (ParseUser user: users){
                        String userProfileName = (String) user.get("profileName");
                        String userDescription = (String) user.get("profileBio");
                        ParseFile profileImage = user.getParseFile("profilePicture");
                        profileImage.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (data != null && e == null) {
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    UserChatModel model = new UserChatModel(userProfileName,userDescription,bitmap);
                                    userChatModelArrayList.add(model);
                                    UserChatTabAdapter userChatTabAdapter = new UserChatTabAdapter(getContext(),userChatModelArrayList);
                                    binding.rvUserChat.setHasFixedSize(true);
                                    binding.rvUserChat.setLayoutManager(new LinearLayoutManager(getContext()));
                                    binding.rvUserChat.setAdapter(userChatTabAdapter);
                                }
                            }
                        });
                    }
                }
                progressDialog.dismiss();
            }
        });

    }
}
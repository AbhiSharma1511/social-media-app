package com.appdeb.socialmedia.MenuActivites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.appdeb.socialmedia.R;
import com.appdeb.socialmedia.databinding.ActivityEditProfileBinding;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class EditProfileActivity extends AppCompatActivity {

    public ActivityEditProfileBinding binding;
    private ProgressDialog progressDialog;
    private String profileName, userBio, userProfession, hobbies, favSports;

    final ParseUser parseUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle(parseUser.getUsername());
        userProfile();

        progressDialog = new ProgressDialog(EditProfileActivity.this);

        binding.btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                profileName = binding.edtProfileName.getText().toString();
                userBio = binding.edtBio.getText().toString();
                userProfession = binding.edtProfession.getText().toString();
                hobbies = binding.edtHobbies.getText().toString();
                favSports = binding.edtSports.getText().toString();

                parseUser.put("profileName",profileName);
                parseUser.put("profileBio",userBio);
                parseUser.put("profileProfession",userProfession);
                parseUser.put("profileHobbies",hobbies);
                parseUser.put("profileFavSports",favSports);

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            progressDialog.dismiss();
                            Toast.makeText(EditProfileActivity.this, "Update successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.rlEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        binding.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void userProfile(){
        binding.edtProfileName.setText((CharSequence) parseUser.get("profileName"));
        binding.edtBio.setText((CharSequence) parseUser.get("profileBio"));
        binding.edtProfession.setText( (CharSequence) parseUser.get("profileProfession"));
        binding.edtHobbies.setText((CharSequence) parseUser.get("profileHobbies"));
        binding.edtSports.setText((CharSequence) parseUser.get("profileFavSports"));
    }
}
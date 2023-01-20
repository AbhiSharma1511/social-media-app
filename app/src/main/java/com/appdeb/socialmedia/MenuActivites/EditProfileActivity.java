package com.appdeb.socialmedia.MenuActivites;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.appdeb.socialmedia.R;
import com.appdeb.socialmedia.databinding.ActivityEditProfileBinding;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    public ActivityEditProfileBinding binding;
    private ProgressDialog progressDialog;
    private String profileName, userBio, userProfession, hobbies, favSports;

    final ParseUser parseUser = ParseUser.getCurrentUser();
    private Uri imageUri;
    public Bitmap imageBitmap;
    ActivityResultLauncher<String> takesPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle(parseUser.getUsername());
        userProfile();
        getProfileImage();
        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setMessage("Uploading...");


        /************************ For choosing the image from the gallery ******************************/
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()== RESULT_OK && result!=null){
                    Intent data = result.getData();
                    imageUri = data.getData();
                    try {
                        imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                        binding.imgUser.setImageBitmap(imageBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
/********************************************************************************/


        binding.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                chooseImage();
//                takesPhoto.launch("image/*");
//                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });

        binding.btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileName = binding.edtProfileName.getText().toString();
                userBio = binding.edtBio.getText().toString();
                userProfession = binding.edtProfession.getText().toString();
                hobbies = binding.edtHobbies.getText().toString();
                favSports = binding.edtSports.getText().toString();

                if (imageBitmap == null){
                    FancyToast.makeText(EditProfileActivity.this,"Choose image!!!",FancyToast.LENGTH_SHORT,FancyToast.DEFAULT,false).show();
                }
                else {
                    progressDialog.show();
                    binding.btnUpdateInfo.setBackgroundColor(Color.GRAY);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    ParseFile parseFile = new ParseFile("pic.png", bytes);
                    parseUser.put("profilePicture", parseFile);
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
                                FancyToast.makeText(EditProfileActivity.this,"Update Successfully.",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                            }
                            else{
                                progressDialog.dismiss();
                                FancyToast.makeText(EditProfileActivity.this,"Error: "+e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                            }
                        }
                    });
                }
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

    }
    /***************************************************************************************************************************/

    public void userProfile(){
        binding.edtProfileName.setText((CharSequence) parseUser.get("profileName"));
        binding.edtBio.setText((CharSequence) parseUser.get("profileBio"));
        binding.edtProfession.setText( (CharSequence) parseUser.get("profileProfession"));
        binding.edtHobbies.setText((CharSequence) parseUser.get("profileHobbies"));
        binding.edtSports.setText((CharSequence) parseUser.get("profileFavSports"));
    }

    public void getProfileImage(){
        ParseFile profileImage = parseUser.getParseFile("profilePicture");
        profileImage.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, ParseException e) {
                if (data!=null && e==null){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data,0, data.length);
                    Toast.makeText(EditProfileActivity.this, "Getting image successfully.", Toast.LENGTH_SHORT).show();
                    binding.imgUser.setImageBitmap((Bitmap.createScaledBitmap(bitmap, binding.imgUser.getWidth(), binding.imgUser.getHeight(), false)));
                }
            }
        });

    }


}
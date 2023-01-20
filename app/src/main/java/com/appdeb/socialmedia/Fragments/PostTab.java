package com.appdeb.socialmedia.Fragments;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appdeb.socialmedia.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PostTab extends Fragment {

    private ImageView imageViewPost;
    private EditText edtImageCaption;
    private Button btnUpdate;

    private Uri imageUri;
    public Bitmap imageBitmap;
    final ParseUser parseUser = ParseUser.getCurrentUser();

    public static final String TITLE = "PostTab";

    public PostTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_post_tab, container, false);

        imageViewPost = view.findViewById(R.id.imgChooseImage);
        edtImageCaption= view.findViewById(R.id.edtCaption);
        btnUpdate = view.findViewById(R.id.btnShareImage);

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading the data...");


        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()== RESULT_OK && result!=null){
                    Intent data = result.getData();
                    imageUri = data.getData();
                    try {
                        imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
                        imageViewPost.setImageBitmap(imageBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        imageViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imageCaption = edtImageCaption.getText().toString();
                if (imageBitmap != null) {
                    btnUpdate.setBackgroundColor(Color.GRAY);
                    progressDialog.show();
                    uploadImage(imageCaption,progressDialog);
                } else {
                    Toast.makeText(getContext(), "Select image first...", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;
    }

    private void uploadImage(String imageCaption, ProgressDialog progressDialog) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ParseFile parseFile = new ParseFile("pic.png", bytes);

        ParseObject parseObject = new ParseObject("Photos");
        parseObject.put("picture", parseFile);
        parseObject.put("pictureCaption", imageCaption);
        parseObject.put("username", ParseUser.getCurrentUser().getUsername());

        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    FancyToast.makeText(getContext(), "Upload Successful.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                    imageViewPost.setImageResource(R.drawable.choose_image);
                    edtImageCaption.setText("");
                } else {
                    FancyToast.makeText(getContext(), "Error: " + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                }
                progressDialog.dismiss();
                btnUpdate.setBackgroundColor(Color.BLUE);
            }
        });
    }
}
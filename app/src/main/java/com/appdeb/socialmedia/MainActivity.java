package com.appdeb.socialmedia;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import com.appdeb.socialmedia.Adapters.TabAdapter;
import com.appdeb.socialmedia.Adapters.ViewPagerAdapter;
import com.appdeb.socialmedia.MenuActivites.EditProfileActivity;
import com.appdeb.socialmedia.MenuActivites.SettingActivty;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private Toolbar toolbar;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;

    String[] tabName = {"🏠","APost","Friend","👤"};
//    ArrayList<Drawable> tabNames = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ParseUser user = ParseUser.getCurrentUser();
        setTitle(user.getUsername());

        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(tabName[position])
        ).attach();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itmPostImage){
            if (Build.VERSION.SDK_INT >=23 &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE},3000);
            }
            else{
//                captureImage();
            }
        }
        else if (item.getItemId() == R.id.itmInsta){
//            startActivity(new Intent(SocialMediaActivity.this,OfficialInstagram.class));
        }
        else if (item.getItemId() == R.id.itmEditProfile){
            startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
        }
        else if (item.getItemId() == R.id.itmCamera){
            openCamera();
        }
        else if (item.getItemId() == R.id.itmSetting){
            startActivity(new Intent(MainActivity.this, SettingActivty.class));
        }
        else if (item.getItemId() == R.id.itmLogout){
            ParseUser.logOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void openCamera() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==3000){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }
        }
    }

}
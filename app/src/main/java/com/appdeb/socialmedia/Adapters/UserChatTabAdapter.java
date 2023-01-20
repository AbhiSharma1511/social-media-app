package com.appdeb.socialmedia.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdeb.socialmedia.Models.UserChatModel;
import com.appdeb.socialmedia.R;
import com.appdeb.socialmedia.databinding.UserChatModelLayoutBinding;

import java.util.ArrayList;

public class UserChatTabAdapter extends RecyclerView.Adapter<UserChatTabAdapter.UserChatViewHolder> {

    private Context context;
    public ArrayList<UserChatModel> arrayList;

    public UserChatTabAdapter(Context context, ArrayList<UserChatModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UserChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_chat_model_layout,parent,false);
        return new UserChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserChatViewHolder holder, int position) {

        UserChatModel model = arrayList.get(position);
        String userProfileName = model.getUserProfileName();
        String userDescription = model.getUserProfileDescription();
        Bitmap userProfileBitmap = model.getUserProfileImageBitmap();

        holder.tvUserName.setText(userProfileName);
        holder.tvUserDescription.setText(userDescription);
        holder.imgUserProfileImage.setImageBitmap(userProfileBitmap);

        holder.imgUserProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.tvUserDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class UserChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvUserDescription;
        ImageView imgUserProfileImage;

        public UserChatViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUserProfileImage = itemView.findViewById(R.id.imgProfile);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserDescription = itemView.findViewById(R.id.tvUserDescription);

        }
    }
}

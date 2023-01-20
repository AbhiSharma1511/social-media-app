package com.appdeb.socialmedia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdeb.socialmedia.Models.CurrentUserPostModel;
import com.appdeb.socialmedia.R;
import java.util.ArrayList;

public class CurrentUserPostAdapter extends RecyclerView.Adapter<CurrentUserPostAdapter.viewHolder> {

    final private Context context;
    public ArrayList<CurrentUserPostModel> arrayList;

    public CurrentUserPostAdapter(Context context, ArrayList<CurrentUserPostModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.currentuserpostlayout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        CurrentUserPostModel model = arrayList.get(position);
        holder.imageView.setImageBitmap(model.getBitmap());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgUserPost);
        }
    }
}



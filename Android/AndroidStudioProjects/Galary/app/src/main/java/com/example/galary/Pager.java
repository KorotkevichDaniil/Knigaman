package com.example.galary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

class Pager extends RecyclerView.Adapter<Pager.ViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Uri> links;
    private Context context;

    Pager(Context context, ArrayList<Uri> links){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.links = links;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(new File(links.get(position).getPath())).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return links.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView image;

        public ViewHolder( View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.full);
        }
    }
}


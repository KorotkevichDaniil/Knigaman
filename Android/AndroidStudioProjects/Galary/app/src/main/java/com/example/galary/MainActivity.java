package com.example.galary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.mediation.Adapter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView list;
    ArrayList<Uri> uris, pictures;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        pictures = getImages();

        adapter = new Adapter(this, pictures);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }

    private ArrayList<Uri> getImages() {
        uris = new ArrayList<Uri>();
        Uri imagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{MediaStore.Images.ImageColumns.DATA ,MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.BUCKET_ID};
        Cursor cursor = this.getContentResolver().query(imagesUri, projection, null,null,null);
        try {
            if(cursor != null){
                cursor.moveToFirst();
            }
            do{
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                uris.add(Uri.parse(datapath));
            }while(cursor.moveToNext());
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return uris;
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private final LayoutInflater inflater;
        private ArrayList<Uri> links;
        private Context context;

        Adapter(Context context, ArrayList<Uri> links){
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.links = links;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            Glide.with(context).load(new File(links.get(position).getPath())).into(holder.image);

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context.getApplicationContext(), FullPicture.class);
                    i.putExtra("list", links);
                    i.putExtra("item", links.get(position).toString());
                    context.startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return links.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private final ImageView image;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
            }
        }
    }
}
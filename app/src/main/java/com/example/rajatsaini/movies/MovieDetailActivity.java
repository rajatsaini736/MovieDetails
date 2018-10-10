package com.example.rajatsaini.movies;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView title,vote,overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        View view = (View)findViewById(R.id.content);
        image = (ImageView)view.findViewById(R.id.imageview);
        title = (TextView)view.findViewById(R.id.tvtitle);
        vote = (TextView)view.findViewById(R.id.tvvote);
        overview = (TextView)view.findViewById(R.id.tvoverview);
        Intent intent = getIntent();
        String movie_title = intent.getStringExtra("MOVIE_TITLE");
        String movie_image_path = intent.getStringExtra("MOVIE_IMAGE_PATH");
        String movie_overview = intent.getStringExtra("MOVIE_OVERVIEW");
        String movie_vote = intent.getStringExtra("MOVIE_VOTE");

        Context context = view.getContext();

        title.setText(movie_title );
        vote.setText(movie_vote);
        overview.setText(movie_overview);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+movie_image_path).into(image);

    }
}

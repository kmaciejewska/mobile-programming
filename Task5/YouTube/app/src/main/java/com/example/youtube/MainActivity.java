package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnMovieListener {
    private ArrayList<MediaObject> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {
        videos = new ArrayList<>(Arrays.asList(Resources.MEDIA_OBJECTS));
        RecyclerView recyclerView = findViewById(R.id.recyclerViewVideos);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, videos, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onMovieClick(int position) { //handles a click inside the recycler
        Intent intent = new Intent(this, WatchActivity.class);
        intent.putExtra("video_url", videos.get(position).getMedia_url());  //detects the video that was clicked and passes its url
        startActivity(intent);
    }
}
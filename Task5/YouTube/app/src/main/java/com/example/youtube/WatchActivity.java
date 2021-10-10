package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class WatchActivity extends YouTubeBaseActivity {
    private static final int RECOVERY_REQUEST = 1;
    private static final int BEGINNING = 0;

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer player;
    Intent intent;
    String url;

    YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            player = youTubePlayer;
            intent = getIntent();
            url = intent.getStringExtra("video_url");
            player.loadVideo(url);

            YouTubePlayer.PlayerStateChangeListener stateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
                @Override
                public void onLoading() {}

                @Override
                public void onLoaded(String s) {}

                @Override
                public void onAdStarted() {}

                @Override
                public void onVideoStarted() {}

                @Override
                public void onVideoEnded() {}

                @Override
                public void onError(YouTubePlayer.ErrorReason errorReason) {
                    showToast(getResources().getString(R.string.error_toast) + " " + errorReason.toString());
                }
            };
            player.setPlayerStateChangeListener(stateChangeListener);
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            if (youTubeInitializationResult.isUserRecoverableError()) {
                youTubeInitializationResult.getErrorDialog(WatchActivity.this, RECOVERY_REQUEST)
                        .show(); //enables the user to recover from error e.g by installing or enabling yt
            } else {
                showToast(getResources().getString(R.string.error_toast) + " " + youTubeInitializationResult.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        Button play = findViewById(R.id.playBtn);
        Button pause = findViewById(R.id.pauseBtn);
        Button stop = findViewById(R.id.stopBtn);
        Button resume = findViewById(R.id.resumeBtn);

        youTubePlayerView = findViewById(R.id.youtube_view);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickButton(view.getId());
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickButton(view.getId());
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickButton(view.getId());
            }
        });
    }

    @Override   //this is needed for the recoverable error, it will try loading again if can recover
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RECOVERY_REQUEST){
            init();
        }
    }

    private void init() {
        youTubePlayerView.initialize(YouTubeConfig.getApiKey(), onInitializedListener);
    }

    private void showToast(String message) {
        Toast.makeText(WatchActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("NonConstantResourceId")
    private void handleClickButton(int id) {
        if(player == null) {
            showToast(getResources().getString(R.string.click_play_error));
            return;
        }

        switch(id) {
            case R.id.pauseBtn:
                player.pause();
                break;
            case R.id.resumeBtn:
                player.play();
                break;
            case R.id.stopBtn:
                player.pause();
                player.seekToMillis(BEGINNING);
                break;
        }
    }
}
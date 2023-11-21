/**
 * MainActivity - The main activity of the Youtube Player app.
 * This activity displays a YouTube video using the AndroidYouTubePlayer library
 * and retrieves video details from the YouTube API.
 *
 * @author Your Name
 * @date November 21, 2023
 */

package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.helloworld.network.YoutubeApiInterface;
import com.example.helloworld.utils.DialogUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Default video ID to be loaded
    private static final String DEFAULT_VIDEO_ID = "YYyr07di8mw";

    private TextView videoTitleTextView;
    private TextView videoDescriptionTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        videoTitleTextView = findViewById(R.id.video_title);
        videoDescriptionTextView = findViewById(R.id.video_description);


        // Show loading dialog while ready YouTube player and retrieving data from YouTube API
        ProgressDialog loadingDialog = DialogUtils.showLoadingDialog(MainActivity.this, "Loading data from YouTube API");


        // Set up YouTubePlayerView with listener for video loading and API data retrieval
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);

                // Retrieve video details from YouTube API
                YoutubeApiInterface.getVideoById(MainActivity.this, DEFAULT_VIDEO_ID, new YoutubeApiInterface.ApiResponseListener() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            // Parse video information from the API response
                            JSONObject videoInformation = response.getJSONArray("items").getJSONObject(0);
                            JSONObject videoSnippet = videoInformation.getJSONObject("snippet");

                            videoTitleTextView.setText(videoSnippet.getString("title"));
                            videoDescriptionTextView.setText(videoSnippet.getString("description"));

                            youTubePlayer.loadVideo(videoInformation.getString("id"), 0);
                        } catch (JSONException e) {
                            videoTitleTextView.setVisibility(View.GONE);
                            videoDescriptionTextView.setText(e.getMessage());
                        } finally {
                            DialogUtils.hideLoadingDialog(loadingDialog);
                        }
                    }

                    @Override
                    public void onError(String error) {
                        videoTitleTextView.setVisibility(View.GONE);
                        videoDescriptionTextView.setText(error);
                        DialogUtils.hideLoadingDialog(loadingDialog);
                    }
                });
            }
        });
    }
}

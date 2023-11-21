/**
 * YoutubeApiInterface - A class for interacting with the YouTube Data API.
 * This class provides methods to retrieve playlist items and video details using the YouTube API.
 * Requires an API key for authentication.
 *
 * @author MD. Jahid Hasan
 * @date November 21, 2023
 */

package com.example.helloworld.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

public class YoutubeApiInterface {

    // Base URL for the YouTube Data API
    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";

    // YouTube API key for authentication
    private static final String YOUTUBE_API_KEY = "AIzaSyBVkCtypBb5XwrqG3mwlr1DjaHZpd1yEUI";

    // Retrieves playlist items from YouTube based on the provided playlist ID.
    public static void getPlaylistItems(Context context, String playlistId, final ApiResponseListener listener) {
        String url = BASE_URL + "playlistItems?part=snippet&playlistId=" + playlistId + "&key=" + YOUTUBE_API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                listener::onSuccess,
                error -> listener.onError(error.toString()));

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    // Retrieves details of a YouTube video based on the provided video ID.
    public static void getVideoById(Context context, String videoId, final ApiResponseListener listener) {
        String url = BASE_URL + "videos?part=snippet&id=" + videoId + "&key=" + YOUTUBE_API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                listener::onSuccess,
                error -> listener.onError(error.getMessage()));

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    /**
     * Interface for handling YouTube API response callbacks.
     */
    public interface ApiResponseListener {
        void onSuccess(JSONObject response);

        void onError(String error);
    }
}

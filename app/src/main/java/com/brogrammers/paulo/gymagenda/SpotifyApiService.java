package com.brogrammers.paulo.gymagenda;

import com.brogrammers.paulo.gymagenda.converters.SpotifyTrackConverter;
import com.brogrammers.paulo.gymagenda.dto.SpotifyTrack;

import org.json.JSONObject;

/**
 * Created by paulo on 4/13/17.
 */

public class SpotifyApiService {

    public SpotifyTrack getCurrentlyPlayingTrack(String url, String accessToken) {
        SpotifyTrack track = new SpotifyTrack();
        String currentlyPlayingTrack = null;
        try {
            currentlyPlayingTrack = new RetrieveData().execute(url, accessToken).get();
            if(currentlyPlayingTrack != null) {
                SpotifyTrackConverter converter = new SpotifyTrackConverter();
                JSONObject jsonObj = new JSONObject(currentlyPlayingTrack);
                track = converter.convertResponse(jsonObj);
            }
        } catch (Exception e) {
            System.out.println("Error getCurrentlyPlayingTrack: " + e);
        }
        return track;
    }

    public String getUserPlaylists(String url, String accessToken) {
        String playList = null;
        try {
            playList = new RetrieveData().execute(url, accessToken).get();
        } catch (Exception e) {
            System.out.println("Error getUserPlaylists: " + e);
        }
        return playList;
    }
}

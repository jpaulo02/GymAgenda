package com.brogrammers.paulo.gymagenda;

/**
 * Created by paulo on 4/13/17.
 */

public enum SpotifyEndpoints {
    GET_CURRENTLY_PLAYING("https://api.spotify.com/v1/me/player/currently-playing"),
    GET_USER_PLAYLISTS("https://api.spotify.com/v1/users/{user_id}/playlists");

    private final String value;

    private SpotifyEndpoints(String s) {
        value = s;
    }

    public String getStringValue() {
        return this.value;
    }
}

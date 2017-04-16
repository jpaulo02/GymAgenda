package com.brogrammers.paulo.gymagenda.dto;

/**
 * Created by paulo on 4/13/17.
 */

public enum SpotifyAlbumType {
    ALBUM("album"),
    SINGLE("single"),
    COMPILATION("compilation");

    private final String value;

    private SpotifyAlbumType(String s) {
        value = s;
    }

    public String getStringValue() {
        return this.value;
    }
}

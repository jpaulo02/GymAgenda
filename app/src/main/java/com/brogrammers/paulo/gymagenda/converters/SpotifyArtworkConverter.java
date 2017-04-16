package com.brogrammers.paulo.gymagenda.converters;

import com.brogrammers.paulo.gymagenda.dto.SpotifyAlbumArtwork;

import org.json.JSONObject;

/**
 * Created by paulo on 4/14/17.
 */

public class SpotifyArtworkConverter {

    public SpotifyAlbumArtwork convertResponse(JSONObject jsonObject) throws Exception {
        SpotifyAlbumArtwork artwork = new SpotifyAlbumArtwork();
        try {
            artwork.setUrl(jsonObject.getString("url"));
            artwork.setHeight(jsonObject.getInt("height"));
            artwork.setWidth(jsonObject.getInt("width"));
            return artwork;
        } catch (Exception e) {
            System.out.println("Error converting artwork " + e);
        }
        return  artwork;
    }
}

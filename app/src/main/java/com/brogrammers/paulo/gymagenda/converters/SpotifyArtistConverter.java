package com.brogrammers.paulo.gymagenda.converters;

import com.brogrammers.paulo.gymagenda.dto.SpotifyArtist;

import org.json.JSONObject;

/**
 * Created by paulo on 4/14/17.
 */

public class SpotifyArtistConverter {

    public SpotifyArtist convertResponse(JSONObject jsonObject) throws Exception {
        SpotifyArtist artist = new SpotifyArtist();
        try {
            artist.setId(jsonObject.getString("id"));
            artist.setName(jsonObject.getString("name"));
            artist.setUri(jsonObject.getString("uri"));
        } catch (Exception e) {
            System.out.println("Error converting artist " + e);
        }
        return artist;
    }
}

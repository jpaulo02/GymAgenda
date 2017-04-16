package com.brogrammers.paulo.gymagenda.converters;

import com.brogrammers.paulo.gymagenda.dto.SpotifyAlbum;
import com.brogrammers.paulo.gymagenda.dto.SpotifyAlbumArtwork;
import com.brogrammers.paulo.gymagenda.dto.SpotifyArtist;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 4/14/17.
 */

public class SpotifyAlbumConverter {

    SpotifyArtistConverter artistConverter = new SpotifyArtistConverter();
    SpotifyArtworkConverter artworkConverter = new SpotifyArtworkConverter();

    public SpotifyAlbum convertResponse(JSONObject jsonObject) throws Exception {
        SpotifyAlbum album = new SpotifyAlbum();
        try {
            List<SpotifyArtist> artists = new ArrayList<>();
            List<SpotifyAlbumArtwork> artworkList = new ArrayList<>();
            album.setId(jsonObject.getString("id"));
            album.setAlbum_type(jsonObject.getString("album_type"));
            JSONArray artistArray = jsonObject.getJSONArray("artists");
            JSONArray imageArray = jsonObject.getJSONArray("images");
            album.setHref(jsonObject.getString("href"));
            album.setName(jsonObject.getString("name"));
            album.setUri(jsonObject.getString("uri"));

            if (artistArray.length() > 0) {
                for (int i = 0; i < artistArray.length(); i++) {
                    SpotifyArtist artist = artistConverter.convertResponse(artistArray.getJSONObject(i));
                    artists.add(artist);
                }
            }
            if (imageArray.length() > 0) {
                for (int i = 0; i < imageArray.length(); i++) {
                    SpotifyAlbumArtwork artwork = artworkConverter.convertResponse(imageArray.getJSONObject(i));
                    artworkList.add(artwork);
                }
            }
            album.setArtists(artists);
            album.setArtworkList(artworkList);
        } catch (Exception e) {
            System.out.println("Error convering spotify album " + e);
        }

        return album;
    }
}

package com.brogrammers.paulo.gymagenda.converters;

import com.brogrammers.paulo.gymagenda.dto.SpotifyArtist;
import com.brogrammers.paulo.gymagenda.dto.SpotifyTrack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 4/14/17.
 */

public class SpotifyTrackConverter {

    public SpotifyTrack convertResponse(JSONObject jsonObject) throws Exception {
        SpotifyTrack track = new SpotifyTrack();
        try {
            List<SpotifyArtist> artists = new ArrayList<>();
            track.setTimestamp(jsonObject.getLong("timestamp"));
            track.setProgress_ms(jsonObject.getInt("progress_ms"));
            track.setIs_playing(jsonObject.getBoolean("is_playing"));
            if (jsonObject.getJSONObject("item") != null) {
                SpotifyAlbumConverter albumConverter = new SpotifyAlbumConverter();
                JSONObject itemObj = jsonObject.getJSONObject("item");
                track.setDuration_ms(itemObj.getInt("duration_ms"));
                track.setExplicit(itemObj.getBoolean("explicit"));
                track.setId(itemObj.getString("id"));
                track.setName(itemObj.getString("name"));
                track.setPopularity(itemObj.getInt("popularity"));
                track.setTrack_number(itemObj.getInt("track_number"));
                track.setUri(itemObj.getString("uri"));
                track.setAlbum(albumConverter.convertResponse(itemObj.getJSONObject("album")));
                JSONArray artistArray = itemObj.getJSONArray("artists");
                if (artistArray.length() > 0) {
                    SpotifyArtistConverter artistConverter = new SpotifyArtistConverter();
                    for (int i = 0; i < artistArray.length(); i++) {
                        SpotifyArtist artist = artistConverter.convertResponse(artistArray.getJSONObject(i));
                        artists.add(artist);
                    }
                }
                track.setArtists(artists);
            }
        } catch (Exception e) {
            System.out.println("Error converting track " + e);
        }
        return track;
    }
}

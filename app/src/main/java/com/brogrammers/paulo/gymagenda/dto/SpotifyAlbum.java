package com.brogrammers.paulo.gymagenda.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 4/13/17.
 */

public class SpotifyAlbum {

    String id;
    String album_type;
    List<SpotifyArtist> artists = new ArrayList<SpotifyArtist>();
    String href;
    List<SpotifyAlbumArtwork> artworkList = new ArrayList<SpotifyAlbumArtwork>();
    String name;
    String uri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }

    public List<SpotifyArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<SpotifyArtist> artists) {
        this.artists = artists;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<SpotifyAlbumArtwork> getArtworkList() {
        return artworkList;
    }

    public void setArtworkList(List<SpotifyAlbumArtwork> artworkList) {
        this.artworkList = artworkList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

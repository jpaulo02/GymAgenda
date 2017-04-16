package com.brogrammers.paulo.gymagenda.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 4/13/17.
 */

public class SpotifyTrack {

    String id;
    String name;
    long timestamp;
    Integer progress_ms;
    Boolean is_playing;
    SpotifyAlbum album;
    List<SpotifyArtist> artists = new ArrayList<SpotifyArtist>();
    Integer duration_ms;
    Boolean explicit;
    String href;
    Integer popularity;
    Integer track_number;
    String uri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getProgress_ms() {
        return progress_ms;
    }

    public void setProgress_ms(Integer progress_ms) {
        this.progress_ms = progress_ms;
    }

    public Boolean getIs_playing() {
        return is_playing;
    }

    public void setIs_playing(Boolean is_playing) {
        this.is_playing = is_playing;
    }

    public SpotifyAlbum getAlbum() {
        return album;
    }

    public void setAlbum(SpotifyAlbum album) {
        this.album = album;
    }

    public List<SpotifyArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<SpotifyArtist> artists) {
        this.artists = artists;
    }

    public Integer getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(Integer duration_ms) {
        this.duration_ms = duration_ms;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getTrack_number() {
        return track_number;
    }

    public void setTrack_number(Integer track_number) {
        this.track_number = track_number;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}


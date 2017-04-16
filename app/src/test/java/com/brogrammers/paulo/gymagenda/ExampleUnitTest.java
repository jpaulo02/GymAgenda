package com.brogrammers.paulo.gymagenda;

import com.brogrammers.paulo.gymagenda.converters.SpotifyTrackConverter;
import com.brogrammers.paulo.gymagenda.dto.SpotifyTrack;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;


public class ExampleUnitTest {


    @Test
    public void convertTrackResponseTest() throws Exception {
        try {
            SpotifyTrack track = this.createTrackResponse();
            assertNotNull(track);
            assertNotNull(track.getTimestamp());
        } catch (Exception e) {
            System.out.println("Error in convertTrackResponseTest " + e);
        }
    }

    private SpotifyTrack createTrackResponse() throws Exception {
        SpotifyTrackConverter converter = new SpotifyTrackConverter();
        String stringResponse = "{\"timestamp\":1491957806689,\"progress_ms\":3072,\"is_playing\":false,\"item\":{\"album\":{\"album_type\":\"album\",\"artists\":[{\"external_urls\":{\"spotify\":\"https:\\/\\/open.spotify.com\\/artist\\/2P5sC9cVZDToPxyomzF1UH\"},\"href\":\"https:\\/\\/api.spotify.com\\/v1\\/artists\\/2P5sC9cVZDToPxyomzF1UH\",\"id\":\"2P5sC9cVZDToPxyomzF1UH\",\"name\":\"Joey Bada$$\",\"type\":\"artist\",\"uri\":\"spotify:artist:2P5sC9cVZDToPxyomzF1UH\"}],\"available_markets\":[\"AD\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"EC\",\"EE\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IS\",\"IT\",\"JP\",\"LI\",\"LT\",\"LU\",\"LV\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"PA\",\"PE\",\"PH\",\"PL\",\"PT\",\"PY\",\"SE\",\"SG\",\"SK\",\"SV\",\"TR\",\"TW\",\"US\",\"UY\"],\"external_urls\":{\"spotify\":\"https:\\/\\/open.spotify.com\\/album\\/6swV0WUnPygRIMgEKn6Ige\"},\"href\":\"https:\\/\\/api.spotify.com\\/v1\\/albums\\/6swV0WUnPygRIMgEKn6Ige\",\"id\":\"6swV0WUnPygRIMgEKn6Ige\",\"images\":[{\"height\":640,\"url\":\"https:\\/\\/i.scdn.co\\/image\\/f1c7856e2e9236385a1fcffc2fb274c4d33aaac5\",\"width\":640},{\"height\":300,\"url\":\"https:\\/\\/i.scdn.co\\/image\\/b08620eeb991e769cb699e0afbd58fd193b627e4\",\"width\":300},{\"height\":64,\"url\":\"https:\\/\\/i.scdn.co\\/image\\/b68b7084ac59f302ca3c587e7b61e72880214270\",\"width\":64}],\"name\":\"ALL-AMERIKKKAN BADA$$\",\"type\":\"album\",\"uri\":\"spotify:album:6swV0WUnPygRIMgEKn6Ige\"},\"artists\":[{\"external_urls\":{\"spotify\":\"https:\\/\\/open.spotify.com\\/artist\\/2P5sC9cVZDToPxyomzF1UH\"},\"href\":\"https:\\/\\/api.spotify.com\\/v1\\/artists\\/2P5sC9cVZDToPxyomzF1UH\",\"id\":\"2P5sC9cVZDToPxyomzF1UH\",\"name\":\"Joey Bada$$\",\"type\":\"artist\",\"uri\":\"spotify:artist:2P5sC9cVZDToPxyomzF1UH\"},{\"external_urls\":{\"spotify\":\"https:\\/\\/open.spotify.com\\/artist\\/6l3HvQ5sa6mXTsMTB19rO5\"},\"href\":\"https:\\/\\/api.spotify.com\\/v1\\/artists\\/6l3HvQ5sa6mXTsMTB19rO5\",\"id\":\"6l3HvQ5sa6mXTsMTB19rO5\",\"name\":\"J. Cole\",\"type\":\"artist\",\"uri\":\"spotify:artist:6l3HvQ5sa6mXTsMTB19rO5\"}],\"available_markets\":[\"AD\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"EC\",\"EE\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IS\",\"IT\",\"JP\",\"LI\",\"LT\",\"LU\",\"LV\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"PA\",\"PE\",\"PH\",\"PL\",\"PT\",\"PY\",\"SE\",\"SG\",\"SK\",\"SV\",\"TR\",\"TW\",\"US\",\"UY\"],\"disc_number\":1,\"duration_ms\":278111,\"explicit\":true,\"external_ids\":{\"isrc\":\"QMKSC1700049\"},\"external_urls\":{\"spotify\":\"https:\\/\\/open.spotify.com\\/track\\/5csdNgCD64XzhsyoRlhzsa\"},\"href\":\"https:\\/\\/api.spotify.com\\/v1\\/tracks\\/5csdNgCD64XzhsyoRlhzsa\",\"id\":\"5csdNgCD64XzhsyoRlhzsa\",\"name\":\"LEGENDARY (feat. J. Cole)\",\"popularity\":68,\"preview_url\":\"https:\\/\\/p.scdn.co\\/mp3-preview\\/e27bc4d1ff80117f02d24d75250a43db8c7de81a?cid=4f1072d78c0245d091989b0115460af4\",\"track_number\":11,\"type\":\"track\",\"uri\":\"spotify:track:5csdNgCD64XzhsyoRlhzsa\"},\"context\":{\"external_urls\":{\"spotify\":\"http:\\/\\/open.spotify.com\\/user\\/jeff_paulo2\\/playlist\\/1gDCwfInID7ld97Org9qK9\"},\"href\":\"https:\\/\\/api.spotify.com\\/v1\\/users\\/jeff_paulo2\\/playlists\\/1gDCwfInID7ld97Org9qK9\",\"type\":\"playlist\",\"uri\":\"spotify:user:jeff_paulo2:playlist:1gDCwfInID7ld97Org9qK9\"}}";
        JSONObject jsonObj = new JSONObject(stringResponse);
        return converter.convertResponse(jsonObj);
    }
}
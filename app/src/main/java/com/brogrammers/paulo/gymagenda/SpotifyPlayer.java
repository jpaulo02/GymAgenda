package com.brogrammers.paulo.gymagenda;

import android.util.Log;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;

/**
 * Created by paulo on 4/16/17.
 */

public class SpotifyPlayer {

    String accessToken;
    String clientId;

    public SpotifyPlayer(String accessToken, String clientId) {
        this.accessToken = accessToken;
        this.clientId = clientId;
    }

//    public playSong() {
//        Config playerConfig = new Config(this, accessToken, clientId);
//        Spotify.getPlayer(playerConfig,this,new Player.InitializationObserver()
//
//        {
//            @Override
//            public void onInitialized (Player player){
//            mPlayer = player;
//            mPlayer.addConnectionStateCallback(DashboardActivity.this);
//            mPlayer.addPlayerNotificationCallback(DashboardActivity.this);
//            //mPlayer.play("spotify:track:6WTZqt2ghvOxdmXCpGb84Z");
//        }
//
//            @Override
//            public void onError (Throwable throwable){
//            Log.e("DashboardActivity", "Could not initialize player: " + throwable.getMessage());
//        }
//        });
//    }
}

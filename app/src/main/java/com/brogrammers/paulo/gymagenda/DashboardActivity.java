package com.brogrammers.paulo.gymagenda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.StrictMode;

import com.brogrammers.paulo.gymagenda.dto.SpotifyTrack;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;

import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.UserPrivate;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DashboardActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener, PlayerNotificationCallback, ConnectionStateCallback {

    //spotify app client id
    private static final String CLIENT_ID = "4f1072d78c0245d091989b0115460af4";
    //client id from google dev console
    //private static final String CLIENT_ID = "179355552324-83i4r983iv673977ktvi8o7p7ngi9lju.apps.googleusercontent.com";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "gymagenda://callback";
    private String ACCESS_TOKEN = null;
    private SpotifyApiService spotifyApiService = new SpotifyApiService();
    private Player mPlayer;
    private static final int REQUEST_CODE = 1337;
    private static final String TAG = "DashboardActivity";
    private GoogleApiClient mGoogleApiClient;
    private TextView userNameTextView;
    private String userName;
    private SpotifyApi api = new SpotifyApi();
    public String NOW_PLAYING = null;
    public Image ALBUM_ARTWORK = null;
    String[] spotifyScopes = new String[]{"streaming", "playlist-read-private", "playlist-read-collaborative", "user-library-read", "user-read-email", "user-read-recently-played", "playlist-read-private", "playlist-read-collaborative", "user-read-currently-playing"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Initialize a GoogleApiClient with access to the Google Sign-In API
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = extras.getString("USER");
            System.out.println("Display name: " + userName);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpotifyTrack track = getNowPlaying();
                Snackbar.make(view, NOW_PLAYING, Snackbar.LENGTH_LONG).show();

                //snackbar with album artwork
                //Snackbar.make(view, add(NOW_PLAYING, track.getAlbum().getArtworkList().get(0).getUrl()), Snackbar.LENGTH_LONG).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout linearLayout = (LinearLayout) navigationView.getHeaderView(0);
        userNameTextView = (TextView) linearLayout.findViewById(R.id.textView);
        userNameTextView.setText(userName);

        //Spotify auth
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(spotifyScopes);
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        //spotifyAuthentication();
    }

    private SpannableStringBuilder add(String text, String imageUri) {
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        try {
            Bitmap bmp = getBitmapFromURL(imageUri);
            bmp = Bitmap.createScaledBitmap(
                    bmp, bmp.getWidth()/6, bmp.getHeight()/6, false);
            System.out.println("Is this null or nah " + bmp.getHeight() + " " + bmp.getWidth());
            ssb.append(" ");
            ImageSpan is = new ImageSpan(DashboardActivity.this, bmp);
            ssb.setSpan(is, ssb.length() - 1, ssb.length(), 0);
            ssb.append("   ");
            ssb.append(text);
        } catch (Exception e ) {
            Log.e("DashboardActivity", "Error adding image to snackbar",e);
        }
        return  ssb;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.sign_out) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        logout();
                    }
                });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void logout(){
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                ACCESS_TOKEN = response.getAccessToken();
                api.setAccessToken(ACCESS_TOKEN);
                SpotifyService spotifyService = api.getService();
                UserPrivate spotifyUser = this.getSpotifyUser(spotifyService);
                /*SpotifyTrack track = this.getCurrentlyPlaying(response.getAccessToken());
                if(track != null) {
                    System.out.println("beeeeyyyah" + track.getUri());
                    if(!CollectionUtils.isEmpty(track.getArtists())) {
                        NOW_PLAYING = track.getArtists().get(0).getName() + " - " + track.getName();
                        Log.d("DashboardActivity", "** NOW_PLAYING " + NOW_PLAYING);
                    }
                }*/
                //this.getSpotifyTrack(spotifyService);
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
                    @Override
                    public void onInitialized(Player player) {
                        mPlayer = player;
                        mPlayer.addConnectionStateCallback(DashboardActivity.this);
                        mPlayer.addPlayerNotificationCallback(DashboardActivity.this);
                        //mPlayer.play("spotify:track:6WTZqt2ghvOxdmXCpGb84Z");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("DashboardActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }

    private SpotifyTrack getCurrentlyPlaying(String accessToken) {
        try {
            return spotifyApiService.getCurrentlyPlayingTrack(SpotifyEndpoints.GET_CURRENTLY_PLAYING.getStringValue(), accessToken);
        } catch (Exception e) {
            System.out.println("Error in getCurrentlyPlaying " + e);
            return new SpotifyTrack();
        }
    }

    public UserPrivate getSpotifyUser(SpotifyService spotifyService){
        spotifyService.getMe(new Callback<UserPrivate>() {
            @Override
            public void success(UserPrivate user, Response response) {
                System.out.println("user email: " + user.email);
            }

            @Override
            public void failure(RetrofitError error) {
                //activity.runOnUiThread(onFailed);
            }
        });
        return null;
    }

    private SpotifyTrack getNowPlaying() {
        SpotifyTrack track = this.getCurrentlyPlaying(ACCESS_TOKEN);
        if(track != null) {
            System.out.println("beeeeyyyah" + track.getUri());
            if(!CollectionUtils.isEmpty(track.getArtists())) {
                NOW_PLAYING = track.getArtists().get(0).getName() + " - " + track.getName();
                Log.d("DashboardActivity", "** NOW_PLAYING " + NOW_PLAYING);
            }
        }
        return track;
    }

    public String getSpotifyTrack(SpotifyService spotifyService){
        spotifyService.getTrack("6WTZqt2ghvOxdmXCpGb84Z", new Callback<Track>() {
            @Override
            public void success(Track track, Response response) {
                NOW_PLAYING = track.artists.get(0).name.toString() + " - "  + track.name;
                ALBUM_ARTWORK = track.album.images.get(2);
                System.out.println("user email: " + track.name);
            }

            @Override
            public void failure(RetrofitError error) {
                //activity.runOnUiThread(onFailed);
            }
        });
        return null;
    }


    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Throwable error) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {
        Log.d("MainActivity", "Playback event received: " + eventType.name());
    }

    @Override
    public void onPlaybackError(ErrorType errorType, String errorDetails) {
        Log.d("MainActivity", "Playback error received: " + errorType.name());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
/*
    public void spotifyAuthentication(){
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }
    */
}

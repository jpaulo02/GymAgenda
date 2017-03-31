package com.brogrammers.paulo.gymagenda;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by paulo on 3/30/17.
 */

public class RetrieveData extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(params[0])
                    .addHeader("Authorization", "Bearer " + params[1])
                    .build();

            okhttp3.Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            System.out.println("Error in RetrieveData: " + e);
        }
        return null;
    }
}

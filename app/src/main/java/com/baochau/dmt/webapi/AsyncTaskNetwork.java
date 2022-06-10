package com.baochau.dmt.webapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

interface CallAPI {
    void showListComment(String contentApi) throws IOException, JSONException;
}

public class AsyncTaskNetwork extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    Context context;
    CallAPI callAPI;

    public AsyncTaskNetwork(Context context, CallAPI callAPI) {
        this.context = context;
        this.callAPI = callAPI;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            StringBuilder stringBuilder = new StringBuilder();
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp = br.readLine();
                stringBuilder.append(temp);
                br.close();
                return stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (callAPI != null) {
            try {
                callAPI.showListComment(s);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }

    }
}

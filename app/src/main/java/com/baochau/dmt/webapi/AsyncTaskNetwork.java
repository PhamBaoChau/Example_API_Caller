package com.baochau.dmt.webapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

interface CallAPI {
    void showListComment(String contentApi);
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

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Đang xử lý. Vui lòng đợi trong ít phút!");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            StringBuilder stringBuilder = new StringBuilder();
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                String temp="";
                while ((line=br.readLine())!=null){
                    temp+=line;
                }
                temp=temp.substring(91,temp.length()-40);
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
        progressDialog.dismiss();
        if (callAPI != null)
            callAPI.showListComment(s);

    }
}

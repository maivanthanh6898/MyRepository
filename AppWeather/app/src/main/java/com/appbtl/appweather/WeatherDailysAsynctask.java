package com.appbtl.appweather;

import android.os.AsyncTask;

import com.appbtl.appweather.model.ListDailys;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class WeatherDailysAsynctask extends AsyncTask<String,Void, ListDailys> {
    OkHttpClient client = new OkHttpClient();
    ListDailys listDailys ;
    @Override
    protected ListDailys doInBackground(String... strings) {
        Request.Builder builder = new Request.Builder();
        builder.url(strings[0]);
        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
            String rs = response.body().string();
            listDailys = new Gson().fromJson(rs,ListDailys.class);
            return listDailys;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ListDailys listDailys) {
        doJsonDailys(listDailys);
    }
    public abstract void doJsonDailys(ListDailys listDailys);
}

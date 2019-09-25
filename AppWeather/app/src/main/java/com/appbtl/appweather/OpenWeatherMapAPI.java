package com.appbtl.appweather;

import android.app.Activity;
import android.content.Context;
import android.view.textclassifier.TextLinks;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appbtl.appweather.model.OpenWeatherJson;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class OpenWeatherMapAPI {
    private OpenWeatherJson result;
    public OpenWeatherJson getJsonAPI(Context context, String city) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=b87ce30a14229dd8e26f167dd2111f06";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        result = new Gson().fromJson(response,OpenWeatherJson.class);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(stringRequest);
        return result;
    }
    public OpenWeatherJson getJsonAPI(Context context,double lon , double lat){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://api.openweathermap.org/data/2.5/weather?" + "lat="+lat+"&lon="+lon + "&appid=b87ce30a14229dd8e26f167dd2111f06";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        result = new Gson().fromJson(response,OpenWeatherJson.class);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(stringRequest);
        return result;
    }
}

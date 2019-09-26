package com.appbtl.appweather;

import android.app.Activity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appbtl.appweather.model.OpenWeatherJson;
import com.google.gson.Gson;

public abstract class OpenWeatherMapAPI {
    private OpenWeatherJson result;
    public void getJsonAPI(final Activity activity, String city) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=b87ce30a14229dd8e26f167dd2111f06";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        result = new Gson().fromJson(response,OpenWeatherJson.class);
                        doJson(result);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(stringRequest);
    }
    public void getJsonAPI(final Activity activity, double lon , double lat){
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        String url = "http://api.openweathermap.org/data/2.5/weather?" + "lat="+lat+"&lon="+lon + "&appid=b87ce30a14229dd8e26f167dd2111f06";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        result = new Gson().fromJson(response,OpenWeatherJson.class);
                        doJson(result);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(stringRequest);
    }
    public abstract void doJson(OpenWeatherJson result);
}

package com.appbtl.appweather;

import android.app.Activity;
import android.location.Location;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.appbtl.appweather.model.OpenWeatherJson;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherAsynctask extends AsyncTask<Void,Void,OpenWeatherJson> {
    OpenWeatherJson openWeatherJson = null;
    private Location location;
    private Activity activity;
    public WeatherAsynctask(Activity activity,Location location) {
        this.location = location;
        this.activity = activity;
    }

    @Override
    protected OpenWeatherJson doInBackground(Void... voids) {
        openWeatherJson = OpenWeatherMapAPI.prediction("hanoi");
        if(openWeatherJson!=null)return openWeatherJson;
        else return null;
    }

    @Override
    protected void onPostExecute(OpenWeatherJson openWeatherJson) {
        TextView tv = (TextView)activity.findViewById(R.id.Temp);
        if (openWeatherJson!=null) tv.setText(""+openWeatherJson.getMain().getTemp());
        else tv.setText("fail");
    }
}

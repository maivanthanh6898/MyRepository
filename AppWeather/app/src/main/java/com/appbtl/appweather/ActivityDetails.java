package com.appbtl.appweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.appbtl.appweather.model.ListDailys;
import com.appbtl.appweather.model.item;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetails extends AppCompatActivity {
    private LinearLayout detaillayout;
    private Intent intent;
    private RecyclerView recyclerView;
    private LocationAPI locationAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        control();
        intent = new Intent(ActivityDetails.this, MainActivity.class);
        detaillayout.setOnTouchListener(new OnSwipeTouchListener(ActivityDetails.this) {
            @Override
            public void onSwipeLeft() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        recyclerView.setOnTouchListener(new OnSwipeTouchListener(ActivityDetails.this){
            @Override
            public void onSwipeLeft() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        locationAPI = new LocationAPI();
        locationAPI.connectLocationApi(this);//kết nối API
        locationAPI.locationRequest();//tạo request để lấy location
        locationAPI.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationAPI.fusedLocationClient.getLastLocation().addOnSuccessListener(ActivityDetails.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    GetWeatherDailys dailys = new GetWeatherDailys();
                    String url = "http://api.openweathermap.org/data/2.5/forecast/daily?lat="+location.getLatitude()+"&lon="+location.getLongitude()+"&units=metric&cnt=7&appid=be8d3e323de722ff78208a7dbb2dcd6f";
                    dailys.execute(url);
                }
            }
        });
    }

    @Override
    public void finish() {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void control() {
        detaillayout = (LinearLayout) findViewById(R.id.detaillayout);
        recyclerView =(RecyclerView)findViewById(R.id.recView);
    }
    protected class GetWeatherDailys extends WeatherDailysAsynctask{
        @Override
        public void doJsonDailys(ListDailys listDailys) {
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager manager =new LinearLayoutManager(ActivityDetails.this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(manager);
            ListDailysAdapter dailysAdapter =new ListDailysAdapter(listDailys,ActivityDetails.this);
            recyclerView.setAdapter(dailysAdapter);
        }
    }
}

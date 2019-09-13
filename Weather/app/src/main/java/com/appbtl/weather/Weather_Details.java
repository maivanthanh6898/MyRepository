package com.appbtl.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Weather_Details extends AppCompatActivity {
    private LinearLayout layoutdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather__details);
        control();
        final Intent intent = new Intent(this,MainActivity.class);
        layoutdetails.setOnTouchListener(new OnSwipeTouchListener(Weather_Details.this){
            @Override
            public void onSwipeLeft() {
                startActivity(intent);
            }
        });
    }
    private void control(){
        layoutdetails = (LinearLayout)findViewById(R.id.layoutdetails);
    }
}

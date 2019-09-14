package com.appbtl.appweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainlayout;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control();
        intent = new Intent(MainActivity.this,ActivityDetails.class);
        mainlayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onSwipeRight() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }

            @Override
            public void onSwipeLeft() {

            }
        });
    }
    private void control(){
        mainlayout = (LinearLayout)findViewById(R.id.mainlayout);
    }
}

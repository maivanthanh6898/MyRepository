package com.appbtl.appweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ActivityDetails extends AppCompatActivity {
    private LinearLayout detaillayout;
    private Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        control();
        intent = new Intent(ActivityDetails.this,MainActivity.class);
        detaillayout.setOnTouchListener(new OnSwipeTouchListener(ActivityDetails.this){
            @Override
            public void onSwipeLeft() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void finish() {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void control(){
        detaillayout = (LinearLayout)findViewById(R.id.detaillayout);
    }
}

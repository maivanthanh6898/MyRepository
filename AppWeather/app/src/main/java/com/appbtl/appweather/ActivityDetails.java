package com.appbtl.appweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.appbtl.appweather.model.item;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetails extends AppCompatActivity {
    private LinearLayout detaillayout;
    private Intent intent;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ArrayList<item> list = new ArrayList<item>();
        listView = (ListView) findViewById(R.id.lvDaily);
        listView.setAdapter(new Adapter(list, ActivityDetails.this));
        control();
        intent = new Intent(ActivityDetails.this, MainActivity.class);
        detaillayout.setOnTouchListener(new OnSwipeTouchListener(ActivityDetails.this) {
            @Override
            public void onSwipeLeft() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        listView.setOnTouchListener(new OnSwipeTouchListener(ActivityDetails.this){
            @Override
            public void onSwipeLeft() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
    }
}

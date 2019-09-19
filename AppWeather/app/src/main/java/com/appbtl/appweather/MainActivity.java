package com.appbtl.appweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainlayout;
    private Intent intent;
    private Context mContext;
    private PopupWindow mPopupWindow;
    private Intent intent1;
    private ImageView imgvisibility,imgpressure,imghumidity,imgwind;
    private TextView city;
    private ConstraintLayout body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control();
        mContext = getApplicationContext();
        imgvisibility.setImageResource(R.drawable.visibility);
        imgpressure.setImageResource(R.drawable.airpress);
        imghumidity.setImageResource(R.drawable.humidity);
        imgwind.setImageResource(R.drawable.speed);
        city = (TextView) findViewById(R.id.City);
        body = (ConstraintLayout)findViewById(R.id.body);
        intent = new Intent(MainActivity.this,ActivityDetails.class);
        intent1 = new Intent(MainActivity.this,ActivityInfo.class);
        mainlayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onSwipeRight() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }

            @Override
            public void onSwipeLeft() {
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customview = inflater.inflate(R.layout.popup,null);
                mPopupWindow = new PopupWindow(
                        customview,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                Button btnFind = (Button)customview.findViewById(R.id.btnAccept);
                EditText editText = (EditText)customview.findViewById(R.id.editText);
                btnFind.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(body, Gravity.CENTER,0,0);
            }
        });
    }
    private void control(){
        mainlayout = (RelativeLayout)findViewById(R.id.mainlayout);
        imgvisibility = (ImageView)findViewById(R.id.imgvisibility);
        imgpressure = (ImageView)findViewById(R.id.imgpressure);
        imghumidity = (ImageView)findViewById(R.id.imghumidity);
        imgwind = (ImageView)findViewById(R.id.imgwind);
    }
}

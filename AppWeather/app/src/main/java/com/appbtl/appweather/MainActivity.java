package com.appbtl.appweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appbtl.appweather.model.OpenWeatherJson;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity{
    private RelativeLayout mainlayout;
    private Intent intent;
    private Context mContext;
    private PopupWindow mPopupWindow;
    private Intent intent1;
    private ImageView imgvisibility,imgpressure,imghumidity,imgwind;
    private TextView city,temp;
    private ConstraintLayout body;
    private LocationAPI locationAPI;
    private OpenWeatherJson result;
    private WeatherAsynctask weatherAsynctask;
    OpenWeatherMapAPI openWeatherMapAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controls();
        mContext = getApplicationContext();
        setIcon();

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
        locationAPI = new LocationAPI();
        locationAPI.connectLocationApi(this);//kết nối API
        locationAPI.locationRequest();//tạo request để lấy location
        locationAPI.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationAPI.fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location!=null){
                    //làm việc với location ở đây
                    getFileJson getJson = new getFileJson();
                    getJson.getJsonAPI(MainActivity.this,location.getLongitude(),location.getLatitude());//truyền tham số location để lấy file json
                }
                else {

                }
            }
        });






    }
    private void controls(){
        city = (TextView) findViewById(R.id.City);
        body = (ConstraintLayout)findViewById(R.id.body);
        mainlayout = (RelativeLayout)findViewById(R.id.mainlayout);
        imgvisibility = (ImageView)findViewById(R.id.imgvisibility);
        imgpressure = (ImageView)findViewById(R.id.imgpressure);
        imghumidity = (ImageView)findViewById(R.id.imghumidity);
        imgwind = (ImageView)findViewById(R.id.imgwind);
        temp = (TextView)findViewById(R.id.txtTempm);
    }
    protected void requestLocationPermission(){
        //xin cấp quyền truy cập vị trí
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    100);
        }
    }
    private class getFileJson extends OpenWeatherMapAPI{
            // có thể tạo nhiều class extends từ OpenweatherMapAPI để cập nhập giao diện
        @Override
        public void doJson(OpenWeatherJson result) {
            double t = result.getMain().getTemp()-275.15;
            //kết quả trả về json thành object
            temp.setText(""+t);
            // làm việc với giao diện ở đây
        }
    }
    private void setIcon(){
        imgvisibility.setImageResource(R.drawable.visibility);
        imgpressure.setImageResource(R.drawable.airpress);
        imghumidity.setImageResource(R.drawable.humidity);
        imgwind.setImageResource(R.drawable.speed);

    }
}

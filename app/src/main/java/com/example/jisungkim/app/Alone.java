package com.example.jisungkim.app;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Alone extends AppCompatActivity implements View.OnClickListener {

    SupportMapFragment mapFragment;
    GoogleMap map;
    @BindView(R.id.fab_gps) FloatingActionButton fab_gps;

    @Override
    public void onClick(View view) {
        requestMyLocation();
    }

    private void requestMyLocation() {
        long minTime = 10000;
        float minDistance = 0;
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
             showCurrentLocation(location);


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });
    }

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("MapActivity", "GOOGLE MAP");

                map = googleMap;
            }
        });

        // MapsInitializer.initialize(this);

        ButterKnife.bind(this);

        fab_gps.setOnClickListener(this);

        Intent intent= getIntent();
        String text=intent.getStringExtra("a1");
        String text2=intent.getStringExtra("a2");
        String text3=intent.getStringExtra("a3");
        String text4=intent.getStringExtra("a4");

        ArrayList<String> array=new ArrayList<String>();

        array.add(text);
        array.add(text2);
        array.add(text3);
        array.add(text4);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);

        ListView list=(ListView)findViewById(R.id.list_item);
        list.setAdapter(adapter);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void mOnClick2(View v){
        Intent i=new Intent(this,Date.class);
        startActivity(i);
    }


    public void mOnClick(View v){
        Intent intent=getIntent();
        String mtext=intent.getStringExtra("a3");
        String mtext2=intent.getStringExtra("a2");
        String mtext3=intent.getStringExtra("a1");
        String mtext4=intent.getStringExtra("a4");


        ArrayList<String> array=new ArrayList<String>();
        switch(v.getId()){

            case R.id.rankbtn:
                array.add(mtext);
                array.add(mtext2);
                array.add(mtext3);
                array.add(mtext4);

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);

                ListView list=(ListView)findViewById(R.id.list_item);
                list.setAdapter(adapter);

                break;

            case R.id.lengthbtn:

                array.add(mtext2);
                array.add(mtext);
                array.add(mtext4);
                array.add(mtext3);

                ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);

                ListView list2=(ListView)findViewById(R.id.list_item);
                list2.setAdapter(adapter2);

                break;

            case R.id.pricebtn:
                array.add(mtext4);
                array.add(mtext2);
                array.add(mtext3);
                array.add(mtext);

                ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);

                ListView list3=(ListView)findViewById(R.id.list_item);
                list3.setAdapter(adapter3);
                break;
        }

    }

}

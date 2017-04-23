package com.example.mohamed.sosemergencycall;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TextView countryName;
    Button geo;
    Button start;
    String locale;
    private static Context context;
    LocationManager mLocationManager;
    MyLocationListener locationListener;
    String country = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        setContentView(R.layout.activity_main);
        countryName = (TextView) findViewById(R.id.country_name);
        countryName.setText("   ");
        spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                country = item.toString();
                countryName.setText(item.toString());
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        geo = (Button) findViewById(R.id.button);
        start = (Button) findViewById(R.id.button2);
        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationListener = new MyLocationListener();
              /*  LocationManager locationManager = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(getAppContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(getAppContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);*/
               // Location location = locationManager.getLastKnownLocation(locationManager.getAllProviders().get(1));
                //      Log.d("location", location.toString());
                Location location = getLastKnownLocation();
                locationListener.onLocationChanged(location);
                country = locationListener.country;
                countryName.setText(locationListener.country);

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CallActivity.class);
                if (!country.equals("") && !country.equals("-- Country --")) {
                    i.putExtra("country", country);
                    startActivity(i);
                }
            }
        });

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }


    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    }

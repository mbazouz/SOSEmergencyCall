package com.example.mohamed.sosemergencycall;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mohamed on 04/12/2016.
 */

public class MyLocationListener implements LocationListener {

    String city, country;
    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        //double latitude = 36.533069;
       // double longitude = 9.670410;

        Log.i("Geo_Location", "Latitude: " + latitude + ", Longitude: " + longitude);

         /*------- To get city name from coordinates -------- */
        String cityName = null;
        String add = null;
        Geocoder gcd = new Geocoder(MainActivity.getAppContext(), Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = gcd.getFromLocation(latitude,
                    longitude, 1);
            Address obj = addresses.get(0);

            if (addresses.size() > 0) {
                 add = obj.getCountryName();

             //   System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                + cityName;
       city = cityName;
        country = add;
    }


    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }


}

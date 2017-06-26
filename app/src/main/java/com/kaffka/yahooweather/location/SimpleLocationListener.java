package com.kaffka.yahooweather.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by kaffka on 6/25/2017.
 */

class SimpleLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

package com.kaffka.yahooweather.location;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.MainThread;

/**
 * Created by kaffka on 6/25/2017.
 */

public class LocationLiveData extends LiveData<Location> {
    private static LocationLiveData sInstance;
    private LocationManager locationManager;

    @MainThread
    public static LocationLiveData get(Context context) {
        if (sInstance == null) {
            sInstance = new LocationLiveData(context.getApplicationContext());
        }
        return sInstance;
    }

    private SimpleLocationListener listener = new SimpleLocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            setValue(location);
        }
    };

    public LocationLiveData(Context context) {
        locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
    }

    @Override
    protected void onActive() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }

    @Override
    protected void onInactive() {
        locationManager.removeUpdates(listener);
    }
}
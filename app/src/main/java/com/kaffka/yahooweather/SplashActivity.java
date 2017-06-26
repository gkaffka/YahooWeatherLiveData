package com.kaffka.yahooweather;

import android.Manifest;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kaffka.yahooweather.api.YahooWeatherUtils;
import com.kaffka.yahooweather.location.LocationLiveData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kaffka on 6/25/2017.
 */

public class SplashActivity extends LifecycleActivity {

    @BindView(R.id.splash_check_weather_button)
    Button checkWeatherButton;
    @BindView(R.id.splash_location_info)
    TextView locationInfoText;

    public final int GPS_FINE_PERMISSION = 23;
    private Location location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ButterKnife.bind(this);
        startLocationService();
    }

    public void checkWeather(View v) {
        if (location != null) {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra(YahooWeatherUtils.LOCATION_AQUIRED, location);
            startActivity(i);
            this.finish();
        } else
            Toast.makeText(this, R.string.unable_to_acquire_location, Toast.LENGTH_LONG).show();

        startLocationService();
    }

    private void startLocationService() {
        if (haveGpsPermission()) {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null)
                locationInfoText.setText(getString(R.string.location_acquired) + YahooWeatherUtils.getCoordinatesStatusText(location));
            startLocationObserver();
            return;
        }
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                GPS_FINE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case GPS_FINE_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startLocationObserver();
                else
                    Toast.makeText(this, R.string.permission_required, Toast.LENGTH_LONG).show();
        }
    }

    public void startLocationObserver() {
        LocationLiveData locationLiveData = new LocationLiveData(SplashActivity.this);
        locationLiveData.observe(this, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                setLocation(location);
                String message = getString(R.string.location_acquired) + YahooWeatherUtils.getCoordinatesStatusText(location);
                locationInfoText.setText(message);
            }
        });
    }

    private boolean haveGpsPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

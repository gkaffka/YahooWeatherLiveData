package com.kaffka.yahooweather;

import android.arch.lifecycle.LifecycleActivity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kaffka.yahooweather.api.YahooWeatherUtils;

/**
 * Created by kaffka on 6/24/2017.
 */

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container,
                WeatherFragment.create(getLocationFromIntent())).commit();
    }

    private Location getLocationFromIntent() {
        return getIntent().getParcelableExtra(YahooWeatherUtils.LOCATION_AQUIRED);
    }

}

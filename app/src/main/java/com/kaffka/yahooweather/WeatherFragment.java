package com.kaffka.yahooweather;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaffka.yahooweather.api.YahooWeatherUtils;
import com.kaffka.yahooweather.databinding.WeatherBinding;
import com.kaffka.yahooweather.model.Weather;
import com.kaffka.yahooweather.repository.WeatherRepository;
import com.kaffka.yahooweather.viewmodel.WeatherViewModel;

/**
 * Created by kaffka on 6/24/2017.
 */

public class WeatherFragment extends LifecycleFragment {
    private WeatherViewModel weatherViewModel;
    private WeatherBinding weatherBinding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        weatherViewModel = new WeatherViewModel(new WeatherRepository());
        weatherBinding = DataBindingUtil.inflate(inflater, R.layout.weather, container, false);
        weatherViewModel.init(YahooWeatherUtils.getWeather(getLocation()));
        weatherBinding.setWeather(weatherViewModel);
        return weatherBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weatherViewModel.getWeather().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(@Nullable Weather weather) {
                if (weather != null) {
                    weatherBinding.weatherProgress.setVisibility(View.GONE);
                    try {
                        weatherBinding.weatherDescription.loadData(
                                YahooWeatherUtils.getCleanDescriptionHtml(
                                        weather.getQuery().getResults().getChannel().getItem().getDescription())
                                , "text/html", "utf-8");
                        weatherBinding.weatherTitleAndPlace.setText(weather.getQuery().getResults().getChannel().getTitle());
                    } catch (NullPointerException e) {
                        Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    public static WeatherFragment create(Location location) {
        WeatherFragment weatherFragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(YahooWeatherUtils.LOCATION_AQUIRED, location);
        weatherFragment.setArguments(bundle);
        return weatherFragment;
    }

    private Location getLocation() {
        return getArguments().getParcelable(YahooWeatherUtils.LOCATION_AQUIRED);
    }
}

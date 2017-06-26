package com.kaffka.yahooweather.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kaffka.yahooweather.model.Weather;
import com.kaffka.yahooweather.repository.WeatherRepository;

/**
 * Created by kaffka on 6/24/2017.
 */

public class WeatherViewModel extends ViewModel {
    private LiveData<Weather> weather;
    private WeatherRepository weatherRepository;

    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void init(String query) {
        if (this.weather == null)
            weather = new MutableLiveData<>();

        weather = weatherRepository.getWeather(query);
    }

    public LiveData<Weather> getWeather() {
        return weather;
    }
}

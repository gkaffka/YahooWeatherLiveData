package com.kaffka.yahooweather.repository;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

import com.kaffka.yahooweather.YahooWeatherApplication;
import com.kaffka.yahooweather.model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kaffka on 6/24/2017.
 */

public class WeatherRepository {

    public MutableLiveData<Weather> getWeather(String query) {
        final MutableLiveData<Weather> data = new MediatorLiveData<>();
        YahooWeatherApplication.getApplicationInstance()
                .getYahooWeatherService()
                .getWeather(query, "json")
                .enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {

                    }
                });
        return data;
    }
}

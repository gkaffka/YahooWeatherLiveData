package com.kaffka.yahooweather;

import android.app.Application;

import com.kaffka.yahooweather.api.YahooWeatherService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kaffka on 6/25/2017.
 */

public class YahooWeatherApplication extends Application {
    private YahooWeatherService yahooWeatherService;
    private static YahooWeatherApplication yahooWeatherApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        yahooWeatherApplication = this;
    }

    public YahooWeatherService getYahooWeatherService() {
        if (yahooWeatherService != null) return yahooWeatherService;

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://query.yahooapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        yahooWeatherService = retrofit.create(YahooWeatherService.class);
        return yahooWeatherService;
    }

    public static YahooWeatherApplication getApplicationInstance(){
        return yahooWeatherApplication;
    }
}

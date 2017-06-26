package com.kaffka.yahooweather.api;

import com.kaffka.yahooweather.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kaffka on 6/24/2017.
 */

public interface YahooWeatherService {

    @GET("v1/public/yql")
    Call<Weather> getWeather(@Query("q") String query, @Query("format") String format);
}

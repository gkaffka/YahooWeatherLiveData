package com.kaffka.yahooweather.api;

import android.location.Location;

import java.util.Locale;

/**
 * Created by kaffka on 6/25/2017.
 */

public class YahooWeatherUtils {
    public static final String LOCATION_AQUIRED = "LOCATION_AQUIRED";

    public static String getWeather(Location location) {
        return String.format(Locale.ENGLISH, "select * from weather.forecast where woeid in (SELECT woeid FROM geo.places WHERE text='(%f, %f)') and u = 'c'",
                location.getLatitude(), location.getLongitude());
    }

    public static String getCleanDescriptionHtml(String data) {
        if (data == null) return "Sem dados disponíveis";
        return data.replace("\n", "").replace("]]>", "").replace("<![CDATA[", "")
                .replace("Low", "   Low");
    }

    public static String getCoordinatesStatusText(Location location){
        return String.format(Locale.ENGLISH, "\nLatitude: %f\nLongitude: %f", location.getLatitude(), location.getLongitude());
    }
}

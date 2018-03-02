package com.parsifal.mvpweather.data.base;

import com.parsifal.mvpweather.data.bean.Weather;

/**
 * Created by dmilicic on 12/13/15.
 */
public interface WeatherRepository {

    Weather getWeather(String cityID);
}

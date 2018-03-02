package com.parsifal.mvpweather.presenter.callback;

import com.parsifal.mvpweather.data.bean.Weather;

/**
 * Created by yangming on 17-11-27.
 */
public interface GetWeatherCallback {

    void onWeatherGet(Weather weather);

    void onWeatherError(String error);
}

package com.parsifal.mvpweather.data;

import com.parsifal.mvpweather.data.bean.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yangming on 17-11-3.
 */
public interface IGetRequest {

    @GET("s6/weather/now/")
    Call<Weather> getWeather(@Query("location") String cityID);
}

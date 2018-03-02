package com.parsifal.mvpweather.presenter.converter;

import com.parsifal.mvpweather.data.bean.Weather;

/**
 * Created by yangming on 17-11-28.
 */
public class WeatherConverter {

    public static String converter(Weather weather) {
        StringBuilder builder = new StringBuilder();
        Weather.HeWeather6Bean heWeather6Bean = weather.getHeWeather6().get(0);
        builder.append(heWeather6Bean.getBasic().getLocation());
        builder.append("天气");
        builder.append(heWeather6Bean.getNow().getCond_txt());
        builder.append("，");
        builder.append(heWeather6Bean.getNow().getWind_dir());
        builder.append("，风力 ");
        builder.append(heWeather6Bean.getNow().getWind_spd());
        builder.append(" 级。");
        return builder.toString();
    }
}

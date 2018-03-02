package com.parsifal.mvpweather.presenter.impl;

import android.os.Handler;

import com.parsifal.mvpweather.data.base.WeatherRepository;
import com.parsifal.mvpweather.data.bean.Weather;
import com.parsifal.mvpweather.presenter.base.MainPresenter;
import com.parsifal.mvpweather.presenter.callback.GetWeatherCallback;
import com.parsifal.mvpweather.presenter.converter.WeatherConverter;
import com.parsifal.mvpweather.ui.base.MainView;

/**
 * Created by yangming on 17-11-27.
 */
public class MainPresenterImpl implements MainPresenter, GetWeatherCallback {

    private MainView mView = null;

    private WeatherRepository mRepository = null;

    private Handler mHandler = null;

    public MainPresenterImpl(MainView view, WeatherRepository repository, Handler handler) {
        this.mView = view;
        this.mRepository = repository;
        this.mHandler = handler;
    }

    @Override
    public void getWeather(final String cityID) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mView.showProgress();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Weather weather = mRepository.getWeather(cityID);
                onWeatherGet(weather);
            }
        }).start();
    }

    @Override
    public void onWeatherGet(final Weather weather) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mView.onWeatherGet(WeatherConverter.converter(weather));
                mView.hideProgress();
            }
        });
    }

    @Override
    public void onWeatherError(String error) {
        mView.showError("Error : " + error);
        mView.hideProgress();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onError(String message) {

    }
}

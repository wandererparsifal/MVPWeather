package com.parsifal.mvpweather.data;

import android.content.Context;

import com.parsifal.mvpweather.data.base.WeatherRepository;
import com.parsifal.mvpweather.data.bean.Weather;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangming on 17-11-27.
 */
public class WeatherRepositoryImpl implements WeatherRepository {

    private Context mContext;

    public WeatherRepositoryImpl(Context context) {
        mContext = context;
    }

    @Override
    public Weather getWeather(String cityID) {
        Interceptor commonParams = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originRequest = chain.request();
                Request request;
                HttpUrl httpUrl = originRequest.url().newBuilder()
                        .addQueryParameter("key", "3c3fbcb0cbb54ff4a3df670144b08e9b")
                        .build();
                request = originRequest.newBuilder().url(httpUrl).build();
                return chain.proceed(request);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(commonParams)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://free-api.heweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        IGetRequest getRequest = retrofit.create(IGetRequest.class);
        Call<Weather> call = getRequest.getWeather(cityID);

        Response<Weather> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Weather weather = null;
        if (null != response) {
            weather = response.body();
        }
        return weather;
    }
}

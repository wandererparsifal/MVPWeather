package com.parsifal.mvpweather.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parsifal.mvpweather.R;
import com.parsifal.mvpweather.data.WeatherRepositoryImpl;
import com.parsifal.mvpweather.presenter.base.MainPresenter;
import com.parsifal.mvpweather.presenter.impl.MainPresenterImpl;
import com.parsifal.mvpweather.ui.base.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mPresenter = null;

    private RelativeLayout mProgress = null;

    private Button mButton = null;

    private TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenterImpl(this, new WeatherRepositoryImpl(this), new Handler());

        setContentView(R.layout.activity_main);
        mProgress = findViewById(R.id.progressLayout);
        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.text);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getWeather("CN101070201");
            }
        });
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        mTextView.setText(message);
    }

    @Override
    public void onWeatherGet(String weather) {
        mTextView.setText(weather);
    }
}

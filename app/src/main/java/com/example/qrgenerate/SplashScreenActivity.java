package com.example.qrgenerate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config= new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.WHITE)
                .withFooterText("CJLR 2021")
                .withLogo(R.mipmap.tracetogether);

        config.getFooterTextView().setTextColor(Color.BLACK);

        View splashScreen=config.create();
        setContentView(splashScreen);



    }
}
package com.example.astrology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.astrology.Common.HelperData;
import com.example.astrology.LoginModules.SignUpActivity;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    HelperData helperData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        helperData = new HelperData(getApplicationContext());

        handler = new Handler();
        handler.postDelayed(() -> {
            if (helperData.getIsLogin()) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
package com.example.astrology.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.astrology.Common.HelperData;
import com.example.astrology.LoginModules.SignUpActivity;
import com.example.astrology.R;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    HelperData helperData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
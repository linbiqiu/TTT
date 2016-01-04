package com.nima519.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.schoolassist.R;
import com.nima519.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity {
    private  static final  long DELAY_TIME = 2000L;

    //延迟跳转
    private  void redirectByTime(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(splashIntent);
                SplashActivity.this.finish();
            }
        },DELAY_TIME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        redirectByTime();
    }
}

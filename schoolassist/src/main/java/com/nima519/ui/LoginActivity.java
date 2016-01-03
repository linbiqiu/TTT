package com.nima519.ui;

import android.os.Bundle;

import com.example.schoolassist.R;
import com.nima519.ui.base.BaseActivity;
import com.nima519.utils.Content;
import com.nima519.utils.L;

import cn.bmob.v3.Bmob;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, Content.ApplicationID);
        L.i(TAG,TAG+"has launched !!!!!");
    }
}

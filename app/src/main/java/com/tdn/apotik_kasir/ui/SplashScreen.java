package com.tdn.apotik_kasir.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.ui.auth.LoginActivity;
import com.tdn.data.persistensi.MyUser;
import com.tdn.domain.model.UserModel;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        UserModel userModel = MyUser.getInstance(SplashScreen.this).getUser();
        if (userModel != null) {
            startActivity(new Intent(SplashScreen.this, MainPage.class));
            finish();
        } else {
            startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            finish();
        }
    }
}
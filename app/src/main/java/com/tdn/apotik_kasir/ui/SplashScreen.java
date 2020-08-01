package com.tdn.apotik_kasir.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.ui.auth.LoginActivity;
import com.tdn.data.persistensi.MyUser;
import com.tdn.domain.model.UserModel;
import com.tdn.domain.object.HomeObject;

import io.realm.Realm;

public class SplashScreen extends AppCompatActivity {
    private Realm realm;

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
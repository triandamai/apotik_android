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
        HomeObject o = new HomeObject();
        o.setAlamatApotek("ALamat");
        o.setNamaApotek("Kadede Farma ");
        o.setNotifikasiExpired("a");
        o.setPembelian(0);
        o.setPenjualan(0);
        o.setStokMinimal("2");
        o.setId(1);
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(o));

    }
}
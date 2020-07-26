package com.tdn.apotik_kasir.ui.auth;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.apotik_kasir.core.callback.AuthListener;
import com.tdn.data.service.ApiService;
import com.tdn.domain.serialize.req.ReqAuth;
import com.tdn.domain.serialize.res.ResponseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class LoginViewModel extends ViewModel {
    private Context context;
    private AuthListener authListener;
    private ApiService apiService;

    public LoginViewModel(Context context, AuthListener actionListener) {
        this.context = context;
        this.authListener = actionListener;
        this.apiService = ApiService.Factory.create();
    }

    public void masuk(String user, String pass) {
        authListener.onStart();
        ReqAuth reqAuth = new ReqAuth();
        reqAuth.setUserName(user);
        reqAuth.setUserPassword(pass);
        Log.e("tes login", reqAuth.toString());
        apiService.auth(reqAuth).enqueue(new Callback<ResponseAuth>() {
            @Override
            public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                Log.e("tes login", response.toString());
                if (cek(response.code())) {
                    if (cek(response.body().getResponseCode())) {
                        authListener.onSuccess(response.body().getResponseMessage(), response.body().getData().get(0));
                    } else {
                        authListener.onError(response.body().getResponseMessage());
                    }
                } else {
                    authListener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseAuth> call, Throwable t) {
                authListener.onError(t.getMessage());
            }
        });

    }
}

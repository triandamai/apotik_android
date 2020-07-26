package com.tdn.data.repository;

import android.content.Context;
import android.util.Log;

import com.tdn.data.service.ApiService;
import com.tdn.domain.model.ObatModel;
import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.object.ObatObject;
import com.tdn.domain.object.PenjualanTempObject;
import com.tdn.domain.serialize.res.ResponseGetObat;
import com.tdn.domain.serialize.res.ResponseGetPenjualanTemp;


import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class Repository {
    public static final String TAG = "LAPORAN :: ";
    private static ApiService service;
    private static Repository repository;
    private static Realm realm;
    private Context context;

    private Repository(Context ctx) {
        realm = Realm.getDefaultInstance();
        service = ApiService.Factory.create();
        context = ctx;
    }

    public synchronized static Repository getInstance(Context context) {
        if (repository == null) {
            repository = new Repository(context);
        }
        return repository;
    }

    public static ApiService getService() {
        if (service == null) {
            service = ApiService.Factory.create();
        }
        return service;
    }

    public void getObats() {
        service.getObat().enqueue(new Callback<ResponseGetObat>() {
            @Override
            public void onResponse(Call<ResponseGetObat> call, Response<ResponseGetObat> response) {
                if (cek(response.code())) {
                    //Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode()) || response.body().getData().size() >= 1) {
                        realm.beginTransaction();
                        realm.delete(ObatObject.class);
                        realm.commitTransaction();
                        for (ObatModel data : response.body().getData()) {
                            ObatObject o = (ObatObject) data.ToObject();
                            realm.executeTransaction(realm -> {
                                realm.copyToRealmOrUpdate(o);
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetObat> call, Throwable t) {

            }
        });

    }

    public void getPenjualanTemp() {
        service.getPenjualanTemp().enqueue(new Callback<ResponseGetPenjualanTemp>() {
            @Override
            public void onResponse(Call<ResponseGetPenjualanTemp> call, Response<ResponseGetPenjualanTemp> response) {
                if (cek(response.code())) {
                    //Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode()) || response.body().getData().size() >= 1) {
                        realm.beginTransaction();
                        realm.delete(PenjualanTempObject.class);
                        realm.commitTransaction();
                        for (PenjualanTempModel data : response.body().getData()) {
                            PenjualanTempObject o = (PenjualanTempObject) data.ToObject();
                            realm.executeTransaction(realm -> {
                                realm.copyToRealmOrUpdate(o);
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPenjualanTemp> call, Throwable t) {

            }
        });


    }


}

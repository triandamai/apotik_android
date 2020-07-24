package com.tdn.data.repository;

import android.content.Context;

import com.tdn.data.service.ApiService;


import io.realm.Realm;

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

    public void getAllUser() {
//        service.getAllUser().enqueue(new Callback<ResponseGetUser>() {
//            @Override
//            public void onResponse(Call<ResponseGetUser> call, Response<ResponseGetUser> response) {
//                Log.e(TAG, response.toString());
//
//                if (cek(response.code())) {
//                    Log.e(TAG, response.body().toString());
//                    if (cek(response.body().getResponseCode()) || response.body().getData().size() >= 1) {
//                        realm.beginTransaction();
//                        realm.delete(UserObject.class);
//                        realm.commitTransaction();
//                        for (UserModel data : response.body().getData()) {
//                            UserObject o = (UserObject) data.ToObject();
//                            realm.executeTransaction(realm -> {
//                                realm.copyToRealmOrUpdate(o);
//                            });
//                        }
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseGetUser> call, Throwable t) {
//                Log.e(TAG, t.toString());
//            }
//        });
    }


}

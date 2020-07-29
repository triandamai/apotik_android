package com.tdn.apotik_kasir.ui.transaction;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.object.PenjualanTempObject;
import com.tdn.domain.serialize.req.ReqPenjualan;
import com.tdn.domain.serialize.req.ReqPenjualanTemp;
import com.tdn.domain.serialize.res.ResponseAction;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class TransactionViewModel extends ViewModel {
    private Realm realm;
    private Context context;
    private ActionListener actionListener;
    private LiveData<List<PenjualanTempObject>> penjualan;
    private ApiService apiService;

    public TransactionViewModel(Context context, ActionListener actionListener) {
        this.realm = Realm.getDefaultInstance();
        this.context = context;
        this.actionListener = actionListener;
        this.apiService = ApiService.Factory.create();

        getFromApi();
        getFromLocal();
    }

    public void getFromLocal() {
        this.penjualan = new RealmLiveResult(realm.where(PenjualanTempObject.class).findAll());
    }

    public void getFromApi() {
        Repository.getInstance(context).getPenjualanTemp();
    }

    public LiveData<List<PenjualanTempObject>> getPenjualan() {
        if (penjualan == null) {
            penjualan = new MutableLiveData<>();
        }
        return penjualan;
    }

    public void savepenjualan(ReqPenjualan req) {
        actionListener.onStart();
        apiService.postPenjualan(req).enqueue(new Callback<ResponseAction>() {
            @Override
            public void onResponse(Call<ResponseAction> call, Response<ResponseAction> response) {
                if (cek(response.code())) {
                    if (cek(response.body().getResponseCode())) {
                        actionListener.onSuccess(response.body().getResponseMessage());
                    } else {
                        actionListener.onError(response.body().getResponseMessage());
                    }
                } else {
                    actionListener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseAction> call, Throwable t) {
                actionListener.onError(t.getMessage());
            }
        });
    }

    public void update(PenjualanTempModel m) {
    }

    public void hapus(PenjualanTempModel m) {
    }
}
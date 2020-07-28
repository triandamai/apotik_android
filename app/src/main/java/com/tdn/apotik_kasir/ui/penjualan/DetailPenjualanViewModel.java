package com.tdn.apotik_kasir.ui.penjualan;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.domain.model.PenjualanDetailModel;
import com.tdn.domain.model.PenjualanModel;
import com.tdn.domain.object.PenjualanDetailObject;
import com.tdn.domain.object.PenjualanObject;

import java.util.List;

import io.realm.Realm;

public class DetailPenjualanViewModel extends ViewModel {
    public LiveData<List<PenjualanDetailObject>> penjualan;
    private Realm realm;
    private Context context;

    public DetailPenjualanViewModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        getFromApi();
        getFromLocal();

    }

    private void getFromApi() {
        Repository.getInstance(context).getPenjualanDetail();
    }

    private void getFromLocal() {
        this.penjualan = new RealmLiveResult(realm.where(PenjualanDetailObject.class).findAll());
    }

    public LiveData<List<PenjualanDetailObject>> getPenjualan() {
        if (penjualan == null) {
            penjualan = new MutableLiveData<>();
        }
        return penjualan;
    }
}
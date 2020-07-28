package com.tdn.apotik_kasir.ui.penjualan;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.domain.model.PenjualanModel;
import com.tdn.domain.object.PenjualanObject;

import java.util.List;

import io.realm.Realm;

public class PenjualanViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public LiveData<List<PenjualanObject>> penjualan;
    private Realm realm;
    private Context context;

    public PenjualanViewModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        getFromApi();
        getFromLocal();

    }

    private void getFromApi() {
        Repository.getInstance(context).getPenjualan();
    }

    private void getFromLocal() {
        this.penjualan = new RealmLiveResult(realm.where(PenjualanObject.class).findAll());
    }

    public LiveData<List<PenjualanObject>> getPenjualan() {
        if (penjualan == null) {
            penjualan = new MutableLiveData<>();
        }
        return penjualan;
    }
}
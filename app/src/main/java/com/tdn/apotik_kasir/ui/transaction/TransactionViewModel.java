package com.tdn.apotik_kasir.ui.transaction;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.object.PenjualanTempObject;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class TransactionViewModel extends ViewModel {
    private Realm realm;
    private Context context;
    private ActionListener actionListener;
    private LiveData<List<PenjualanTempObject>> penjualan;

    public TransactionViewModel(Context context, ActionListener actionListener) {
        this.realm = Realm.getDefaultInstance();
        this.context = context;
        this.actionListener = actionListener;

        getFromApi();
        getFromLocal();
    }

    private void getFromLocal() {
        this.penjualan = new RealmLiveResult(realm.where(PenjualanTempObject.class).findAll());
    }

    private void getFromApi() {
        Repository.getInstance(context).getPenjualanTemp();
    }

    public LiveData<List<PenjualanTempObject>> getPenjualan() {
        if (penjualan == null) {
            penjualan = new MutableLiveData<>();
        }
        return penjualan;
    }
}
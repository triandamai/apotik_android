package com.tdn.apotik_kasir.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.data.local.RealmLiveObject;
import com.tdn.data.repository.Repository;
import com.tdn.domain.model.HomeModel;
import com.tdn.domain.object.HomeObject;

import io.realm.Realm;

public class HomeViewModel extends ViewModel {
    private Realm realm;
    private Context context;
    private ActionListener actionListener;
    private LiveData<HomeObject> homeObjectLiveData;

    public HomeViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.actionListener = actionListener;
        this.realm = Realm.getDefaultInstance();
        this.homeObjectLiveData = new MutableLiveData<>();
        HomeObject o = new HomeObject();
        o.setId(1);
        o.setStokMinimal("0");
        o.setPenjualan(0);
        o.setPembelian(0);
        o.setNotifikasiExpired("0");
        o.setNamaApotek("Kadede Farma");
        o.setAlamatApotek("Jl.Purwokerto");

        getFromApi();
        getFromLocal();
    }

    public LiveData<HomeObject> getHomeObjectLiveData() {
        if (homeObjectLiveData == null) {
            homeObjectLiveData = new MutableLiveData<>();

        }
        return homeObjectLiveData;
    }

    private void getFromLocal() {
        this.homeObjectLiveData = new RealmLiveObject(realm.where(HomeObject.class).findFirst());
    }

    private void getFromApi() {
        Repository.getInstance(context).getHome();
    }
    // TODO: Implement the ViewModel
}
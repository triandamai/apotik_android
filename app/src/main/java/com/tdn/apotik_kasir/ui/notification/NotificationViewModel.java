package com.tdn.apotik_kasir.ui.notification;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.domain.object.NotifikasiObject;

import java.util.List;

import io.realm.Realm;

public class NotificationViewModel extends ViewModel {
    private Context context;
    private Realm realm;
    public LiveData<List<NotifikasiObject>> notifikasiObjectLiveData;

    public NotificationViewModel(Context context) {
        this.realm = Realm.getDefaultInstance();
        this.context = context;
        getFromApi();
        getFromLocal();
    }

    public void getFromLocal() {
        this.notifikasiObjectLiveData = new RealmLiveResult(realm.where(NotifikasiObject.class).findAll());
    }

    public void getFromApi() {
        Repository.getInstance(context).getNotifikasi();
    }

    public LiveData<List<NotifikasiObject>> getNotifikasiObjectLiveData() {
        if (this.notifikasiObjectLiveData == null) {
            this.notifikasiObjectLiveData = new MutableLiveData<>();
        }
        return notifikasiObjectLiveData;
    }
}
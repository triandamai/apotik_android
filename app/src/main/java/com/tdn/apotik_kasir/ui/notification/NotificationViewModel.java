package com.tdn.apotik_kasir.ui.notification;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.domain.object.NotifikasiObject;

import io.realm.Realm;

public class NotificationViewModel extends ViewModel {
    private Context context;
    private Realm realm;
    public LiveData<NotifikasiObject> notifikasiObjectLiveData;

    public NotificationViewModel(Context context) {
        this.realm = Realm.getDefaultInstance();
        this.context = context;
        getFromApi();
        getFromLocal();
    }

    private void getFromLocal() {
    }

    private void getFromApi() {

    }

}
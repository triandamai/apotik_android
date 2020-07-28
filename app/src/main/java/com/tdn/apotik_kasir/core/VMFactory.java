package com.tdn.apotik_kasir.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.apotik_kasir.core.callback.AuthListener;
import com.tdn.apotik_kasir.ui.auth.LoginViewModel;
import com.tdn.apotik_kasir.ui.home.HomeViewModel;
import com.tdn.apotik_kasir.ui.inventory.InventoryViewModel;
import com.tdn.apotik_kasir.ui.inventory.NewItemViewModel;
import com.tdn.apotik_kasir.ui.notification.NotificationViewModel;
import com.tdn.apotik_kasir.ui.penjualan.DetailPenjualanViewModel;
import com.tdn.apotik_kasir.ui.penjualan.PenjualanViewModel;
import com.tdn.apotik_kasir.ui.settings.SettingViewModel;
import com.tdn.apotik_kasir.ui.suplier.SuplierViewModel;
import com.tdn.apotik_kasir.ui.transaction.NewTransactionViewModel;
import com.tdn.apotik_kasir.ui.transaction.TransactionViewModel;

public class VMFactory implements ViewModelProvider.Factory {
    private Context context;
    private String id = "";
    private ActionListener actionListener;
    private AuthListener authListener;

    public VMFactory(@NonNull Context context) {
        this.context = context;
    }

    public VMFactory(@NonNull AuthListener context) {
        this.authListener = context;
    }

    public VMFactory(@NonNull ActionListener context) {
        this.actionListener = context;
    }

    public VMFactory(@NonNull Context context, ActionListener actionListener) {
        this.context = context;
        this.actionListener = actionListener;
    }

    public VMFactory(@NonNull Context context, AuthListener actionListener) {
        this.context = context;
        this.authListener = actionListener;
    }

    public VMFactory(@NonNull Context context, ActionListener actionListener, String id) {
        this.context = context;
        this.actionListener = actionListener;
        this.id = id;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(context, authListener);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(InventoryViewModel.class)) {
            return (T) new InventoryViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(NewItemViewModel.class)) {
            return (T) new NewItemViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(NotificationViewModel.class)) {
            return (T) new NotificationViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(SettingViewModel.class)) {
            return (T) new SettingViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(SuplierViewModel.class)) {
            return (T) new SuplierViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(NewTransactionViewModel.class)) {
            return (T) new NewTransactionViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(TransactionViewModel.class)) {
            return (T) new TransactionViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(DetailPenjualanViewModel.class)) {
            return (T) new DetailPenjualanViewModel(context);
        } else if (modelClass.isAssignableFrom(PenjualanViewModel.class)) {
            return (T) new PenjualanViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}

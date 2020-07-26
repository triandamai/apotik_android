package com.tdn.apotik_kasir.ui.notification;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.core.VMFactory;
import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.apotik_kasir.core.callback.AdapterClicked;
import com.tdn.apotik_kasir.databinding.FragmentNotificationBinding;

public class NotificationFragment extends Fragment {

    private NotificationViewModel mViewModel;
    private FragmentNotificationBinding binding;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_notification, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), actionListener)).get(NotificationViewModel.class);

        return binding.getRoot();
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess(@NonNull String message) {

        }

        @Override
        public void onError(@NonNull String message) {

        }
    };
    private AdapterClicked adapterClicked = posisi -> {

    };


}
package com.tdn.apotik_kasir.ui.notification;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.core.VMFactory;
import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.apotik_kasir.core.callback.AdapterClicked;
import com.tdn.apotik_kasir.databinding.FragmentNotificationBinding;
import com.tdn.domain.object.NotifikasiObject;

import java.util.List;

public class NotificationFragment extends Fragment {

    private NotificationViewModel mViewModel;
    private FragmentNotificationBinding binding;
    private AdapterNotification adapterNotification;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_notification, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext())).get(NotificationViewModel.class);
        adapterNotification = new AdapterNotification(getContext(), adapterClicked);
        binding.rv.setAdapter(adapterNotification);
        binding.swipe.setOnRefreshListener(() -> {
            mViewModel.getFromApi();
            new Handler().postDelayed(() -> {
                mViewModel.getFromLocal();
                binding.swipe.setRefreshing(false);
            }, 100);
        });
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

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(NotificationViewModel mViewModel) {
        mViewModel.getNotifikasiObjectLiveData().observe(getViewLifecycleOwner(), notifikasiObjects -> {
            if (notifikasiObjects != null) {
                adapterNotification.setData(notifikasiObjects);
            }
        });
    }
}
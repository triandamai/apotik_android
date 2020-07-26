package com.tdn.apotik_kasir.ui.home;

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
import com.tdn.apotik_kasir.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentHomeBinding binding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_home, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), actionListener)).get(HomeViewModel.class);
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


}
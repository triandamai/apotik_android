package com.tdn.apotik_kasir.ui.penjualan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.databinding.DetailPenjualanFragmentBinding;

public class DetailPenjualanFragment extends Fragment {

    private DetailPenjualanViewModel mViewModel;
    private DetailPenjualanFragmentBinding binding;

    public static DetailPenjualanFragment newInstance() {
        return new DetailPenjualanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.detail_penjualan_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailPenjualanViewModel.class);
        // TODO: Use the ViewModel
    }

}
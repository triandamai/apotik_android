package com.tdn.apotik_kasir.ui.penjualan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
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
import com.tdn.apotik_kasir.core.callback.AdapterClicked;
import com.tdn.apotik_kasir.databinding.DetailPenjualanFragmentBinding;
import com.tdn.domain.object.PenjualanDetailObject;

import java.util.List;

public class DetailPenjualanFragment extends Fragment {

    private DetailPenjualanViewModel mViewModel;
    private DetailPenjualanFragmentBinding binding;
    private AdapterDetailPenjualan adapterDetailPenjualan;

    public static DetailPenjualanFragment newInstance() {
        return new DetailPenjualanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.detail_penjualan_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext())).get(DetailPenjualanViewModel.class);
        adapterDetailPenjualan = new AdapterDetailPenjualan(getContext(), posisi -> {

        });
        binding.rv.setAdapter(adapterDetailPenjualan);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DetailPenjualanViewModel mViewModel) {
        mViewModel.getPenjualan().observe(getViewLifecycleOwner(), penjualanDetailObjects -> {
            if (penjualanDetailObjects != null) {
                adapterDetailPenjualan.setData(penjualanDetailObjects);
            } else {

            }
        });
    }
}
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
import com.tdn.apotik_kasir.databinding.PenjualanFragmentBinding;
import com.tdn.domain.object.PenjualanObject;

import java.util.List;

public class PenjualanFragment extends Fragment {

    private PenjualanViewModel mViewModel;
    private PenjualanFragmentBinding binding;
    private AdapterPenjualan adapterPenjualan;

    public static PenjualanFragment newInstance() {
        return new PenjualanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.penjualan_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext())).get(PenjualanViewModel.class);
        binding.getRoot();
        adapterPenjualan = new AdapterPenjualan(getContext(), posisi -> {

        });
        binding.rv.setAdapter(adapterPenjualan);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(PenjualanViewModel mViewModel) {
        mViewModel.getPenjualan().observe(getViewLifecycleOwner(), penjualanObjects -> {
            if (penjualanObjects != null) {
                adapterPenjualan.setData(penjualanObjects);
            } else {

            }
        });
    }
}
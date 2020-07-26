package com.tdn.apotik_kasir.ui.transaction;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
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
import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.apotik_kasir.core.callback.AdapterClicked;
import com.tdn.apotik_kasir.databinding.FragmentTransactionBinding;
import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.object.PenjualanTempObject;

import java.util.List;

public class TransactionFragment extends Fragment {

    private TransactionViewModel mViewModel;
    private FragmentTransactionBinding binding;
    private AdapterTransaction adapterTransaction;

    public static TransactionFragment newInstance() {
        return new TransactionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_transaction, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), actionListener)).get(TransactionViewModel.class);
        adapterTransaction = new AdapterTransaction(getContext(), adapterClicked);
        binding.rv.setAdapter(adapterTransaction);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(TransactionViewModel mViewModel) {
        mViewModel.getPenjualan().observe(getViewLifecycleOwner(), penjualanTempObjects -> {
            if (penjualanTempObjects != null) {
                adapterTransaction.setData(penjualanTempObjects);
            } else {

            }
        });

    }

    private AdapterClicked adapterClicked = posisi -> {

    };
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
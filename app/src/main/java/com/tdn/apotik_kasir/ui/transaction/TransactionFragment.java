package com.tdn.apotik_kasir.ui.transaction;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.core.VMFactory;
import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.apotik_kasir.core.callback.AdapterClicked;
import com.tdn.apotik_kasir.databinding.FragmentTransactionBinding;
import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.object.PenjualanTempObject;
import com.tdn.domain.serialize.req.ReqPenjualan;

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
        binding.swipe.setOnRefreshListener(() -> {
            mViewModel.getFromApi();
            mViewModel.getFromLocal();
            binding.swipe.setRefreshing(false);
        });
        binding.btnLogout.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Selesaikan Transaksi ? ");


            EditText input = new EditText(getContext());

            input.setInputType(InputType.TYPE_CLASS_NUMBER |
                    InputType.TYPE_NUMBER_FLAG_DECIMAL);
            builder.setView(input);
            builder.setCancelable(true);

            builder.setPositiveButton("Tambahkan", (dialogInterface, i) -> {
                input.setText("0");
                ReqPenjualan reqPenjualan = new ReqPenjualan();
                reqPenjualan.setSubtotal(input.getText().toString());
                mViewModel.savepenjualan(reqPenjualan);
            });

            builder.show();

        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getFromApi();
        mViewModel.getFromLocal();
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
            Snackbar.make(binding.getRoot(), "Proses..", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onError(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };

}
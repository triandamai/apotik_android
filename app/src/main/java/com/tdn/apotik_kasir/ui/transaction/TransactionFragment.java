package com.tdn.apotik_kasir.ui.transaction;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
import com.tdn.data.persistensi.MyUser;
import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.object.PenjualanTempObject;
import com.tdn.domain.serialize.req.ReqPenjualan;
import com.tdn.domain.serialize.req.ReqPenjualanTemp;

public class TransactionFragment extends Fragment {

    private TransactionViewModel mViewModel;
    private FragmentTransactionBinding binding;
    private AdapterTransaction adapterTransaction;
    private String total = "0";

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

            builder.setPositiveButton("Selesai", (dialogInterface, i) -> {

                ReqPenjualan reqPenjualanTemp = new ReqPenjualan();
                reqPenjualanTemp.setSubtotal(MyUser.getInstance(getContext()).getTotal());
                mViewModel.savepenjualan(reqPenjualanTemp);
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
                double total = 0;
                for (PenjualanTempObject o : penjualanTempObjects) {
                    double jml = Double.parseDouble(o.getTempTotalharga());
                    total = total + jml;
                }
                binding.tvTotal.setText("Total Rp " + total);

                MyUser.getInstance(getContext()).setTotal(String.valueOf(total));
            } else {
                adapterTransaction.notifyDataSetChanged();
            }
        });

    }

    private AdapterClicked adapterClicked = posisi -> {
        PenjualanTempModel m = (PenjualanTempModel) adapterTransaction.getFromPosition(posisi).ToModel();
        EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.setText(m.getTempJumlah());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Ubah " + m.getObatNama() + " ?");
        builder.setPositiveButton("Simpan", (dialogInterface, i) -> {
            mViewModel.update(m);
        });
        builder.setNegativeButton("Hapus", (dialogInterface, i) -> {
            mViewModel.hapus(m);
        });
        builder.create();
        builder.show();
    };
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses..", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();

            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_penjualan);
        }

        @Override
        public void onError(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyUser.getInstance(getContext()).setTotal("0");
    }

}

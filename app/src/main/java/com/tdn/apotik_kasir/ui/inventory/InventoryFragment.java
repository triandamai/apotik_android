package com.tdn.apotik_kasir.ui.inventory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
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
import com.tdn.apotik_kasir.databinding.FragmentInventoryBinding;
import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.object.ObatObject;

import java.util.List;

public class InventoryFragment extends Fragment {

    private InventoryViewModel mViewModel;
    private FragmentInventoryBinding binding;
    private AdapterInventory adapterInventory;

    public static InventoryFragment newInstance() {
        return new InventoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_inventory, container, false);
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), actionListener)).get(InventoryViewModel.class);
        adapterInventory = new AdapterInventory(getContext(), adapterClicked);
        binding.rv.setAdapter(adapterInventory);
        binding.swipe.setOnRefreshListener(() -> {
            mViewModel.getFromApi();
            new Handler().postDelayed(() -> {
                binding.swipe.setRefreshing(false);
            }, 1000);
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getListObats().observe(getViewLifecycleOwner(), obatObjects -> {
            if (obatObjects != null) {
                adapterInventory.setData(obatObjects);
            } else {
                Snackbar.make(binding.getRoot(), "Gagal Ambil Data.", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Menambahkan..", BaseTransientBottomBar.LENGTH_LONG).show();
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
    private AdapterClicked adapterClicked = posisi -> {
        ObatObject o = adapterInventory.getFromPosition(posisi);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Tambahkan Ke keranjang ");
        builder.setMessage(o.getObatNama());

        final EditText input = new EditText(getContext());

        input.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);
        builder.setCancelable(true);

        builder.setPositiveButton("Tambahkan", (dialogInterface, i) -> {
            PenjualanTempModel temp = new PenjualanTempModel();

            input.setText("1");
            double harga = Double.parseDouble(o.getObatJual());
            double jumlah = Double.parseDouble(input.getText().toString());
            double total = harga * jumlah;
            temp.setTempJumlah(input.getText().toString());
            temp.setTempId("");

            temp.setTempNama(o.getObatNama());
            temp.setTempObatId(o.getObatId());
            temp.setTempTotalharga(String.valueOf(total));
            mViewModel.tambahCart(temp);
        });

        builder.show();
    };

}
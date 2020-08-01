package com.tdn.apotik_kasir.ui.inventory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.core.VMFactory;
import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.apotik_kasir.databinding.FragmentInventoryBinding;
import com.tdn.domain.object.ObatObject;

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
        mViewModel = new ViewModelProvider(this, new VMFactory(getContext(), actionListener))
                .get(InventoryViewModel.class);
        adapterInventory = new AdapterInventory(getContext(), posisi -> {

            ObatObject o = adapterInventory.getFromPosition(posisi);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Tambahkan Ke keranjang ");
            builder.setMessage(o.getObatNama());

            EditText input = new EditText(getContext());
            input.setText("1");
            input.setInputType(InputType.TYPE_CLASS_NUMBER |
                    InputType.TYPE_NUMBER_FLAG_DECIMAL);
            builder.setView(input);
            builder.setPositiveButton("Tambahkan", (dialog, which) -> {
                mViewModel.tambahCart(o, input.getText().toString());

            });

            builder.show();
        });
        binding.rv.setAdapter(adapterInventory);
        binding.swipe.setOnRefreshListener(() -> {
            mViewModel.getFromApi();
            mViewModel.getFromLocal();
            new Handler().postDelayed(() -> {
                binding.swipe.setRefreshing(false);
            }, 1000);
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(InventoryViewModel mViewModel) {
        mViewModel.getListObats().observe(getViewLifecycleOwner(), obatObjects -> {
            if (obatObjects != null) {
                adapterInventory.setData(obatObjects);
                adapterInventory.notifyDataSetChanged();
            } else {
                Snackbar.make(binding.getRoot(), "Gagal Ambil Data.", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {

            Snackbar.make(binding.parent, "Menambahkan..", BaseTransientBottomBar.LENGTH_LONG).show();

        }

        @Override
        public void onSuccess(@NonNull String message) {
            Snackbar.make(binding.parent, message, BaseTransientBottomBar.LENGTH_LONG).show();

        }

        @Override
        public void onError(@NonNull String message) {
            Snackbar.make(binding.parent, message, BaseTransientBottomBar.LENGTH_LONG).show();

        }
    };


}
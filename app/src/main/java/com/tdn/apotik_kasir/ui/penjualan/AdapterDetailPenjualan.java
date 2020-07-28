package com.tdn.apotik_kasir.ui.penjualan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.core.callback.AdapterClicked;
import com.tdn.apotik_kasir.databinding.ItemObatBinding;
import com.tdn.domain.object.ObatObject;
import com.tdn.domain.object.PenjualanObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterDetailPenjualan extends RecyclerView.Adapter<AdapterDetailPenjualan.MyViewHolder> {
    private List<PenjualanObject> obatObjectList = new ArrayList<>();
    private Context context;
    private AdapterClicked adapterClicked;

    public AdapterDetailPenjualan(Context context, AdapterClicked adapterClicked) {
        this.context = context;
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemObatBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_obat, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PenjualanObject o = obatObjectList.get(position);
//        holder.binding.tvNamaItem.setText(o.getObatNama());
//        holder.binding.tvSatuanJual.setText("Harga : " + o.getObatJual());
//        holder.binding.tvStok.setText("Stok : " + o.getObatStok());
        holder.binding.lyItem.setOnClickListener(view -> {

            adapterClicked.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return obatObjectList == null ? 0 : obatObjectList.size();
    }

    public void setData(List<PenjualanObject> TabunganModels) {
        if (this.obatObjectList == null) {
            this.obatObjectList.addAll(TabunganModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterDetailPenjualan.this.obatObjectList.size();
                }

                @Override
                public int getNewListSize() {
                    return TabunganModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterDetailPenjualan.this.obatObjectList.get(oldItemPosition).getPenjualanId() == TabunganModels.get(newItemPosition).getPenjualanId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    PenjualanObject lama = TabunganModels.get(oldItemPosition);
                    PenjualanObject baru = TabunganModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.obatObjectList = TabunganModels;
            result.dispatchUpdatesTo(this);

        }

    }

    public PenjualanObject getFromPosition(int posisi) {
        return obatObjectList.get(posisi);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemObatBinding binding;

        public MyViewHolder(@NonNull ItemObatBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}

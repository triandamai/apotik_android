package com.tdn.apotik_kasir.ui.transaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.core.callback.AdapterClicked;
import com.tdn.apotik_kasir.databinding.ItemTempBinding;
import com.tdn.domain.model.PenjualanTempModel;
import com.tdn.domain.object.PenjualanTempObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterTransaction extends RecyclerView.Adapter<AdapterTransaction.MyViewHolder> {
    private List<PenjualanTempObject> penjualanTempModels = new ArrayList<>();

    private Context context;
    private AdapterClicked adapterClicked;

    public AdapterTransaction(Context context, AdapterClicked adapterClicked) {
        this.context = context;
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTempBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_temp, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PenjualanTempObject m = penjualanTempModels.get(position);
        holder.binding.tvNamaItem.setText(m.get);
        holder.binding.tvSatuanJual.setText(m.getTempJumlah());
        holder.binding.tvStok.setText(m.getTempTotalharga());

        holder.binding.lyItem.setOnClickListener(view -> {
            adapterClicked.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return penjualanTempModels == null ? 0 : penjualanTempModels.size();
    }

    public void setData(List<PenjualanTempObject> TabunganModels) {
        if (this.penjualanTempModels == null) {
            this.penjualanTempModels.addAll(TabunganModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterTransaction.this.penjualanTempModels.size();
                }

                @Override
                public int getNewListSize() {
                    return TabunganModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterTransaction.this.penjualanTempModels.get(oldItemPosition).getTempId() == TabunganModels.get(newItemPosition).getTempId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    PenjualanTempObject lama = TabunganModels.get(oldItemPosition);
                    PenjualanTempObject baru = TabunganModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.penjualanTempModels = TabunganModels;
            result.dispatchUpdatesTo(this);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemTempBinding binding;

        public MyViewHolder(@NonNull ItemTempBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }


    }
}

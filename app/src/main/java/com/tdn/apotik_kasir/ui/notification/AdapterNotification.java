package com.tdn.apotik_kasir.ui.notification;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.core.callback.AdapterClicked;
import com.tdn.apotik_kasir.databinding.ItemNotifikasiBinding;
import com.tdn.domain.object.NotifikasiObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.MyViewHolder> {
    private List<NotifikasiObject> notifikasiObjectList = new ArrayList<>();
    private Context context;
    private AdapterClicked adapterClicked;

    public AdapterNotification(Context context, AdapterClicked adapterClicked) {
        this.context = context;
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotifikasiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_notifikasi, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NotifikasiObject o = notifikasiObjectList.get(position);
        holder.binding.tvTitle.setText(o.getStatus());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.binding.tvKet.setText(Html.fromHtml(o.getMessage(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.binding.tvKet.setText(Html.fromHtml(o.getMessage()));
        }

    }

    @Override
    public int getItemCount() {
        return notifikasiObjectList == null ? 0 : notifikasiObjectList.size();
    }

    public void setData(List<NotifikasiObject> TabunganModels) {
        if (this.notifikasiObjectList == null) {
            this.notifikasiObjectList.addAll(TabunganModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return notifikasiObjectList.size();
                }

                @Override
                public int getNewListSize() {
                    return TabunganModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterNotification.this.notifikasiObjectList.get(oldItemPosition).getIdNotif() == TabunganModels.get(newItemPosition).getIdNotif();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    NotifikasiObject lama = TabunganModels.get(oldItemPosition);
                    NotifikasiObject baru = TabunganModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.notifikasiObjectList = TabunganModels;
            result.dispatchUpdatesTo(this);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemNotifikasiBinding binding;

        public MyViewHolder(@NonNull ItemNotifikasiBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}

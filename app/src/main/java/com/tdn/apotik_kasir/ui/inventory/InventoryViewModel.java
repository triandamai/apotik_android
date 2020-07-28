package com.tdn.apotik_kasir.ui.inventory;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.apotik_kasir.core.callback.ActionListener;
import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.object.ObatObject;
import com.tdn.domain.serialize.req.ReqPenjualanTemp;
import com.tdn.domain.serialize.res.ResponseAction;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class InventoryViewModel extends ViewModel {
    private Realm realm;
    private Context context;
    private ActionListener actionListener;
    private LiveData<List<ObatObject>> listObats;
    private ApiService apiService;


    public InventoryViewModel(Context context, ActionListener actionListener) {
        this.actionListener = actionListener;
        this.realm = Realm.getDefaultInstance();
        this.context = context;
        this.apiService = ApiService.Factory.create();
        getFromApi();
        getFromLocal();
    }

    public void getFromLocal() {
        this.listObats = new RealmLiveResult(realm.where(ObatObject.class).findAll());
    }

    public void getFromApi() {
        Repository.getInstance(context).getObats();
    }

    public LiveData<List<ObatObject>> getListObats() {
        if (listObats == null) {
            listObats = new MutableLiveData<>();
        }
        return listObats;
    }


    public void tambahCart(ObatObject o, String toString) {

        actionListener.onStart();
        ReqPenjualanTemp temp = new ReqPenjualanTemp();

        if (toString != null) {
            double jumlah = Double.parseDouble(toString);
            if (jumlah <= Double.parseDouble(o.getDetailJumlah())) {
                double harga = Double.parseDouble(o.getDetailHargaJual());
                double total = harga * jumlah;

                temp.setTempJumlah(toString);
                temp.setTempId("");
                temp.setTempIdStok(o.getDetailId());
                temp.setTempTotal(String.valueOf(total));
                Log.e("tes", temp.toString());
                apiService.postTempPenjualan(temp).enqueue(new Callback<ResponseAction>() {
                    @Override
                    public void onResponse(Call<ResponseAction> call, Response<ResponseAction> response) {
                        if (cek(response.code())) {
                            if (cek(response.body().getResponseCode())) {
                                actionListener.onSuccess(response.body().getResponseMessage());
                            } else {
                                actionListener.onError(response.body().getResponseMessage());
                            }
                        } else {
                            actionListener.onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAction> call, Throwable t) {
                        actionListener.onError(t.getMessage());
                    }
                });

            } else {
                actionListener.onError("Stok tidak cukup!!");
            }

        } else {
            actionListener.onError("Isi Jumlah!!");
        }
    }
}
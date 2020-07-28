package com.tdn.domain.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tdn.domain.model.BaseModel;
import com.tdn.domain.model.PenjualanDetailModel;

import io.realm.RealmObject;

public class PenjualanDetailObject extends RealmObject {
    @SerializedName("detail_id")
    @Expose
    private String detailId;
    @SerializedName("detail_id_transaksi")
    @Expose
    private String detailIdTransaksi;
    @SerializedName("detail_obat_id")
    @Expose
    private String detailObatId;
    @SerializedName("detail_jumlah")
    @Expose
    private String detailJumlah;
    @SerializedName("detail_harga")
    @Expose
    private String detailHarga;
    @SerializedName("obat_id")
    @Expose
    private String obatId;
    @SerializedName("obat_nama")
    @Expose
    private String obatNama;
    @SerializedName("obat_stok")
    @Expose
    private String obatStok;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getDetailIdTransaksi() {
        return detailIdTransaksi;
    }

    public void setDetailIdTransaksi(String detailIdTransaksi) {
        this.detailIdTransaksi = detailIdTransaksi;
    }

    public String getDetailObatId() {
        return detailObatId;
    }

    public void setDetailObatId(String detailObatId) {
        this.detailObatId = detailObatId;
    }

    public String getDetailJumlah() {
        return detailJumlah;
    }

    public void setDetailJumlah(String detailJumlah) {
        this.detailJumlah = detailJumlah;
    }

    public String getDetailHarga() {
        return detailHarga;
    }

    public void setDetailHarga(String detailHarga) {
        this.detailHarga = detailHarga;
    }

    public String getObatId() {
        return obatId;
    }

    public void setObatId(String obatId) {
        this.obatId = obatId;
    }

    public String getObatNama() {
        return obatNama;
    }

    public void setObatNama(String obatNama) {
        this.obatNama = obatNama;
    }

    public String getObatStok() {
        return obatStok;
    }

    public void setObatStok(String obatStok) {
        this.obatStok = obatStok;
    }

    @Override
    public String toString() {
        return "PenjualanDetailObject{" +
                "detailId='" + detailId + '\'' +
                ", detailIdTransaksi='" + detailIdTransaksi + '\'' +
                ", detailObatId='" + detailObatId + '\'' +
                ", detailJumlah='" + detailJumlah + '\'' +
                ", detailHarga='" + detailHarga + '\'' +
                ", obatId='" + obatId + '\'' +
                ", obatNama='" + obatNama + '\'' +
                ", obatStok='" + obatStok + '\'' +
                '}';
    }

    public Object ToModel() {
        PenjualanDetailModel o = new PenjualanDetailModel();
        o.setDetailHarga(detailHarga);
        o.setDetailId(detailId);
        o.setDetailIdTransaksi(detailIdTransaksi);
        o.setDetailJumlah(detailJumlah);
        o.setDetailObatId(detailObatId);
        o.setObatNama(obatNama);
        o.setObatStok(obatStok);
        o.setObatId(obatId);
        return o;
    }

}

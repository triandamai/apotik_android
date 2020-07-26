package com.tdn.domain.object;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ObatObject extends RealmObject {

    private String obatJual;


    private String obatStok;


    private String obatNama;

    @PrimaryKey
    private String obatId;


    private String obatBeli;

    public void setObatJual(String obatJual) {
        this.obatJual = obatJual;
    }

    public String getObatJual() {
        return obatJual;
    }

    public void setObatStok(String obatStok) {
        this.obatStok = obatStok;
    }

    public String getObatStok() {
        return obatStok;
    }

    public void setObatNama(String obatNama) {
        this.obatNama = obatNama;
    }

    public String getObatNama() {
        return obatNama;
    }

    public void setObatId(String obatId) {
        this.obatId = obatId;
    }

    public String getObatId() {
        return obatId;
    }

    public void setObatBeli(String obatBeli) {
        this.obatBeli = obatBeli;
    }

    public String getObatBeli() {
        return obatBeli;
    }

    @Override
    public String toString() {
        return
                "ObatModel{" +
                        "obat_jual = '" + obatJual + '\'' +
                        ",obat_stok = '" + obatStok + '\'' +
                        ",obat_nama = '" + obatNama + '\'' +
                        ",obat_id = '" + obatId + '\'' +
                        ",obat_beli = '" + obatBeli + '\'' +
                        "}";
    }
}

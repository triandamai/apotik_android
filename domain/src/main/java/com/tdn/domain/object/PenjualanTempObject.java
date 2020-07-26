package com.tdn.domain.object;

import com.google.gson.annotations.SerializedName;
import com.tdn.domain.model.PenjualanTempModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PenjualanTempObject extends RealmObject {

    private String tempTotalharga;


    private String tempObatId;

    @PrimaryKey
    private String tempId;


    private String tempJumlah;


    private String tempNama;

    public PenjualanTempObject() {
    }

    public PenjualanTempObject(String tempTotalharga, String tempObatId, String tempId, String tempJumlah, String tempNama) {
        this.tempTotalharga = tempTotalharga;
        this.tempObatId = tempObatId;
        this.tempId = tempId;
        this.tempJumlah = tempJumlah;
        this.tempNama = tempNama;
    }

    public String getTempTotalharga() {
        return tempTotalharga;
    }

    public void setTempTotalharga(String tempTotalharga) {
        this.tempTotalharga = tempTotalharga;
    }

    public String getTempObatId() {
        return tempObatId;
    }

    public void setTempObatId(String tempObatId) {
        this.tempObatId = tempObatId;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getTempJumlah() {
        return tempJumlah;
    }

    public void setTempJumlah(String tempJumlah) {
        this.tempJumlah = tempJumlah;
    }

    public String getTempNama() {
        return tempNama;
    }

    public void setTempNama(String tempNama) {
        this.tempNama = tempNama;
    }

    @Override
    public String toString() {
        return "PenjualanTempObject{" +
                "tempTotalharga='" + tempTotalharga + '\'' +
                ", tempObatId='" + tempObatId + '\'' +
                ", tempId='" + tempId + '\'' +
                ", tempJumlah='" + tempJumlah + '\'' +
                ", tempNama='" + tempNama + '\'' +
                '}';
    }

    public Object ToModel() {
        PenjualanTempModel o = new PenjualanTempModel();
        o.setTempId(tempId);
        o.setTempJumlah(tempJumlah);
        o.setTempNama(tempNama);
        o.setTempObatId(tempObatId);
        o.setTempTotalharga(tempTotalharga);

        return o;
    }
}

package com.tdn.domain.model;

import com.google.gson.annotations.SerializedName;
import com.tdn.domain.object.PenjualanTempObject;

public class PenjualanTempModel extends BaseModel {

    @SerializedName("temp_totalharga")
    private String tempTotalharga;

    @SerializedName("temp_obat_id")
    private String tempObatId;

    @SerializedName("temp_id")
    private String tempId;

    @SerializedName("temp_jumlah")
    private String tempJumlah;

    @SerializedName("temp_nama")
    private String tempNama;

    public void setTempTotalharga(String tempTotalharga) {
        this.tempTotalharga = tempTotalharga;
    }

    public String getTempTotalharga() {
        return tempTotalharga;
    }

    public void setTempObatId(String tempObatId) {
        this.tempObatId = tempObatId;
    }

    public String getTempObatId() {
        return tempObatId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempJumlah(String tempJumlah) {
        this.tempJumlah = tempJumlah;
    }

    public String getTempJumlah() {
        return tempJumlah;
    }

    public void setTempNama(String tempNama) {
        this.tempNama = tempNama;
    }

    public String getTempNama() {
        return tempNama;
    }

    @Override
    public String toString() {
        return
                "PenjualanTempModel{" +
                        "temp_totalharga = '" + tempTotalharga + '\'' +
                        ",temp_obat_id = '" + tempObatId + '\'' +
                        ",temp_id = '" + tempId + '\'' +
                        ",temp_jumlah = '" + tempJumlah + '\'' +
                        ",temp_nama = '" + tempNama + '\'' +
                        "}";
    }

    @Override
    public Object ToObject() {
        PenjualanTempObject o = new PenjualanTempObject();
        o.setTempId(tempId);
        o.setTempJumlah(tempJumlah);
        o.setTempNama(tempNama);
        o.setTempObatId(tempObatId);
        o.setTempTotalharga(tempTotalharga);

        return o;
    }
}
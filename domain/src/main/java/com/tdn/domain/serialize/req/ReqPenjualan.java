package com.tdn.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqPenjualan {
    @SerializedName("subtotal")
    @Expose
    private String subtotal;

    /**
     * No args constructor for use in serialization
     */
    public ReqPenjualan() {
    }

    /**
     * @param subtotal
     */
    public ReqPenjualan(String subtotal) {
        super();
        this.subtotal = subtotal;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }


}

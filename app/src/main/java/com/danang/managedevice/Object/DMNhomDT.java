package com.danang.managedevice.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DMNhomDT {

    @SerializedName("ma_nhomdt")
    @Expose
    private String maNhomdt;
    @SerializedName("ten_dt")
    @Expose
    private String tenDt;

    /**
     * No args constructor for use in serialization
     *
     */
    public DMNhomDT() {
    }

    /**
     *
     * @param maNhomdt
     * @param tenDt
     */
    public DMNhomDT(String maNhomdt, String tenDt) {
        super();
        this.maNhomdt = maNhomdt;
        this.tenDt = tenDt;
    }

    public String getMaNhomdt() {
        return maNhomdt;
    }

    public void setMaNhomdt(String maNhomdt) {
        this.maNhomdt = maNhomdt;
    }

    public String getTenDt() {
        return tenDt;
    }

    public void setTenDt(String tenDt) {
        this.tenDt = tenDt;
    }

}
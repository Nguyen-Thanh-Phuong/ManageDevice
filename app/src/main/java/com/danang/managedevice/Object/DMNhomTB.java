package com.danang.managedevice.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DMNhomTB {

    @SerializedName("manhom")
    @Expose
    private String manhom="";
    @SerializedName("ten_nhomtbi")
    @Expose
    private String tenNhomtbi="";
    @SerializedName("dvt")
    @Expose
    private String dvt="";
    @SerializedName("loai")
    @Expose
    private Integer loai;
    @SerializedName("makt")
    @Expose
    private String makt="";
    @SerializedName("DM_Nhom_DT")
    @Expose
    private List<DMNhomDT> dMNhomDT = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DMNhomTB() {
    }

    /**
     *
     * @param tenNhomtbi
     * @param dvt
     * @param manhom
     * @param loai
     * @param dMNhomDT
     * @param makt
     */
    public DMNhomTB(String manhom, String tenNhomtbi, String dvt, Integer loai, String makt, List<DMNhomDT> dMNhomDT) {
        super();
        this.manhom = manhom;
        this.tenNhomtbi = tenNhomtbi;
        this.dvt = dvt;
        this.loai = loai;
        this.makt = makt;
        this.dMNhomDT = dMNhomDT;
    }

    public String getManhom() {
        return manhom;
    }

    public void setManhom(String manhom) {
        this.manhom = manhom;
    }

    public String getTenNhomtbi() {
        return tenNhomtbi;
    }

    public void setTenNhomtbi(String tenNhomtbi) {
        this.tenNhomtbi = tenNhomtbi;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public Integer getLoai() {
        return loai;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public String getMakt() {
        return makt;
    }

    public void setMakt(String makt) {
        this.makt = makt;
    }

    public List<DMNhomDT> getDMNhomDT() {
        return dMNhomDT;
    }

    public void setDMNhomDT(List<DMNhomDT> dMNhomDT) {
        this.dMNhomDT = dMNhomDT;
    }

}
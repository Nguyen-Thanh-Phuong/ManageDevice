package com.danang.managedevice.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QLTB {
    @SerializedName("maql")
    @Expose
    private String maql="";
    @SerializedName("tendonvi")
    @Expose
    private String tendonvi;
    @SerializedName("maso")
    @Expose
    private String maso="";
    @SerializedName("loaimay")
    @Expose
    private String loaimay="";
    @SerializedName("noicap")
    @Expose
    private String noicap="";
    @SerializedName("ngaycap")
    @Expose
    private String ngaycap="";
    @SerializedName("noimua")
    @Expose
    private String noimua="";
    @SerializedName("ngaymua")
    @Expose
    private String ngaymua="";
    @SerializedName("mucdich")
    @Expose
    private String mucdich="";
    @SerializedName("nguoisd")
    @Expose
    private String nguoisd="";
    @SerializedName("ghichu")
    @Expose
    private String ghichu="";
    @SerializedName("AD")
    @Expose
    private String aD="";
    @SerializedName("manhom")
    @Expose
    private String manhom="";
    @SerializedName("maloai")
    @Expose
    private String maloai="";
    @SerializedName("nguoinhap")
    @Expose
    private String nguoinhap="";
    @SerializedName("ngaynhap")
    @Expose
    private String ngaynhap="";
    @SerializedName("OS")
    @Expose
    private String oS="";
    @SerializedName("Banquyen")
    @Expose
    private Boolean banquyen=false;
    @SerializedName("Virut")
    @Expose
    private Boolean virut=false;
    @SerializedName("Domain")
    @Expose
    private Boolean domain=false;
    @SerializedName("Tenmay")
    @Expose
    private String tenmay="";
    @SerializedName("ten_file")
    @Expose
    private String tenFile="";
    @SerializedName("ma_tsan")
    @Expose
    private String maTsan="";
    @SerializedName("ma_nhomdt")
    @Expose
    private String maNhomdt="";


    public QLTB(String maql) {
        this.maql = maql;
    }
    /**
     *
     */
    public QLTB() {
    }

    /**
     *
     * @param ngaycap
     * @param nguoinhap
     * @param tenFile
     * @param maso
     * @param aD
     * @param oS
     * @param ghichu
     * @param manhom
     * @param noicap
     * @param ngaymua
     * @param loaimay
     * @param virut
     * @param tendonvi
     * @param nguoisd
     * @param noimua
     * @param mucdich
     * @param maloai
     * @param domain
     * @param maNhomdt
     * @param banquyen
     * @param maql
     * @param tenmay
     * @param ngaynhap
     * @param maTsan
     */
    public QLTB(String maql, String tendonvi, String maso, String loaimay, String noicap, String ngaycap, String noimua, String ngaymua, String mucdich, String nguoisd, String ghichu, String aD, String manhom, String maloai, String nguoinhap, String ngaynhap, String oS, Boolean banquyen, Boolean virut, Boolean domain, String tenmay, String tenFile, String maTsan, String maNhomdt) {
        super();
        this.maql = maql;
        this.tendonvi = tendonvi;
        this.maso = maso;
        this.loaimay = loaimay;
        this.noicap = noicap;
        this.ngaycap = ngaycap;
        this.noimua = noimua;
        this.ngaymua = ngaymua;
        this.mucdich = mucdich;
        this.nguoisd = nguoisd;
        this.ghichu = ghichu;
        this.aD = aD;
        this.manhom = manhom;
        this.maloai = maloai;
        this.nguoinhap = nguoinhap;
        this.ngaynhap = ngaynhap;
        this.oS = oS;
        this.banquyen = banquyen;
        this.virut = virut;
        this.domain = domain;
        this.tenmay = tenmay;
        this.tenFile = tenFile;
        this.maTsan = maTsan;
        this.maNhomdt = maNhomdt;
    }

    public String getMaql() {
        return maql;
    }

    public void setMaql(String maql) {
        this.maql = maql;
    }

    public String getTendonvi() {
        return tendonvi;
    }

    public void setTendonvi(String tendonvi) {
        this.tendonvi = tendonvi;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getLoaimay() {
        return loaimay;
    }

    public void setLoaimay(String loaimay) {
        this.loaimay = loaimay;
    }

    public String getNoicap() {
        return noicap;
    }

    public void setNoicap(String noicap) {
        this.noicap = noicap;
    }

    public String getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(String ngaycap) {
        this.ngaycap = ngaycap;
    }

    public String getNoimua() {
        return noimua;
    }

    public void setNoimua(String noimua) {
        this.noimua = noimua;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public String getMucdich() {
        return mucdich;
    }

    public void setMucdich(String mucdich) {
        this.mucdich = mucdich;
    }

    public String getNguoisd() {
        return nguoisd;
    }

    public void setNguoisd(String nguoisd) {
        this.nguoisd = nguoisd;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getAD() {
        return aD;
    }

    public void setAD(String aD) {
        this.aD = aD;
    }

    public String getManhom() {
        return manhom;
    }

    public void setManhom(String manhom) {
        this.manhom = manhom;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getNguoinhap() {
        return nguoinhap;
    }

    public void setNguoinhap(String nguoinhap) {
        this.nguoinhap = nguoinhap;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getOS() {
        return oS;
    }

    public void setOS(String oS) {
        this.oS = oS;
    }

    public Boolean getBanquyen() {
        return banquyen;
    }

    public void setBanquyen(Boolean banquyen) {
        this.banquyen = banquyen;
    }

    public Boolean getVirut() {
        return virut;
    }

    public void setVirut(Boolean virut) {
        this.virut = virut;
    }

    public Boolean getDomain() {
        return domain;
    }

    public void setDomain(Boolean domain) {
        this.domain = domain;
    }

    public String getTenmay() {
        return tenmay;
    }

    public void setTenmay(String tenmay) {
        this.tenmay = tenmay;
    }

    public String getTenFile() {
        return tenFile;
    }

    public void setTenFile(String tenFile) {
        this.tenFile = tenFile;
    }

    public String getMaTsan() {
        return maTsan;
    }

    public void setMaTsan(String maTsan) {
        this.maTsan = maTsan;
    }

    public String getMaNhomdt() {
        return maNhomdt;
    }

    public void setMaNhomdt(String maNhomdt) {
        this.maNhomdt = maNhomdt;
    }

}
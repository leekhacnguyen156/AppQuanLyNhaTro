package com.example.quanlynhatro.ModelApp;

public class Loaiphong {
    private int mMaloai;
    private int mMatro;
    private String mTenloai;
    private double mGialoai;

    //set data

    public Loaiphong(int mMatro, String mTenloai, double mGialoai) {
        this.mMatro = mMatro;
        this.mTenloai = mTenloai;
        this.mGialoai = mGialoai;
    }

    //get data

    public Loaiphong(int mMaloai, int mMatro, String mTenloai, double mGialoai) {
        this.mMaloai = mMaloai;
        this.mMatro = mMatro;
        this.mTenloai = mTenloai;
        this.mGialoai = mGialoai;
    }

    public int getmMaloai() {
        return mMaloai;
    }

    public void setmMaloai(int mMaloai) {
        this.mMaloai = mMaloai;
    }

    public int getmMatro() {
        return mMatro;
    }

    public void setmMatro(int mMatro) {
        this.mMatro = mMatro;
    }

    public String getmTenloai() {
        return mTenloai;
    }

    public void setmTenloai(String mTenloai) {
        this.mTenloai = mTenloai;
    }

    public double getmGialoai() {
        return mGialoai;
    }

    public void setmGialoai(double mGialoai) {
        this.mGialoai = mGialoai;
    }
}

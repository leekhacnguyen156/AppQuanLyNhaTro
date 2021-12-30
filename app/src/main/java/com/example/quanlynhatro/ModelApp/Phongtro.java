package com.example.quanlynhatro.ModelApp;

public class Phongtro {
    private int mMaphong;
    private int mMaloai;
    private String mTenphong;
    private int mKhachhientai;
    private int mKhachtoida;

    //Set data

    public Phongtro(int mMaloai, String mTenphong, int mKhachhientai, int mKhachtoida) {
        this.mMaloai = mMaloai;
        this.mTenphong = mTenphong;
        this.mKhachhientai = mKhachhientai;
        this.mKhachtoida = mKhachtoida;
    }


    //Get data

    public Phongtro(int mMaphong, int mMaloai, String mTenphong, int mKhachhientai, int mKhachtoida) {
        this.mMaphong = mMaphong;
        this.mMaloai = mMaloai;
        this.mTenphong = mTenphong;
        this.mKhachhientai = mKhachhientai;
        this.mKhachtoida = mKhachtoida;
    }

    public int getmMaphong() {
        return mMaphong;
    }

    public void setmMaphong(int mMaphong) {
        this.mMaphong = mMaphong;
    }

    public int getmMaloai() {
        return mMaloai;
    }

    public void setmMaloai(int mMaloai) {
        this.mMaloai = mMaloai;
    }

    public String getmTenphong() {
        return mTenphong;
    }

    public void setmTenphong(String mTenphong) {
        this.mTenphong = mTenphong;
    }

    public int getmKhachhientai() {
        return mKhachhientai;
    }

    public void setmKhachhientai(int mKhachhientai) {
        this.mKhachhientai = mKhachhientai;
    }

    public int getmKhachtoida() {
        return mKhachtoida;
    }

    public void setmKhachtoida(int mKhachtoida) {
        this.mKhachtoida = mKhachtoida;
    }
}

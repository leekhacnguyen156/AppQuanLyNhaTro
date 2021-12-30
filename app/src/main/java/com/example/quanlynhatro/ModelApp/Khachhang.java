package com.example.quanlynhatro.ModelApp;

import java.util.Date;

public class Khachhang {
    private int mMakhach;
    private int mMaphong;
    private String mTen;
    private int mGioitinh;
    private Date mNgaysinh;
    private String mSdt;
    private String mCmnd;
    private String mQuequan;
    private byte[] mHinhanh;
    private int isDelete;

    //Set data

    public Khachhang(int mMaphong, String mTen, int mGioitinh, Date mNgaysinh, String mSdt, String mCmnd, String mQuequan, byte[] mHinhanh, int isDelete) {
        this.mMaphong = mMaphong;
        this.mTen = mTen;
        this.mGioitinh = mGioitinh;
        this.mNgaysinh = mNgaysinh;
        this.mSdt = mSdt;
        this.mCmnd = mCmnd;
        this.mQuequan = mQuequan;
        this.mHinhanh = mHinhanh;
        this.isDelete = isDelete;
    }


    //Getdata


    public Khachhang(int mMakhach, int mMaphong, String mTen, int mGioitinh, Date mNgaysinh, String mSdt, String mCmnd, String mQuequan, byte[] mHinhanh, int isDelete) {
        this.mMakhach = mMakhach;
        this.mMaphong = mMaphong;
        this.mTen = mTen;
        this.mGioitinh = mGioitinh;
        this.mNgaysinh = mNgaysinh;
        this.mSdt = mSdt;
        this.mCmnd = mCmnd;
        this.mQuequan = mQuequan;
        this.mHinhanh = mHinhanh;
        this.isDelete = isDelete;
    }

    public int getmMakhach() {
        return mMakhach;
    }

    public void setmMakhach(int mMakhach) {
        this.mMakhach = mMakhach;
    }

    public int getmMaphong() {
        return mMaphong;
    }

    public void setmMaphong(int mMaphong) {
        this.mMaphong = mMaphong;
    }

    public String getmTen() {
        return mTen;
    }

    public void setmTen(String mTen) {
        this.mTen = mTen;
    }

    public int getmGioitinh() {
        return mGioitinh;
    }

    public void setmGioitinh(int mGioitinh) {
        this.mGioitinh = mGioitinh;
    }

    public Date getmNgaysinh() {
        return mNgaysinh;
    }

    public void setmNgaysinh(Date mNgaysinh) {
        this.mNgaysinh = mNgaysinh;
    }

    public String getmSdt() {
        return mSdt;
    }

    public void setmSdt(String mSdt) {
        this.mSdt = mSdt;
    }

    public String getmCmnd() {
        return mCmnd;
    }

    public void setmCmnd(String mCmnd) {
        this.mCmnd = mCmnd;
    }

    public String getmQuequan() {
        return mQuequan;
    }

    public void setmQuequan(String mQuequan) {
        this.mQuequan = mQuequan;
    }

    public byte[] getmHinhanh() {
        return mHinhanh;
    }

    public void setmHinhanh(byte[] mHinhanh) {
        this.mHinhanh = mHinhanh;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}

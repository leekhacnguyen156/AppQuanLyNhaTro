package com.example.quanlynhatro.ModelApp;

import java.util.Date;

public class Traphongtro {
    private int mMatraphong;
    private int mMakhach;
    private Date mNgaytra;

    //Them du lieu


    public Traphongtro(int mMakhach, Date mNgaytra) {
        this.mMakhach = mMakhach;
        this.mNgaytra = mNgaytra;
    }

    //Get du lieu
    public Traphongtro(int mMatraphong, int mMakhach, Date mNgaytra) {
        this.mMatraphong = mMatraphong;
        this.mMakhach = mMakhach;
        this.mNgaytra = mNgaytra;
    }

    public int getmMatraphong() {
        return mMatraphong;
    }

    public void setmMatraphong(int mMatraphong) {
        this.mMatraphong = mMatraphong;
    }

    public int getmMakhach() {
        return mMakhach;
    }

    public void setmMakhach(int mMakhach) {
        this.mMakhach = mMakhach;
    }

    public Date getmNgaytra() {
        return mNgaytra;
    }

    public void setmNgaytra(Date mNgaytra) {
        this.mNgaytra = mNgaytra;
    }
}


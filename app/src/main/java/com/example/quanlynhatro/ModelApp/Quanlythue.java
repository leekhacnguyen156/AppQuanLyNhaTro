package com.example.quanlynhatro.ModelApp;

import java.util.Date;

public class Quanlythue {
    private int mMathue;
    private int mMakhach;
    private Date mNgaythue;


    //Them du lieu

    public Quanlythue(int mMakhach, Date mNgaythue) {
        this.mMakhach = mMakhach;
        this.mNgaythue = mNgaythue;
    }


    //Get data

    public Quanlythue(int mMathue, int mMakhach, Date mNgaythue) {
        this.mMathue = mMathue;
        this.mMakhach = mMakhach;
        this.mNgaythue = mNgaythue;
    }

    public int getmMathue() {
        return mMathue;
    }

    public void setmMathue(int mMathue) {
        this.mMathue = mMathue;
    }

    public int getmMakhach() {
        return mMakhach;
    }

    public void setmMakhach(int mMakhach) {
        this.mMakhach = mMakhach;
    }

    public Date getmNgaythue() {
        return mNgaythue;
    }

    public void setmNgaythue(Date mNgaythue) {
        this.mNgaythue = mNgaythue;
    }

}

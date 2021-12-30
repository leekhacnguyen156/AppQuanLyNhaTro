package com.example.quanlynhatro.ModelApp;

public class Thongtintro {
    private int mMatro;
    private String mTentro;
    private String mDiachi;
    private double mGiadien;
    private double mGianuoc;

    // Them data
    public Thongtintro(String mTentro, String mDiachi, double mGiadien, double mGianuoc) {
        this.mTentro = mTentro;
        this.mDiachi = mDiachi;
        this.mGiadien = mGiadien;
        this.mGianuoc = mGianuoc;
    }

    // Get data
    public Thongtintro(int mMatro, String mTentro, String mDiachi, double mGiadien, double mGianuoc) {
        this.mMatro = mMatro;
        this.mTentro = mTentro;
        this.mDiachi = mDiachi;
        this.mGiadien = mGiadien;
        this.mGianuoc = mGianuoc;
    }

    public int getmMatro() {
        return mMatro;
    }

    public void setmMatro(int mMatro) {
        this.mMatro = mMatro;
    }

    public String getmTentro() {
        return mTentro;
    }

    public void setmTentro(String mTentro) {
        this.mTentro = mTentro;
    }

    public String getmDiachi() {
        return mDiachi;
    }

    public void setmDiachi(String mDiachi) {
        this.mDiachi = mDiachi;
    }

    public double getmGiadien() {
        return mGiadien;
    }

    public void setmGiadien(double mGiadien) {
        this.mGiadien = mGiadien;
    }

    public double getmGianuoc() {
        return mGianuoc;
    }

    public void setmGianuoc(double mGianuoc) {
        this.mGianuoc = mGianuoc;
    }
}

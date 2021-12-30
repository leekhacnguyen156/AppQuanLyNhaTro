package com.example.quanlynhatro.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.ModelApp.Hoadon;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Quanlythue;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.ModelApp.Traphongtro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Hoadon;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Loaiphong;
import com.example.quanlynhatro.SQLite.ModelData.TB_Phongtro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Quanlythue;
import com.example.quanlynhatro.SQLite.ModelData.TB_Thongtintro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Traphongtro;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuanLyNhaTro";
    private static int VERSION = 1;


    //Xu ly khi Content values put kieu du lieu Date
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static String datetoString(Date date) {
        return df.format(date);
    }

    public static Date Stringtodate(String strDate) {
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return new Date();
        }
    }


    private static class QUERY {
        //Tao table
        private static final String QUERY_CREATE_TB_Thongtintro = "CREATE TABLE IF NOT EXISTS "
                + TB_Thongtintro.TABLENAME + " ("
                + TB_Thongtintro.MATRO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Thongtintro.TENTRO + " NVARCHAR(255) NOT NULL, "
                + TB_Thongtintro.DIACHI + " NVARCHAR(255), "
                + TB_Thongtintro.GIADIEN + " DOUBLE, "
                + TB_Thongtintro.GIANUOC + " DOUBLE)";

        private static final String QUERY_CREATE_TB_Loaiphong = "CREATE TABLE IF NOT EXISTS "
                + TB_Loaiphong.TABLENAME + " ("
                + TB_Loaiphong.MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Loaiphong.MATRO + " INTEGER NOT NULL, "
                + TB_Loaiphong.TENLOAI + " NVARCHAR(255) NOT NULL, "
                + TB_Loaiphong.GIALOAI + " DOUBLE, "
                + "FOREIGN KEY (" + TB_Loaiphong.MATRO + ") REFERENCES " + TB_Thongtintro.TABLENAME + "(" + TB_Thongtintro.MATRO + "))";
        ;


        private static final String QUERY_CREATE_TB_Phongtro = "CREATE TABLE IF NOT EXISTS "
                + TB_Phongtro.TABLENAME + " ("
                + TB_Phongtro.MAPHONG + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Phongtro.MALOAI + " INTEGER NOT NULL, "
                + TB_Phongtro.TENPHONG + " NVARCHAR(255) NOT NULL, "
                + TB_Phongtro.KHACHHIENTAI + " TINYINT, "
                + TB_Phongtro.KHACHTOIDA + " TINYINT, "
                + "FOREIGN KEY (" + TB_Phongtro.MALOAI + ") REFERENCES " + TB_Loaiphong.TABLENAME + "(" + TB_Loaiphong.MALOAI + "))";
        ;


        private static final String QUERY_CREATE_TB_Khachhang = "CREATE TABLE IF NOT EXISTS "
                + TB_Khachhang.TABLENAME + " ("
                + TB_Khachhang.MAKHACH + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Khachhang.MAPHONG + " INTEGER, "
                + TB_Khachhang.TEN + " NVARCHAR(255) NOT NULL, "
                + TB_Khachhang.GIOITINH + " INTEGER, "
                + TB_Khachhang.NGAYSINH + " DATE, "
                + TB_Khachhang.SDT + " VARCHAR, "
                + TB_Khachhang.CMND + " VARCHAR, "
                + TB_Khachhang.QUEQUAN + " TEXT, "
                + TB_Khachhang.HINHANH + " BLOB, "
                + TB_Khachhang.ISDELETE + " INTEGER, "
                + " FOREIGN KEY (" + TB_Khachhang.MAPHONG + ") REFERENCES " + TB_Phongtro.TABLENAME + "(" + TB_Phongtro.MAPHONG + "))";

        private static final String QUERY_CREATE_TB_Hoadon = "CREATE TABLE IF NOT EXISTS "
                + TB_Hoadon.TABLENAME + " ("
                + TB_Hoadon.MAHD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Hoadon.MAPHONG + " INTEGER NOT NULL, "
                + TB_Hoadon.NGAYTHU + " DATE, "
                + TB_Hoadon.SODIEN + " SMALLINT, "
                + TB_Hoadon.GIADIEN + " DOUBLE, "
                + TB_Hoadon.SONUOC + " SMALLINT, "
                + TB_Hoadon.GIANUOC + " DOUBLE, "
                + TB_Hoadon.PHIPHATSINH + " DOUBLE, "
                + TB_Hoadon.SOPHATSINH + " SMALLINT, "
                + TB_Hoadon.TONGTIEN + " DOUBLE, "
                + TB_Hoadon.TRANGTHAI + " SMALLINT, "
                + TB_Hoadon.GHICHU + " TEXT, "
                + TB_Hoadon.THANG + " SMALLINT, "
                + TB_Hoadon.NAM + " SMALLINT, "
                + "FOREIGN KEY (" + TB_Hoadon.MAPHONG + ") REFERENCES " + TB_Phongtro.TABLENAME + "(" + TB_Phongtro.MAPHONG + "))";


        private static final String QUERY_CREATE_TB_Quanlythue = "CREATE TABLE IF NOT EXISTS "
                + TB_Quanlythue.TABLENAME + " ("
                + TB_Quanlythue.MATHUE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Quanlythue.MAKHACH + " INTEGER NOT NULL, "
                + TB_Quanlythue.NGAYTHUE + " DATE, "
                + "FOREIGN KEY (" + TB_Quanlythue.MAKHACH + ") REFERENCES " + TB_Khachhang.TABLENAME + "(" + TB_Khachhang.MAKHACH + "))";


        private static final String QUERY_CREATE_TB_Traphongtro = "CREATE TABLE IF NOT EXISTS "
                + TB_Traphongtro.TABLENAME + " ("
                + TB_Traphongtro.MATRAPHONG + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Traphongtro.MAKHACH + " INTEGER NOT NULL, "
                + TB_Traphongtro.NGAYTRA + " DATE   , "
                + "FOREIGN KEY (" + TB_Traphongtro.MAKHACH + ") REFERENCES " + TB_Khachhang.TABLENAME + "(" + TB_Khachhang.MAKHACH + "))";

    }

    public DataSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbSQLite) {
        dbSQLite.execSQL(QUERY.QUERY_CREATE_TB_Thongtintro);
        dbSQLite.execSQL(QUERY.QUERY_CREATE_TB_Loaiphong);
        dbSQLite.execSQL(QUERY.QUERY_CREATE_TB_Phongtro);
        dbSQLite.execSQL(QUERY.QUERY_CREATE_TB_Khachhang);
        dbSQLite.execSQL(QUERY.QUERY_CREATE_TB_Hoadon);
        dbSQLite.execSQL(QUERY.QUERY_CREATE_TB_Traphongtro);
        dbSQLite.execSQL(QUERY.QUERY_CREATE_TB_Quanlythue);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //** Xu ly data
    // Thêm data table Thongtintro
    public void add1Thongtintro(Thongtintro thongtintro) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Thongtintro.TENTRO, thongtintro.getmTentro());
        values.put(TB_Thongtintro.DIACHI, thongtintro.getmDiachi());
        values.put(TB_Thongtintro.GIADIEN, thongtintro.getmGiadien());
        values.put(TB_Thongtintro.GIANUOC, thongtintro.getmGianuoc());
        sqliteDATA.insert(TB_Thongtintro.TABLENAME, null, values);
        sqliteDATA.close();
    }

    // Lấy all data table thongtintro
    public ArrayList<Thongtintro> getThongtintro() {
        ArrayList<Thongtintro> arrayThongtintro = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Thongtintro.TABLENAME;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                arrayThongtintro.add(new Thongtintro(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayThongtintro;
    }

    // Lấy all data table thongtintro mới nhất
    public Thongtintro getThongtintronew() {
        Thongtintro thongtintro = null;
        String SELECT_QUERY = "SELECT * FROM " + TB_Thongtintro.TABLENAME + " ORDER BY " + TB_Thongtintro.MATRO + " DESC LIMIT 0, 1";
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            thongtintro = new Thongtintro(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4));
        }
        sqLiteDATA.close();
        return thongtintro;
    }

    //update nha tro
    public void updateNhatro(Thongtintro thongtintro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Thongtintro.TENTRO, thongtintro.getmTentro());
        values.put(TB_Thongtintro.DIACHI, thongtintro.getmDiachi());
        values.put(TB_Thongtintro.GIADIEN, thongtintro.getmGiadien());
        values.put(TB_Thongtintro.GIANUOC, thongtintro.getmGianuoc());
        db.update(TB_Thongtintro.TABLENAME, values, TB_Thongtintro.MATRO + " = ?", new String[]{String.valueOf(thongtintro.getmMatro())});
        db.close();
    }

    // Lấy all data table thongtintro theo mã trọ
    public Thongtintro getThongtintrotheomatro(int matro) {
        Thongtintro thongtintro = null;
        String SELECT_QUERY = "SELECT * FROM " + TB_Thongtintro.TABLENAME + " WHERE " + TB_Thongtintro.MATRO + " = " + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            thongtintro = new Thongtintro(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4));
        }
        sqLiteDATA.close();
        return thongtintro;
    }


    // Thêm data table Loaiphong
    public void add1Loaiphong(Loaiphong loaiphong) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Loaiphong.MATRO, loaiphong.getmMatro());
        values.put(TB_Loaiphong.TENLOAI, loaiphong.getmTenloai());
        values.put(TB_Loaiphong.GIALOAI, loaiphong.getmGialoai());
        sqliteDATA.insert(TB_Loaiphong.TABLENAME, null, values);
        sqliteDATA.close();
    }

    // Lấy all data table Loaiphong
    public ArrayList<Loaiphong> getLoaiphong() {
        ArrayList<Loaiphong> arrayLoaiphong = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Loaiphong.TABLENAME;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                arrayLoaiphong.add(new Loaiphong(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getDouble(3)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayLoaiphong;
    }

    // Lấy all data table Loaiphong theo mã trọ
    public ArrayList<Loaiphong> getLoaiphongtheomatro(int matro) {
        ArrayList<Loaiphong> arrayLoaiphong = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Loaiphong.TABLENAME + " WHERE " + TB_Loaiphong.MATRO + " = " + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                arrayLoaiphong.add(new Loaiphong(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getDouble(3)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayLoaiphong;
    }

    // Lấy all data table Loaiphong theo mã trọ
    public Loaiphong getLoaiphongtheomaloai(int maloai) {
        Loaiphong loaiphong = null;
        String SELECT_QUERY = "SELECT * FROM " + TB_Loaiphong.TABLENAME + " WHERE " + TB_Loaiphong.MALOAI + " = " + maloai;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                loaiphong = new Loaiphong(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getDouble(3));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return loaiphong;
    }

    //get loai phong trung ten
    public int getloaiphongtrungten(String tenlp, int matro){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SELECT_QUERY = "SELECT * FROM " + TB_Loaiphong.TABLENAME + " WHERE " + TB_Loaiphong.TENLOAI + " = '" + tenlp + "' AND "+ TB_Loaiphong.MATRO + " = " + matro;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_QUERY,null);
        return cursor.getCount();
    }

    //get loai phong trung ten update
    public int getloaiphongtrungtenupdate(String tenlp, int maloai ,int matro){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SELECT_QUERY = "SELECT * FROM " + TB_Loaiphong.TABLENAME + " WHERE " + TB_Loaiphong.TENLOAI + " = '" + tenlp + "' AND "+ TB_Loaiphong.MATRO + " = " + matro + " AND " + TB_Loaiphong.MALOAI + " != " + maloai;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_QUERY,null);
        return cursor.getCount();
    }

    //update Loai phong
    public void updateloaiphong(Loaiphong loaiphong) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Loaiphong.TENLOAI, loaiphong.getmTenloai());
        values.put(TB_Loaiphong.GIALOAI, loaiphong.getmGialoai());
        sqLiteDatabase.update(TB_Loaiphong.TABLENAME, values, TB_Loaiphong.MALOAI + " = ?", new String[]{String.valueOf(loaiphong.getmMaloai())});
        sqLiteDatabase.close();
    }


    // Thêm data table Phongtro
    public void add1Phongtro(Phongtro phongtro) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Phongtro.MALOAI, phongtro.getmMaloai());
        values.put(TB_Phongtro.TENPHONG, phongtro.getmTenphong());
        values.put(TB_Phongtro.KHACHHIENTAI, phongtro.getmKhachhientai());
        values.put(TB_Phongtro.KHACHTOIDA, phongtro.getmKhachtoida());
        sqliteDATA.insert(TB_Phongtro.TABLENAME, null, values);
        sqliteDATA.close();
    }

    // Lấy all data table Phongtro
    public ArrayList<Phongtro> getPhongtrotheomatro(int matro) {
        ArrayList<Phongtro> arrayPhongtro = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Phongtro.TABLENAME + " AS pt Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                + " pt." + TB_Phongtro.MALOAI + " = " + " lp." + TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                + " lp." + TB_Loaiphong.MATRO + " = ttt." + TB_Thongtintro.MATRO + " WHERE ttt." + TB_Thongtintro.MATRO + " = "
                + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                arrayPhongtro.add(new Phongtro(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayPhongtro;
    }

    // check data table Phongtro
    public int checktenphongtro(String tenpt, int maloai ,int matro) {
        ArrayList<Phongtro> arrayPhongtro = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Phongtro.TABLENAME + " AS pt Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                + " pt." + TB_Phongtro.MALOAI + " = " + " lp." + TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                + " lp." + TB_Loaiphong.MATRO + " = ttt." + TB_Thongtintro.MATRO + " WHERE ttt." + TB_Thongtintro.MATRO + " = "
                + matro + " AND " + "pt." + TB_Phongtro.TENPHONG + " = '" + tenpt + "'" + " AND " + "lp." + TB_Loaiphong.MALOAI + " = " + maloai;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        return cursor.getCount();
    }

    // check data table Phongtro update
    public int checktenphongtroupdate(String tenpt, int maphong,int maloai ,int matro) {
        ArrayList<Phongtro> arrayPhongtro = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Phongtro.TABLENAME + " AS pt Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                + " pt." + TB_Phongtro.MALOAI + " = " + " lp." + TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                + " lp." + TB_Loaiphong.MATRO + " = ttt." + TB_Thongtintro.MATRO + " WHERE ttt." + TB_Thongtintro.MATRO + " = "
                + matro + " AND " + "pt." + TB_Phongtro.TENPHONG + " = '" + tenpt + "'" + " AND " + "lp." + TB_Loaiphong.MALOAI + " = " + maloai
                + " AND " + TB_Phongtro.MAPHONG + " != " + maphong;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        return cursor.getCount();
    }

    // Lấy all data table Phongtro
    public ArrayList<Phongtro> getPhongtrotheomatrohavehdcd(int matro, int month, int year) {
        ArrayList<Phongtro> arrayPhongtro = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Phongtro.TABLENAME + " AS pt Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                + " pt." + TB_Phongtro.MALOAI + " = " + " lp." + TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                + " lp." + TB_Loaiphong.MATRO + " = ttt." + TB_Thongtintro.MATRO
                + " JOIN " + TB_Hoadon.TABLENAME + " AS hd ON " + "pt." + TB_Phongtro.MAPHONG + " = " + "hd." + TB_Hoadon.MAPHONG
                + " WHERE ttt." + TB_Thongtintro.MATRO + " = "
                + matro
                + " AND "
                + " hd." + TB_Hoadon.MAPHONG + " > " + 0
                + " AND "
                + "hd." + TB_Hoadon.THANG + " = " + month
                + " AND "
                + "hd." + TB_Hoadon.NAM + " = " + year
                + " AND "
                + "hd." + TB_Hoadon.TRANGTHAI + " = " + 0;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                arrayPhongtro.add(new Phongtro(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayPhongtro;
    }

    // Lấy all data table Phongtro
    public ArrayList<Phongtro> getPhongtrotheomatrohavehddd(int matro, int month, int year) {
        ArrayList<Phongtro> arrayPhongtro = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Phongtro.TABLENAME + " AS pt Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                + " pt." + TB_Phongtro.MALOAI + " = " + " lp." + TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                + " lp." + TB_Loaiphong.MATRO + " = ttt." + TB_Thongtintro.MATRO
                + " JOIN " + TB_Hoadon.TABLENAME + " AS hd ON " + "pt." + TB_Phongtro.MAPHONG + " = " + "hd." + TB_Hoadon.MAPHONG
                + " WHERE ttt." + TB_Thongtintro.MATRO + " = "
                + matro
                + " AND "
                + " hd." + TB_Hoadon.MAPHONG + " > " + 0
                + " AND "
                + "hd." + TB_Hoadon.THANG + " = " + month
                + " AND "
                + "hd." + TB_Hoadon.NAM + " = " + year
                + " AND "
                + "hd." + TB_Hoadon.TRANGTHAI + " = " + 1;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                arrayPhongtro.add(new Phongtro(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayPhongtro;
    }

    // Lấy all data table Phongtro
    public ArrayList<Phongtro> getPhongtrotheomaloai(int maloai) {
        ArrayList<Phongtro> arrayPhongtro = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Phongtro.TABLENAME + " WHERE " + TB_Phongtro.MALOAI + " = " + maloai;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                arrayPhongtro.add(new Phongtro(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayPhongtro;
    }

    //Lấy 1 phòng trọ để sửa thông tin
    public Phongtro get1Phongtrotheomaphong(int maphong) {
        Phongtro phongtro = null;
        String SELECT_QUERY = "SELECT * FROM " + TB_Phongtro.TABLENAME + " WHERE " + TB_Phongtro.MAPHONG + " = " + maphong;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            phongtro = new Phongtro(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4));
        }
        sqLiteDATA.close();
        return phongtro;
    }


    //Lay so luong khach hien tai
    public int getKhachhientaicua1phongtro(int maphong) {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TB_Khachhang.TABLENAME + " WHERE " + TB_Khachhang.MAPHONG + " = " + maphong + " AND " + TB_Khachhang.ISDELETE + " = 0");
        return (int) count;
    }

    //them sl khach hien tai vao database
    public void themkhachhientai(Phongtro phongtro) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = phongtro.getmMaphong();
        int slkhachht = getKhachhientaicua1phongtro(id);
        ContentValues values = new ContentValues();
        values.put(TB_Phongtro.KHACHHIENTAI, slkhachht);
        db.update(TB_Phongtro.TABLENAME, values, TB_Phongtro.MAPHONG + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Lay so luong loai phong hien tai cua 1 nha tro theo ma tro
    public int getSoluongloaiphongcua1nhatro(int matro) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Loaiphong> arLoaiphong = MainActivity.dataSQLite.getLoaiphongtheomatro(matro);
        return arLoaiphong.size();
    }

    //Lay sl phong hien tai cua 1 nha tro
    public int getSlPhongtrocua1nhatro(int matro) {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = 0;
        ArrayList<Loaiphong> arLoaiphong = MainActivity.dataSQLite.getLoaiphongtheomatro(matro);
        for (Loaiphong lp : arLoaiphong) {
            ArrayList<Phongtro> arphongtro = getPhongtrotheomaloai(lp.getmMaloai());
            count = count + arphongtro.size();
        }
        return (int) count;
    }

    //Lay sl khach hien tai cua nha tro
    public int getSlKhachcua1nhatro(int matro) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Loaiphong> arLoaiphong = getLoaiphongtheomatro(matro);
        long soluong = 0;
        for (Loaiphong lp : arLoaiphong) {
            ArrayList<Phongtro> arPhongtro = getPhongtrotheomaloai(lp.getmMaloai());
            for (Phongtro pt : arPhongtro) {
                soluong = soluong + getKhachhientaicua1phongtro(pt.getmMaphong());
            }

        }
        return (int) soluong;
    }

    // Thêm data table Khachhang
    public void add1Khachhang(Khachhang khachhang) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Khachhang.MAPHONG, khachhang.getmMaphong());
        values.put(TB_Khachhang.TEN, khachhang.getmTen());
        values.put(TB_Khachhang.GIOITINH, khachhang.getmGioitinh());
        String ngaysinh = datetoString(khachhang.getmNgaysinh());
        values.put(TB_Khachhang.NGAYSINH, ngaysinh);
        values.put(TB_Khachhang.SDT, khachhang.getmSdt());
        values.put(TB_Khachhang.QUEQUAN, khachhang.getmQuequan());
        values.put(TB_Khachhang.CMND, khachhang.getmCmnd());
        values.put(TB_Khachhang.HINHANH, khachhang.getmHinhanh());
        values.put(TB_Khachhang.ISDELETE, khachhang.getIsDelete());
        sqliteDATA.insert(TB_Khachhang.TABLENAME, null, values);
        sqliteDATA.close();
    }










    // Lấy all data table Khachhang
    public ArrayList<Khachhang> getKhachhang(int matro){
        ArrayList<Khachhang> arrayKhachhang = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM "+TB_Khachhang.TABLENAME +" AS kh Join "+TB_Phongtro.TABLENAME+" AS pt ON "
                +" kh."+TB_Khachhang.MAPHONG + " = " + " pt."+TB_Phongtro.MAPHONG + " Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                +" pt."+TB_Phongtro.MALOAI + " = lp." +TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                +" lp."+TB_Loaiphong.MATRO + " = ttt." +TB_Thongtintro.MATRO + " WHERE ttt."+ TB_Thongtintro.MATRO + " = "
                + matro + " AND " + TB_Khachhang.ISDELETE + " = 0";
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if(cursor.moveToFirst()){

            do{
                arrayKhachhang.add(new Khachhang(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        Stringtodate(cursor.getString(4)),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getBlob(8),
                        cursor.getInt(9)));


            }while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayKhachhang;
    }

    //get khach hang phieu thue
    public ArrayList<Khachhang> getKhachhangphieuthue(int matro){
        ArrayList<Khachhang> arrayKhachhang = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM "+TB_Khachhang.TABLENAME +" AS kh Join "+TB_Phongtro.TABLENAME+" AS pt ON "
                +" kh."+TB_Khachhang.MAPHONG + " = " + " pt."+TB_Phongtro.MAPHONG + " Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                +" pt."+TB_Phongtro.MALOAI + " = lp." +TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                +" lp."+TB_Loaiphong.MATRO + " = ttt." +TB_Thongtintro.MATRO + " WHERE ttt."+ TB_Thongtintro.MATRO + " = "
                + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if(cursor.moveToFirst()){

            do{
                arrayKhachhang.add(new Khachhang(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        Stringtodate(cursor.getString(4)),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getBlob(8),
                        cursor.getInt(9)));


            }while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayKhachhang;
    }

    // Lấy all data table Khachhang
    public ArrayList<Khachhang> getKhachhangtheomaphong(int maphong) {
        ArrayList<Khachhang> arrayKhachhang = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Khachhang.TABLENAME + " WHERE " + TB_Khachhang.MAPHONG + " = " + maphong;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {

            do {
                arrayKhachhang.add(new Khachhang(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        Stringtodate(cursor.getString(4)),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getBlob(8),
                        cursor.getInt(9)));


            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayKhachhang;
    }

    // Lấy all data table khachhang mới nhất
    public Khachhang getkhachhangnew() {
        Khachhang khachhang = null;
        String SELECT_QUERY = "SELECT * FROM " + TB_Khachhang.TABLENAME + " ORDER BY " + TB_Khachhang.MAKHACH + " DESC LIMIT 0, 1";
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            khachhang = new Khachhang(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    Stringtodate(cursor.getString(4)),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getBlob(8),
                    cursor.getInt(9));
        }
        sqLiteDATA.close();
        return khachhang;
    }

    // Lấy all data table khachhang theo ma khach
    public Khachhang getkhachhangtheomakhach(int makhach) {
        Khachhang khachhang = null;
        String SELECT_QUERY = "SELECT * FROM " + TB_Khachhang.TABLENAME + " WHERE " + TB_Khachhang.MAKHACH + " = " + makhach;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            khachhang = new Khachhang(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    Stringtodate(cursor.getString(4)),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getBlob(8),
                    cursor.getInt(9));
        }
        sqLiteDATA.close();
        return khachhang;
    }

    // Lấy all data table Khachhang isdelete
    public ArrayList<Khachhang> getKhachhangisdelete(int matro) {
        ArrayList<Khachhang> arrayKhachhang = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " +TB_Khachhang.TABLENAME +" AS kh Join "+TB_Phongtro.TABLENAME+" AS pt ON "
                +" kh."+TB_Khachhang.MAPHONG + " = " + " pt."+TB_Phongtro.MAPHONG + " Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                +" pt."+TB_Phongtro.MALOAI + " = lp." +TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                +" lp."+TB_Loaiphong.MATRO + " = ttt." +TB_Thongtintro.MATRO + " WHERE ttt."+ TB_Thongtintro.MATRO + " = "
                + matro + " AND " + TB_Khachhang.ISDELETE + " = 1";
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {

            do {
                arrayKhachhang.add(new Khachhang(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        Stringtodate(cursor.getString(4)),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getBlob(8),
                        cursor.getInt(9)));


            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayKhachhang;
    }

    // Thêm data table Traphongtro
    public void add1Traphongtro(Traphongtro traphongtro) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Traphongtro.MAKHACH, traphongtro.getmMakhach());
        String ngaytra = datetoString(traphongtro.getmNgaytra());
        values.put(TB_Traphongtro.NGAYTRA, ngaytra);
        sqliteDATA.insert(TB_Traphongtro.TABLENAME, null, values);
        sqliteDATA.close();
    }

    // Lấy all data table Traphongtro
    public ArrayList<Traphongtro> getTraphongtro(int matro){
        ArrayList<Traphongtro> arrayTraphongtro = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM "+TB_Traphongtro.TABLENAME +" AS tpt JOIN "
                + TB_Khachhang.TABLENAME + " AS kh ON tpt." + TB_Traphongtro.MAKHACH + " = kh." + TB_Khachhang.MAKHACH
                + " Join " +TB_Phongtro.TABLENAME+" AS pt ON "
                +" kh."+TB_Khachhang.MAPHONG + " = " + " pt."+TB_Phongtro.MAPHONG + " Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                +" pt."+TB_Phongtro.MALOAI + " = lp." +TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                +" lp."+TB_Loaiphong.MATRO + " = ttt." +TB_Thongtintro.MATRO + " WHERE ttt."+ TB_Thongtintro.MATRO + " = "
                + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if(cursor.moveToFirst()){
            do{
                int matraphong = cursor.getInt(0);
                int makhach = cursor.getInt(1);
                Date ngaytra = Stringtodate(cursor.getString(2));

                arrayTraphongtro.add(new Traphongtro(matraphong,makhach,ngaytra));
            }while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayTraphongtro;
    }

    // Thêm data table Hoadon
    public void add1Hoadon(Hoadon hoadon) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Hoadon.MAPHONG, hoadon.getmMaphong());
        String ngaythu = datetoString(hoadon.getmNgaythu());
        values.put(TB_Hoadon.NGAYTHU, ngaythu);
        values.put(TB_Hoadon.SODIEN, hoadon.getmSodien());
        values.put(TB_Hoadon.GIADIEN, hoadon.getmGiadien());
        values.put(TB_Hoadon.SONUOC, hoadon.getmSonuoc());
        values.put(TB_Hoadon.GIANUOC, hoadon.getmGianuoc());
        values.put(TB_Hoadon.PHIPHATSINH, hoadon.getmPhiphatsinh());
        values.put(TB_Hoadon.SOPHATSINH, hoadon.getmSophatsinh());
        values.put(TB_Hoadon.TONGTIEN, hoadon.getmTongtien());
        values.put(TB_Hoadon.TRANGTHAI, hoadon.getmTrangthai());
        values.put(TB_Hoadon.GHICHU, hoadon.getmGhichu());
        values.put(TB_Hoadon.THANG, hoadon.getmThang());
        values.put(TB_Hoadon.NAM, hoadon.getmNam());
        sqliteDATA.insert(TB_Hoadon.TABLENAME, null, values);
        sqliteDATA.close();
    }

    // Lấy all data table Hoadon
    //////////////////////////
    ////////////////////////////
    /////////////////////////////
    ///////////////////////////

    // Thêm data table Quanlythue
    public void add1Quanlythue(Quanlythue quanlythue) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Quanlythue.MAKHACH, quanlythue.getmMakhach());
        String ngaythue = datetoString(quanlythue.getmNgaythue());
        values.put(TB_Quanlythue.NGAYTHUE, ngaythue);
        sqliteDATA.insert(TB_Quanlythue.TABLENAME, null, values);
        sqliteDATA.close();
    }

    // Lấy all data table Quanlythue theo ma tro
    public ArrayList<Quanlythue> getQuanlythue(int matro) {
        ArrayList<Quanlythue> arrayQuanlythue = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TB_Quanlythue.TABLENAME + " AS qlt JOIN "
                + TB_Khachhang.TABLENAME + " AS kh ON qlt." + TB_Quanlythue.MAKHACH + " = kh." + TB_Khachhang.MAKHACH
                + " Join " + TB_Phongtro.TABLENAME + " AS pt ON "
                + " kh." + TB_Khachhang.MAPHONG + " = " + " pt." + TB_Phongtro.MAPHONG + " Join " + TB_Loaiphong.TABLENAME + " AS lp ON "
                + " pt." + TB_Phongtro.MALOAI + " = lp." + TB_Loaiphong.MALOAI + " Join " + TB_Thongtintro.TABLENAME + " AS ttt ON "
                + " lp." + TB_Loaiphong.MATRO + " = ttt." + TB_Thongtintro.MATRO + " WHERE ttt." + TB_Thongtintro.MATRO + " = "
                + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                int mathue = cursor.getInt(0);
                int makhach = cursor.getInt(1);
                Date ngaythue = Stringtodate(cursor.getString(2));

                arrayQuanlythue.add(new Quanlythue(mathue, makhach, ngaythue));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return arrayQuanlythue;
    }

    //get quan ly thue theo ma khach
    public Quanlythue getQuanlythuetheomakhach(int makhach){
        Quanlythue quanlythue = null;
        String SELECT_QUERY = "SELECT * FROM " + TB_Quanlythue.TABLENAME + " WHERE " + TB_Quanlythue.MAKHACH + " = " + makhach;

        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            quanlythue = new Quanlythue(
                cursor.getInt(0),
                cursor.getInt(1),
                Stringtodate(cursor.getString(2)));
        }
        sqLiteDATA.close();
        return quanlythue;
    }

    //get phieu tra theo ma khach
    public Traphongtro getphieutratheomakhach(int makhach){
        Traphongtro traphongtro = null;
        String SELECT_QUERY = "SELECT * FROM " + TB_Traphongtro.TABLENAME + " WHERE " + TB_Traphongtro.MAKHACH + " = " + makhach;

        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            traphongtro = new Traphongtro(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    Stringtodate(cursor.getString(2)));
        }
        sqLiteDATA.close();
        return traphongtro;
    }

    //Xoa du lieu tu bang = id
    public void deleteid(String tablename, String field, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tablename, field + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Ham update thong tin kh
    public void updatekhachhang(Khachhang khachhang, int id) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Khachhang.MAPHONG, khachhang.getmMaphong());
        values.put(TB_Khachhang.TEN, khachhang.getmTen());
        values.put(TB_Khachhang.GIOITINH, khachhang.getmGioitinh());
        String ngaysinh = datetoString(khachhang.getmNgaysinh());
        values.put(TB_Khachhang.NGAYSINH, ngaysinh);
        values.put(TB_Khachhang.SDT, khachhang.getmSdt());
        values.put(TB_Khachhang.QUEQUAN, khachhang.getmQuequan());
        values.put(TB_Khachhang.CMND, khachhang.getmCmnd());
        values.put(TB_Khachhang.HINHANH, khachhang.getmHinhanh());
        values.put(TB_Khachhang.ISDELETE, khachhang.getIsDelete());
        sqliteDATA.update(TB_Khachhang.TABLENAME, values, TB_Khachhang.MAKHACH + " = ?", new String[]{String.valueOf(id)});
        sqliteDATA.close();
    }

    public void updateTraphongtrocuakhachhang(Khachhang khachhang){
        SQLiteDatabase db = this.getWritableDatabase();
        int id = khachhang.getmMakhach();
        ContentValues values = new ContentValues();
        values.put(TB_Khachhang.ISDELETE, 1);
        db.update(TB_Khachhang.TABLENAME,values,TB_Khachhang.MAKHACH + " = ?" ,new String[]{String.valueOf(khachhang.getmMakhach())});
        db.close();
    }


    //Ham update phong tro
    public void updatephongtro(Phongtro phongtro, String tablename, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Phongtro.MALOAI, phongtro.getmMaloai());
        values.put(TB_Phongtro.TENPHONG, phongtro.getmTenphong());
        values.put(TB_Phongtro.KHACHHIENTAI, phongtro.getmKhachhientai());
        values.put(TB_Phongtro.KHACHTOIDA, phongtro.getmKhachtoida());
        db.update(tablename, values, TB_Phongtro.MAPHONG + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


    public int counthoadontheomaphong(int maphong, int month, int year) {
        String SELECT_QUERY = "SELECT * FROM "
                + TB_Hoadon.TABLENAME + " WHERE "
                + TB_Hoadon.THANG + " = " + month
                + " AND "
                + TB_Hoadon.NAM + " = " + year
                + " AND "
                + TB_Hoadon.MAPHONG + " = " + maphong;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        return cursor.getCount();
    }

    public Hoadon gethoadontheomaphongnew(int maphong, int month, int year) {
        Hoadon hoadon = null;
        String SELECT_QUERY = "SELECT * FROM "
                + TB_Hoadon.TABLENAME + " WHERE "
                + TB_Hoadon.THANG + " = " + month
                + " AND "
                + TB_Hoadon.NAM + " = " + year
                + " AND "
                + TB_Hoadon.MAPHONG + " = " + maphong;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            hoadon = new Hoadon(cursor.getInt(0),
                    cursor.getInt(1),
                    Stringtodate(cursor.getString(2)),
                    cursor.getInt(3),
                    cursor.getDouble(4),
                    cursor.getInt(5),
                    cursor.getDouble(6),
                    cursor.getDouble(7),
                    cursor.getInt(8),
                    cursor.getDouble(9),
                    cursor.getInt(10),
                    cursor.getString(11),
                    cursor.getInt(12),
                    cursor.getInt(13));
        }
        sqLiteDATA.close();
        return hoadon;
    }

    public ArrayList<Hoadon> gethoadontheomatrochuadong(int matro, int month, int year) {
        ArrayList<Hoadon> hoadon = new ArrayList<>(

        );
        String SELECT_QUERY = "SELECT * FROM "
                + TB_Hoadon.TABLENAME + " AS hd JOIN "
                + TB_Phongtro.TABLENAME + " AS pt ON " + "hd." + TB_Hoadon.MAPHONG + " = " + "pt." + TB_Phongtro.MAPHONG
                + " JOIN " + TB_Loaiphong.TABLENAME + " AS lp ON " + "pt." + TB_Phongtro.MALOAI + " = " + "lp." + TB_Loaiphong.MALOAI
                + " JOIN " + TB_Thongtintro.TABLENAME + " AS ttt ON " + "lp." + TB_Loaiphong.MATRO + " = " + "lp." + TB_Thongtintro.MATRO
                + " WHERE "
                + "hd." + TB_Hoadon.THANG + " = " + month
                + " AND "
                + "hd." + TB_Hoadon.NAM + " = " + year
                + " AND "
                + "hd." + TB_Hoadon.TRANGTHAI + " = " + 0
                + " AND "
                + "ttt." + TB_Thongtintro.MATRO + " = " + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                hoadon.add(new Hoadon(cursor.getInt(0),
                        cursor.getInt(1),
                        Stringtodate(cursor.getString(2)),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getInt(5),
                        cursor.getDouble(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        cursor.getDouble(9),
                        cursor.getInt(10),
                        cursor.getString(11),
                        cursor.getInt(12),
                        cursor.getInt(13)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return hoadon;
    }

    public ArrayList<Hoadon> gethoadontheomatrodadong(int matro, int month, int year) {
        ArrayList<Hoadon> hoadon = new ArrayList<>(

        );
        String SELECT_QUERY = "SELECT * FROM "
                + TB_Hoadon.TABLENAME + " AS hd JOIN "
                + TB_Phongtro.TABLENAME + " AS pt ON " + "hd." + TB_Hoadon.MAPHONG + " = " + "pt." + TB_Phongtro.MAPHONG
                + " JOIN " + TB_Loaiphong.TABLENAME + " AS lp ON " + "pt." + TB_Phongtro.MALOAI + " = " + "lp." + TB_Loaiphong.MALOAI
                + " JOIN " + TB_Thongtintro.TABLENAME + " AS ttt ON " + "lp." + TB_Loaiphong.MATRO + " = " + "lp." + TB_Thongtintro.MATRO
                + " WHERE "
                + "hd." + TB_Hoadon.THANG + " = " + month
                + " AND "
                + "hd." + TB_Hoadon.NAM + " = " + year
                + " AND "
                + "hd." + TB_Hoadon.TRANGTHAI + " = " + 1
                + " AND "
                + "ttt." + TB_Thongtintro.MATRO + " = " + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                hoadon.add(new Hoadon(cursor.getInt(0),
                        cursor.getInt(1),
                        Stringtodate(cursor.getString(2)),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getInt(5),
                        cursor.getDouble(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        cursor.getDouble(9),
                        cursor.getInt(10),
                        cursor.getString(11),
                        cursor.getInt(12),
                        cursor.getInt(13)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return hoadon;
    }

    //Ham update thong tin kh
    public void updatehoadon(Hoadon hoadon, int id) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Hoadon.MAPHONG, hoadon.getmMaphong());
        values.put(TB_Hoadon.SODIEN, hoadon.getmSodien());
        values.put(TB_Hoadon.GIADIEN, hoadon.getmGiadien());
        values.put(TB_Hoadon.SONUOC, hoadon.getmSonuoc());
        values.put(TB_Hoadon.GIANUOC, hoadon.getmGianuoc());
        values.put(TB_Hoadon.PHIPHATSINH, hoadon.getmPhiphatsinh());
        values.put(TB_Hoadon.SOPHATSINH, hoadon.getmSophatsinh());
        values.put(TB_Hoadon.TONGTIEN, hoadon.getmTongtien());
        values.put(TB_Hoadon.GHICHU, hoadon.getmGhichu());
        sqliteDATA.update(TB_Hoadon.TABLENAME, values, TB_Hoadon.MAHD + " = ?", new String[]{String.valueOf(id)});
        sqliteDATA.close();
    }

    //Ham update thong tin kh
    public void updatestatushoadon(Date ngaythu, int status, int id) {
        SQLiteDatabase sqliteDATA = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_Hoadon.NGAYTHU, datetoString(ngaythu));
        values.put(TB_Hoadon.TRANGTHAI, status);
        sqliteDATA.update(TB_Hoadon.TABLENAME, values, TB_Hoadon.MAHD + " = ?", new String[]{String.valueOf(id)});
        sqliteDATA.close();
    }

    public ArrayList<Hoadon> gethoadontheomatrotk(int matro, int year) {
        ArrayList<Hoadon> hoadon = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM "
                + TB_Hoadon.TABLENAME + " AS hd JOIN "
                + TB_Phongtro.TABLENAME + " AS pt ON " + "hd." + TB_Hoadon.MAPHONG + " = " + "pt." + TB_Phongtro.MAPHONG
                + " JOIN " + TB_Loaiphong.TABLENAME + " AS lp ON " + "pt." + TB_Phongtro.MALOAI + " = " + "lp." + TB_Loaiphong.MALOAI
                + " JOIN " + TB_Thongtintro.TABLENAME + " AS ttt ON " + "lp." + TB_Loaiphong.MATRO + " = " + "lp." + TB_Thongtintro.MATRO
                + " WHERE "
                + "hd." + TB_Hoadon.NAM + " = " + year
                + " AND "
                + "hd." + TB_Hoadon.TRANGTHAI + " = " + 1
                + " AND "
                + "ttt." + TB_Thongtintro.MATRO + " = " + matro;
        SQLiteDatabase sqLiteDATA = this.getReadableDatabase();
        Cursor cursor = sqLiteDATA.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                hoadon.add(new Hoadon(cursor.getInt(0),
                        cursor.getInt(1),
                        Stringtodate(cursor.getString(2)),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getInt(5),
                        cursor.getDouble(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        cursor.getDouble(9),
                        cursor.getInt(10),
                        cursor.getString(11),
                        cursor.getInt(12),
                        cursor.getInt(13)));
            } while (cursor.moveToNext());
        }
        sqLiteDATA.close();
        return hoadon;
    }
}

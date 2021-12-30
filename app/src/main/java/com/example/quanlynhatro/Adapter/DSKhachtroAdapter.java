package com.example.quanlynhatro.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.Khachtro.DanhsachKhachtro;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Quanlythue;

import java.util.ArrayList;
import java.util.Locale;

public class DSKhachtroAdapter extends BaseAdapter {

    private ArrayList<Khachhang> arrKhachhang;
    private ArrayList<Phongtro> arPhongtro;
    private ArrayList<Khachhang> arKH;
    private DanhsachKhachtro danhsachKhachtro;
    private int layout;

    public DSKhachtroAdapter(ArrayList<Khachhang> arrKhachhang, ArrayList<Phongtro> arPhongtro, DanhsachKhachtro danhsachKhachtro, int layout) {
        this.arrKhachhang = arrKhachhang;
        this.arPhongtro = arPhongtro;
        this.danhsachKhachtro = danhsachKhachtro;
        this.layout = layout;
        this.arKH = new ArrayList<Khachhang>();
        this.arKH.addAll(arrKhachhang);
    }

    @Override
    public int getCount() {
        return arrKhachhang.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTenKhachtro;
        TextView txtPhongtro;
        ImageView btnXoaKhach;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) danhsachKhachtro.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtTenKhachtro = (TextView) convertView.findViewById(R.id.txtTenKhachhang);
            viewHolder.txtPhongtro = (TextView) convertView.findViewById(R.id.txtPhongtro);
            viewHolder.btnXoaKhach = (ImageView) convertView.findViewById(R.id.btnDeleteKhachhang);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DSKhachtroAdapter.ViewHolder) convertView.getTag();
        }

        final Khachhang khachhang = arrKhachhang.get(position);
        viewHolder.txtTenKhachtro.setText(khachhang.getmTen()+"");
        for (Phongtro phongtro : arPhongtro){
            if(phongtro.getmMaphong() == khachhang.getmMaphong()){
                viewHolder.txtPhongtro.setText(phongtro.getmTenphong()+"");
                break;
            }
        }

        //nut xoa khach
        viewHolder.btnXoaKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(danhsachKhachtro);
                alertDialog.setTitle("! Thông báo xóa");
                alertDialog.setMessage("Bạn muốn xóa khách hàng này ?");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.dataSQLite.deleteid(TB_Khachhang.TABLENAME, TB_Khachhang.MAKHACH, khachhang.getmMakhach());
                        MainActivity.dataSQLite.deleteid(TB_Quanlythue.TABLENAME,TB_Quanlythue.MAKHACH,khachhang.getmMakhach());
                        Phongtro phongtro = MainActivity.dataSQLite.get1Phongtrotheomaphong(khachhang.getmMaphong());
                        MainActivity.dataSQLite.themkhachhientai(phongtro);
                        danhsachKhachtro.deletekhachtro();
                        Toast.makeText(danhsachKhachtro, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();


            }
        });

//        viewHolder.layoutKhachhangLV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(danhsachKhachtro, ThongtinkhachtroInf.class);
//                intent.putExtra("makhach", khachhang.getmMakhach());
//                danhsachKhachtro.startActivity(intent);
//            }
//        });

        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        arrKhachhang.clear();
        if(charText.length() == 0){
            arrKhachhang.addAll(arKH);
        }else{
            for(Khachhang khachhang : arKH){
                if(khachhang.getmTen().toLowerCase(Locale.getDefault()).contains(charText)){
                    arrKhachhang.add(khachhang);
                }
            }
        }
        notifyDataSetChanged();
    }
}

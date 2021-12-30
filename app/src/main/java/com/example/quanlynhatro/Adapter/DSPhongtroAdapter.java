package com.example.quanlynhatro.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Phongtro.DanhsachPhongtro;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Hoadon;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Loaiphong;
import com.example.quanlynhatro.SQLite.ModelData.TB_Phongtro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Quanlythue;
import com.example.quanlynhatro.SQLite.ModelData.TB_Thongtintro;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DSPhongtroAdapter extends BaseAdapter{
    private ArrayList <Phongtro> arrayphongtro;
    private DanhsachPhongtro contextDanhsachphongtro;
    private int layout;
    private ArrayList<Phongtro> arPT;
    private ArrayList<Loaiphong> arrayloaiphong;

    public DSPhongtroAdapter(ArrayList<Phongtro> arrayphongtro, DanhsachPhongtro contextDanhsachphongtro, int layout, ArrayList<Loaiphong> arrayloaiphong) {
        this.arrayphongtro = arrayphongtro;
        this.contextDanhsachphongtro = contextDanhsachphongtro;
        this.layout = layout;
        this.arrayloaiphong = arrayloaiphong;
        this.arPT = new ArrayList<Phongtro>();
        this.arPT.addAll(arrayphongtro);
    }

    @Override
    public int getCount() {
        return arrayphongtro.size();
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
        TextView txtTenphongtro;
        TextView txtLoaiphongtro;
        ImageView btnDeletePhongtro;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DSPhongtroAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) contextDanhsachphongtro.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtTenphongtro = (TextView) convertView.findViewById(R.id.txtTenPhongtro);
            viewHolder.txtLoaiphongtro = (TextView) convertView.findViewById(R.id.txtLoaiPhongtro);
            viewHolder.btnDeletePhongtro = (ImageView) convertView.findViewById(R.id.btnDeletePhongtro);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DSPhongtroAdapter.ViewHolder) convertView.getTag();
        }

        final Phongtro phongtro = arrayphongtro.get(position);
        for(Loaiphong a : arrayloaiphong){
            if(a.getmMaloai() == phongtro.getmMaloai()){
                viewHolder.txtLoaiphongtro.setText(a.getmTenloai()+"");
                break;
            }
        }
        viewHolder.txtTenphongtro.setText(phongtro.getmTenphong()+"");
        viewHolder.btnDeletePhongtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(contextDanhsachphongtro);
                alertDialog.setTitle("Thông báo xóa!!");
                alertDialog.setMessage("Bạn muốn xóa phòng trọ này ? \n\nLƯU Ý: Nếu xóa phòng trọ này, tất cả khách trọ, phiếu thuê của khách trọ thuộc phòng, hóa đơn sẽ đồng loạt bị xóa!!!");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.dataSQLite.deleteid(TB_Phongtro.TABLENAME, TB_Phongtro.MAPHONG, phongtro.getmMaphong());
                        MainActivity.dataSQLite.deleteid(TB_Hoadon.TABLENAME,TB_Hoadon.MAPHONG,phongtro.getmMaphong());
                        ArrayList<Khachhang> arKhachhangxoa = MainActivity.dataSQLite.getKhachhangtheomaphong(phongtro.getmMaphong());
                        for(Khachhang kh : arKhachhangxoa){
                            MainActivity.dataSQLite.deleteid(TB_Quanlythue.TABLENAME,TB_Quanlythue.MAKHACH,kh.getmMakhach());
                        }
                        MainActivity.dataSQLite.deleteid(TB_Khachhang.TABLENAME,TB_Khachhang.MAPHONG,phongtro.getmMaphong());
                        Toast.makeText(contextDanhsachphongtro, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
                        contextDanhsachphongtro.DeletePhongtro();
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


        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        arrayphongtro.clear();
        if(charText.length() == 0){
            arrayphongtro.addAll(arPT);
        }else{
            for(Phongtro phongtro : arPT){
                if(phongtro.getmTenphong().toLowerCase(Locale.getDefault()).contains(charText)){
                    arrayphongtro.add(phongtro);
                }
            }
        }
        notifyDataSetChanged();
    }
}

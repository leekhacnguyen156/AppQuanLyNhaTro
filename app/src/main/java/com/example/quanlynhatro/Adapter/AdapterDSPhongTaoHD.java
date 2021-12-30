package com.example.quanlynhatro.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.quanlynhatro.Activity.HomeMain;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Nhatro.NhatroActivity;
import com.example.quanlynhatro.Activity.Phongtro.DanhsachPhongtro;
import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.HoaDonView;
import com.example.quanlynhatro.Activity.Thutien.TaoHD;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Hoadon;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Loaiphong;
import com.example.quanlynhatro.SQLite.ModelData.TB_Phongtro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Quanlythue;
import com.example.quanlynhatro.SQLite.ModelData.TB_Traphongtro;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterDSPhongTaoHD extends BaseAdapter {
    private ArrayList<Phongtro> arrayphongtro;
    private Context context;
    private int layout;
    private int month;
    private int year;
    private ArrayList<Phongtro> arPTT;

    public AdapterDSPhongTaoHD(ArrayList<Phongtro> arrayphongtro, Context context, int layout, int month, int year) {
        this.arrayphongtro = arrayphongtro;
        this.context = context;
        this.layout = layout;
        this.month = month;
        this.year = year;
        this.arPTT = new ArrayList<Phongtro>();
        this.arPTT.addAll(arrayphongtro);
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
        CardView cardviewPTTHD;
        TextView txtTenPhongtroTaohd;
        TextView txtSoluongkhach;
        TextView txtStatusP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtTenPhongtroTaohd = (TextView) convertView.findViewById(R.id.txtTenPhongtroTaohd);
            viewHolder.txtSoluongkhach = (TextView) convertView.findViewById(R.id.txtSoluongkhach);
            viewHolder.txtStatusP = (TextView) convertView.findViewById(R.id.txtStatusP);
            viewHolder.cardviewPTTHD = (CardView) convertView.findViewById(R.id.cardviewPTTHD);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Phongtro phongtro = arrayphongtro.get(position);
        final int ihd = MainActivity.dataSQLite.counthoadontheomaphong(phongtro.getmMaphong(), month, year);
        viewHolder.txtTenPhongtroTaohd.setText(phongtro.getmTenphong());
        viewHolder.txtSoluongkhach.setText("Số lượng khách: " + phongtro.getmKhachhientai()+"");
        if (ihd > 0){
            viewHolder.txtStatusP.setText("Đã tạo");
            viewHolder.txtStatusP.setBackgroundResource(R.drawable.custom_status_success);
        }else{
            viewHolder.txtStatusP.setText("Chưa tạo");
            viewHolder.txtStatusP.setBackgroundResource(R.drawable.custom_status_fail);
        }
        viewHolder.cardviewPTTHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ihd > 0){
                    Intent intent = new Intent(context , HoaDonView.class);
                    Bundle extras = new Bundle();
                    extras.putInt("maloai",phongtro.getmMaloai());
                    extras.putInt("maphong", phongtro.getmMaphong());
                    intent.putExtras(extras);
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context , TaoHD.class);
                    Bundle extras = new Bundle();
                    extras.putInt("maloai",phongtro.getmMaloai());
                    extras.putInt("maphong", phongtro.getmMaphong());
                    intent.putExtras(extras);
                    context.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        arrayphongtro.clear();
        if (charText.length() == 0) {
            arrayphongtro.addAll(arPTT);
        } else {
            for (Phongtro phongtro : arPTT) {
                if (phongtro.getmTenphong().toLowerCase(Locale.getDefault()).contains(charText)) {
                    arrayphongtro.add(phongtro);
                }
            }
        }
        notifyDataSetChanged();
    }
}

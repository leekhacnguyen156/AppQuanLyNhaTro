package com.example.quanlynhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Quanlythue;
import com.example.quanlynhatro.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class AdapterDSPhieuthue extends BaseAdapter {
    private ArrayList<Quanlythue> arQuanlythue;
    private ArrayList<Khachhang> arKhachhang;
    private int layout;
    private Context context;
    private ArrayList<Khachhang> arKH;
    private static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private static String datetoString (Date date){
        return df.format(date);
    }

    public AdapterDSPhieuthue(ArrayList<Khachhang> arKhachhang,Context context, int layout){
        this.arKhachhang = arKhachhang;
        this.context = context;
        this.layout = layout;
        this.arKH = new ArrayList<Khachhang>();
        this.arKH.addAll(arKhachhang);
    }

    @Override
    public int getCount() {
        return arKhachhang.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            viewHolder.txtTenkhachthue = (TextView) convertView.findViewById(R.id.txtTenKhachthue);
            viewHolder.txtNgaythue = (TextView) convertView.findViewById(R.id.txtNgaythue);
           // viewHolder.imgisDelete = (ImageView) convertView.findViewById(R.id.imgisdelete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (AdapterDSPhieuthue.ViewHolder) convertView.getTag();
        }

        final Khachhang khachhang = arKhachhang.get(position);
        final Quanlythue quanlythue = MainActivity.dataSQLite.getQuanlythuetheomakhach(khachhang.getmMakhach());
//        final Quanlythue quanlythue = arQuanlythue.get(position);
//        final Khachhang khachhang = MainActivity.dataSQLite.getkhachhangtheomakhach(quanlythue.getmMakhach());
        viewHolder.txtTenkhachthue.setText(khachhang.getmTen()+"");
        viewHolder.txtNgaythue.setText(datetoString(quanlythue.getmNgaythue()));
        return convertView;
    }

    private class ViewHolder{
        TextView txtTenkhachthue;
        TextView txtNgaythue;
        //ImageView imgisDelete;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        arKhachhang.clear();
        if(charText.length() == 0){
            arKhachhang.addAll(arKH);
        }else{
            for(Khachhang khachhang : arKH){
                if(khachhang.getmTen().toLowerCase(Locale.getDefault()).contains(charText)){
                    arKhachhang.add(khachhang);
                }
            }
        }
        notifyDataSetChanged();
    }
}

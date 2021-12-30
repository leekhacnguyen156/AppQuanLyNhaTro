package com.example.quanlynhatro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.HoaDonView;
import com.example.quanlynhatro.Activity.Thutien.TaoHD;
import com.example.quanlynhatro.ModelApp.Hoadon;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterStatusHoaDon extends BaseAdapter {

    private ArrayList<Hoadon> arrHoadon;
    private Context context;
    private int layout;
    private ArrayList<Phongtro> arPT;
    private  ArrayList<Phongtro> arrPhongtro;

    public AdapterStatusHoaDon(ArrayList<Hoadon> arrHoadon, Context context, int layout, ArrayList<Phongtro> arrPhongtro) {
        this.arrHoadon = arrHoadon;
        this.context = context;
        this.layout = layout;
        this.arrPhongtro = arrPhongtro;
        this.arPT = new ArrayList<Phongtro>();
        this.arPT.addAll(arrPhongtro);
    }

    @Override
    public int getCount() {
        return arrPhongtro.size();
    }

    private class ViewHolder {
        CardView cardviewPTTCD;
        TextView txtTenPhongtroCD;
        TextView txtGiaPhong;
        TextView txtStatusHD;
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtTenPhongtroCD = (TextView) convertView.findViewById(R.id.txtTenPhongtroCD);
            viewHolder.txtGiaPhong = (TextView) convertView.findViewById(R.id.txtGiaPhong);
            viewHolder.txtStatusHD = (TextView) convertView.findViewById(R.id.txtStatusHD);
            viewHolder.cardviewPTTCD = (CardView) convertView.findViewById(R.id.cardviewPTTCD);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Phongtro phongtro = arrPhongtro.get(position);
        viewHolder.txtTenPhongtroCD.setText(phongtro.getmTenphong());

        for (Hoadon hoadon : arrHoadon){
            if(hoadon.getmMaphong() == phongtro.getmMaphong()){
                if(hoadon.getmTrangthai() == 0){
                    viewHolder.txtStatusHD.setBackgroundResource(R.drawable.custom_status_fail);
                    viewHolder.txtStatusHD.setText("Chưa đóng");
                }else{
                    viewHolder.txtStatusHD.setBackgroundResource(R.drawable.custom_status_success);
                    viewHolder.txtStatusHD.setText("Đã đóng");
                }
                viewHolder.txtGiaPhong.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmTongtien()));
                break;
            }
        }

        viewHolder.cardviewPTTCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , HoaDonView.class);
                Bundle extras = new Bundle();
                extras.putInt("maloai", phongtro.getmMaloai());
                extras.putInt("maphong", phongtro.getmMaphong());
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        arrPhongtro.clear();
        if (charText.length() == 0) {
            arrPhongtro.addAll(arPT);
        } else {
            for (Phongtro phongtro : arPT) {
                if (phongtro.getmTenphong().toLowerCase(Locale.getDefault()).contains(charText)) {
                    arrPhongtro.add(phongtro);
                }
            }
        }
        notifyDataSetChanged();
    }
}

package com.example.quanlynhatro.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.Khachtro.ThongtinkhachtroInf;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.TaoNhatro.Thietlaploaiphong;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Traphongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Loaiphong;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DSLoaiphongAdapter extends BaseAdapter {

    private ArrayList<Loaiphong> arrayLoaiphong;
    private Thietlaploaiphong contexThietlaploaiphong;
    private int layout;


    public DSLoaiphongAdapter(ArrayList<Loaiphong> arrayLoaiphong, Thietlaploaiphong contexThietlaploaiphong, int layout) {
        this.arrayLoaiphong = arrayLoaiphong;
        this.contexThietlaploaiphong = contexThietlaploaiphong;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayLoaiphong.size();
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
        TextView txtTenLoaiphong;
        TextView txtGiaLoaiphong;
        ImageButton btnDeleteLoaiphong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) contexThietlaploaiphong.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtTenLoaiphong = (TextView) convertView.findViewById(R.id.txtTenLoaiphong);
            viewHolder.txtGiaLoaiphong = (TextView) convertView.findViewById(R.id.txtGiaLoaiphong);
            viewHolder.btnDeleteLoaiphong = (ImageButton) convertView.findViewById(R.id.btnDeleteLoaiphong);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Loaiphong loaiphong = arrayLoaiphong.get(position);
        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((loaiphong.getmGialoai()));

        viewHolder.txtTenLoaiphong.setText(loaiphong.getmTenloai());
        viewHolder.txtGiaLoaiphong.setText(formated + " VNĐ");

        viewHolder.btnDeleteLoaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(contexThietlaploaiphong);
                alertDialog.setTitle("Thông báo!").setMessage("Bạn có chắc xóa loại phòng này?");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.dataSQLite.deleteid(TB_Loaiphong.TABLENAME, TB_Loaiphong.MALOAI, loaiphong.getmMaloai());
                        contexThietlaploaiphong.DeleteLoaiPhong();
                        Toast.makeText(contexThietlaploaiphong, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });

        return convertView;
    }
}

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

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Nhatro.DanhsachNhatro;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Hoadon;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Loaiphong;
import com.example.quanlynhatro.SQLite.ModelData.TB_Phongtro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Quanlythue;
import com.example.quanlynhatro.SQLite.ModelData.TB_Thongtintro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Traphongtro;

import java.util.ArrayList;

public class DSNhatroAdapter extends BaseAdapter {
    private ArrayList<Thongtintro> arrayThongtintro;
    private DanhsachNhatro danhSachNhatro;
    private int layout;

    public DSNhatroAdapter(ArrayList<Thongtintro> arrayThongtintro, DanhsachNhatro danhSachNhatro, int layout) {
        this.arrayThongtintro = arrayThongtintro;
        this.danhSachNhatro = danhSachNhatro;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayThongtintro.size();
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
        TextView txtTentro;
        TextView txtDiachi;
        ImageView btnDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) danhSachNhatro.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtTentro = (TextView) convertView.findViewById(R.id.txtTenTro);
            viewHolder.txtDiachi = (TextView) convertView.findViewById(R.id.txtDiachi);
            viewHolder.btnDelete = (ImageView) convertView.findViewById(R.id.deleteNhatro);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DSNhatroAdapter.ViewHolder) convertView.getTag();
        }

        final Thongtintro thongtintro = arrayThongtintro.get(position);

        viewHolder.txtTentro.setText(thongtintro.getmTentro()+"");
        viewHolder.txtDiachi.setText(thongtintro.getmDiachi()+"");
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(danhSachNhatro);
                alertDialog.setTitle("! Thông báo xóa");
                alertDialog.setMessage("Bạn muốn xóa nhà trọ này ?\nDữ liệu của toàn bộ nhà trọ sẽ mất !");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Loaiphong> arLoaiphong = MainActivity.dataSQLite.getLoaiphongtheomatro(thongtintro.getmMatro());
                        ArrayList<Khachhang> arKhachhang = MainActivity.dataSQLite.getKhachhang(thongtintro.getmMatro());
                        ArrayList<Phongtro> arPhongtro = MainActivity.dataSQLite.getPhongtrotheomatro(thongtintro.getmMatro());
                        for(Khachhang a : arKhachhang){
                               MainActivity.dataSQLite.deleteid(TB_Traphongtro.TABLENAME,TB_Traphongtro.MAKHACH,a.getmMakhach());
                               MainActivity.dataSQLite.deleteid(TB_Quanlythue.TABLENAME,TB_Quanlythue.MAKHACH,a.getmMakhach());
                        }

                        for(Phongtro a : arPhongtro){
                            MainActivity.dataSQLite.deleteid(TB_Khachhang.TABLENAME,TB_Khachhang.MAPHONG,a.getmMaphong());
                            MainActivity.dataSQLite.deleteid(TB_Hoadon.TABLENAME,TB_Hoadon.MAPHONG,a.getmMaphong());
                        }

                        for(Loaiphong a : arLoaiphong){
                           MainActivity.dataSQLite.deleteid(TB_Phongtro.TABLENAME,TB_Phongtro.MALOAI,a.getmMaloai());
                        }
                        MainActivity.dataSQLite.deleteid(TB_Loaiphong.TABLENAME,TB_Loaiphong.MATRO,thongtintro.getmMatro());
                        MainActivity.dataSQLite.deleteid(TB_Thongtintro.TABLENAME, TB_Thongtintro.MATRO, thongtintro.getmMatro());
                        danhSachNhatro.Deletenhatro();
                        Toast.makeText(danhSachNhatro, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
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
}

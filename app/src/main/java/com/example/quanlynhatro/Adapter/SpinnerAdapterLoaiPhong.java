package com.example.quanlynhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.R;

import java.util.List;

public class SpinnerAdapterLoaiPhong extends ArrayAdapter<Loaiphong> {

    LayoutInflater layoutInflater;

    public SpinnerAdapterLoaiPhong(@NonNull Context context, int resource, @NonNull List<Loaiphong> loaiphongs) {
        super(context, resource, loaiphongs);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.spinner_line_loaiphong, null, true);
        Loaiphong loaiphong = getItem(position);
        TextView txtTenLoaiPhongSpinner = (TextView) rowView.findViewById(R.id.txtTenLoaiPhongSpinner);
        txtTenLoaiPhongSpinner.setText(loaiphong.getmTenloai());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.spinner_line_loaiphong, null, true);
        }
        Loaiphong loaiphong = getItem(position);
        TextView txtTenLoaiPhongSpinner = (TextView) convertView.findViewById(R.id.txtTenLoaiPhongSpinner);
        txtTenLoaiPhongSpinner.setText(loaiphong.getmTenloai());
        return convertView;
    }
}

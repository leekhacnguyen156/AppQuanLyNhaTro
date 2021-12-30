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
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;

import java.util.List;

public class SpinnerAdapterPhongtro extends ArrayAdapter<Phongtro> {

    LayoutInflater layoutInflater;

    public SpinnerAdapterPhongtro(@NonNull Context context, int resource, @NonNull List<Phongtro> phongtros) {
        super(context, resource, phongtros);
        layoutInflater = LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.spinner_line_phongtro, null, true);
        Phongtro phongtro = getItem(position);
        TextView txtTenPhongTroSpinner = (TextView) rowView.findViewById(R.id.txtTenPhongTroSpinner);
        txtTenPhongTroSpinner.setText(phongtro.getmTenphong());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.spinner_line_phongtro, null, true);
        }
        Phongtro phongtro = getItem(position);
        TextView txtTenPhongTroSpinner = (TextView) convertView.findViewById(R.id.txtTenPhongTroSpinner);
        txtTenPhongTroSpinner.setText(phongtro.getmTenphong());
        return convertView;
    }
}

package com.example.quanlynhatro.Activity.ThueTraPhong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Adapter.AdapterDSPhieuthue;
import com.example.quanlynhatro.Adapter.AdapterDSPhieutra;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Quanlythue;
import com.example.quanlynhatro.ModelApp.Traphongtro;
import com.example.quanlynhatro.R;

import java.util.ArrayList;
import java.util.Collections;

public class Fragment_Tra extends Fragment {
    private View view;
    public static ListView listView;
    private SearchView searchView;
    private ArrayList<Khachhang> arKhachhang;
    public static AdapterDSPhieutra adapterDSPhieutra ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_tra, container, false);
        }
        Anhxa();
        SetAdapterTraphongtro();
        return view;
    }

    private void SetAdapterTraphongtro() {
        arKhachhang = MainActivity.dataSQLite.getKhachhangisdelete(ThueTraPhong.matro);
        Collections.reverse(arKhachhang);
        adapterDSPhieutra = new AdapterDSPhieutra(arKhachhang,getContext(),R.layout.line_phieutra);
        listView.setAdapter(adapterDSPhieutra);
        adapterDSPhieutra.notifyDataSetChanged();

    }

    private void Anhxa() {
        listView = (ListView) view.findViewById(R.id.listPhongTra);
        searchView = (SearchView) view.findViewById(R.id.searchPT);
    }
}

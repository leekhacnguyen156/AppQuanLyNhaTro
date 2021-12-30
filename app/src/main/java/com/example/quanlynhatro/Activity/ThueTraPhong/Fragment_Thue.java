package com.example.quanlynhatro.Activity.ThueTraPhong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Adapter.AdapterDSPhieuthue;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Quanlythue;
import com.example.quanlynhatro.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Fragment_Thue extends Fragment {
    private View view;
    public static ListView listView;
    private ArrayList<Khachhang> arKhachhang;
    public static AdapterDSPhieuthue adapterDSPhieuthue;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){

            view = inflater.inflate(R.layout.fragment_thue, container, false);
        }
        Anhxa();
        SetAdapterDSPhieuthue();
        return view;
    }

    private void SetAdapterDSPhieuthue() {
        arKhachhang = MainActivity.dataSQLite.getKhachhangphieuthue(ThueTraPhong.matro);
        Collections.reverse(arKhachhang);
        adapterDSPhieuthue = new AdapterDSPhieuthue(arKhachhang,getContext(),R.layout.line_phieuthue);
        listView.setAdapter(adapterDSPhieuthue);
        adapterDSPhieuthue.notifyDataSetChanged();
    }



    private void Anhxa() {

        listView = (ListView) view.findViewById(R.id.listPhongThue);
    }

}

package com.example.quanlynhatro.Activity.Thutien.FragmentHoadon;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Thutien.HoadonThutien;
import com.example.quanlynhatro.Adapter.AdapterStatusHoaDon;
import com.example.quanlynhatro.ModelApp.Hoadon;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;

import java.util.ArrayList;

public class Fragment_Dadong extends Fragment {
    private View view;
    public static ListView listViewDD;
    public static AdapterStatusHoaDon adapterStatusHoaDonD;
    private int month ;
    private int year ;
    private ArrayList<Hoadon> arrHoadon = new ArrayList<>();
    private ArrayList<Phongtro> arrayPhongtro = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_dadong, container, false);
        }

        AnhXa();

        SETADAPTERD();

        HoadonThutien.txtNamhientaiHD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SETADAPTERD();
            }
        });

        return view;
    }

    private void AnhXa() {
        listViewDD = (ListView) view.findViewById(R.id.listPhongDadong);
    }

    private void SETADAPTERD() {
        String[] separated = HoadonThutien.txtNamhientaiHD.getText().toString().split("-");
        month = Integer.parseInt(separated[0]);
        year = Integer.parseInt(separated[1]);
        arrHoadon = MainActivity.dataSQLite.gethoadontheomatrodadong(HoadonThutien.matro, month, year);
        arrayPhongtro = MainActivity.dataSQLite.getPhongtrotheomatrohavehddd(HoadonThutien.matro, month, year);
        adapterStatusHoaDonD = new AdapterStatusHoaDon(arrHoadon, getContext(), R.layout.line_chuadong,arrayPhongtro);
        listViewDD.setAdapter(adapterStatusHoaDonD);
        adapterStatusHoaDonD.notifyDataSetChanged();
    }

    public void reload(){
        SETADAPTERD();
    }

    @Override
    public void onResume() {
        SETADAPTERD();
        super.onResume();
    }
}

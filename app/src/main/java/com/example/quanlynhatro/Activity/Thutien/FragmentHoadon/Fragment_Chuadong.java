package com.example.quanlynhatro.Activity.Thutien.FragmentHoadon;

import android.os.Bundle;
import android.telecom.PhoneAccount;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Thutien.HoadonThutien;
import com.example.quanlynhatro.Adapter.AdapterStatusHoaDon;
import com.example.quanlynhatro.ModelApp.Hoadon;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;

import java.util.ArrayList;

public class Fragment_Chuadong extends Fragment {
    private View view;
    public static ListView listViewCD;
    public static AdapterStatusHoaDon adapterStatusHoaDon;
    private int month ;
    private int year ;
    private ArrayList<Hoadon> arrHoadon = new ArrayList<>();
    private ArrayList<Phongtro> arrayPhongtro = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_chuadong, container, false);
        }

        AnhXa();

        SETADAPTER();

        HoadonThutien.txtNamhientaiHD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SETADAPTER();
            }
        });

        return view;
    }

    private void AnhXa() {
        listViewCD = (ListView) view.findViewById(R.id.listPhongChuadong);
    }

    private void SETADAPTER() {
        String[] separated = HoadonThutien.txtNamhientaiHD.getText().toString().split("-");
        month = Integer.parseInt(separated[0]);
        year = Integer.parseInt(separated[1]);
        arrHoadon = MainActivity.dataSQLite.gethoadontheomatrochuadong(HoadonThutien.matro, month, year);
        arrayPhongtro = MainActivity.dataSQLite.getPhongtrotheomatrohavehdcd(HoadonThutien.matro, month, year);
        adapterStatusHoaDon = new AdapterStatusHoaDon(arrHoadon, getContext(), R.layout.line_chuadong,arrayPhongtro);
        listViewCD.setAdapter(adapterStatusHoaDon);
        adapterStatusHoaDon.notifyDataSetChanged();
    }

    public void reload(){
        SETADAPTER();
    }

    @Override
    public void onResume() {
        SETADAPTER();
        super.onResume();
    }
}

package com.example.quanlynhatro.Activity.Thutien.FragmentHoadon;

import android.os.Bundle;
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
import com.example.quanlynhatro.Adapter.AdapterDSPhongTaoHD;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;

import java.util.ArrayList;

public class Fragment_Taohoadon extends Fragment {
    private View view;
    public static  ListView listPhongTaohoadon;
    private ArrayList<Phongtro> arrPhongtro = new ArrayList<>();
    private int month ;
    private int year ;
    public static AdapterDSPhongTaoHD adapterDSPhongTaoHD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_taohoadon, container, false);
        }



        AnhXa();
        SET_ADAPTER_LV_TAO_HD();

        HoadonThutien.txtNamhientaiHD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SET_ADAPTER_LV_TAO_HD();
            }
        });

        return view;
    }

    private void SET_ADAPTER_LV_TAO_HD() {
        String[] separated = HoadonThutien.txtNamhientaiHD.getText().toString().split("-");
        month = Integer.parseInt(separated[0]);
        year = Integer.parseInt(separated[1]);
        arrPhongtro = MainActivity.dataSQLite.getPhongtrotheomatro(HoadonThutien.matro);
        adapterDSPhongTaoHD = new AdapterDSPhongTaoHD(arrPhongtro, getContext(), R.layout.line_phongtaohd, month, year);
        listPhongTaohoadon.setAdapter(adapterDSPhongTaoHD);
        adapterDSPhongTaoHD.notifyDataSetChanged();
    }

    private void AnhXa() {
        listPhongTaohoadon = (ListView) view.findViewById(R.id.listPhongTaohoadon);
    }
    @Override
    public void onResume() {
        SET_ADAPTER_LV_TAO_HD();
        super.onResume();
    }

}

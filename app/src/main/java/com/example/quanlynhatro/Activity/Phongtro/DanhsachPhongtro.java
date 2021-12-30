package com.example.quanlynhatro.Activity.Phongtro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.HomeMain;
import com.example.quanlynhatro.Activity.Khachtro.DanhsachKhachtro;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Nhatro.DanhsachNhatro;
import com.example.quanlynhatro.Activity.TaoNhatro.Thietlaploaiphong;
import com.example.quanlynhatro.Adapter.DSPhongtroAdapter;
import com.example.quanlynhatro.Adapter.SpinnerAdapterLoaiPhong;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DanhsachPhongtro extends AppCompatActivity {
    private ArrayList<Phongtro> arrayphongtro;
    private ArrayList<Loaiphong> arrayloaiphong;
    private ListView listviewDSPT;
    private DSPhongtroAdapter dsPhongtroAdapter;
    private int matro = -1;
    private Button btnAddPhongtro;
    private int vt = 0;
    private SearchView searchView;
    private ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_phongtro);
        matro = getIntent().getIntExtra("matro", -1);
        AnhXa();
        SetAdapterPhongtro();

        btnAddPhongtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayloaiphong.isEmpty()){
                    Toast.makeText(DanhsachPhongtro.this, "Nhà trọ cần ít nhất 1 loại phòng để tạo phòng trọ!", Toast.LENGTH_SHORT).show();
                }else{
                    ShowDialogAddPhong();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DanhsachPhongtro.super.onBackPressed();
            }
        });

        listviewDSPT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhsachPhongtro.this, Thongtinphongtro.class);
                Bundle extras = new Bundle();
                extras.putInt("matro",matro);
                extras.putInt("maphong", arrayphongtro.get(position).getmMaphong());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    dsPhongtroAdapter.filter("");
                    listviewDSPT.clearTextFilter();

                }else{
                    dsPhongtroAdapter.filter(newText);
                }
                return true;
            }
        });
    }




    private void SetAdapterPhongtro() {
        arrayphongtro = MainActivity.dataSQLite.getPhongtrotheomatro(matro);
        arrayloaiphong = MainActivity.dataSQLite.getLoaiphongtheomatro(matro);
        dsPhongtroAdapter = new DSPhongtroAdapter(arrayphongtro,DanhsachPhongtro.this,R.layout.line_phongtro,arrayloaiphong);
        listviewDSPT.setAdapter(dsPhongtroAdapter);
        dsPhongtroAdapter.notifyDataSetChanged();
    }


    private void AnhXa() {
        btnAddPhongtro = (Button) findViewById(R.id.btnAddPhongtro);
        listviewDSPT = (ListView) findViewById(R.id.listviewDSPT);
        btnBack = (ImageButton) findViewById(R.id.btnBackDSPT);
        searchView = (SearchView) findViewById(R.id.searchPT);
    }



    private void ShowDialogAddPhong(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_themphongtro);
        final EditText edtTenPhongThem = (EditText) dialog.findViewById(R.id.edtTenPhongThem);
        final EditText edtSlkhachtoida = (EditText) dialog.findViewById(R.id.edtslkhachtoida);
        final Button btnOkthemphong = (Button) dialog.findViewById(R.id.btnOkthemphong);
        final Button btnHuythemphong = (Button) dialog.findViewById(R.id.btnHuythemphong);
        final Spinner spinnerLoaiphong = (Spinner) dialog.findViewById(R.id.spinnerLoaiphong);

        SpinnerAdapterLoaiPhong spinnerAdapterLoaiPhong = new SpinnerAdapterLoaiPhong(this, R.layout.spinner_line_loaiphong, arrayloaiphong);
        // attaching data adapter to spinner
        spinnerLoaiphong.setAdapter(spinnerAdapterLoaiPhong);

        edtSlkhachtoida.addTextChangedListener(new TextWatcher() {
            private String current ="";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtSlkhachtoida.setText(formated);
                        edtSlkhachtoida.setSelection(formated.length());
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        spinnerLoaiphong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerLoaiphong.setSelection(position);
                vt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnOkthemphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tenphong = edtTenPhongThem.getText().toString().trim();
                String Slkhachtoida = edtSlkhachtoida.getText().toString().trim();
                int soluong = 0;
                NumberFormat usFormat = NumberFormat.getNumberInstance(Locale.ITALY);
                try {
                    soluong = usFormat.parse(Slkhachtoida).intValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(Tenphong.isEmpty()){
                    Toast.makeText(DanhsachPhongtro.this, "Vui lòng nhập tên phòng !", Toast.LENGTH_SHORT).show();
                }else if(Slkhachtoida.isEmpty()){
                    Toast.makeText(DanhsachPhongtro.this, "Vui lòng nhập số lượng khách tối đa của phòng !", Toast.LENGTH_SHORT).show();
                }else if(soluong < 0){
                    Toast.makeText(DanhsachPhongtro.this, "Vui lòng nhập số lượng khách tối đa hợp lệ !", Toast.LENGTH_SHORT).show();
                }else if(MainActivity.dataSQLite.checktenphongtro(Tenphong, arrayloaiphong.get(vt).getmMaloai() ,matro) > 0){
                    Toast.makeText(DanhsachPhongtro.this, "Tên phòng đã tồn tại !", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dataSQLite.add1Phongtro(new Phongtro(arrayloaiphong.get(vt).getmMaloai(), Tenphong,0, soluong));
                    SetAdapterPhongtro();
                    Toast.makeText(DanhsachPhongtro.this, "Thêm phòng trọ thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
        btnHuythemphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DeletePhongtro(){
        SetAdapterPhongtro();
    }

    @Override
    protected void onResume() {
        SetAdapterPhongtro();
        super.onResume();
    }
}

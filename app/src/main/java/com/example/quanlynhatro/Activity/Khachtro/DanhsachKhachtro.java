package com.example.quanlynhatro.Activity.Khachtro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Adapter.DSKhachtroAdapter;
import com.example.quanlynhatro.Adapter.SpinnerAdapterPhongtro;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Quanlythue;
import com.example.quanlynhatro.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class DanhsachKhachtro extends AppCompatActivity {

    private ListView lvKhachtro;
    private ArrayList<Khachhang> arKhachhang= new ArrayList<>();
    private DSKhachtroAdapter dsKhachtroAdapter;
    private Button btnThemkhach;
    private ImageButton btnBack;
    private int matro = -1;
    private int vt = 0;
    private SearchView searchView;
    private int gioitinh = -1;
    private String nsthemvaodata = "";
    private ArrayList<Phongtro> arPhongtro = new ArrayList<>();
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private String todaydate = df.format(Calendar.getInstance().getTime());
    public static String datetoString (Date date){
        return df.format(date);
    }
    public static Date Stringtodate (String strDate){
        try{
            return df.parse(strDate);
        }catch (ParseException e){
            return new Date();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_khachtro);
        matro = getIntent().getIntExtra("matro", -1);
        AnhXa();
        SetAdapterDSKhachtro();

        btnThemkhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arPhongtro.isEmpty()){
                    Toast.makeText(DanhsachKhachtro.this, "Cần có ít nhất 1 phòng trọ để thêm khách trọ!", Toast.LENGTH_SHORT).show();
                }else {
                    SetDialogAddKhachtro();
                }
            }
        });

        lvKhachtro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhsachKhachtro.this, ThongtinkhachtroInf.class);
                Bundle extras = new Bundle();
                extras.putInt("matro",matro);
                extras.putInt("makhach", arKhachhang.get(position).getmMakhach());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DanhsachKhachtro.super.onBackPressed();
            }
        });
        Locale locale = new Locale("vie");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    dsKhachtroAdapter.filter("");
                    lvKhachtro.clearTextFilter();

                }else{
                    dsKhachtroAdapter.filter(newText);
                }
                return true;
            }
        });
    }


    private void AnhXa() {
        lvKhachtro = (ListView) findViewById(R.id.listviewDSKT);
        btnThemkhach = (Button) findViewById(R.id.btnAddKhachtro);
        btnBack = (ImageButton) findViewById(R.id.btnBackDSKT);
        searchView = (SearchView) findViewById(R.id.searchKH);
    }
    private void SetAdapterDSKhachtro() {
        arKhachhang.clear();
        arKhachhang = MainActivity.dataSQLite.getKhachhang(matro);
        Collections.reverse(arKhachhang);
        arPhongtro = MainActivity.dataSQLite.getPhongtrotheomatro(matro);
        dsKhachtroAdapter = new DSKhachtroAdapter(arKhachhang, arPhongtro,DanhsachKhachtro.this, R.layout.line_khachhang);
        lvKhachtro.setAdapter(dsKhachtroAdapter);
        dsKhachtroAdapter.notifyDataSetChanged();
    }
    private void SetDialogAddKhachtro() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_themkhachtro);
        final EditText edtTenThemkhach = (EditText) dialog.findViewById(R.id.edtTenThemkhachtro);
        final EditText edtNgaysinhThemkhach = (EditText) dialog.findViewById(R.id.edtNgaysinhThemkhachtro);
        final Spinner spinnerPhongtro = (Spinner) dialog.findViewById(R.id.spinnerPhongtro);
        Button btnOkThemkhach = (Button) dialog.findViewById(R.id.btnOkthemkhachtro);
        Button btnHuyThemkhach = (Button) dialog.findViewById(R.id.btnHuythemkhachtro);
        final RadioButton radiobtnNuThem = (RadioButton) dialog.findViewById(R.id.radiobtnNuThem);
        final RadioButton radiobtnNamThem = (RadioButton) dialog.findViewById(R.id.radiobtnNamThem);



        edtNgaysinhThemkhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = 2000;
                int selectedMonth = 0;
                int selectedDayOfMonth = 1;

                // Date Select Listener.
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                    }
                };

                DatePickerDialog datePickerDialog = new DatePickerDialog(dialog.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog,
                        dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
                datePickerDialog.show();
            }
        });

        SpinnerAdapterPhongtro spinnerAdapterPhongtro = new SpinnerAdapterPhongtro(this, R.layout.spinner_line_phongtro, arPhongtro);
        // attaching data adapter to spinner
        spinnerPhongtro.setAdapter(spinnerAdapterPhongtro);
        spinnerAdapterPhongtro.notifyDataSetChanged();

        spinnerPhongtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPhongtro.setSelection(position);
                vt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnOkThemkhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radiobtnNamThem.isChecked()){
                    gioitinh = 1;
                }else if(radiobtnNuThem.isChecked()){
                    gioitinh = 0;
                }
                String Tenkhach = edtTenThemkhach.getText().toString().trim();
                if(Tenkhach.isEmpty()){
                    Toast.makeText(DanhsachKhachtro.this, "Vui lòng nhập tên khách hàng!", Toast.LENGTH_SHORT).show();
                }else if (gioitinh == -1){
                    Toast.makeText(DanhsachKhachtro.this, "Vui lòng chọn giới tính!!", Toast.LENGTH_SHORT).show();
                }else if(edtNgaysinhThemkhach.getText().toString().isEmpty()){
                    Toast.makeText(DanhsachKhachtro.this, "Vui lòng chọn ngày sinh!!", Toast.LENGTH_SHORT).show();
                }else if(arPhongtro.get(vt).getmKhachhientai() == arPhongtro.get(vt).getmKhachtoida()){
                    Toast.makeText(DanhsachKhachtro.this, "Phòng đã đầy!", Toast.LENGTH_SHORT).show();
                }else{
                    Date ngaysinh = Stringtodate(nsthemvaodata);
                    MainActivity.dataSQLite.add1Khachhang(new Khachhang(arPhongtro.get(vt).getmMaphong(),Tenkhach, gioitinh ,ngaysinh , "", "", "", null, 0));
                    Toast.makeText(DanhsachKhachtro.this, "Tạo khách hàng thành công!", Toast.LENGTH_SHORT).show();
                    Phongtro phongtro = MainActivity.dataSQLite.get1Phongtrotheomaphong(arPhongtro.get(vt).getmMaphong());
                    MainActivity.dataSQLite.themkhachhientai(phongtro);
                    dialog.dismiss();
                    gioitinh = -1;
                    Khachhang khachhang = MainActivity.dataSQLite.getkhachhangnew();
                    MainActivity.dataSQLite.add1Quanlythue(new Quanlythue(khachhang.getmMakhach(), Stringtodate(todaydate)));
                    SetAdapterDSKhachtro();
                }
            }
        });
        btnHuyThemkhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public  void deletekhachtro(){
        SetAdapterDSKhachtro();
    }

    @Override
    protected void onResume() {
        SetAdapterDSKhachtro();
        super.onResume();
    }
}
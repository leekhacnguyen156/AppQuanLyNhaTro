package com.example.quanlynhatro.Activity.TaoNhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.Loaiphong.DanhsachLoaiphong;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Nhatro.DanhsachNhatro;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Themtro extends AppCompatActivity {

    private EditText edtTentroThem;
    private EditText edtDiachitroThem;
    private EditText edtGiadienThem;
    private EditText edtGianuocThem;
    private ImageButton btnOkThemtro;
    private ImageButton btnHuyThemtro ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themtro);
        AnhXa();

        edtGiadienThem.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtGiadienThem.setText(formated);
                        edtGiadienThem.setSelection(formated.length());
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtGianuocThem.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtGianuocThem.setText(formated);
                        edtGianuocThem.setSelection(formated.length());
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnOkThemtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentroThem = edtTentroThem.getText().toString().trim();
                String diachitroThem = edtDiachitroThem.getText().toString().trim();
                String giadienThem = edtGiadienThem.getText().toString().trim();
                String gianuocThem = edtGianuocThem.getText().toString().trim();
                double giadien = 0;
                double gianuoc = 0;
                NumberFormat usFormat = NumberFormat.getNumberInstance(Locale.ITALY);
                try {
                    giadien = usFormat.parse(giadienThem).doubleValue();
                    gianuoc = usFormat.parse(gianuocThem).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(tentroThem.isEmpty()){
                    Toast.makeText(Themtro.this, "Vui lòng nhập tên nhà trọ !", Toast.LENGTH_SHORT).show();
                }else if(diachitroThem.isEmpty()){
                    Toast.makeText(Themtro.this, "Vui lòng địa chỉ nhà trọ !", Toast.LENGTH_SHORT).show();
                }else if(giadienThem.isEmpty()){
                    Toast.makeText(Themtro.this, "Vui lòng nhập giá điện nhà trọ !", Toast.LENGTH_SHORT).show();
                }else if(giadien< 0){
                    Toast.makeText(Themtro.this, "Vui lòng nhập giá điện hợp lệ !", Toast.LENGTH_SHORT).show();
                }else if(gianuocThem.isEmpty()){
                    Toast.makeText(Themtro.this, "Vui lòng nhập giá nước nhà trọ !", Toast.LENGTH_SHORT).show();
                }else if(gianuoc < 0){
                    Toast.makeText(Themtro.this, "Vui lòng nhập giá nước hợp lệ !", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dataSQLite.add1Thongtintro(new Thongtintro(tentroThem, diachitroThem, giadien, gianuoc));
                    startActivity(new Intent(Themtro.this, Thietlaploaiphong.class));
                    Themtro.super.finish();
                }
            }
        });
        btnHuyThemtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themtro.super.onBackPressed();
            }
        });
    }

    private void AnhXa() {
        edtTentroThem = (EditText) findViewById(R.id.edtTentroThem);
        edtDiachitroThem = (EditText) findViewById(R.id.edtDiachitroThem);
        edtGiadienThem = (EditText) findViewById(R.id.edtGiadienThem);
        edtGianuocThem = (EditText) findViewById(R.id.edtGianuocThem);
        btnOkThemtro = (ImageButton) findViewById(R.id.btnOkthemtro);
        btnHuyThemtro = (ImageButton) findViewById(R.id.btnHuythemtro);
    }
}
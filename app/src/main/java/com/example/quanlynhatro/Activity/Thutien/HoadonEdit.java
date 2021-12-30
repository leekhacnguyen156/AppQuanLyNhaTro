package com.example.quanlynhatro.Activity.Thutien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.ModelApp.Hoadon;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HoadonEdit extends AppCompatActivity {

    private EditText edtTienPhongHD, edtTienDienHD, edtTienNuocHD, edtTienPhatSinhHD, edtGhichu ;
    private EditText edtSoLuongPhongHD, edtSoLuongDienHD, edtSoLuongNuocHD, edtSoTienPhatSinh ;
    private Button btnEditHD;
    private TextView txthoadonsua;
    private int maloai, maphong;
    private Loaiphong loaiphong;
    private Hoadon hoadon;
    private Phongtro phongtro;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon_edit);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        maloai = extras.getInt("maloai", -1);
        maphong = extras.getInt("maphong", -1);
        String[] separated = HoadonThutien.txtNamhientaiHD.getText().toString().split("-");
        int month = Integer.parseInt(separated[0]);
        int year = Integer.parseInt(separated[1]);

        loaiphong = MainActivity.dataSQLite.getLoaiphongtheomaloai(maloai);
        hoadon = MainActivity.dataSQLite.gethoadontheomaphongnew(maphong, month, year);
        phongtro = MainActivity.dataSQLite.get1Phongtrotheomaphong(maphong);
        AnhXa();
        LoadData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoadonEdit.super.onBackPressed();
            }
        });

        edtTienPhongHD.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtTienPhongHD.setText(formated);
                        edtTienPhongHD.setSelection(formated.length());
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

        edtTienDienHD.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtTienDienHD.setText(formated);
                        edtTienDienHD.setSelection(formated.length());
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

        edtTienNuocHD.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtTienNuocHD.setText(formated);
                        edtTienNuocHD.setSelection(formated.length());
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

        edtTienPhatSinhHD.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtTienPhatSinhHD.setText(formated);
                        edtTienPhatSinhHD.setSelection(formated.length());
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

        btnEditHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSoLuongDienHD.getText().toString().trim().isEmpty() ||
                        edtSoLuongNuocHD.getText().toString().trim().isEmpty() ||
                        edtSoTienPhatSinh.getText().toString().trim().isEmpty() ||
                        edtTienDienHD.getText().toString().trim().isEmpty() ||
                        edtTienNuocHD.getText().toString().trim().isEmpty() ||
                        edtTienPhatSinhHD.getText().toString().trim().isEmpty() ||
                        edtTienPhongHD.getText().toString().trim().isEmpty()){
                    Toast.makeText(HoadonEdit
                            .this, "Vui lòng điền đầy đủ thông tin cần thiết !", Toast.LENGTH_LONG).show();
                }else {
                    int msodien = Integer.parseInt(edtSoLuongDienHD.getText().toString().trim());
                    double mgiadien = 0;
                    int msonuoc = Integer.parseInt(edtSoLuongNuocHD.getText().toString().trim());
                    double mgianuoc = 0;
                    int msophatsinh = Integer.parseInt(edtSoTienPhatSinh.getText().toString().trim());
                    double mgiaphatsinh = 0;
                    String mghichu = edtGhichu.getText().toString().trim();
                    double mtienphong = 0;
                    NumberFormat usFormat = NumberFormat.getNumberInstance(Locale.ITALY);
                    try {
                        mgiadien = usFormat.parse(edtTienDienHD.getText().toString().trim()).doubleValue();
                        mgianuoc = usFormat.parse(edtTienNuocHD.getText().toString().trim()).doubleValue();
                        mgiaphatsinh = usFormat.parse(edtTienPhatSinhHD.getText().toString().trim()).doubleValue();
                        mtienphong = usFormat.parse(edtTienPhongHD.getText().toString().trim()).doubleValue();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    double mtongtien = mtienphong + (msodien * mgiadien) + (msonuoc * mgianuoc) + (msophatsinh * mgiaphatsinh);
                    Hoadon hoadona = new Hoadon(maphong, null, msodien, mgiadien, msonuoc, mgianuoc, mgiaphatsinh, msophatsinh, mtongtien, 0, mghichu, 0, 0);
                    MainActivity.dataSQLite.updatehoadon(hoadona, hoadon.getmMahd());
                    HoadonEdit.super.onBackPressed();
                }
            }
        });
    }

    private void LoadData() {
        edtTienPhongHD.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmTongtien() - ( ( hoadon.getmGiadien() * hoadon.getmSodien() ) + ( hoadon.getmGianuoc() * hoadon.getmSonuoc()) + ( hoadon.getmPhiphatsinh() * hoadon.getmSophatsinh() ) ) ));
        edtTienDienHD.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmGiadien()));
        edtTienNuocHD.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmGianuoc()));
        edtTienPhatSinhHD.setText(NumberFormat.getInstance(new Locale("it", "IT")).format((hoadon.getmSophatsinh() * hoadon.getmPhiphatsinh())));
        edtSoLuongPhongHD.setText("1");
        edtSoLuongDienHD.setText(hoadon.getmSodien() + "");
        edtSoLuongNuocHD.setText(hoadon.getmSonuoc() + "");
        edtSoTienPhatSinh.setText(hoadon.getmSophatsinh() + "");
        txthoadonsua.setText("Phòng " + phongtro.getmTenphong() + " ( " + hoadon.getmThang() + " - " + hoadon.getmNam() + " )");
    }

    private void AnhXa() {
        edtTienPhongHD = (EditText) findViewById(R.id.edtTienPhongHDS);
        edtTienDienHD = (EditText) findViewById(R.id.edtTienDienHDS);
        edtTienNuocHD = (EditText) findViewById(R.id.edtTienNuocHDS);
        edtTienPhatSinhHD = (EditText) findViewById(R.id.edtTienPhatSinhHDS);
        edtGhichu = (EditText) findViewById(R.id.edtGhichuS);
        edtSoLuongPhongHD = (EditText) findViewById(R.id.edtSoLuongPhongHDS);
        edtSoLuongDienHD = (EditText) findViewById(R.id.edtSoLuongDienHDS);
        edtSoLuongNuocHD = (EditText) findViewById(R.id.edtSoLuongNuocHDS);
        edtSoTienPhatSinh = (EditText) findViewById(R.id.edtSoTienPhatSinhS);
        btnEditHD = (Button) findViewById(R.id.btnEditHD);
        txthoadonsua = (TextView) findViewById(R.id.txtThongtinPhongSua);
        btnBack = (ImageButton) findViewById(R.id.btnBackSuaHoadon);
    }
}
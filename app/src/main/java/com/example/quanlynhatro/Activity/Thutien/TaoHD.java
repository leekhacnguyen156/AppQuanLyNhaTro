package com.example.quanlynhatro.Activity.Thutien;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.Loaiphong.DanhsachLoaiphong;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.HoaDonView;
import com.example.quanlynhatro.ModelApp.Hoadon;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.R;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaoHD extends AppCompatActivity {

    private int maloai;
    private int maphong;
    private Dialog dialog;
    private Loaiphong loaiphong;
    private Thongtintro thongtintro;
    private Phongtro phongtro;
    private Button btnCreateHD;
    private ImageButton btnBack;
    private TextView txtThongtinPhongTao;
    private EditText edtTienPhongHD, edtTienDienHD, edtTienNuocHD, edtTienPhatSinhHD, edtGhichu ;
    private EditText edtSoLuongPhongHD, edtSoLuongDienHD, edtSoLuongNuocHD, edtSoTienPhatSinh ;
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dfhien = new SimpleDateFormat("dd-MM-yyyy");
    private String todaydatehienthi = dfhien.format(Calendar.getInstance().getTime());
    private String todayThemvaodata = df.format(Calendar.getInstance().getTime());
    public static String datetoString (Date date){
        return df.format(date);
    }
    String[] separated = HoadonThutien.txtNamhientaiHD.getText().toString().split("-");
    int month = Integer.parseInt(separated[0]);
    int year = Integer.parseInt(separated[1]);
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
        setContentView(R.layout.activity_tao_h_d);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        maloai = extras.getInt("maloai", -1);
        maphong = extras.getInt("maphong",-1);

        phongtro = MainActivity.dataSQLite.get1Phongtrotheomaphong(maphong);
        loaiphong = MainActivity.dataSQLite.getLoaiphongtheomaloai(maloai);
        thongtintro = MainActivity.dataSQLite.getThongtintrotheomatro(loaiphong.getmMatro());
        AnhXa();
        LoadData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaoHD.super.onBackPressed();
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

        btnCreateHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSoLuongDienHD.getText().toString().trim().isEmpty() ||
                        edtSoLuongNuocHD.getText().toString().trim().isEmpty() ||
                        edtSoTienPhatSinh.getText().toString().trim().isEmpty() ||
                        edtTienDienHD.getText().toString().trim().isEmpty() ||
                        edtTienNuocHD.getText().toString().trim().isEmpty() ||
                        edtTienPhatSinhHD.getText().toString().trim().isEmpty() ||
                        edtTienPhongHD.getText().toString().trim().isEmpty()){
                    Toast.makeText(TaoHD.this, "Vui lòng điền đầy đủ thông tin cần thiết !", Toast.LENGTH_LONG).show();
                }else{
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
                    Hoadon hoadon = new Hoadon(maphong, Stringtodate(todayThemvaodata), msodien, mgiadien, msonuoc, mgianuoc, mgiaphatsinh, msophatsinh, mtongtien, 0, mghichu, month, year);
                    MainActivity.dataSQLite.add1Hoadon(hoadon);
                    Toast.makeText(TaoHD.this, "Tạo hóa đơn thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TaoHD.this , HoaDonView.class);
                    Bundle extras = new Bundle();
                    extras.putInt("maloai",maloai);
                    extras.putInt("maphong", maphong);
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void AnhXa() {
        edtTienPhongHD = (EditText) findViewById(R.id.edtTienPhongHD);
        edtTienDienHD = (EditText) findViewById(R.id.edtTienDienHD);
        edtTienNuocHD = (EditText) findViewById(R.id.edtTienNuocHD);
        edtTienPhatSinhHD = (EditText) findViewById(R.id.edtTienPhatSinhHD);
        edtGhichu = (EditText) findViewById(R.id.edtGhichu);
        edtSoLuongPhongHD = (EditText) findViewById(R.id.edtSoLuongPhongHD);
        edtSoLuongDienHD = (EditText) findViewById(R.id.edtSoLuongDienHD);
        edtSoLuongNuocHD = (EditText) findViewById(R.id.edtSoLuongNuocHD);
        edtSoTienPhatSinh = (EditText) findViewById(R.id.edtSoTienPhatSinh);
        btnCreateHD = (Button) findViewById(R.id.btnCreateHD);
        btnBack = (ImageButton) findViewById(R.id.btnBackTaoHoadon);
        txtThongtinPhongTao = (TextView) findViewById(R.id.txtThongtinPhongTao);
    }

    @SuppressLint("SetTextI18n")
    private void LoadData() {
        edtTienPhongHD.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(loaiphong.getmGialoai()));
        edtTienDienHD.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(thongtintro.getmGiadien()));
        edtTienNuocHD.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(thongtintro.getmGianuoc()));
        edtTienPhatSinhHD.setText("0");
        edtSoLuongPhongHD.setText("1");
        edtSoLuongDienHD.setText("0");
        edtSoLuongNuocHD.setText("0");
        edtSoTienPhatSinh.setText("0");
        txtThongtinPhongTao.setText("Phòng " + phongtro.getmTenphong() + " ( " + month + " - " + year + " )");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
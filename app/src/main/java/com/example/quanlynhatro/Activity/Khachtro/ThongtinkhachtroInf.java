    package com.example.quanlynhatro.Activity.Khachtro;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Nhatro.ThongtinnhatroInf;
import com.example.quanlynhatro.Adapter.SpinnerAdapterPhongtro;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Traphongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ThongtinkhachtroInf extends AppCompatActivity {

    private int makhach;
    private int matro , vt, maphongtrocu;
    private Khachhang khachhang;
    private Spinner spinnerPhongtroInf;
    private EditText edtQuequanInf, edtCmndInf, edtSdtInf, edtNgaysinhInf, editTenKhachtroInf;
    private RadioButton radiobtnNam, radiobtnNu;
    private ImageButton btnBackInfKhachtro,btnSaveInfKhachtro;
    private Button btnTraphongtroinf;
    private ArrayList<Phongtro> arPhongtro = new ArrayList();
    private String nsthemvaodata = "";
    private int gioitinh;
    private ImageView AvtProfile;
    private boolean checkchangedinf = false;
    private int RESQUEST_CODE_FILE = 123;

    private static SimpleDateFormat dfhien = new SimpleDateFormat("dd-MM-yyyy");
    private static SimpleDateFormat dfvaodata = new SimpleDateFormat("yyyy-MM-dd");
    public static String datetoString (Date date){
        return dfhien.format(date);
    }
    private String todaydate = dfvaodata.format(Calendar.getInstance().getTime());
    public static String datetoStringld (Date date){
        return dfvaodata.format(date);
    }
    public static Date Stringtodate (String strDate){
        try{
            return dfvaodata.parse(strDate);
        }catch (ParseException e){
            return new Date();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachtro_inf);
        final Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        matro = extras.getInt("matro", -1);
        makhach = extras.getInt("makhach",-1);
        arPhongtro = MainActivity.dataSQLite.getPhongtrotheomatro(matro);
        khachhang = MainActivity.dataSQLite.getkhachhangtheomakhach(makhach);
        maphongtrocu = khachhang.getmMaphong();

        nsthemvaodata = datetoStringld(khachhang.getmNgaysinh());
        AnhXa();
        LoadDataInput();
        CheckTextChanged();

        AvtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAvt = new Intent(Intent.ACTION_PICK);
                intentAvt.setType("image/*");
                startActivityForResult(intentAvt, RESQUEST_CODE_FILE);

            }
        });

        btnTraphongtroinf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThongtinkhachtroInf.this);
                alertDialog.setTitle("Thông báo!").setMessage("Bạn có chắc trả phòng trọ?");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ThongtinkhachtroInf.this, "Khách hàng trả phòng thành công!", Toast.LENGTH_SHORT).show();
                        MainActivity.dataSQLite.add1Traphongtro(new Traphongtro(khachhang.getmMakhach(),Stringtodate(todaydate)));
                        MainActivity.dataSQLite.updateTraphongtrocuakhachhang(khachhang);
                        Phongtro phongtro = MainActivity.dataSQLite.get1Phongtrotheomaphong(khachhang.getmMaphong());
                        MainActivity.dataSQLite.themkhachhientai(phongtro);

                        ThongtinkhachtroInf.super.onBackPressed();
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.show();
            }
        });
        btnBackInfKhachtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkchangedinf){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThongtinkhachtroInf.this);
                    alertDialog.setTitle("Thông báo!").setMessage("Bạn có chắc không muốn lưu lại những thay đổi?");
                    alertDialog.setCancelable(true);
                    alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ThongtinkhachtroInf.this, "Không lưu thay đổi!", Toast.LENGTH_SHORT).show();
                            ThongtinkhachtroInf.super.onBackPressed();
                        }
                    });
                    alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alert = alertDialog.create();
                    alert.show();
                }else {
                    ThongtinkhachtroInf.super.onBackPressed();
                }
            }
        });

        edtNgaysinhInf.setOnClickListener(new View.OnClickListener() {
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

                        edtNgaysinhInf.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        nsthemvaodata = String.valueOf(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                };

                DatePickerDialog datePickerDialog = new DatePickerDialog(ThongtinkhachtroInf.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
                datePickerDialog.show();
            }
        });

        btnSaveInfKhachtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTenKhachtroInf.getText().toString().isEmpty()){
                    Toast.makeText(ThongtinkhachtroInf.this, "Vui lòng nhập Tên khách hàng", Toast.LENGTH_SHORT).show();
                }else if(arPhongtro.get(vt).getmKhachhientai() == arPhongtro.get(vt).getmKhachtoida() && arPhongtro.get(vt).getmMaphong() != khachhang.getmMaphong()){
                    Toast.makeText(ThongtinkhachtroInf.this, "Phòng "+arPhongtro.get(vt).getmTenphong()+" đã vượt quá giới hạn khách tối đa!", Toast.LENGTH_SHORT).show();
                }else{
                    if(radiobtnNam.isChecked()){
                        gioitinh = 1;
                    }else if(radiobtnNu.isChecked()) {
                        gioitinh = 0;
                    }

                    Date ngaysinh = Stringtodate(nsthemvaodata);

                    BitmapDrawable bitmapDrawable = (BitmapDrawable) AvtProfile.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayInputStream);

                    Khachhang khachhang = new Khachhang(arPhongtro.get(vt).getmMaphong(),editTenKhachtroInf.getText().toString(),gioitinh,ngaysinh,edtSdtInf.getText().toString(),edtCmndInf.getText().toString(),edtQuequanInf.getText().toString(),byteArrayInputStream.toByteArray(), 0);
                    MainActivity.dataSQLite.updatekhachhang(khachhang,makhach);
                    if(arPhongtro.get(vt).getmMaphong() != maphongtrocu){
                        Phongtro phongtrocu = MainActivity.dataSQLite.get1Phongtrotheomaphong(maphongtrocu);
                        Phongtro phongtro = MainActivity.dataSQLite.get1Phongtrotheomaphong(khachhang.getmMaphong());
                        MainActivity.dataSQLite.themkhachhientai(phongtrocu);
                        MainActivity.dataSQLite.themkhachhientai(phongtro);
                    }
                    Toast.makeText(ThongtinkhachtroInf.this, "Lưu thông tin khách trọ thành công!", Toast.LENGTH_SHORT).show();
                    ThongtinkhachtroInf.super.onBackPressed();
                }
            }
        });
    }

    private void CheckTextChanged() {
        //catch textchanged event
        edtNgaysinhInf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedinf = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedinf = true;
            }
        });
        editTenKhachtroInf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedinf = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedinf = true;
            }
        });
        edtSdtInf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedinf = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedinf = true;
            }
        });
        edtCmndInf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedinf = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedinf = true;
            }
        });
        edtQuequanInf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedinf = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedinf = true;
            }
        });
        //
    }

    private void AnhXa() {
        spinnerPhongtroInf = (Spinner) findViewById(R.id.spinnerPhongtroInf);
        edtQuequanInf = (EditText) findViewById(R.id.edtQuequanInf);
        edtCmndInf = (EditText) findViewById(R.id.edtCmndInf);
        edtSdtInf = (EditText) findViewById(R.id.edtSdtInf);
        edtNgaysinhInf = (EditText) findViewById(R.id.edtNgaysinhInf);
        radiobtnNam = (RadioButton) findViewById(R.id.radiobtnNam);
        radiobtnNu = (RadioButton) findViewById(R.id.radiobtnNu);
        editTenKhachtroInf = (EditText) findViewById(R.id.editTenKhachtroInf);
        btnBackInfKhachtro = (ImageButton) findViewById(R.id.btnBackInfKhachtro);
        btnSaveInfKhachtro = (ImageButton) findViewById(R.id.btnSaveInfKhachtro);
        btnTraphongtroinf = (Button) findViewById(R.id.btnTraphongkhachhangInf);
        AvtProfile = (ImageView) findViewById(R.id.AvtProfile);
    }

    private void LoadDataInput() {
        SpinnerAdapterPhongtro spinnerAdapterPhongtro = new SpinnerAdapterPhongtro(this, R.layout.spinner_line_phongtro, arPhongtro);
        // attaching data adapter to spinner
        spinnerPhongtroInf.setAdapter(spinnerAdapterPhongtro);
        for(int i = 0; i < arPhongtro.size(); i++){
            if(arPhongtro.get(i).getmMaphong() == khachhang.getmMaphong()) {
                spinnerPhongtroInf.setSelection(i);
                vt = i;
                break;
            }
        }
        spinnerAdapterPhongtro.notifyDataSetChanged();
        //get vitri spinner
        spinnerPhongtroInf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPhongtroInf.setSelection(position);
                vt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editTenKhachtroInf.setText(khachhang.getmTen()+"");
        edtQuequanInf.setText(khachhang.getmQuequan()+"");
        edtCmndInf.setText(khachhang.getmCmnd()+"");
        edtSdtInf.setText(khachhang.getmSdt()+"");
        edtCmndInf.setText(khachhang.getmCmnd()+"");
        edtNgaysinhInf.setText(datetoString(khachhang.getmNgaysinh()));

        if (khachhang.getmHinhanh() != null){
            byte[] Avt = khachhang.getmHinhanh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(Avt, 0, Avt.length);
            AvtProfile.setImageBitmap(bitmap);
        }

        if (khachhang.getmGioitinh() == 1){
            radiobtnNam.setChecked(true);
            radiobtnNu.setChecked(false);
        }else if(khachhang.getmGioitinh() == 0){
            radiobtnNu.setChecked(true);
            radiobtnNam.setChecked(false);
        }else{
            radiobtnNu.setChecked(false);
            radiobtnNam.setChecked(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RESQUEST_CODE_FILE && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                AvtProfile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
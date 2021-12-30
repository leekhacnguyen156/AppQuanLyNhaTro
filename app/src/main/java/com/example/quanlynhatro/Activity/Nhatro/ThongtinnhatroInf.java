
package com.example.quanlynhatro.Activity.Nhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.quanlynhatro.Activity.Khachtro.ThongtinkhachtroInf;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.TaoNhatro.Themtro;
import com.example.quanlynhatro.Activity.TaoNhatro.Thietlaploaiphong;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Hoadon;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Loaiphong;
import com.example.quanlynhatro.SQLite.ModelData.TB_Phongtro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Quanlythue;
import com.example.quanlynhatro.SQLite.ModelData.TB_Thongtintro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Traphongtro;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class ThongtinnhatroInf extends AppCompatActivity {

    private int matro = -1;
    private EditText editTenTro;
    private EditText edtDiachi;
    private EditText edtGiadien;
    private EditText edtGianuoc;
    private Button btnDeleteNhatro;
    private ImageButton btnSaveNhatro;
    private Thongtintro thongtintro;
    private ImageButton btnBackInfNhatro;
    private boolean checkchangedtext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinnhatro_inf);
        matro = getIntent().getIntExtra("matro", -1);
        thongtintro = MainActivity.dataSQLite.getThongtintrotheomatro(matro);
        AnhXa();
        SetDataNhaTro();

        edtGiadien.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedtext = true;
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtGiadien.setText(formated);
                        edtGiadien.setSelection(formated.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedtext = true;
            }
        });

        edtGianuoc.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedtext = true;
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtGianuoc.setText(formated);
                        edtGianuoc.setSelection(formated.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedtext = true;
            }
        });
        editTenTro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedtext = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedtext = true;
            }
        });
        edtDiachi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkchangedtext = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkchangedtext = true;
            }
        });
        btnDeleteNhatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThongtinnhatroInf.this);
                alertDialog.setTitle("! Thông báo xóa");
                alertDialog.setMessage("Bạn muốn xóa nhà trọ này ?\nDữ liệu của toàn bộ nhà trọ sẽ mất !");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Loaiphong> arLoaiphong = MainActivity.dataSQLite.getLoaiphongtheomatro(thongtintro.getmMatro());
                        ArrayList<Khachhang> arKhachhang = MainActivity.dataSQLite.getKhachhang(thongtintro.getmMatro());
                        ArrayList<Phongtro> arPhongtro = MainActivity.dataSQLite.getPhongtrotheomatro(thongtintro.getmMatro());
                        for(Khachhang a : arKhachhang){
                            MainActivity.dataSQLite.deleteid(TB_Traphongtro.TABLENAME,TB_Traphongtro.MAKHACH,a.getmMakhach());
                            MainActivity.dataSQLite.deleteid(TB_Quanlythue.TABLENAME,TB_Quanlythue.MAKHACH,a.getmMakhach());
                        }

                        for(Phongtro a : arPhongtro){
                            MainActivity.dataSQLite.deleteid(TB_Khachhang.TABLENAME,TB_Khachhang.MAPHONG,a.getmMaphong());
                            MainActivity.dataSQLite.deleteid(TB_Hoadon.TABLENAME,TB_Hoadon.MAPHONG,a.getmMaphong());
                        }

                        for(Loaiphong a : arLoaiphong){
                            MainActivity.dataSQLite.deleteid(TB_Phongtro.TABLENAME,TB_Phongtro.MALOAI,a.getmMaloai());
                        }
                        MainActivity.dataSQLite.deleteid(TB_Loaiphong.TABLENAME,TB_Loaiphong.MATRO,thongtintro.getmMatro());
                        MainActivity.dataSQLite.deleteid(TB_Thongtintro.TABLENAME, TB_Thongtintro.MATRO, thongtintro.getmMatro());
                        Toast.makeText(ThongtinnhatroInf.this, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        ThongtinnhatroInf.super.finish();
                        startActivity(new Intent(ThongtinnhatroInf.this, DanhsachNhatro.class));
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        btnBackInfNhatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkchangedtext){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThongtinnhatroInf.this);
                    alertDialog.setTitle("Thông báo!").setMessage("Bạn có chắc không muốn lưu lại những thay đổi?");
                    alertDialog.setCancelable(true);
                    alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ThongtinnhatroInf.this, "Không lưu thay đổi!", Toast.LENGTH_SHORT).show();
                            ThongtinnhatroInf.super.onBackPressed();
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
                    ThongtinnhatroInf.super.onBackPressed();
                }
            }
        });

        btnSaveNhatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentro = editTenTro.getText().toString().trim();
                String diachitro = edtDiachi.getText().toString().trim();
                String giadienstr = edtGiadien.getText().toString().trim();
                String gianuocstr= edtGianuoc.getText().toString().trim();
                double giadien = 0;
                double gianuoc = 0;
                NumberFormat usFormat = NumberFormat.getNumberInstance(Locale.ITALY);
                try {
                    giadien = usFormat.parse(giadienstr).doubleValue();
                    gianuoc = usFormat.parse(gianuocstr).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(tentro.isEmpty()){
                    Toast.makeText(ThongtinnhatroInf.this, "Vui lòng nhập tên nhà trọ !", Toast.LENGTH_SHORT).show();
                }else if(diachitro.isEmpty()){
                    Toast.makeText(ThongtinnhatroInf.this, "Vui lòng địa chỉ nhà trọ !", Toast.LENGTH_SHORT).show();
                }else if(giadienstr.isEmpty()){
                    Toast.makeText(ThongtinnhatroInf.this, "Vui lòng nhập giá điện nhà trọ !", Toast.LENGTH_SHORT).show();
                }else if(giadien< 0){
                    Toast.makeText(ThongtinnhatroInf.this, "Vui lòng nhập giá điện hợp lệ !", Toast.LENGTH_SHORT).show();
                }else if(gianuocstr.isEmpty()){
                    Toast.makeText(ThongtinnhatroInf.this, "Vui lòng nhập giá nước nhà trọ !", Toast.LENGTH_SHORT).show();
                }else if(gianuoc < 0){
                    Toast.makeText(ThongtinnhatroInf.this, "Vui lòng nhập giá nước hợp lệ !", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dataSQLite.updateNhatro(new Thongtintro(matro,tentro, diachitro, giadien, gianuoc));
                    Toast.makeText(ThongtinnhatroInf.this, "Lưu thông tin trọ thành công!", Toast.LENGTH_SHORT).show();
                    ThongtinnhatroInf.super.onBackPressed();
                }
            }
        });
    }


    private void SetDataNhaTro() {
        editTenTro.setText(thongtintro.getmTentro());
        edtDiachi.setText(thongtintro.getmDiachi());
        String formatedGD = NumberFormat.getInstance(new Locale("it", "IT")).format((thongtintro.getmGiadien()));
        edtGiadien.setText(formatedGD);
        String formatedGN = NumberFormat.getInstance(new Locale("it", "IT")).format((thongtintro.getmGianuoc()));
        edtGianuoc.setText(formatedGN);
    }

    private void AnhXa() {
        editTenTro = (EditText) findViewById(R.id.editTenTroInf);
        edtDiachi= (EditText) findViewById(R.id.edtDiachinf);
        edtGiadien = (EditText) findViewById(R.id.edtGiadienInf);
        edtGianuoc= (EditText) findViewById(R.id.edtGianuocInf);
        btnDeleteNhatro = (Button) findViewById(R.id.btnDeleteNhatroInf);
        btnBackInfNhatro = (ImageButton) findViewById(R.id.btnBackInfNhatro);
        btnSaveNhatro = (ImageButton) findViewById(R.id.btnSaveInfNhatro);
    }
}
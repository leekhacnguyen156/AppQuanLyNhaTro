package com.example.quanlynhatro.Activity.TaoNhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.HomeMain;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Nhatro.DanhsachNhatro;
import com.example.quanlynhatro.Adapter.DSLoaiphongAdapter;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Loaiphong;
import com.example.quanlynhatro.SQLite.ModelData.TB_Thongtintro;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Thietlaploaiphong extends AppCompatActivity {

    private ListView lvDanhsachloaiphong;
    private ImageButton btnOkDSLoaiphong;
    private Thongtintro thongtintro;
    private ArrayList<Loaiphong> arrLoaiphong = new ArrayList<>();
    private DSLoaiphongAdapter dsLoaiphongAdapter;
    private Button btnAddLoaiphong;
    private ImageButton btnHuythemtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thietlaploaiphong);
        AnhXa();
        thongtintro = MainActivity.dataSQLite.getThongtintronew();
        SetAdapterDSLoaiphong();

        btnHuythemtro.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                thongtintro = MainActivity.dataSQLite.getThongtintronew();
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Thietlaploaiphong.this);
                alertDialog.setTitle("Thông báo !");
                alertDialog.setMessage("Bạn muốn hủy thêm trọ ?");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.dataSQLite.deleteid(TB_Thongtintro.TABLENAME, TB_Thongtintro.MATRO, thongtintro.getmMatro());
                        dialog.dismiss();
                        Thietlaploaiphong.super.onBackPressed();
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

        btnOkDSLoaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrLoaiphong.size() <= 0){
                    Toast.makeText(Thietlaploaiphong.this,"Vui lòng thêm ít nhất 1 loại trọ, để quản lý trọ !", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Thietlaploaiphong.this, "Tạo nhà trọ thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Thietlaploaiphong.this, HomeMain.class);
                    intent.putExtra("matro", thongtintro.getmMatro());
                    startActivity(intent);
                    Thietlaploaiphong.super.finish();
                }
            }
        });

        btnAddLoaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogAddLoaiphong();
            }
        });
    }


    private void SetAdapterDSLoaiphong() {
        arrLoaiphong = MainActivity.dataSQLite.getLoaiphongtheomatro(thongtintro.getmMatro());
        dsLoaiphongAdapter = new DSLoaiphongAdapter(arrLoaiphong, Thietlaploaiphong.this,R.layout.line_loaiphong);
        lvDanhsachloaiphong.setAdapter(dsLoaiphongAdapter);
        dsLoaiphongAdapter.notifyDataSetChanged();
    }

    private void AnhXa() {
        lvDanhsachloaiphong = (ListView) findViewById(R.id.listviewdsLoaiThem);
        btnOkDSLoaiphong = (ImageButton) findViewById(R.id.btnOkThemDSLoaitro);
        btnAddLoaiphong = (Button) findViewById(R.id.btnAddLoaiphongdialog);
        btnHuythemtro = (ImageButton) findViewById(R.id.btnHuythemtroTLLP);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    private void ShowDialogAddLoaiphong(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_themloaiphong);
        final EditText edtTenloaiphongthem = (EditText) dialog.findViewById(R.id.edtTenLoaiphongThem);
        final EditText edtGialoaiphongthem = (EditText) dialog.findViewById(R.id.edtGialoaiphongThem);
        Button btnOkthemloaiphong = (Button) dialog.findViewById(R.id.btnOkthemloaiphong);
        Button btnHuythemloaiphong = (Button) dialog.findViewById(R.id.btnHuythemloaiphong);

        edtGialoaiphongthem.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtGialoaiphongthem.setText(formated);
                        edtGialoaiphongthem.setSelection(formated.length());
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

        btnOkthemloaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tenloaiphong = edtTenloaiphongthem.getText().toString().trim();
                String Gialoaiphong = edtGialoaiphongthem.getText().toString().trim();
                double giaphong = 0;
                NumberFormat usFormat = NumberFormat.getNumberInstance(Locale.ITALY);
                try {
                    giaphong = usFormat.parse(Gialoaiphong).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(Tenloaiphong.isEmpty()){
                    Toast.makeText(Thietlaploaiphong.this, "Vui lòng nhập tên loại phòng !", Toast.LENGTH_SHORT).show();
                }else if(Gialoaiphong.isEmpty()){
                    Toast.makeText(Thietlaploaiphong.this, "Vui lòng nhập giá loại phòng !", Toast.LENGTH_SHORT).show();
                }else if(giaphong< 0){
                    Toast.makeText(Thietlaploaiphong.this, "Vui lòng nhập giá loại phòng hợp lệ !", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dataSQLite.add1Loaiphong(new Loaiphong(thongtintro.getmMatro(), Tenloaiphong, giaphong));
                    SetAdapterDSLoaiphong();
                    dialog.dismiss();
                }
            }
        });
        btnHuythemloaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DeleteLoaiPhong(){
        SetAdapterDSLoaiphong();
    }
}
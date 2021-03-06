package com.example.quanlynhatro.Activity.Phongtro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.Khachtro.DanhsachKhachtro;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Adapter.SpinnerAdapterLoaiPhong;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Hoadon;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Phongtro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Quanlythue;

import java.util.ArrayList;

public class Thongtinphongtro extends AppCompatActivity {

    private int maphong = -1;
    private int matro = -1, vt = -1;
    private EditText edtTenphongtro;
    private EditText edtSokhachhientai;
    private EditText edtSokhachtoida;
    private Spinner spinnerLoaiphong;
    private Phongtro phongtro;
    private ImageButton btnBack;
    private ImageButton btnSave;
    private Button btnDelete;
    private ArrayList<Loaiphong> arrayloaiphong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinphongtro_inf);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        matro = extras.getInt("matro", -1);
        maphong = extras.getInt("maphong",-1);
        phongtro = MainActivity.dataSQLite.get1Phongtrotheomaphong(maphong);
        MainActivity.dataSQLite.themkhachhientai(phongtro);
        Anhxa();
        SetDataPhongtro();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thongtinphongtro.super.onBackPressed();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(Thongtinphongtro.this);
                alertDialog.setTitle("Th??ng b??o x??a!!");
                alertDialog.setMessage("B???n mu???n x??a ph??ng tr??? n??y ? \n\nL??U ??: N???u x??a ph??ng tr??? n??y, t???t c??? kh??ch tr???, phi???u thu?? c???a kh??ch tr??? thu???c ph??ng, h??a ????n s??? ?????ng lo???t b??? x??a!!!");
                alertDialog.setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.dataSQLite.deleteid(TB_Phongtro.TABLENAME, TB_Phongtro.MAPHONG, phongtro.getmMaphong());
                        MainActivity.dataSQLite.deleteid(TB_Hoadon.TABLENAME,TB_Hoadon.MAPHONG,phongtro.getmMaphong());
                        ArrayList<Khachhang> arKhachhangxoa = MainActivity.dataSQLite.getKhachhangtheomaphong(phongtro.getmMaphong());
                        for(Khachhang kh : arKhachhangxoa){
                            MainActivity.dataSQLite.deleteid(TB_Quanlythue.TABLENAME,TB_Quanlythue.MAKHACH,kh.getmMakhach());
                        }
                        MainActivity.dataSQLite.deleteid(TB_Khachhang.TABLENAME,TB_Khachhang.MAPHONG,phongtro.getmMaphong());
                        Toast.makeText(Thongtinphongtro.this, "???? x??a th??nh c??ng!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Thongtinphongtro.super.onBackPressed();
                    }
                });
                alertDialog.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSokhachtoida.getText().toString().isEmpty()) {
                    Toast.makeText(Thongtinphongtro.this, "Vui l??ng nh???p s??? l?????ng kh??ch t???i ??a !", Toast.LENGTH_SHORT).show();
                }else if(MainActivity.dataSQLite.checktenphongtroupdate(edtSokhachtoida.getText().toString(), maphong, phongtro.getmMaloai(), matro) > 0){
                    Toast.makeText(Thongtinphongtro.this, "T??n ph??ng ???? t???n t???i !", Toast.LENGTH_SHORT).show();
                }else {
                    int sokhachtd = Integer.parseInt(edtSokhachtoida.getText().toString());
                    int sokhachht = Integer.parseInt(edtSokhachhientai.getText().toString());
                    if(sokhachtd <sokhachht){
                        Toast.makeText(Thongtinphongtro.this, "Vui l??ng nh???p l???i s??? l?????ng kh??ch t???i ??a !", Toast.LENGTH_SHORT).show();
                    }else {
                        Phongtro phongtro = new Phongtro(arrayloaiphong.get(vt).getmMaloai(), edtTenphongtro.getText().toString(), Integer.parseInt(edtSokhachhientai.getText().toString()), Integer.parseInt(edtSokhachtoida.getText().toString()));
                        MainActivity.dataSQLite.updatephongtro(phongtro, TB_Phongtro.TABLENAME, maphong);
                        Thongtinphongtro.super.onBackPressed();
                        Toast.makeText(Thongtinphongtro.this, "L??u th??ng tin ph??ng tr??? th??nh c??ng!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    private void SetDataPhongtro(){
        arrayloaiphong = new ArrayList<>();
        edtTenphongtro.setText(phongtro.getmTenphong());
        edtSokhachhientai.setText(phongtro.getmKhachhientai()+"");
        edtSokhachtoida.setText(phongtro.getmKhachtoida()+"");
        arrayloaiphong = MainActivity.dataSQLite.getLoaiphongtheomatro(matro);
        //Creating adapter for spinner
        SpinnerAdapterLoaiPhong spinnerAdapterLoaiPhong = new SpinnerAdapterLoaiPhong(this, R.layout.spinner_line_loaiphong, arrayloaiphong);
        // attaching data adapter to spinner
        spinnerLoaiphong.setAdapter(spinnerAdapterLoaiPhong);
        for(int i = 0; i < arrayloaiphong.size();i++){
            if(arrayloaiphong.get(i).getmMaloai() == phongtro.getmMaloai()){
                spinnerLoaiphong.setSelection(i);
                break;
            }
        }
        //?????i v??? tr?? ????? l???y lo???i ph??ng ???? ch???n
        spinnerAdapterLoaiPhong.notifyDataSetChanged();
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
    }

    private void Anhxa() {
        edtTenphongtro = (EditText) findViewById(R.id.edtTenPhongTroInf);
        edtSokhachhientai = (EditText) findViewById(R.id.edtSokhachhientaiInf);
        edtSokhachtoida = (EditText) findViewById(R.id.edtSokhachtoidaInf);
        spinnerLoaiphong = (Spinner) findViewById(R.id.spinnerLoaiphongInf);
        btnBack = findViewById(R.id.btnBackInfPhongtro);
        btnSave = findViewById(R.id.btnSaveInfPhongtro);
        btnDelete = findViewById(R.id.btnDeletePhongtroInf);
    }


}
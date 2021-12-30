package com.example.quanlynhatro.Activity.Nhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.HomeMain;
import com.example.quanlynhatro.Activity.Khachtro.ThongtinkhachtroInf;
import com.example.quanlynhatro.Activity.Loaiphong.DanhsachLoaiphong;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.TaoNhatro.Themtro;
import com.example.quanlynhatro.Adapter.DSNhatroAdapter;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.ModelApp.Traphongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Thongtintro;

import java.util.ArrayList;
import java.util.Collections;

public class DanhsachNhatro extends AppCompatActivity {

    private ListView lvNhatro;
    private ArrayList<Thongtintro> arThongtintro = new ArrayList<>();
    private DSNhatroAdapter dsNhatroAdapter;
    private Button btnThemtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_nhatro);
        AnhXa();
        SetAdapterDSNhatro();

        btnThemtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhsachNhatro.this, Themtro.class));
            }
        });


        lvNhatro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhsachNhatro.this, HomeMain.class);
                intent.putExtra("matro", arThongtintro.get(position).getmMatro());
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        lvNhatro = (ListView) findViewById(R.id.listviewDSNhatro);
        btnThemtro = (Button) findViewById(R.id.btnAddNhaTro);
    }

    private void SetAdapterDSNhatro() {
        arThongtintro = MainActivity.dataSQLite.getThongtintro();
        Collections.reverse(arThongtintro);
        dsNhatroAdapter = new DSNhatroAdapter(arThongtintro, DanhsachNhatro.this, R.layout.line_nhatro);
        lvNhatro.setAdapter(dsNhatroAdapter);
        dsNhatroAdapter.notifyDataSetChanged();
    }

    public void Deletenhatro(){
        SetAdapterDSNhatro();
    }

    @Override
    protected void onResume() {
        SetAdapterDSNhatro();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DanhsachNhatro.this);
        alertDialog.setTitle("Thông báo !").setMessage("Bạn có muốn thoát?");
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
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
}
package com.example.quanlynhatro.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.quanlynhatro.Activity.Khachtro.DanhsachKhachtro;
import com.example.quanlynhatro.Activity.Nhatro.DanhsachNhatro;
import com.example.quanlynhatro.Activity.Nhatro.NhatroActivity;
import com.example.quanlynhatro.Activity.Phongtro.DanhsachPhongtro;
import com.example.quanlynhatro.Activity.Thongke.Thongke;
import com.example.quanlynhatro.Activity.ThueTraPhong.ThueTraPhong;
import com.example.quanlynhatro.Activity.Thutien.HoadonThutien;
import com.example.quanlynhatro.Adapter.CategoryAdapter;
import com.example.quanlynhatro.ModelApp.CategoryHome;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.R;

import java.util.ArrayList;

public class HomeMain extends AppCompatActivity {

    private GridView gridViewCategory;
    private ArrayList<CategoryHome> arrayCategory = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private int matro = -1;
    private TextView txtTenNhaTroMain;
    private Thongtintro thongtintro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        matro = getIntent().getIntExtra("matro", -1);
        AnhXa();

        // Add Category
        AddCategoryHome();

        // Set Adapter
        categoryAdapter = new CategoryAdapter(arrayCategory, HomeMain.this, R.layout.line_category);
        gridViewCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        loaddata();

        //Intent to another Activity
        gridViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(HomeMain.this, NhatroActivity.class);
                        intent.putExtra("matro", matro);
                        break;
                    case 1:
                        intent = new Intent(HomeMain.this, DanhsachPhongtro.class);
                        intent.putExtra("matro", matro);
                        break;
                    case 2:
                        intent = new Intent(HomeMain.this, DanhsachKhachtro.class);
                        intent.putExtra("matro", matro);
                        break;
                    case 3:
                        intent = new Intent(HomeMain.this, HoadonThutien.class);
                        intent.putExtra("matro", matro);
                        break;
                    case 4:
                        intent = new Intent(HomeMain.this, Thongke.class);
                        intent.putExtra("matro", matro);
                        break;
                    case 5:
                        intent = new Intent(HomeMain.this, ThueTraPhong.class);
                        intent.putExtra("matro", matro);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        loaddata();
        super.onResume();
    }

    //Category button
    private void AddCategoryHome() {
        arrayCategory.add(new CategoryHome("Tổng quan",R.drawable.motelhome));
        arrayCategory.add(new CategoryHome("Phòng trọ",R.drawable.room));
        arrayCategory.add(new CategoryHome("Khách trọ",R.drawable.customer));
        arrayCategory.add(new CategoryHome("Thu tiền",R.drawable.money));
        arrayCategory.add(new CategoryHome("Thống kê",R.drawable.barchart));
        arrayCategory.add(new CategoryHome("Thuê/trả phòng", R.drawable.document));
    }

    private void AnhXa() {
        gridViewCategory = (GridView) findViewById(R.id.gridViewCategory);
        txtTenNhaTroMain = (TextView) findViewById(R.id.txtTenNhaTroMain);
    }

    private void loaddata(){
        thongtintro = MainActivity.dataSQLite.getThongtintrotheomatro(matro);
        txtTenNhaTroMain.setText(thongtintro.getmTentro());
    }

}
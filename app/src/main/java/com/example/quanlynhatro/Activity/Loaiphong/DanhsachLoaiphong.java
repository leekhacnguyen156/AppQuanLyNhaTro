package com.example.quanlynhatro.Activity.Loaiphong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Adapter.AdapterDSLoaiPhongActivity;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class DanhsachLoaiphong extends AppCompatActivity {

    private ImageButton btnBackDSLP;
    private Button btnAddLoaiphong;
    private ImageButton btnDeleteLoaiphong;
    int matro = -1;
    private SearchView searchView;
    private ListView listviewDSLP;
    private ArrayList<Loaiphong> arrLoaiphong = new ArrayList<>();
    private AdapterDSLoaiPhongActivity adapterDSLoaiPhongActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_loaiphong);
        matro = getIntent().getIntExtra("matro", -1);
        AnhXa();
        SetAdapterDSLoaiphong();
        btnBackDSLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DanhsachLoaiphong.super.onBackPressed();
            }
        });

        btnAddLoaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogAddLoaiphong();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    adapterDSLoaiPhongActivity.filter("");
                    listviewDSLP.clearTextFilter();

                }else{
                    adapterDSLoaiPhongActivity.filter(newText);
                }
                return true;
            }
        });
    }

    private void AnhXa() {
        btnBackDSLP = (ImageButton) findViewById(R.id.btnBackDSLP);
        btnAddLoaiphong = (Button) findViewById(R.id.btnAddLoaiphong);
        listviewDSLP = (ListView) findViewById(R.id.listviewDSLP);
        btnDeleteLoaiphong = (ImageButton) findViewById(R.id.btnDeleteLoaiphong);
        searchView = (SearchView) findViewById(R.id.searchLP);
    }

    private void SetAdapterDSLoaiphong(){
        arrLoaiphong = MainActivity.dataSQLite.getLoaiphongtheomatro(matro);
        adapterDSLoaiPhongActivity = new AdapterDSLoaiPhongActivity(arrLoaiphong, DanhsachLoaiphong.this, R.layout.line_loaiphong);
        listviewDSLP.setAdapter(adapterDSLoaiPhongActivity);
        adapterDSLoaiPhongActivity.notifyDataSetChanged();
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
                    Toast.makeText(DanhsachLoaiphong.this, "Vui lòng nhập tên loại phòng !", Toast.LENGTH_SHORT).show();
                }else if(Gialoaiphong.isEmpty()){
                    Toast.makeText(DanhsachLoaiphong.this, "Vui lòng nhập giá loại phòng !", Toast.LENGTH_SHORT).show();
                }else if(giaphong< 0){
                    Toast.makeText(DanhsachLoaiphong.this, "Vui lòng nhập giá loại phòng hợp lệ !", Toast.LENGTH_SHORT).show();
                }else if(MainActivity.dataSQLite.getloaiphongtrungten(Tenloaiphong, matro) > 0 ){
                    Toast.makeText(DanhsachLoaiphong.this, "Tên loại phòng đã tồn tại !", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dataSQLite.add1Loaiphong(new Loaiphong(matro, Tenloaiphong, giaphong));
                    Toast.makeText(DanhsachLoaiphong.this, "Thêm loại phòng thành công!", Toast.LENGTH_SHORT).show();
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
package com.example.quanlynhatro.Activity.Thutien.FragmentHoadon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Nhatro.DanhsachNhatro;
import com.example.quanlynhatro.Activity.Thutien.HoadonEdit;
import com.example.quanlynhatro.Activity.Thutien.HoadonThutien;
import com.example.quanlynhatro.Activity.Thutien.TaoHD;
import com.example.quanlynhatro.ModelApp.Hoadon;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Hoadon;
import com.example.quanlynhatro.SQLite.ModelData.TB_Thongtintro;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HoaDonView extends AppCompatActivity {

    private TextView txtSLPhong, btndeleteHD;
    private TextView txtDGPhong, txtNameHD;
    private TextView txtTTPhong, edtNgayThu;

    private TextView txtSLDien, txtGhichuview;
    private TextView txtDGDien;
    private TextView txtTTDien;

    private TextView txtSLNuoc;
    private TextView txtDGNuoc;
    private TextView txtTTNuoc;

    private TextView txtSLPhatsinh;
    private TextView txtDGPhatsinh;
    private TextView txtTTPhatsinh;

    private TextView txtTT, txtNgayThu;

    private TextView txtStatusHDM;

    private ImageButton btnCloseHD;

    private Button btnEditHoaDon, btnThanhToanHD;

    int maloai;
    int maphong;
    private static SimpleDateFormat dfhien = new SimpleDateFormat("dd-MM-yyyy");
    private static SimpleDateFormat dfvaodata = new SimpleDateFormat("yyyy-MM-dd");
    public static String datetoString (Date date){
        return dfhien.format(date);
    }
    private String todaydate = dfvaodata.format(Calendar.getInstance().getTime());
    private String todaydateHien = dfhien.format(Calendar.getInstance().getTime());
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

    private Loaiphong loaiphong;
    private Hoadon hoadon;
    private Phongtro phongtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_view);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        maloai = extras.getInt("maloai", -1);
        maphong = extras.getInt("maphong", -1);
        AnhXa();
        LoadData();

        btnCloseHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonView.super.onBackPressed();
            }
        });

        btndeleteHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hoadon.getmTrangthai() == 0){
                    AlertDialog.Builder alertXN = new AlertDialog.Builder(HoaDonView.this);
                    alertXN.setTitle("Thông báo xóa");
                    alertXN.setMessage("Bạn có muốn xóa hóa đơn ???");
                    alertXN.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.dataSQLite.deleteid(TB_Hoadon.TABLENAME, TB_Hoadon.MAHD, hoadon.getmMahd());
                            Toast.makeText(HoaDonView.this, "Thanh toán thành công !", Toast.LENGTH_SHORT).show();
                            finish();
                            Toast.makeText(HoaDonView.this, "Xóa hóa đơn thành công !", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alertXN.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertXN.show();
                }else{
                    Toast.makeText(HoaDonView.this, "Hóa đơn đã thanh toán vui lòng không sửa !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEditHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hoadon.getmTrangthai() == 0){
                    Intent intent = new Intent(HoaDonView.this , HoadonEdit.class);
                    Bundle extras = new Bundle();
                    extras.putInt("maloai",maloai);
                    extras.putInt("maphong", maphong);
                    intent.putExtras(extras);
                    startActivity(intent);
                }else{
                    Toast.makeText(HoaDonView.this, "Hóa đơn đã thanh toán vui lòng không sửa !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnThanhToanHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hoadon.getmTrangthai() == 0){
                    AlertDialog.Builder alertXN = new AlertDialog.Builder(HoaDonView.this);
                    alertXN.setTitle("Thông báo thanh toán");
                    alertXN.setMessage("Bạn có muốn thanh toán ???");
                    alertXN.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            MainActivity.dataSQLite.updatestatushoadon(Stringtodate(todaydate),1, hoadon.getmMahd());
                            Toast.makeText(HoaDonView.this, "Thanh toán thành công !", Toast.LENGTH_SHORT).show();
                            LoadData();
                        }
                    });

                    alertXN.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertXN.show();
                }else{
                    Toast.makeText(HoaDonView.this, "Hóa đơn đã thanh toán !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void LoadData() {
        String[] separated = HoadonThutien.txtNamhientaiHD.getText().toString().split("-");
        int month = Integer.parseInt(separated[0]);
        int year = Integer.parseInt(separated[1]);
        loaiphong = MainActivity.dataSQLite.getLoaiphongtheomaloai(maloai);
        hoadon = MainActivity.dataSQLite.gethoadontheomaphongnew(maphong, month, year);
        phongtro = MainActivity.dataSQLite.get1Phongtrotheomaphong(maphong);

        if(hoadon.getmTrangthai() == 0){
            txtStatusHDM.setBackgroundResource(R.drawable.custom_status_fail);
            txtStatusHDM.setText("Chưa đóng");
            txtNgayThu.setText("Ngày tạo:");
        }else{
            txtNgayThu.setText("Ngày thu:");
            txtStatusHDM.setBackgroundResource(R.drawable.custom_status_success);
            txtStatusHDM.setText("Đã đóng");
        }

        txtNameHD.setText("HD.Phòng " + phongtro.getmTenphong()+"");

        ////////
        edtNgayThu.setText(todaydateHien);

        txtSLPhong.setText("1");
        txtDGPhong.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmTongtien() - ( ( hoadon.getmGiadien() * hoadon.getmSodien() ) + ( hoadon.getmGianuoc() * hoadon.getmSonuoc()) + ( hoadon.getmPhiphatsinh() * hoadon.getmSophatsinh() ) ) ));
        txtTTPhong.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmTongtien() - ( ( hoadon.getmGiadien() * hoadon.getmSodien() ) + ( hoadon.getmGianuoc() * hoadon.getmSonuoc()) + ( hoadon.getmPhiphatsinh() * hoadon.getmSophatsinh() ) ) ));

        txtSLDien.setText(hoadon.getmSodien() + "");
        txtDGDien.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmGiadien()));
        txtTTDien.setText(NumberFormat.getInstance(new Locale("it", "IT")).format((hoadon.getmGiadien() * hoadon.getmSodien())));

        txtSLNuoc.setText(hoadon.getmSonuoc() + "");
        txtDGNuoc.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmGianuoc()));
        txtTTNuoc.setText(NumberFormat.getInstance(new Locale("it", "IT")).format((hoadon.getmSonuoc() * hoadon.getmGianuoc())));

        txtSLPhatsinh.setText(hoadon.getmSophatsinh() + "");
        txtDGPhatsinh.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmPhiphatsinh()));
        txtTTPhatsinh.setText(NumberFormat.getInstance(new Locale("it", "IT")).format((hoadon.getmSophatsinh() * hoadon.getmPhiphatsinh())));

        txtTT.setText(NumberFormat.getInstance(new Locale("it", "IT")).format(hoadon.getmTongtien()));

        txtGhichuview.setText("Ghi chú: " + hoadon.getmGhichu());
    }

    private void AnhXa() {
        txtSLPhong = (TextView) findViewById(R.id.slPhong);
        txtDGPhong = (TextView) findViewById(R.id.dgPhong);
        txtTTPhong = (TextView) findViewById(R.id.ttPhong);

        txtSLDien = (TextView) findViewById(R.id.slDien);
        txtDGDien = (TextView) findViewById(R.id.dgDien);
        txtTTDien = (TextView) findViewById(R.id.ttDien);

        txtSLNuoc = (TextView) findViewById(R.id.slNuoc);
        txtDGNuoc = (TextView) findViewById(R.id.dgNuoc);
        txtTTNuoc = (TextView) findViewById(R.id.ttNuoc);

        txtSLPhatsinh = (TextView) findViewById(R.id.slPhatsinh);
        txtDGPhatsinh = (TextView) findViewById(R.id.dgPhatsinh);
        txtTTPhatsinh = (TextView) findViewById(R.id.ttPhatsinh);

        btndeleteHD = (TextView) findViewById(R.id.btndeleteHDV);

        txtTT = (TextView) findViewById(R.id.txtTongtien);

        txtNameHD = (TextView) findViewById(R.id.txtNameHD);

        txtStatusHDM = (TextView) findViewById(R.id.txtStatusHDM);

        edtNgayThu = (TextView) findViewById(R.id.edtNgayThu);

        txtNgayThu = (TextView) findViewById(R.id.txtNgayThu);

        btnCloseHD = (ImageButton) findViewById(R.id.btnCloseHD);

        btnEditHoaDon = (Button) findViewById(R.id.btnEditHoaDon);

        btnThanhToanHD = (Button) findViewById(R.id.btnThanhToanHD);

        txtGhichuview = (TextView) findViewById(R.id.txtGhichuview);
    }

    @Override
    protected void onResume() {
        LoadData();
        super.onResume();
    }
}
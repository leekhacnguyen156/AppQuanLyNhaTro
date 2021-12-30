package com.example.quanlynhatro.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.Loaiphong.DanhsachLoaiphong;
import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.ModelApp.Khachhang;
import com.example.quanlynhatro.ModelApp.Loaiphong;
import com.example.quanlynhatro.ModelApp.Phongtro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Hoadon;
import com.example.quanlynhatro.SQLite.ModelData.TB_Khachhang;
import com.example.quanlynhatro.SQLite.ModelData.TB_Loaiphong;
import com.example.quanlynhatro.SQLite.ModelData.TB_Phongtro;
import com.example.quanlynhatro.SQLite.ModelData.TB_Quanlythue;
import com.example.quanlynhatro.SQLite.ModelData.TB_Traphongtro;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterDSLoaiPhongActivity extends BaseAdapter {

    private ArrayList<Loaiphong> arrayLoaiphong;
    private DanhsachLoaiphong contexdanhsachLoaiphong;
    private ArrayList<Loaiphong> arLP;
    private int layout;

    public AdapterDSLoaiPhongActivity(ArrayList<Loaiphong> arrayLoaiphong, DanhsachLoaiphong contexdanhsachLoaiphong, int layout) {
        this.arrayLoaiphong = arrayLoaiphong;
        this.contexdanhsachLoaiphong = contexdanhsachLoaiphong;
        this.layout = layout;
        this.arLP = new ArrayList<Loaiphong>();
        this.arLP.addAll(arrayLoaiphong);
    }

    @Override
    public int getCount() {
        return arrayLoaiphong.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        TextView txtTenLoaiphong;
        TextView txtGiaLoaiphong;
        ImageButton btnDeleteLoaiphong;
        RelativeLayout layoutRLLoaiphong;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) contexdanhsachLoaiphong.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtTenLoaiphong = (TextView) convertView.findViewById(R.id.txtTenLoaiphong);
            viewHolder.txtGiaLoaiphong = (TextView) convertView.findViewById(R.id.txtGiaLoaiphong);
            viewHolder.btnDeleteLoaiphong = (ImageButton) convertView.findViewById(R.id.btnDeleteLoaiphong);
            viewHolder.layoutRLLoaiphong = (RelativeLayout) convertView.findViewById(R.id.layoutRLLoaiphong);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Loaiphong loaiphong = arrayLoaiphong.get(position);
        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((loaiphong.getmGialoai()));

        viewHolder.txtTenLoaiphong.setText(loaiphong.getmTenloai());
        viewHolder.txtGiaLoaiphong.setText(formated + " VNĐ");

        viewHolder.btnDeleteLoaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayLoaiphong.size() <= 1) {
                    Toast.makeText(contexdanhsachLoaiphong, "Nhà trọ phải có ít nhất 1 loại trọ !", Toast.LENGTH_SHORT).show();
                } else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(contexdanhsachLoaiphong);
                    alertDialog.setTitle("Thông báo xóa!");
                    alertDialog.setMessage("Bạn muốn xóa loại phòng trọ này ?\n\nNếu xóa Loại phòng này, tất cả phòng trọ, khách hàng, hóa đơn... có liên quan sẽ bị xóa theo!!");
                    alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ArrayList<Phongtro> phongtros = MainActivity.dataSQLite.getPhongtrotheomaloai(loaiphong.getmMaloai());
                            for (Phongtro phongtro : phongtros){
                                ArrayList<Khachhang> khachhangs = new ArrayList<>();
                                khachhangs.clear();
                                khachhangs = MainActivity.dataSQLite.getKhachhangtheomaphong(phongtro.getmMaphong());
                                for (Khachhang khachhang : khachhangs){
                                    MainActivity.dataSQLite.deleteid(TB_Quanlythue.TABLENAME, TB_Quanlythue.MAKHACH, khachhang.getmMakhach());
                                    MainActivity.dataSQLite.deleteid(TB_Traphongtro.TABLENAME, TB_Quanlythue.MAKHACH, khachhang.getmMakhach());
                                }
                                MainActivity.dataSQLite.deleteid(TB_Khachhang.TABLENAME, TB_Khachhang.MAPHONG, phongtro.getmMaphong());
                                MainActivity.dataSQLite.deleteid(TB_Hoadon.TABLENAME, TB_Hoadon.MAPHONG, phongtro.getmMaphong());
                            }
                            MainActivity.dataSQLite.deleteid(TB_Phongtro.TABLENAME, TB_Phongtro.MALOAI, loaiphong.getmMaloai());
                            MainActivity.dataSQLite.deleteid(TB_Loaiphong.TABLENAME, TB_Loaiphong.MALOAI, loaiphong.getmMaloai());
                            contexdanhsachLoaiphong.DeleteLoaiPhong();
                            Toast.makeText(contexdanhsachLoaiphong, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
            }
        });

        viewHolder.layoutRLLoaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogAddLoaiphong(contexdanhsachLoaiphong, loaiphong);
            }
        });

        return convertView;
    }

    private void ShowDialogAddLoaiphong(final DanhsachLoaiphong contexdanhsachLoaiphong, final Loaiphong loaiphong){
        final Dialog dialog = new Dialog(contexdanhsachLoaiphong);
        dialog.setContentView(R.layout.dialog_sualoaiphong);
        final EditText edtTenloaiphongsua = (EditText) dialog.findViewById(R.id.edtTenLoaiphongSua);
        final EditText edtGialoaiphongsua = (EditText) dialog.findViewById(R.id.edtGialoaiphongSua);
        Button btnOksualoaiphong = (Button) dialog.findViewById(R.id.btnOkSualoaiphong);
        Button btnHuysualoaiphong = (Button) dialog.findViewById(R.id.btnHuySualoaiphong);

        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((loaiphong.getmGialoai()));
        edtTenloaiphongsua.setText(loaiphong.getmTenloai()+"");
        edtGialoaiphongsua.setText(formated);

        edtGialoaiphongsua.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    if (!s.toString().equals(current)) {
                        String cleanString = s.toString().replaceAll("[,.]", "");
                        double parsed = Double.parseDouble(cleanString);
                        String formated = NumberFormat.getInstance(new Locale("it", "IT")).format((parsed));
                        current = formated;
                        edtGialoaiphongsua.setText(formated);
                        edtGialoaiphongsua.setSelection(formated.length());
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

        btnOksualoaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tenloaiphong = edtTenloaiphongsua.getText().toString().trim();
                String Gialoaiphong = edtGialoaiphongsua.getText().toString().trim();
                double giaphong = 0;
                NumberFormat usFormat = NumberFormat.getNumberInstance(Locale.ITALY);
                try {
                    giaphong = usFormat.parse(Gialoaiphong).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(Tenloaiphong.isEmpty()){
                    Toast.makeText(contexdanhsachLoaiphong, "Vui lòng nhập tên loại phòng !", Toast.LENGTH_SHORT).show();
                }else if(Gialoaiphong.isEmpty()){
                    Toast.makeText(contexdanhsachLoaiphong, "Vui lòng nhập giá loại phòng !", Toast.LENGTH_SHORT).show();
                }else if(giaphong< 0){
                    Toast.makeText(contexdanhsachLoaiphong, "Vui lòng nhập giá loại phòng hợp lệ !", Toast.LENGTH_SHORT).show();
                }else if(MainActivity.dataSQLite.getloaiphongtrungtenupdate(Tenloaiphong, loaiphong.getmMaloai(), loaiphong.getmMatro()) > 0){
                    Toast.makeText(contexdanhsachLoaiphong, "Tên loại phòng đã tồn tại !", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dataSQLite.updateloaiphong(new Loaiphong(loaiphong.getmMaloai(),loaiphong.getmMatro(),Tenloaiphong,giaphong));
                    Toast.makeText(contexdanhsachLoaiphong, "Sửa thông tin loại phòng thành công!", Toast.LENGTH_SHORT).show();
                    contexdanhsachLoaiphong.DeleteLoaiPhong();
                    dialog.dismiss();
                }
            }
        });
        btnHuysualoaiphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        arrayLoaiphong.clear();
        if(charText.length() == 0){
            arrayLoaiphong.addAll(arLP);
        }else{
            for(Loaiphong loaiphong : arLP){
                if(loaiphong.getmTenloai().toLowerCase(Locale.getDefault()).contains(charText)){
                    arrayLoaiphong.add(loaiphong);
                }
            }
        }
        notifyDataSetChanged();
    }
}

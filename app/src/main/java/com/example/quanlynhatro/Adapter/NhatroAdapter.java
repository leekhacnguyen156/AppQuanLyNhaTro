package com.example.quanlynhatro.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.Activity.Nhatro.DanhsachNhatro;
import com.example.quanlynhatro.ModelApp.Thongtintro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.ModelData.TB_Thongtintro;

import java.util.ArrayList;

public class NhatroAdapter extends BaseAdapter {
    private ArrayList<Thongtintro> arrayThongtintro;
    private Context context;
    private int layout;

    public NhatroAdapter(ArrayList<Thongtintro> arrayThongtintro, Context context, int layout) {
        this.arrayThongtintro = arrayThongtintro;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayThongtintro.size();
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
        TextView txtTentro;
        CardView cardViewDST;
//        ImageButton btnSuanhatro,btnXoanhatro;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtTentro = (TextView) convertView.findViewById(R.id.txtTenTro);
            viewHolder.cardViewDST = (CardView) convertView.findViewById(R.id.cardviewDST);
//            viewHolder.btnSuanhatro = (ImageButton) convertView.findViewById(R.id.editNhatro);
//            viewHolder.btnXoanhatro = (ImageButton) convertView.findViewById(R.id.deleteNhatro);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NhatroAdapter.ViewHolder) convertView.getTag();
        }

        final Thongtintro thongtintro = arrayThongtintro.get(position);

        viewHolder.txtTentro.setText(thongtintro.getmTentro());

        //nut sua nha tro
//        viewHolder.btnSuanhatro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, Thongtintro.class));
//            }
//        });
//
//        //nut xoa nha tro
//        viewHolder.btnXoanhatro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                XacNhanXoa(thongtintro);
//            }
//        });
        return convertView;
    }

    private void XacNhanXoa(final Thongtintro thongtintro){
        AlertDialog.Builder alertXN = new AlertDialog.Builder(context);
        alertXN.setTitle("Thông báo xóa");
        alertXN.setMessage("Bạn có muốn xóa ???");
        alertXN.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.dataSQLite.deleteid(TB_Thongtintro.TABLENAME, TB_Thongtintro.MATRO, thongtintro.getmMatro());
                Intent intent = new Intent(context, DanhsachNhatro.class);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

        alertXN.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertXN.show();
    }

}

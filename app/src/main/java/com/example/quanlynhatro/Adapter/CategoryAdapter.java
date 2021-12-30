package com.example.quanlynhatro.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.quanlynhatro.ModelApp.CategoryHome;
import com.example.quanlynhatro.R;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private ArrayList<CategoryHome> arrayCategory;
    private Context context;
    private int layout;

    public CategoryAdapter(ArrayList<CategoryHome> arrayCategory, Context context, int layout) {
        this.arrayCategory = arrayCategory;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayCategory.size();
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
        ImageView imgIcon;
        TextView txtNameCategory;
        CardView cardViewCategory;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtNameCategory = (TextView) convertView.findViewById(R.id.txtNameCategory);
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.imageviewIcon);
            viewHolder.cardViewCategory = (CardView) convertView.findViewById(R.id.cardviewCategory);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CategoryHome categoryHome = arrayCategory.get(position);

        viewHolder.txtNameCategory.setText(categoryHome.getmName()+"");
        viewHolder.imgIcon.setImageResource(categoryHome.getmIcon());

        return convertView;
    }
}

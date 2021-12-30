package com.example.quanlynhatro.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Chuadong;
import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Dadong;
import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Taohoadon;

public class AdapterTablayout extends FragmentStatePagerAdapter {

    public AdapterTablayout(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment_Taohoadon fragment_taohoadon = new Fragment_Taohoadon();
                return  fragment_taohoadon;
            case 1:
                Fragment_Chuadong fragment_chuadong = new Fragment_Chuadong();
                return  fragment_chuadong;
            case 2:
                Fragment_Dadong fragment_dadong = new Fragment_Dadong();
                return  fragment_dadong;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 0:
                title = "Tạo hóa đơn";
                break;
            case 1:
                title = "Chưa đóng";
                break;
            case 2:
                title = "Đã đóng";
                break;
            default:
                return null;
        }
        return title;
    }
}

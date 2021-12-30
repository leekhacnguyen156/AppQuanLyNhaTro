package com.example.quanlynhatro.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quanlynhatro.Activity.ThueTraPhong.Fragment_Thue;
import com.example.quanlynhatro.Activity.ThueTraPhong.Fragment_Tra;
public class AdapterTabThueTra extends FragmentStatePagerAdapter {

    public AdapterTabThueTra(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment_Thue fragment_thue = new Fragment_Thue();
                return  fragment_thue;
            case 1:
                Fragment_Tra fragment_tra = new Fragment_Tra();
                return  fragment_tra;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 0:
                title = "Thuê phòng";
                break;
            case 1:
                title = "Trả phòng";
                break;
            default:
                return null;
        }
        return title;
    }
}

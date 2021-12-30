package com.example.quanlynhatro.Activity.ThueTraPhong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;

import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Chuadong;
import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Dadong;
import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Taohoadon;
import com.example.quanlynhatro.Adapter.AdapterTabThueTra;
import com.example.quanlynhatro.Adapter.AdapterTablayout;
import com.example.quanlynhatro.R;
import com.google.android.material.tabs.TabLayout;

public class ThueTraPhong extends AppCompatActivity {

    private TabLayout tabLayoutTT;
    private ViewPager viewPagerTT;
    private ImageButton btnBack;
    public static int matro = -1;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matro = getIntent().getIntExtra("matro", -1);
        setContentView(R.layout.activity_thue_tra_phong);
        AnhXa();
        AdapterTabThueTra adapterTablayout = new AdapterTabThueTra(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerTT.setAdapter(adapterTablayout);
        tabLayoutTT.setupWithViewPager(viewPagerTT);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThueTraPhong.super.onBackPressed();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    Fragment_Thue.adapterDSPhieuthue.filter("");
                    Fragment_Thue.listView.clearTextFilter();
                    Fragment_Tra.adapterDSPhieutra.filter("");
                    Fragment_Tra.listView.clearTextFilter();
                } else {
                    Fragment_Thue.adapterDSPhieuthue.filter(newText);
                    Fragment_Tra.adapterDSPhieutra.filter(newText);
                }
                return true;
            }
        });
    }
    private void AnhXa() {
        tabLayoutTT = (TabLayout) findViewById(R.id.tabLayoutTT);
        viewPagerTT = (ViewPager) findViewById(R.id.viewPagerTT);
        searchView = (SearchView) findViewById(R.id.searchTTP);
        btnBack = (ImageButton) findViewById(R.id.btnBackThuetra);
    }
}
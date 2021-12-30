package com.example.quanlynhatro.Activity.Thutien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Chuadong;
import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Dadong;
import com.example.quanlynhatro.Activity.Thutien.FragmentHoadon.Fragment_Taohoadon;
import com.example.quanlynhatro.Adapter.AdapterTablayout;
import com.example.quanlynhatro.R;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HoadonThutien extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static TextView txtNamhientaiHD;
    public static ImageButton btnPreviousNamHD, btnNextNamHD, btnBackHoadonThutien;
    public static int matro = -1;
    public static SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon_thutien);
        AnhXa();
        SET_FRAGMENT();
        matro = getIntent().getIntExtra("matro", -1);

        //Set Locale
        Locale locale = new Locale("vie");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
        @SuppressLint("SimpleDateFormat") DateFormat dateFormatter = new SimpleDateFormat("MM-yyyy");
        dateFormatter.setLenient(false);
        Date currentTime = Calendar.getInstance().getTime();
        String dateyear = dateFormatter.format(currentTime);

        txtNamhientaiHD.setText(dateyear);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    Fragment_Chuadong.adapterStatusHoaDon.filter("");
                    Fragment_Chuadong.listViewCD.clearTextFilter();

                    Fragment_Taohoadon.adapterDSPhongTaoHD.filter("");
                    Fragment_Taohoadon.listPhongTaohoadon.clearTextFilter();

                    Fragment_Dadong.adapterStatusHoaDonD.filter("");
                    Fragment_Dadong.listViewDD.clearTextFilter();

                } else {
                    Fragment_Chuadong.adapterStatusHoaDon.filter(newText);
                    Fragment_Taohoadon.adapterDSPhongTaoHD.filter(newText);
                    Fragment_Dadong.adapterStatusHoaDonD.filter(newText);
                }
                return true;
            }
        });

        txtNamhientaiHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int yearSelected;
                int monthSelected;


                //Set default values
                Calendar calendar = Calendar.getInstance();
                yearSelected = calendar.get(Calendar.YEAR);
                monthSelected = calendar.get(Calendar.MONTH);

                MonthYearPickerDialogFragment dialogFragment = MonthYearPickerDialogFragment
                        .getInstance(monthSelected, yearSelected);

                dialogFragment.show(getSupportFragmentManager(), null);

                dialogFragment.setOnDateSetListener(new MonthYearPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int year, int monthOfYear) {
                        txtNamhientaiHD.setText((monthOfYear + 1) + "-" + year);
                    }
                });
            }
        });

        btnBackHoadonThutien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoadonThutien.super.onBackPressed();
            }
        });

        btnPreviousNamHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] separated = txtNamhientaiHD.getText().toString().split("-");
                int month = Integer.parseInt(separated[0]);
                int year = Integer.parseInt(separated[1]);
                if (month == 1) {
                    year = year - 1;
                    month = 12;
                } else {
                    month = month - 1;
                }

                txtNamhientaiHD.setText(month + "-" + year);
            }
        });

        btnNextNamHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] separated = txtNamhientaiHD.getText().toString().split("-");
                int month = Integer.parseInt(separated[0]);
                int year = Integer.parseInt(separated[1]);
                if (month == 12) {
                    year = year + 1;
                    month = 1;
                } else {
                    month = month + 1;
                }

                txtNamhientaiHD.setText(month + "-" + year);
            }
        });
    }

    private void SET_FRAGMENT() {
        AdapterTablayout adapterTablayout = new AdapterTablayout(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapterTablayout);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void AnhXa() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        txtNamhientaiHD = (TextView) findViewById(R.id.txtNamhientaiHD);
        btnPreviousNamHD = (ImageButton) findViewById(R.id.btnPreviousNamHD);
        btnNextNamHD = (ImageButton) findViewById(R.id.btnNextNamHD);
        btnBackHoadonThutien = (ImageButton) findViewById(R.id.btnBackHoadonThutien);
        searchView = (SearchView) findViewById(R.id.searchHD);
    }
}
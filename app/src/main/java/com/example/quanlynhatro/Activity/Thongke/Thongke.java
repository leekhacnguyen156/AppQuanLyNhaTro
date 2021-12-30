package com.example.quanlynhatro.Activity.Thongke;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhatro.Activity.MainActivity;
import com.example.quanlynhatro.ModelApp.Hoadon;
import com.example.quanlynhatro.R;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Thongke extends AppCompatActivity {
    private ImageButton btnBack;
    private BarChart barChart;
    private ArrayList<BarEntry> barEntries;
    private int matro;
    private TextView txtNamhientaiTK;
    private ImageButton btnPreviousNamTK, btnNextNamTK;
    private ArrayList<Hoadon> hoadonArrayList = new ArrayList<>();
    private float[] Tong = new float[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        matro = getIntent().getIntExtra("matro", -1);
        btnBack = (ImageButton) findViewById(R.id.btnBackThongke);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thongke.super.onBackPressed();
            }
        });

        AnhXa();

        LoadChart();

        //Set Locale
        Locale locale = new Locale("vie");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
        @SuppressLint("SimpleDateFormat") DateFormat dateFormatter = new SimpleDateFormat("yyyy");
        dateFormatter.setLenient(false);
        Date currentTime = Calendar.getInstance().getTime();
        final String dateyear = dateFormatter.format(currentTime);

        txtNamhientaiTK.setText(dateyear);

        btnPreviousNamTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String separated = txtNamhientaiTK.getText().toString();
                int year = Integer.parseInt(separated);
                year = year - 1;
                txtNamhientaiTK.setText(year+"");
                LoadChart();
            }
        });

        btnNextNamTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String separated = txtNamhientaiTK.getText().toString();
                int year = Integer.parseInt(separated);
                year = year + 1;
                txtNamhientaiTK.setText(year+"");
                LoadChart();
            }
        });

        txtNamhientaiTK.setOnClickListener(new View.OnClickListener() {
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
                        txtNamhientaiTK.setText(year+"");
                        LoadChart();
                    }
                });
            }
        });

    }

    private void LoadChart() {
        String separated = txtNamhientaiTK.getText().toString();
        int year = Integer.parseInt(separated);
        hoadonArrayList = MainActivity.dataSQLite.gethoadontheomatrotk(matro, year);
        for(int i = 0; i < Tong.length; i++){
            Tong[i] = 0;
        }
        for (Hoadon a : hoadonArrayList){
            Tong[a.getmThang() - 1] += a.getmTongtien();
        }

        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, Tong[0]));
        barEntries.add(new BarEntry(2, Tong[1]));
        barEntries.add(new BarEntry(3, Tong[2]));
        barEntries.add(new BarEntry(4, Tong[3]));
        barEntries.add(new BarEntry(5, Tong[4]));
        barEntries.add(new BarEntry(6, Tong[5]));
        barEntries.add(new BarEntry(7, Tong[6]));
        barEntries.add(new BarEntry(8, Tong[7]));
        barEntries.add(new BarEntry(9, Tong[8]));
        barEntries.add(new BarEntry(10, Tong[9]));
        barEntries.add(new BarEntry(11, Tong[10]));
        barEntries.add(new BarEntry(12, Tong[11]));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(30f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barData.setDrawValues(false);
        barChart.setData(barData);
        barChart.getDescription().setText("Thống kê các tháng trong năm");
        barChart.animateY(2000);
    }

    private void AnhXa() {
        barChart = (BarChart) findViewById(R.id.chartThongke);
        txtNamhientaiTK = (TextView) findViewById(R.id.txtNamhientaiTK);
        btnNextNamTK = (ImageButton) findViewById(R.id.btnNextNamTK);
        btnPreviousNamTK = (ImageButton) findViewById(R.id.btnPreviousNamTK);
    }
}
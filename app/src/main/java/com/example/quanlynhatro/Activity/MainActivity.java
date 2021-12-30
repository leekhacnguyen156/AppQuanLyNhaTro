package com.example.quanlynhatro.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quanlynhatro.Activity.Nhatro.DanhsachNhatro;
import com.example.quanlynhatro.R;
import com.example.quanlynhatro.SQLite.DataSQLite;

public class MainActivity extends AppCompatActivity {

    public static DataSQLite dataSQLite;
    private TextView nameMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSQLite = new DataSQLite(MainActivity.this);

        //
        AnhXa();

        // Set animation logoMain
        nameMain.animate()
                .alpha(1f)
                .setDuration(4000);
        //Hàm chạy time
        Thread time = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4500);
                            startActivity(new Intent(MainActivity.this, DanhsachNhatro.class));
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        time.start();
    }

    private void AnhXa() {
        nameMain = (TextView) findViewById(R.id.name_Main);
    }
}
package com.example.cuahangbangiay.GUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.example.cuahangbangiay.DAO.SQLite;
import com.example.cuahangbangiay.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static ChipNavigationBar chipNavigationBar;
    public static NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chipNavigationBar = findViewById(R.id.menu);
        chipNavigationBar.setItemSelected(R.id.home, true);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main).navigate(R.id.sanPham);
                        break;
                    case R.id.gio_hang:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main).navigate(R.id.gioHang);
                        break;
                    case R.id.tai_khoan:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main).navigate(R.id.taikhoan);
                        break;

                }
            }
        });
    }
}
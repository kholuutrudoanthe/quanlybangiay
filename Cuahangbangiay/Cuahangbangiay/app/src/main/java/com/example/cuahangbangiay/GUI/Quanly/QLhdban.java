package com.example.cuahangbangiay.GUI.Quanly;

import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.DS_HDN;
import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.timkiem_mhd;
import static com.example.cuahangbangiay.DAO.HoaDonXuatDAO.DS_HDX;
import static com.example.cuahangbangiay.DAO.HoaDonXuatDAO.timkiem_hdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cuahangbangiay.BUS.HoaDonBanAdapter;
import com.example.cuahangbangiay.BUS.HoaDonNhapAdapter;
import com.example.cuahangbangiay.R;

public class QLhdban extends AppCompatActivity {
    ImageView img;
    EditText timkiem;
    Button cn;
    RecyclerView recy_2;
    HoaDonBanAdapter hoaDonBanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlhdban);
        recy_2=findViewById(R.id.recy_2);
        img=findViewById(R.id.img);
        timkiem=findViewById(R.id.timkiem);
        cn=findViewById(R.id.cn);
        recy_2.setLayoutManager(new LinearLayoutManager(this));
        hoaDonBanAdapter = new HoaDonBanAdapter(DS_HDX(), this);
        recy_2.setAdapter(hoaDonBanAdapter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonBanAdapter = new HoaDonBanAdapter(DS_HDX(), QLhdban.this);
                recy_2.setAdapter(hoaDonBanAdapter);

            }
        });
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (timkiem.getText().toString().trim().isEmpty()) {
                    hoaDonBanAdapter = new HoaDonBanAdapter(DS_HDX(), QLhdban.this);
                    recy_2.setAdapter(hoaDonBanAdapter);
                } else {
                    hoaDonBanAdapter = new HoaDonBanAdapter(timkiem_hdb(timkiem.getText().toString().trim()), QLhdban.this);
                    recy_2.setAdapter(hoaDonBanAdapter);
                }
            }
        });
    }
}
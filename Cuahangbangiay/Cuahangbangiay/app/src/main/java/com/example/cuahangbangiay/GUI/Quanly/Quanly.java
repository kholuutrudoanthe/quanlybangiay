package com.example.cuahangbangiay.GUI.Quanly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cuahangbangiay.GUI.MainActivityLogIn;
import com.example.cuahangbangiay.GUI.Quanly.QLsanpham.QLSanPham;
import com.example.cuahangbangiay.R;

public class Quanly extends AppCompatActivity {
    private CardView qlsp, qlkh, qln, qlx;
    private Button dx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly);
        qlsp = findViewById(R.id.qlsp);
        qlkh = findViewById(R.id.qlkh);
        qln = findViewById(R.id.qln);
        qlx = findViewById(R.id.qlx);
        dx = findViewById(R.id.dx);
        qlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quanly.this, QLSanPham.class));
            }
        });
        qlkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quanly.this, QLkhachhang.class));
            }
        });
        qln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quanly.this, QLNhap.class));
            }
        });
        qlx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quanly.this, QLhdban.class));
            }
        });
        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quanly.this, MainActivityLogIn.class));
            }
        });
    }
}
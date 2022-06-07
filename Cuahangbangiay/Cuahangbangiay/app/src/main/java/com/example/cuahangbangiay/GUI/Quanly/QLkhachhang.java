package com.example.cuahangbangiay.GUI.Quanly;

import static com.example.cuahangbangiay.DAO.SanPhamDao.Tk_sp;
import static com.example.cuahangbangiay.DAO.SanPhamDao.getall_sanpham;
import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.getall_tk;
import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.timkhiekh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cuahangbangiay.BUS.khachhang_Adapter;
import com.example.cuahangbangiay.BUS.sp_adapter;
import com.example.cuahangbangiay.GUI.Quanly.QLsanpham.QLSanPham;
import com.example.cuahangbangiay.GUI.Quanly.QLsanpham.QLthemsp;
import com.example.cuahangbangiay.R;

public class QLkhachhang extends AppCompatActivity {
    ImageView img;
    EditText timkiem;
    Button cn;
    RecyclerView recy_2;
    khachhang_Adapter khachhang_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlkhachhang);
        img=findViewById(R.id.img);
        timkiem=findViewById(R.id.timkiem);
        cn=findViewById(R.id.cn);
        recy_2=findViewById(R.id.recy_2);
        recy_2.setLayoutManager(new LinearLayoutManager(this));
        khachhang_adapter = new khachhang_Adapter(getall_tk(), QLkhachhang.this);
        recy_2.setAdapter(khachhang_adapter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khachhang_adapter = new khachhang_Adapter(getall_tk(), QLkhachhang.this);
                recy_2.setAdapter(khachhang_adapter);
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
                    khachhang_adapter = new khachhang_Adapter(getall_tk(), QLkhachhang.this);
                    recy_2.setAdapter(khachhang_adapter);
                } else {
                    khachhang_adapter = new khachhang_Adapter(timkhiekh(timkiem.getText().toString().trim()), QLkhachhang.this);
                    recy_2.setAdapter(khachhang_adapter);

                }
            }
        });
    }
}
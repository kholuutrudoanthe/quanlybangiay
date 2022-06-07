package com.example.cuahangbangiay.GUI.Quanly.QLsanpham;

import static com.example.cuahangbangiay.DAO.SanPhamDao.Tk_sp;
import static com.example.cuahangbangiay.DAO.SanPhamDao.getall_sanpham;
import static com.example.cuahangbangiay.DAO.SanPhamDao.them_sanpham;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbangiay.BUS.sp_adapter;
import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

public class QLSanPham extends AppCompatActivity {
    ImageView img;
    EditText timkiem;
    Button add,cn;
    RecyclerView recy_2;
    sp_adapter sp_adapter;
    public static String Masp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsan_pham);
        img=findViewById(R.id.img);
        timkiem=findViewById(R.id.timkiem);
        add=findViewById(R.id.add);
        cn=findViewById(R.id.cn);
        recy_2=findViewById(R.id.recy_2);
        recy_2.setLayoutManager(new GridLayoutManager(QLSanPham.this,2));
        sp_adapter = new sp_adapter(getall_sanpham(), QLSanPham.this);
        recy_2.setAdapter(sp_adapter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QLSanPham.this,QLthemsp.class));
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp_adapter = new sp_adapter(getall_sanpham(), QLSanPham.this);
                recy_2.setAdapter(sp_adapter);


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
                    sp_adapter = new sp_adapter(getall_sanpham(), QLSanPham.this);
                    recy_2.setAdapter(sp_adapter);

                } else {
                    sp_adapter = new sp_adapter(Tk_sp(timkiem.getText().toString().trim()), QLSanPham.this);
                    recy_2.setAdapter(sp_adapter);

                }
            }
        });
    }


}
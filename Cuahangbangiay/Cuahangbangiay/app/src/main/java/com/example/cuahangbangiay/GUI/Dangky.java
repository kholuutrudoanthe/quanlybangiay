package com.example.cuahangbangiay.GUI;

import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.kiem_tra_tk;
import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.them_taikhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbangiay.DTO.Taikhoan;
import com.example.cuahangbangiay.R;

public class Dangky extends AppCompatActivity {
    EditText hoten,diachi,sdt,email,mk;
    Button btndk;
    TextView dn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        hoten=findViewById(R.id.hoten);
        diachi=findViewById(R.id.diachi);
        sdt=findViewById(R.id.sdt);
        email=findViewById(R.id.email);
        mk=findViewById(R.id.mk);
        btndk=findViewById(R.id.btndk);
        dn=findViewById(R.id.dn);
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString().trim();
                String hoten1 = hoten.getText().toString().trim();
                String mk1 = mk.getText().toString().trim();
                String sdt1 = sdt.getText().toString().trim();
                String diachi1 = diachi.getText().toString().trim();
                if (email1.isEmpty() && hoten1.isEmpty() && mk1.isEmpty()&& sdt1.isEmpty()&& diachi1.isEmpty() ) {
                    Toast.makeText(Dangky.this, "Vui lòng ko để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Taikhoan taikhoan=new Taikhoan(email1,hoten1,mk1,sdt1,diachi1);
                    if (!kiem_tra_tk(email1)) {
                        if (them_taikhoan(taikhoan)) {
                            Toast.makeText(Dangky.this, "Đã đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivityLogIn.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Dangky.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Dangky.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dangky.this, MainActivityLogIn.class));
                finish();
            }
        });
    }
}
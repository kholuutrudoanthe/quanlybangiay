package com.example.cuahangbangiay.GUI;

import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.kiem_tra_dn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbangiay.DAO.SQLite;
import com.example.cuahangbangiay.GUI.Quanly.Quanly;
import com.example.cuahangbangiay.R;

public class MainActivityLogIn extends AppCompatActivity {
    public static SQLite SQLite_;
    public static String email_;
    Button btndn;
    EditText mk, email;
    TextView dk;
    public static int vitri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log_in);
        SQLite_ = new SQLite(MainActivityLogIn.this);
        btndn = findViewById(R.id.btndn);
        email = findViewById(R.id.email);
        mk = findViewById(R.id.mk);
        dk = findViewById(R.id.dk);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityLogIn.this, Dangky.class));
            }
        });
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString().trim();
                String mk1 = mk.getText().toString().trim();
                email_ = email1;
                if(email1.equals("admin") || email1.equals("admin") ){
                    Toast.makeText(MainActivityLogIn.this, "Đăng nhập quản trị thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityLogIn.this, Quanly.class);
                    startActivity(intent);
                    vitri=0;
                }else{
                    if (email1.isEmpty() || mk1.isEmpty()) {
                        Toast.makeText(MainActivityLogIn.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        if (kiem_tra_dn(email1, mk1)) {
                            email_=email1;
                            Toast.makeText(MainActivityLogIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivityLogIn.this, MainActivity.class);
                            startActivity(intent);
                            vitri=1;

                        } else {
                            Toast.makeText(MainActivityLogIn.this, "Tài khoản chưa được đăng ký", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });
    }
}
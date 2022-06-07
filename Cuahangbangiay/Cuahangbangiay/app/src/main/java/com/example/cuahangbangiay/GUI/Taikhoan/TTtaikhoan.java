package com.example.cuahangbangiay.GUI.Taikhoan;

import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.Sua_taikhoan;
import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.get_thongtin;
import static com.example.cuahangbangiay.GUI.MainActivityLogIn.email_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cuahangbangiay.DTO.Taikhoan;
import com.example.cuahangbangiay.R;

public class TTtaikhoan extends AppCompatActivity {
    EditText ten, email, sdt, diachi;
    ImageView image;
    Button btndn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tttaikhoan);
        ten = findViewById(R.id.ten);
        image = findViewById(R.id.image);
        email = findViewById(R.id.email);
        sdt = findViewById(R.id.sdt);
        diachi = findViewById(R.id.diachi);
        btndn = findViewById(R.id.btndn);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        getdata();
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Sua_taikhoan(ten.getText().toString().trim(), sdt.getText().toString().trim(), diachi.getText().toString().trim())) {
                    Toast.makeText(TTtaikhoan.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(TTtaikhoan.this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void getdata() {
        Taikhoan taikhoan = get_thongtin(email_);
        ten.setText(taikhoan.getHoten());
        email.setText(taikhoan.getEmail());
        sdt.setText(taikhoan.getSdt());
        diachi.setText(taikhoan.getDiachi());
    }
}
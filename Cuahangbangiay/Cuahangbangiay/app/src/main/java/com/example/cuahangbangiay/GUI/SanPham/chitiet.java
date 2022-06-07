package com.example.cuahangbangiay.GUI.SanPham;

import static com.example.cuahangbangiay.DAO.GioHangDAO.them_giohang;
import static com.example.cuahangbangiay.DAO.SanPhamDao.get_sp;
import static com.example.cuahangbangiay.GUI.MainActivity.currencyVN;
import static com.example.cuahangbangiay.GUI.MainActivityLogIn.email_;
import static com.example.cuahangbangiay.GUI.Quanly.QLsanpham.QLSanPham.Masp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.cuahangbangiay.DTO.GioHang;
import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.R;

public class chitiet extends AppCompatActivity {
    ImageView img, image, tru, cong;
    TextView tensp, soluong, giatien, sl, tongtien;
    Button btn;
    int tien;
    int tientong;
    int slsp = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        img = findViewById(R.id.img);
        image = findViewById(R.id.image);
        tru = findViewById(R.id.tru);
        cong = findViewById(R.id.cong);
        tensp = findViewById(R.id.tensp);
        soluong = findViewById(R.id.soluong);
        giatien = findViewById(R.id.giatien);
        sl = findViewById(R.id.sl);
        tongtien = findViewById(R.id.tongtien);
        btn = findViewById(R.id.btn);
        onclick();
        getdata();

    }

    private void onclick() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slsp = slsp + 1;
                tientong = tien * slsp;
                sl.setText(slsp + "");
                tongtien.setText("Tổng tiền: " + currencyVN.format(tientong));
            }
        });
        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (slsp != 1) {
                    slsp = slsp - 1;
                    tientong = tien * slsp;
                    sl.setText(slsp + "");
                    tongtien.setText("Tổng tiền: " + currencyVN.format(tientong));
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GioHang giohang = new GioHang(0, email_, Masp, slsp, tientong, 0);

                if (them_giohang(giohang)) {
                    Toast.makeText(chitiet.this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(chitiet.this, "Đặt hàng thất bại!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void getdata() {
        SanPham sanpham = get_sp(Masp);
        Bitmap bitmap = BitmapFactory.decodeByteArray(sanpham.getHINHANH(), 0, sanpham.getHINHANH().length);
        image.setImageBitmap(bitmap);
        tensp.setText(sanpham.getTENSP());
        soluong.setText("Số lượng còn: " + sanpham.getSOLUONG());
        giatien.setText(currencyVN.format(sanpham.getGIATIEN()));
        tien = sanpham.getGIATIEN();
        tientong = tien;
        tongtien.setText("Tổng tiền: " + currencyVN.format(tien));
    }
}
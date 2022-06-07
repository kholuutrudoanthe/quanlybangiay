package com.example.cuahangbangiay.GUI.Quanly.QLsanpham;

import static com.example.cuahangbangiay.DAO.SanPhamDao.Sua_sanpham;
import static com.example.cuahangbangiay.DAO.SanPhamDao.get_sp;
import static com.example.cuahangbangiay.DAO.SanPhamDao.them_sanpham;
import static com.example.cuahangbangiay.GUI.Quanly.QLsanpham.QLSanPham.Masp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

public class QLsuasp extends AppCompatActivity {
    EditText masp, tensp, soluong, giatien;
    Button btnthoat, btnthem;
    ImageView getImg;
    private Uri img_uri;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsuasp);
        getImg = findViewById(R.id.img);
        masp = findViewById(R.id.masp);
        tensp = findViewById(R.id.tensp);
        soluong = findViewById(R.id.soluong);
        giatien = findViewById(R.id.giatien);
        btnthem = findViewById(R.id.btnthem);
        btnthoat = findViewById(R.id.btnthoat);
        soluong.setText("0");
        SanPham sanPham=get_sp(Masp);
        masp.setText(sanPham.getMASP());
        tensp.setText(sanPham.getTENSP());
        soluong.setText(sanPham.getSOLUONG()+"");
        giatien.setText(sanPham.getGIATIEN()+"");
        bitmap = BitmapFactory.decodeByteArray(sanPham.getHINHANH(), 0, sanPham.getHINHANH().length);
        getImg.setImageBitmap(bitmap);

        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera(v);
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msp = masp.getText().toString().trim();
                String tsp = tensp.getText().toString().trim();
                String sl = soluong.getText().toString().trim();
                String gt = giatien.getText().toString().trim();
                //chuyển đổi từ drawable về bit map
                BitmapDrawable bitmapDrawable = (BitmapDrawable) getImg.getDrawable();
                bitmap = bitmapDrawable.getBitmap();
                //chuyển đổi từ bitmap về byte
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhanh = byteArrayOutputStream.toByteArray();
                if (msp.isEmpty() || tsp.isEmpty() || sl.isEmpty() || gt.isEmpty()) {
                    Toast.makeText(QLsuasp.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    SanPham sanPham = new SanPham(msp, tsp, Integer.parseInt(gt), Integer.parseInt(sl), hinhanh);
                    if (Sua_sanpham(sanPham)) {
                        onBackPressed();
                        Toast.makeText(QLsuasp.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QLsuasp.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            img_uri = data.getData();
            getImg.setImageURI(img_uri);
        }

    }

    public void Camera(View view) {
        PopupMenu popupMenu = new PopupMenu(QLsuasp.this, view);
        popupMenu.inflate(R.menu.menu_camera);
        popupMenu.setGravity(Gravity.CENTER);
        popupMenu.setForceShowIcon(true);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //dùng thư viên   'com.github.dhaval2404:imagepicker:2.1'
                    case R.id.menu_camera:
                        ImagePicker.with(QLsuasp.this)
                                .crop(16f, 16f)
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .cameraOnly()
                                .start();
                        return true;
                    case R.id.menu_chon_anh:
                        ImagePicker.with(QLsuasp.this)
                                .crop(16f, 16f)
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .galleryOnly()
                                .start();
                        return true;

                }
                return false;
            }
        });
        popupMenu.show();
    }
}
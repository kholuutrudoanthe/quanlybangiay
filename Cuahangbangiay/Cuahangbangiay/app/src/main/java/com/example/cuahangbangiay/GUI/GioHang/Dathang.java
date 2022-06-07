package com.example.cuahangbangiay.GUI.GioHang;

import static com.example.cuahangbangiay.DAO.GioHangDAO.Sua_trangthai;
import static com.example.cuahangbangiay.DAO.GioHangDAO.getall_giohang;
import static com.example.cuahangbangiay.DAO.HoaDonXuatDAO.Them_HDX;
import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.get_thongtin;
import static com.example.cuahangbangiay.GUI.MainActivity.currencyVN;
import static com.example.cuahangbangiay.GUI.MainActivityLogIn.email_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbangiay.BUS.GioHangAdapter;
import com.example.cuahangbangiay.DTO.GioHang;
import com.example.cuahangbangiay.DTO.HoaDonBan;
import com.example.cuahangbangiay.DTO.Taikhoan;
import com.example.cuahangbangiay.R;

public class Dathang extends AppCompatActivity {
    RecyclerView list_item;
    ImageView image;
    LinearLayout layout1;
    GioHangAdapter giohangadapter;
    TextView ttt, ten, sdt, diachi;
    int tongtien_;
    Taikhoan taikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);
        list_item = findViewById(R.id.list_item);
        ttt = findViewById(R.id.ttt);
        ten = findViewById(R.id.ten);
        sdt = findViewById(R.id.sdt);
        diachi = findViewById(R.id.diachi);
        image = findViewById(R.id.image);
        layout1 = findViewById(R.id.layout1);
        taikhoan = get_thongtin(email_);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getall_giohang().size() != 0) {
                    for (GioHang giohang : getall_giohang()
                    ) {

                        HoaDonBan HoaDonBan = new HoaDonBan(0, giohang.getIdgh());
                        Them_HDX(HoaDonBan);
                        Sua_trangthai(giohang.getIdgh(), 1);
                    }
                }
                Toast.makeText(Dathang.this, "Đặt hàng thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });
        getdata1();
        getdata();
    }

    private void getdata1() {

        ten.setText("Họ tên: " + taikhoan.getHoten());
        if (taikhoan.getSdt().isEmpty()) {
            sdt.setText("SĐT: Thêm số điện thoại");
        } else {
            sdt.setText("SĐT: " + taikhoan.getSdt());
        }
        if (taikhoan.getDiachi().isEmpty()) {
            diachi.setText("Địa chỉ: Thêm địa chỉ");
        } else {
            diachi.setText("Địa chỉ: " + taikhoan.getDiachi());

        }
        if (getall_giohang().size() != 0) {
            for (GioHang giohang : getall_giohang()
            ) {
                tongtien_ = tongtien_ + giohang.getTongtien();
                ttt.setText(currencyVN.format(tongtien_));
            }
        }

    }

    private void getdata() {
        giohangadapter = new GioHangAdapter(getall_giohang(), Dathang.this, 1);
        list_item.setLayoutManager(new LinearLayoutManager(Dathang.this));
        list_item.setAdapter(giohangadapter);
        giohangadapter.notifyDataSetChanged();
    }
}
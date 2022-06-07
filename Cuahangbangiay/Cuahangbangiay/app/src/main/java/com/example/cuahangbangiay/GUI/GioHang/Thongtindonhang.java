package com.example.cuahangbangiay.GUI.GioHang;

import static com.example.cuahangbangiay.DAO.HoaDonXuatDAO.DS_HDX;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangbangiay.BUS.DonHangAdapter;
import com.example.cuahangbangiay.R;

public class Thongtindonhang extends AppCompatActivity {
    RecyclerView list_item;
    ImageView image;
    DonHangAdapter don_hangadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtindonhang);
        list_item = findViewById(R.id.list_item);
        image = findViewById(R.id.image);
        don_hangadapter = new DonHangAdapter(DS_HDX(), Thongtindonhang.this);
        list_item.setLayoutManager(new LinearLayoutManager(Thongtindonhang.this));
        list_item.setAdapter(don_hangadapter);
        don_hangadapter.notifyDataSetChanged();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
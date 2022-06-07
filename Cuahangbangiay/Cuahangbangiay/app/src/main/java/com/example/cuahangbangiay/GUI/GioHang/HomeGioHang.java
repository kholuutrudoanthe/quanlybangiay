package com.example.cuahangbangiay.GUI.GioHang;

import static com.example.cuahangbangiay.DAO.GioHangDAO.getall_giohang;
import static com.example.cuahangbangiay.GUI.MainActivity.chipNavigationBar;
import static com.example.cuahangbangiay.GUI.MainActivity.currencyVN;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuahangbangiay.BUS.GioHangAdapter;
import com.example.cuahangbangiay.DTO.GioHang;
import com.example.cuahangbangiay.R;


public class HomeGioHang extends Fragment {

    RecyclerView list_item;
    TextView  tongtien;
    LinearLayout layout_giohang, layout1;
    GioHangAdapter giohangadapter;
    LinearLayout dathang;
    Button muasp;
    int tongtien_;

    public HomeGioHang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gio_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_item = view.findViewById(R.id.list_item);
        layout1 = view.findViewById(R.id.layout1);
        tongtien = view.findViewById(R.id.tongtien);
        dathang = view.findViewById(R.id.dathang);
        muasp = view.findViewById(R.id.muasp);
        layout_giohang = view.findViewById(R.id.layout_giohang);
        getdata();
        getdata1();

        dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Dathang.class));
            }
        });
        muasp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipNavigationBar.setItemSelected(R.id.home, true);
            }
        });
    }
    private void getdata1() {

        if (getall_giohang().size()!=0) {
            layout1.setVisibility(View.VISIBLE);
            layout_giohang.setVisibility(View.GONE);
            for (GioHang giohang : getall_giohang()
            ) {
                tongtien_ = tongtien_ + giohang.getTongtien();
                tongtien.setText(currencyVN.format(tongtien_));
            }
        }else {
            layout1.setVisibility(View.GONE);
            layout_giohang.setVisibility(View.VISIBLE);
        }

    }

    private void getdata() {
        giohangadapter = new GioHangAdapter(getall_giohang(), getContext(),0);
        list_item.setLayoutManager(new LinearLayoutManager(getContext()));
        list_item.setAdapter(giohangadapter);
        giohangadapter.notifyDataSetChanged();

    }
}
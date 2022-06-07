package com.example.cuahangbangiay.GUI.Taikhoan;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cuahangbangiay.GUI.GioHang.Thongtindonhang;
import com.example.cuahangbangiay.GUI.MainActivityLogIn;
import com.example.cuahangbangiay.R;

public class HomeTaikhoan extends Fragment {
    LinearLayout layout2, dx, layout1;

    public HomeTaikhoan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_taikhoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout2 = view.findViewById(R.id.layout2);
        dx = view.findViewById(R.id.dx);
        layout1 = view.findViewById(R.id.layout1);
        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivityLogIn.class));
                getActivity().finish();
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TTtaikhoan.class));
            }
        });
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Thongtindonhang.class));
            }
        });
    }
}
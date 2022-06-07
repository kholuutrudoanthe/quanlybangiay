package com.example.cuahangbangiay.GUI.SanPham;

import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.DS_HDN;
import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.timkiem_mhd;
import static com.example.cuahangbangiay.DAO.SanPhamDao.Tk_sp;
import static com.example.cuahangbangiay.DAO.SanPhamDao.getall_sanpham;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cuahangbangiay.BUS.HoaDonNhapAdapter;
import com.example.cuahangbangiay.BUS.sp_adapter;
import com.example.cuahangbangiay.GUI.Quanly.QLNhap;
import com.example.cuahangbangiay.R;
public class HomeSanPham extends Fragment {
    EditText timkiem;
    sp_adapter sp_adapter;
    RecyclerView recy_2;
    public HomeSanPham() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_san_pham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recy_2 = view.findViewById(R.id.recy_2);
        timkiem = view.findViewById(R.id.timkiem);
        recy_2.setLayoutManager(new GridLayoutManager(getContext(),2));
        sp_adapter = new sp_adapter(getall_sanpham(), getContext());
        recy_2.setAdapter(sp_adapter);
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
                    sp_adapter = new sp_adapter(getall_sanpham(), getContext());
                    recy_2.setAdapter(sp_adapter);
                } else {
                    sp_adapter = new sp_adapter(Tk_sp(timkiem.getText().toString().trim()), getContext());
                    recy_2.setAdapter(sp_adapter);
                }
            }
        });
    }
}
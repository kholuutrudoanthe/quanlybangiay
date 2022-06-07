package com.example.cuahangbangiay.GUI.Quanly;

import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.DS_HDN;
import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.Them_HDN;
import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.timkiem_mhd;
import static com.example.cuahangbangiay.DAO.SanPhamDao.Sua_sl;
import static com.example.cuahangbangiay.DAO.SanPhamDao.getall_sanpham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbangiay.BUS.HoaDonNhapAdapter;
import com.example.cuahangbangiay.DTO.HoaDonNhap;
import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.DTO.Taikhoan;
import com.example.cuahangbangiay.R;

public class QLNhap extends AppCompatActivity {
    ImageView img;
    EditText timkiem;
    Button add,cn;
    RecyclerView recy_2;
    Dialog dialog;
    HoaDonNhapAdapter hoaDonNhapAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlnhap);
        img=findViewById(R.id.img);
        timkiem=findViewById(R.id.timkiem);
        add=findViewById(R.id.add);
        cn=findViewById(R.id.cn);
        recy_2=findViewById(R.id.recy_2);
        dialog = new Dialog(this);
        recy_2.setLayoutManager(new LinearLayoutManager(this));
        hoaDonNhapAdapter = new HoaDonNhapAdapter(DS_HDN(), this);
        recy_2.setAdapter(hoaDonNhapAdapter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getall_sanpham().size()==0){
                    Toast.makeText(QLNhap.this, "Vui lòng thêm sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.setContentView(R.layout.dialog_them_sua);
                setdialog(dialog);
                EditText mahdn = dialog.findViewById(R.id.mahd);
                Spinner spinner = dialog.findViewById(R.id.spinner);
                EditText tensp = dialog.findViewById(R.id.tensp);
                EditText giatien = dialog.findViewById(R.id.giatien);
                EditText tennv = dialog.findViewById(R.id.tennv);
                EditText soluong = dialog.findViewById(R.id.soluong);
                EditText tongtien = dialog.findViewById(R.id.tongtien);


                Button btnthem = dialog.findViewById(R.id.btnthem);
                Button btnthoat = dialog.findViewById(R.id.btnthoat);

                final ArrayAdapter adapter = new ArrayAdapter(QLNhap.this, R.layout.dropdown_item,getall_sanpham());
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinner.setSelection(position);
                        SanPham sanPham = (SanPham) spinner.getSelectedItem();
                        tensp.setText(sanPham.getTENSP());
                        giatien.setText(sanPham.getGIATIEN()+"");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                soluong.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (soluong.getText().toString().trim().isEmpty()) {
                            tongtien.setText("");
                        } else {
                            if (!giatien.getText().toString().isEmpty()){
                                tongtien.setText(Integer.parseInt(soluong.getText().toString().trim()) * Integer.parseInt(giatien.getText().toString().trim()) + "");

                            }

                        }
                    }
                });
                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mhd=mahdn.getText().toString().trim();
                        String tsp=tensp.getText().toString().trim();
                        String gt=giatien.getText().toString().trim();
                        String tnv=tennv.getText().toString().trim();
                        String sl=soluong.getText().toString().trim();
                        String tt=tongtien.getText().toString().trim();
                        SanPham sanPham = (SanPham) spinner.getSelectedItem();


                        if (mhd.isEmpty()||tsp.isEmpty()||gt.isEmpty()||tnv.isEmpty()||sl.isEmpty()||tt.isEmpty()){
                            Toast.makeText(QLNhap.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else {
                            HoaDonNhap HoaDonNhap=new HoaDonNhap(mhd,sanPham.getMASP(),tsp,tnv,Integer.parseInt(sl),sanPham.getGIATIEN(),Integer.parseInt(tt));
                            if (Them_HDN(HoaDonNhap)){
                                Sua_sl(sanPham.getMASP(),Integer.parseInt(sl)+sanPham.getSOLUONG());
                                dialog.dismiss();
                                Toast.makeText(QLNhap.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(QLNhap.this, "Mã hóa đơn đã tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();

            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonNhapAdapter = new HoaDonNhapAdapter(DS_HDN(), QLNhap.this);
                recy_2.setAdapter(hoaDonNhapAdapter);

            }
        });
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
                    hoaDonNhapAdapter = new HoaDonNhapAdapter(DS_HDN(), QLNhap.this);
                    recy_2.setAdapter(hoaDonNhapAdapter);
                } else {
                    hoaDonNhapAdapter = new HoaDonNhapAdapter(timkiem_mhd(timkiem.getText().toString().trim()), QLNhap.this);
                    recy_2.setAdapter(hoaDonNhapAdapter);
                }
            }
        });
    }
    public static void setdialog(Dialog dialog){

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams w = window.getAttributes();
        w.gravity = Gravity.CENTER;
        window.setAttributes(w);
        dialog.setCancelable(false);
    }
}
package com.example.cuahangbangiay.BUS;


import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.Sua_HoaDonNhap;
import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.Them_HDN;
import static com.example.cuahangbangiay.DAO.HoaDonNhapDAO.xoa_hdn;
import static com.example.cuahangbangiay.DAO.SanPhamDao.Sua_sl;
import static com.example.cuahangbangiay.DAO.SanPhamDao.getall_sanpham;
import static com.example.cuahangbangiay.GUI.Quanly.QLNhap.setdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbangiay.DTO.HoaDonNhap;
import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.GUI.Quanly.QLNhap;
import com.example.cuahangbangiay.R;


import java.util.List;

public class HoaDonNhapAdapter extends RecyclerView.Adapter<HoaDonNhapAdapter.HoaDonNhapViewholdle> {
    List<HoaDonNhap> lists;
    Context context;
    Dialog dialog;

    public HoaDonNhapAdapter(List<HoaDonNhap> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public HoaDonNhapViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hdn, parent, false);
        return new HoaDonNhapViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonNhapViewholdle holder, int position) {
        HoaDonNhap HoaDonNhap = lists.get(position);
        holder.textmahdn.setText(HoaDonNhap.getMahdn());
        holder.textmasp.setText(HoaDonNhap.getMasp());
        holder.texttennv.setText(HoaDonNhap.getTennv());
        holder.texttensp.setText(HoaDonNhap.getTensp() + "");
        holder.textsl.setText(HoaDonNhap.getSoluong() + "");
        holder.textdg.setText(HoaDonNhap.getDongia() + "");
        holder.texttt.setText(HoaDonNhap.getTongtien() + "");
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_them_sua);
                setdialog(dialog);
                EditText mahdn = dialog.findViewById(R.id.mahd);
                Spinner spinner = dialog.findViewById(R.id.spinner);
                EditText tensp = dialog.findViewById(R.id.tensp);
                EditText giatien = dialog.findViewById(R.id.giatien);
                EditText tennv = dialog.findViewById(R.id.tennv);
                EditText soluong = dialog.findViewById(R.id.soluong);
                EditText tongtien = dialog.findViewById(R.id.tongtien);
                mahdn.setEnabled(false);
                mahdn.setText(HoaDonNhap.getMahdn());
                tennv.setText(HoaDonNhap.getTennv());
                soluong.setText(HoaDonNhap.getSoluong()+"");
                tongtien.setText(HoaDonNhap.getTongtien()+"");


                Button btnthem = dialog.findViewById(R.id.btnthem);
                Button btnthoat = dialog.findViewById(R.id.btnthoat);

                final ArrayAdapter adapter = new ArrayAdapter(context, R.layout.dropdown_item,getall_sanpham());
                spinner.setAdapter(adapter);
                int giatri = -1;
                for (int i = 0; i < getall_sanpham().size(); i++) {
                    if (getall_sanpham().get(i).getMASP().equalsIgnoreCase(HoaDonNhap.getMahdn())) {
                        giatri = i;
                        break;
                    }
                }
                spinner.setSelection(giatri);
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
                            Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else {
                            HoaDonNhap HoaDonNhap=new HoaDonNhap(mhd,sanPham.getMASP(),tsp,tnv,Integer.parseInt(sl),sanPham.getGIATIEN(),Integer.parseInt(tt));
                            if (Sua_HoaDonNhap(HoaDonNhap)){
                                Sua_sl(sanPham.getMASP(),Integer.parseInt(sl)+sanPham.getSOLUONG());
                                dialog.dismiss();
                                Toast.makeText(context, "sửa thành thành công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();
            }
        });
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Bạn có muốn xóa hóa đơn này ko")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (xoa_hdn(HoaDonNhap.getMahdn())) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });

                builder.create();
                builder.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    public class HoaDonNhapViewholdle extends RecyclerView.ViewHolder {
        TextView textmahdn, textmasp, texttensp, textsl, textdg, texttt,texttennv;
        ImageView imageViewedit, imageViewDelete;

        public HoaDonNhapViewholdle(@NonNull View itemView) {
            super(itemView);
            textmahdn = itemView.findViewById(R.id.textmahdn);
            textmasp = itemView.findViewById(R.id.textmasp);
            texttennv = itemView.findViewById(R.id.texttennv);
            texttensp = itemView.findViewById(R.id.texttensp);
            textsl = itemView.findViewById(R.id.textsl);
            textdg = itemView.findViewById(R.id.textdg);
            texttt = itemView.findViewById(R.id.texttt);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}

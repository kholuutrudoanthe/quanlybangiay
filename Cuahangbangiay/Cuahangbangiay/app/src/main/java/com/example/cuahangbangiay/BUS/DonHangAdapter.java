package com.example.cuahangbangiay.BUS;

import static com.example.cuahangbangiay.DAO.GioHangDAO.get_giohang;
import static com.example.cuahangbangiay.DAO.GioHangDAO.xoa_giohang;
import static com.example.cuahangbangiay.DAO.HoaDonXuatDAO.xoa_hdx;
import static com.example.cuahangbangiay.DAO.SanPhamDao.get_sp;
import static com.example.cuahangbangiay.GUI.MainActivity.currencyVN;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbangiay.DTO.GioHang;
import com.example.cuahangbangiay.DTO.HoaDonBan;
import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.R;

import java.util.List;

public class DonHangAdapter  extends RecyclerView.Adapter<DonHangAdapter.don_hangviewhodler>{
    List<HoaDonBan> lists;
    Context fragment;

    public DonHangAdapter(List<HoaDonBan> lists, Context fragment) {
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public don_hangviewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dh,parent,false);
        return new don_hangviewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull don_hangviewhodler holder, int position) {
        HoaDonBan donXuat = lists.get(position);
        GioHang GioHang=get_giohang(donXuat.getIdgh());
        holder.tongtien.setText("Tổng tiền: "+currencyVN.format(GioHang.getTongtien()));
        if (GioHang==null){
            return;
        }
        SanPham sanpham = get_sp(GioHang.getMASP());
        if (sanpham!=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(sanpham.getHINHANH(), 0, sanpham.getHINHANH().length);
            holder.img.setImageBitmap(bitmap);
            holder.tensp.setText(sanpham.getTENSP());
            holder.tienx1.setText(currencyVN.format(sanpham.getGIATIEN()));
        }
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment);
                builder.setMessage("Bạn có muốn xóa đơn hàng này ko")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                xoa_hdx(donXuat.getIddh());
                                xoa_giohang(donXuat.getIdgh());
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
        if (lists!=null){
            return lists.size();
        }
        return 0;
    }

    public class don_hangviewhodler extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tensp, tienx1,tongtien;
        Button btn;

        public don_hangviewhodler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ImageView_Product);

            tensp = itemView.findViewById(R.id.tensp);
            tienx1 = itemView.findViewById(R.id.tienx1);
            btn = itemView.findViewById(R.id.btn);
            tongtien = itemView.findViewById(R.id.tongtien);
        }
    }
}

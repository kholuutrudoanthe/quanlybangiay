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

public class HoaDonBanAdapter extends RecyclerView.Adapter<HoaDonBanAdapter.Hoadonbanviewhodler>{
    List<HoaDonBan> lists;
    Context fragment;

    public HoaDonBanAdapter(List<HoaDonBan> lists, Context fragment) {
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public Hoadonbanviewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hdb,parent,false);
        return new Hoadonbanviewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hoadonbanviewhodler holder, int position) {
        HoaDonBan donXuat = lists.get(position);
        GioHang GioHang=get_giohang(donXuat.getIdgh());
        holder.texttt.setText(currencyVN.format(GioHang.getTongtien()));
        holder.textsl.setText(GioHang.getSl()+"");
        holder.textmkh.setText(GioHang.getEMAIL()+"");
        holder.textmahdn.setText(donXuat.getIdgh()+"");
        if (GioHang==null){
            return;
        }
        SanPham sanpham = get_sp(GioHang.getMASP());
        if (sanpham!=null){
            holder.textmasp.setText(sanpham.getMASP());
            holder.texttensp.setText(sanpham.getTENSP());
            holder.textdg.setText(currencyVN.format(sanpham.getGIATIEN()));
        }
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment);
                builder.setMessage("Bạn có muốn xóa hóa đơn bán này ko")
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

    public class Hoadonbanviewhodler extends RecyclerView.ViewHolder {
        TextView textmahdn, textmasp, texttensp, textsl,textdg,texttt,textmkh;
        ImageView  imageViewDelete;
        public Hoadonbanviewhodler(@NonNull View itemView) {
            super(itemView);
            textmahdn = itemView.findViewById(R.id.textmahdn);
            textmasp = itemView.findViewById(R.id.textmasp);
            texttensp = itemView.findViewById(R.id.texttensp);
            textsl = itemView.findViewById(R.id.textsl);
            textdg = itemView.findViewById(R.id.textdg);
            textmkh = itemView.findViewById(R.id.textmkh);
            texttt = itemView.findViewById(R.id.texttt);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}

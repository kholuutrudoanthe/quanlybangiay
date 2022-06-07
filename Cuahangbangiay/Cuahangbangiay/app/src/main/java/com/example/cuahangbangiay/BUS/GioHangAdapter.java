package com.example.cuahangbangiay.BUS;


import static com.example.cuahangbangiay.DAO.GioHangDAO.Sua_giohang;
import static com.example.cuahangbangiay.DAO.GioHangDAO.xoa_giohang;
import static com.example.cuahangbangiay.DAO.SanPhamDao.get_sp;
import static com.example.cuahangbangiay.GUI.MainActivity.currencyVN;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.cuahangbangiay.DTO.GioHang;
import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.R;


import java.util.List;


public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.giohangviewhodler> {
    List<GioHang> list;
    Context fragment;
    int position_g;
    int sl;
    int tongtien;

    public GioHangAdapter(List<GioHang> list, Context fragment, int position_g) {
        this.list = list;
        this.fragment = fragment;
        this.position_g = position_g;
    }

    @NonNull
    @Override
    public giohangviewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragment).inflate(R.layout.item_gh, parent, false);
        return new giohangviewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull giohangviewhodler holder, int position) {
        GioHang GioHang = list.get(position);
        if (position_g == 1) {
            holder.delete.setVisibility(View.GONE);
            holder.layout.setVisibility(View.GONE);
        }
        SanPham sanpham = get_sp(GioHang.getMASP());
        sl = GioHang.getSl();
        if (sanpham != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(sanpham.getHINHANH(), 0, sanpham.getHINHANH().length);
            holder.img.setImageBitmap(bitmap);
            holder.tensp.setText(sanpham.getTENSP());
            holder.tienx1.setText(currencyVN.format(sanpham.getGIATIEN()));
        }
        holder.sl.setText(GioHang.getSl() + "");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment);
                builder.setMessage("Bạn có muốn xóa sản phẩm này ko")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                xoa_giohang(GioHang.getIdgh());
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
        holder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl = sl + 1;
                holder.sl.setText(sl + "");
                tongtien = sanpham.getGIATIEN() * sl;
                Sua_giohang(GioHang.getIdgh(), sl, tongtien);
            }
        });
        holder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sl != 1) {
                    sl = sl - 1;
                    holder.sl.setText(sl + "");
                    tongtien = sanpham.getGIATIEN() * sl;
                    Sua_giohang(GioHang.getIdgh(), sl, tongtien);
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class giohangviewhodler extends RecyclerView.ViewHolder {
        ImageView img, cong, tru, delete;
        TextView tensp, tienx1, sl;
        LinearLayout layout;

        public giohangviewhodler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            cong = itemView.findViewById(R.id.cong);
            tru = itemView.findViewById(R.id.tru);
            tensp = itemView.findViewById(R.id.tensp);
            tienx1 = itemView.findViewById(R.id.tienx1);
            sl = itemView.findViewById(R.id.sl);
            delete = itemView.findViewById(R.id.delete);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}

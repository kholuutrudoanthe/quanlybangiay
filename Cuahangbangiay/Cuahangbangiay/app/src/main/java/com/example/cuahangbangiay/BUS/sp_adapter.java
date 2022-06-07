package com.example.cuahangbangiay.BUS;


import static com.example.cuahangbangiay.DAO.SanPhamDao.xoa_sp;
import static com.example.cuahangbangiay.GUI.MainActivity.currencyVN;
import static com.example.cuahangbangiay.GUI.MainActivityLogIn.vitri;
import static com.example.cuahangbangiay.GUI.Quanly.QLsanpham.QLSanPham.Masp;
import static com.example.cuahangbangiay.GUI.Quanly.Quanly.*;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.GUI.Quanly.QLsanpham.QLsuasp;
import com.example.cuahangbangiay.GUI.SanPham.chitiet;
import com.example.cuahangbangiay.R;


import java.util.List;

public class sp_adapter extends RecyclerView.Adapter<sp_adapter.sp_viewholder> {
    List<SanPham>list;
    Context fragment;

    public sp_adapter(List<SanPham> list, Context fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public sp_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(fragment).inflate(R.layout.item_sp,parent,false);
        return new sp_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sp_viewholder holder, int position) {
        SanPham sanPham=list.get(position);
        if (vitri==1){
            holder.layout.setVisibility(View.GONE);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Masp=sanPham.getMASP();
                    fragment.startActivity(new Intent(fragment, chitiet.class));
                }
            });
        }
        holder.name.setText(sanPham.getTENSP());
        holder.giatien.setText(currencyVN.format(sanPham.getGIATIEN()));
        Bitmap bitmap = BitmapFactory.decodeByteArray(sanPham.getHINHANH(), 0, sanPham.getHINHANH().length);
        holder.imageView.setImageBitmap(bitmap);
        holder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Masp=sanPham.getMASP();
                fragment.startActivity(new Intent(fragment, QLsuasp.class));
            }
        });
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment);
                builder.setMessage("Bạn có muốn xóa ko")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (xoa_sp(sanPham.getMASP())) {
                                    Toast.makeText(fragment, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(fragment, "Xóa thất bại", Toast.LENGTH_SHORT).show();
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
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class sp_viewholder extends RecyclerView.ViewHolder{
        ImageView imageView,sua,xoa;
        TextView name,giatien;
        LinearLayout layout;
        public sp_viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            giatien=itemView.findViewById(R.id.giatien);
            layout=itemView.findViewById(R.id.layout);
            sua=itemView.findViewById(R.id.sua);
            xoa=itemView.findViewById(R.id.xoa);
        }
    }
}

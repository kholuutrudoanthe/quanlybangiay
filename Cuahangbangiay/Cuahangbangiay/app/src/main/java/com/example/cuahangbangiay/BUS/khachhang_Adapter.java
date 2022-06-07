package com.example.cuahangbangiay.BUS;

import static com.example.cuahangbangiay.DAO.TaiKhoanDAO.xoa_tk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbangiay.DTO.Taikhoan;
import com.example.cuahangbangiay.R;

import java.util.List;

public class khachhang_Adapter  extends RecyclerView.Adapter<khachhang_Adapter.KhViewholdle> {
    List<Taikhoan> lists;
    Context context;

    public khachhang_Adapter(List<Taikhoan> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public KhViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kh, parent, false);
        return new KhViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhViewholdle holder, int position) {
        Taikhoan taikhoan = lists.get(position);
        holder.email.setText(taikhoan.getEmail());
        holder.tenkh.setText(taikhoan.getHoten());
        holder.diachi.setText(taikhoan.getDiachi());
        holder.sdt.setText(taikhoan.getSdt());
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage("Bạn có muốn xóa khách hàng này ko")
                            .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (xoa_tk(taikhoan.getEmail())) {
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

    public class KhViewholdle extends RecyclerView.ViewHolder {
        TextView email, tenkh, diachi, sdt;
        ImageView  imageViewDelete;

        public KhViewholdle(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            tenkh = itemView.findViewById(R.id.tenkh);
            diachi = itemView.findViewById(R.id.diachi);
            sdt = itemView.findViewById(R.id.sdt);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}

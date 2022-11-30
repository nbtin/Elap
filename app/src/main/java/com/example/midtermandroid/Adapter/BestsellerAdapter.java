package com.example.midtermandroid.Adapter;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.midtermandroid.Activity.ShowDetailActivity;
import com.example.midtermandroid.Domain.BrandDomain;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.R;

import java.util.ArrayList;

public class BestsellerAdapter extends RecyclerView.Adapter<BestsellerAdapter.ViewHolder> {
    ArrayList<LaptopDomain> laptopDomains;

    public BestsellerAdapter(ArrayList<LaptopDomain> laptopDomains) {
        this.laptopDomains = laptopDomains;
    }

    public static String formatter(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bestseller, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(laptopDomains.get(position).getTitle());
//        int feeValue = laptopDomains.get(position).getFee();
        holder.fee.setText(String.valueOf(formatter(laptopDomains.get(position).getFee())));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(laptopDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", laptopDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.clLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", laptopDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return laptopDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        TextView viewBtn;
        ConstraintLayout clLaptop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            fee = itemView.findViewById(R.id.tvFee);
            pic = itemView.findViewById(R.id.ivPic);
            viewBtn = itemView.findViewById(R.id.btnView);
            clLaptop = itemView.findViewById(R.id.clLaptop);
        }
    }
}

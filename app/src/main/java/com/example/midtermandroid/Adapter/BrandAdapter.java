package com.example.midtermandroid.Adapter;

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
import com.example.midtermandroid.Domain.BrandDomain;
import com.example.midtermandroid.R;

import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    ArrayList<BrandDomain> brandDomains;

    public BrandAdapter(ArrayList<BrandDomain> brandDomains) {
        this.brandDomains = brandDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_brand, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.brandName.setText(brandDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0: {
                picUrl = "brand_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brand_background1));
                break;
            }
            case 1: {
                picUrl = "brand_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brand_background2));
                break;
            }
            case 2: {
                picUrl = "brand_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brand_background3));
                break;
            }
            case 3: {
                picUrl = "brand_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brand_background4));
                break;
            }
            case 4: {
                picUrl = "brand_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brand_background5));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.brandPic);
    }

    @Override
    public int getItemCount() {
        return brandDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView brandName;
        ImageView brandPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.tvBrandName);
            brandPic = itemView.findViewById(R.id.ivBrandPic);
            mainLayout = itemView.findViewById(R.id.clMainLayout);
        }
    }
}

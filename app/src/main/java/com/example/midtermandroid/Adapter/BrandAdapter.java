package com.example.midtermandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.midtermandroid.Activity.MainActivity;
import com.example.midtermandroid.Activity.ShowDetailActivity;
import com.example.midtermandroid.Domain.BrandDomain;
import com.example.midtermandroid.R;

import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    private ArrayList<BrandDomain> brandDomains;
    private Context currentContext;

    public BrandAdapter(ArrayList<BrandDomain> brandDomains, Context context) {
        this.brandDomains = brandDomains;
        this.currentContext = context;
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
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bestseller_background));
                break;
            }
            case 1: {
                picUrl = "brand_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bestseller_background));
                break;
            }
            case 2: {
                picUrl = "brand_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bestseller_background));
                break;
            }
            case 3: {
                picUrl = "brand_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bestseller_background));
                break;
            }
            case 4: {
                picUrl = "brand_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bestseller_background));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.brandPic);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentContext instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) currentContext;
                    mainActivity.updateLaptopWithBrandList(holder.brandName.getText().toString());
                } else {
                    Toast.makeText(currentContext, "Action for this context has not been developed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

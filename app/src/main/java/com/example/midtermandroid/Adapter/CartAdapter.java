package com.example.midtermandroid.Adapter;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.Helper.ManagementCart;
import com.example.midtermandroid.Interface.ChangeNumber;
import com.example.midtermandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<LaptopDomain> laptopDomains;
    private ManagementCart managementCart;
    private ChangeNumber changeNumber;

    public CartAdapter(ArrayList<LaptopDomain> laptopDomains, Context context, ChangeNumber changeNumber) {
        this.laptopDomains = laptopDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumber = changeNumber;
    }

    public static String formatter(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(laptopDomains.get(position).getTitle());
//        holder.itemFee.setText(String.valueOf(laptopDomains.get(position).getFee()));
        holder.itemTotal.setText(String.valueOf(String.valueOf(formatter(laptopDomains.get(position).getNumberInCart() * laptopDomains.get(position).getFee()))));

        holder.num.setText(String.valueOf(laptopDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(laptopDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

//        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);
        Picasso.with(holder.itemView.getContext()).load(laptopDomains.get(position).getPic()).into(holder.pic);

        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.addNumberLaptop(laptopDomains, position, new ChangeNumber() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumber.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberLaptop(laptopDomains, position, new ChangeNumber() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumber.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return laptopDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, itemFee;
        ImageView pic, addItem, minusItem;
        TextView itemTotal, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitleCart);
//            itemFee = itemView.findViewById(R.id.tvItemFee);
            pic = itemView.findViewById(R.id.ivPicCart);
            addItem = itemView.findViewById(R.id.ivAdd);
            minusItem = itemView.findViewById(R.id.ivMinus);
            itemTotal = itemView.findViewById(R.id.tvTotalFee);
            num = itemView.findViewById(R.id.tvNumber);
        }
    }

}

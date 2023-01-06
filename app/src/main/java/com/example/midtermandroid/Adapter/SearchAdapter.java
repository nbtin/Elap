package com.example.midtermandroid.Adapter;

import com.example.midtermandroid.Activity.ShowDetailActivity;
import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<LaptopDomain> mListLaptops;

    public SearchAdapter(List<LaptopDomain> mListLaptops){
        this.mListLaptops = mListLaptops;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(view);
    }

    public static String formatter(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        LaptopDomain laptop = mListLaptops.get(position);
        if(laptop == null){
            return;
        }

        Picasso.with(holder.itemView.getContext()).load(mListLaptops.get(position).getPic()).into(holder.imgLaptop);
        holder.tvName.setText(mListLaptops.get(position).getTitle());
        holder.tvPrice.setText(String.valueOf(mListLaptops.get(position).getFee()));
        holder.searchLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", mListLaptops.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListLaptops != null){
            return mListLaptops.size();
        }
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgLaptop;
        private TextView tvName;
        private TextView tvPrice;
        RelativeLayout searchLaptop;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.img_laptop);
            tvName = itemView.findViewById(R.id.name_laptop);
            tvPrice = itemView.findViewById(R.id.price_laptop);
            searchLaptop = itemView.findViewById(R.id.item_search);
        }
    }
}

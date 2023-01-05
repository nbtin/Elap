package com.example.midtermandroid.Adapter;

import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

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

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        LaptopDomain laptop = mListLaptops.get(position);
        if(laptop == null){
            return;
        }

        holder.imgLaptop.setImageResource(R.drawable.lap_1);
        holder.tvName.setText("Sản phẩm");
        holder.tvPrice.setText("Giá sản phẩm");
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

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.img_laptop);
            tvName = itemView.findViewById(R.id.name_laptop);
            tvPrice = itemView.findViewById(R.id.price_laptop);
        }
    }
}

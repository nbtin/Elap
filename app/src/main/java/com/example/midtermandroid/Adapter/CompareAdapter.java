package com.example.midtermandroid.Adapter;

import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtermandroid.Domain.LaptopDomain;
import com.example.midtermandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompareAdapter extends RecyclerView.Adapter<CompareAdapter.ViewHolder> {
    ArrayList<LaptopDomain> laptopDomains;
    private IClickListener mIClickListener;

    public interface  IClickListener{
        void onClickCompare(LaptopDomain laptop);
    }

    public CompareAdapter(ArrayList<LaptopDomain> laptopDomains, IClickListener iClickListener) {
        this.laptopDomains = laptopDomains;
        this.mIClickListener = iClickListener;
    }

    public static String formatter(int value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_compare, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LaptopDomain laptop = laptopDomains.get(position);
        holder.title.setText(laptopDomains.get(position).getTitle());
//        int feeValue = laptopDomains.get(position).getFee();
        holder.fee.setText(String.valueOf(formatter(laptopDomains.get(position).getFee())));

//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(laptopDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        Picasso.with(holder.itemView.getContext()).load(laptopDomains.get(position).getPic()).into(holder.pic);

        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(), ShowCompareActivity.class);
//                intent.putExtra("object", laptopDomains.get(position));
//                holder.itemView.getContext().startActivity(intent);
                mIClickListener.onClickCompare(laptop);
            }
        });

        holder.clLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(), ShowCompareActivity.class);
//                intent.putExtra("object", laptopDomains.get(position));
//                holder.itemView.getContext().startActivity(intent);
                mIClickListener.onClickCompare(laptop);
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

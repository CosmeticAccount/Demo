package com.isolsgroup.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.isolsgroup.demo.R;
import com.isolsgroup.demo.listener.OnHomeItemClickListener;
import com.isolsgroup.demo.model.HomeResponse;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeResponse> mList;
    private OnHomeItemClickListener homeItemClickListener;
    private RequestManager glide;
    public HomeAdapter(List<HomeResponse> mList, RequestManager glide) {
        this.mList = mList;
        this.glide = glide;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CustomViewHolder)holder).txt_name.setText(mList.get(position).getProductName());
        ((CustomViewHolder)holder).txtPrice.setText(mList.get(position).getProductPrice ());
        //mList.get(position).getResourceId()
        glide.load(mList.get(position).getImages())
                .into(((CustomViewHolder)holder).imageView);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name, txtPrice;
        ImageView imageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.imageView);
            txtPrice = itemView.findViewById(R.id.txt_price);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (homeItemClickListener != null) homeItemClickListener.OnHomeAdapterItemClicked(getAdapterPosition());
        }
    }
    @Override
    public int getItemCount() {
        return (null !=  mList? mList.size() : 0);
    }

    public void setClickListener(OnHomeItemClickListener homeItemClickListener) {
        this.homeItemClickListener = homeItemClickListener;
    }
}

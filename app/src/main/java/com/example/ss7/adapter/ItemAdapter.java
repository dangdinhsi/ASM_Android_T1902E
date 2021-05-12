package com.example.ss7.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ss7.R;
import com.example.ss7.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter{
    private Activity activity;
    private List<Item> listItem;

    public ItemAdapter(Activity activity, List<Item> listItem) {
        this.activity = activity;
        this.listItem = listItem;
    }

    public void reloadData(List<Item> list){
        this.listItem = list;
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = activity.getLayoutInflater().inflate(R.layout.item_layout,parent,false);
        ItemHolder holder = new ItemHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder vh = (ItemHolder) holder;
        Item model = listItem.get(position);
        vh.tvDate.setText(model.getDate());
        vh.tvTitle.setText(model.getTitle());
        vh.tvContent.setText(model.getContent().getDescription());
        Glide.with(activity).load(model.getImage()).into(vh.ivCover);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView tvTitle,tvContent,tvDate;
        ImageView ivCover;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvContent = itemView.findViewById(R.id.tvContent);
            ivCover = itemView.findViewById(R.id.ivCover);
        }
    }
}

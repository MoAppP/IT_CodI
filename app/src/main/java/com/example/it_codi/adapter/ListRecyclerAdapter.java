package com.example.it_codi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_codi.R;
import com.example.it_codi.activity.ClothesinfoActivity;
import com.example.it_codi.database.Clothes;

import java.util.ArrayList;

public class ListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    ArrayList<Clothes> list;
    Context context;

    public ListRecyclerAdapter(ArrayList<Clothes> list) {
        this.list = list;
    }
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_recycler, parent, false);
            return new ItemHolder(view);
        }
        else {
            context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_recycler_loading, parent, false);
            return new LoadingHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemHolder) {
            ItemHolder itemHolder = (ItemHolder) viewHolder;
            Clothes clothes = list.get(position);
            itemHolder.iv.setImageBitmap(Bitmap.createScaledBitmap(clothes.getImg(), 700, 700, true));
            itemHolder.iv.setOnClickListener(view -> {
                Log.d("test", Integer.valueOf(position).toString()+"position");
                Intent intent = new Intent(context, ClothesinfoActivity.class);
                intent.putExtra("clothes_uid", clothes.getUid());
                context.startActivity(intent);
            });
        }
        else if (viewHolder instanceof LoadingHolder){

        }
    }

    @Override public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public long getItemId(int position) {
        if (list.get(position) != null){
            return Long.valueOf(list.get(position).getUid());
        }
        else{
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
}
class ItemHolder extends RecyclerView.ViewHolder {
    ImageView iv;
    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        iv = itemView.findViewById(R.id.recycler_view_holder_iv);
    }
}
class LoadingHolder extends RecyclerView.ViewHolder {
    ProgressBar progressBar;
    public LoadingHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.itemLoad_progressBar);
    }
}
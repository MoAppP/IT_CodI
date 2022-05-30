package com.example.it_codi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_codi.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewHolderPage> {

    private ArrayList<Integer> listData;
    private TextView tv_page = null;

    public ViewPagerAdapter(ArrayList<Integer> data) {
        this.listData = data;
    }

    public ViewPagerAdapter(ArrayList<Integer> data, TextView tv) {
        this.listData = data;
        this.tv_page = tv;
    }

    @Override
    public ViewHolderPage onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPage holder, int position) {
        if (holder != null) {
            int cur_page = position % listData.size();
            holder.onBind(listData.get(cur_page));
        }
    }


    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolderPage holder) {
        super.onViewAttachedToWindow(holder);


    }


    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

}
class ViewHolderPage extends RecyclerView.ViewHolder {
    private ImageView iv_home;

    ViewHolderPage(View itemView) {
        super(itemView);
        iv_home = itemView.findViewById(R.id.iv_view_holder);
        //cur_position = position;
    }

    public void onBind(int imgId){
        iv_home.setImageResource(imgId);
    }

}
package com.example.it_codi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_codi.R;
import com.example.it_codi.activity.ClothesinfoActivity;
import com.example.it_codi.database.Clothes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ListRecyclerAdapter extends RecyclerView.Adapter<Holder> {
    ArrayList<Clothes> list;
    Context context;
    public ListRecyclerAdapter(ArrayList<Clothes> list) {
        this.list = list;
    }
    @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Clothes clothes = list.get(position);
        holder.iv.setImageBitmap(clothes.getImg());
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClothesinfoActivity.class);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                clothes.getImg().compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("byteArray",byteArray);

                ArrayList<String> tags = new ArrayList<String>();
                if(!clothes.getType().equals(""))
                    tags.add(clothes.getType());
                if(!clothes.getSpring().equals(""))
                    tags.add(clothes.getSpring());
                if(!clothes.getSummer().equals(""))
                    tags.add(clothes.getSummer());
                if(!clothes.getAutumn().equals(""))
                    tags.add(clothes.getAutumn());
                if(!clothes.getWinter().equals(""))
                    tags.add(clothes.getWinter());
                if(!clothes.getStyle().equals(""))
                    tags.add(clothes.getStyle());
                if(!clothes.getName().equals(""))
                    tags.add(clothes.getName());
                intent.putExtra("tags",tags);

                intent.putExtra("like",clothes.getLike());

                context.startActivity(intent);

            }
        });
    }

    @Override public int getItemCount() {
        return list.size();
    }
}
class Holder extends RecyclerView.ViewHolder {
    ImageView iv;
    public Holder(@NonNull View itemView) {
        super(itemView);
        iv = itemView.findViewById(R.id.recycler_view_holder_iv);
    }
}
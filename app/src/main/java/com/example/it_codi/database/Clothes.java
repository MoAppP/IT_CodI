package com.example.it_codi.database;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Clothes {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private Bitmap img;
    private String type;
    private String size;

    public Clothes(Bitmap img, String type, String size){
        this.img = img;
        this.type = type;
        this.size = size;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Clothes))
            return false;
        Clothes otherClothes = (Clothes) obj;
//        return super.equals(obj);

        return otherClothes.getUid() == uid;
    }
}

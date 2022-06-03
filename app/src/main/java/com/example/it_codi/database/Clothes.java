package com.example.it_codi.database;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Clothes {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private Bitmap img;
    private String type;
    private String spring;
    private String summer;
    private String autumn;
    private String winter;
    private String style;
    private String name;
    private boolean like;

    public Clothes(Bitmap img, String type, String spring, String summer, String autumn, String winter, String style, String name, boolean like){
        this.img = img;
        this.type = type;
        this.spring = spring;
        this.summer = summer;
        this.autumn = autumn;
        this.winter = winter;
        this.style = style;
        this.name = name;
        this.like = like;
    }

    public int getUid() { return uid; }

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

    public void setType(String type) { this.type = type; }

    public String getSpring() { return spring; }

    public void setSpring(String spring) { this.spring = spring; }

    public String getSummer() { return summer; }

    public void setSummer(String summer) { this.summer = summer; }

    public String getAutumn() { return autumn; }

    public void setAutumn(String autumn) { this.autumn = autumn; }

    public String getWinter() { return winter; }

    public void setWinter(String winter) { this.winter = winter; }

    public String getStyle() { return style; }

    public void setStyle(String style) { this.style = style; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Clothes))
            return false;
        Clothes otherClothes = (Clothes) obj;
//        return super.equals(obj);

        return otherClothes.getUid() == uid;
    }

    @Override
    public int hashCode() {
        return this.uid;
    }
}
package com.example.it_codi.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ClothesDao {

    @Query("SELECT * FROM clothes")
    List<Clothes> findAll();

    @Query("SELECT * FROM clothes where uid=:uid")
    Clothes findById(int uid);

    @Query("SELECT * FROM clothes WHERE type=:type")
    List<Clothes> findByType(String type);

    @Query("SELECT * FROM clothes WHERE size=:size")
    List<Clothes> findBySize(String size);

    @Insert
    void insert(Clothes clothes);

    @Delete
    void delete(Clothes clothes); //내부에 값을 넣어서 삭제 가능(오버로딩)

    @Query("DELETE FROM clothes")
    void deleteAll();
}

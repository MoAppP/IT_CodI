package com.example.it_codi.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM clothes WHERE spring=:spring")
    List<Clothes> findBySpring(String spring);

    @Query("SELECT * FROM clothes WHERE summer=:summer")
    List<Clothes> findBySummer(String summer);

    @Query("SELECT * FROM clothes WHERE autumn=:autumn")
    List<Clothes> findByAutumn(String autumn);

    @Query("SELECT * FROM clothes WHERE winter=:winter")
    List<Clothes> findByWinter(String winter);

    @Query("SELECT * FROM clothes WHERE " +
            "type=:type AND spring=:season OR " +
            "type=:type AND summer=:season OR " +
            "type=:type AND autumn=:season OR " +
            "type=:type AND winter=:season")
    List<Clothes> findByTypeAndSeason(String type, String season);

    @Query("SELECT * FROM clothes WHERE type=:type AND spring=:spring AND style=:style")
    List<Clothes> findByTypeSpringStyle(String type, String spring, String style);

    @Query("SELECT * FROM clothes WHERE type=:type AND summer=:summer AND style=:style")
    List<Clothes> findByTypeSummerStyle(String type, String summer, String style);

    @Query("SELECT * FROM clothes WHERE type=:type AND autumn=:autumn AND style=:style")
    List<Clothes> findByTypeAutumnStyle(String type, String autumn, String style);

    @Query("SELECT * FROM clothes WHERE type=:type AND winter=:winter AND style=:style")
    List<Clothes> findByTypeWinterStyle(String type, String winter, String style);

    @Query("SELECT * FROM clothes WHERE style=:style")
    List<Clothes> findByStyle(String style);

//    @Query("SELECT * FROM clothes WHERE like=:like")
//    List<Clothes> findByLike(boolean like);

    @Insert
    void insert(Clothes clothes);

    @Update
    void updateClothes(Clothes clothes);

    @Delete
    void delete(Clothes clothes); //내부에 값을 넣어서 삭제 가능(오버로딩)

    @Query("DELETE FROM clothes")
    void deleteAll();
}
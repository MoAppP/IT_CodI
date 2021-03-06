package com.example.it_codi.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClothesDao {

    @Query("SELECT * FROM clothes")
    List<Clothes> findAll();
    @Query("SELECT uid FROM clothes")
    List<Integer> findAllUid();

    @Query("SELECT type FROM clothes")
    List<String> findAllType();

    @Query("SELECT * FROM clothes where uid=:uid")
    Clothes findById(int uid);

    @Query("SELECT * FROM clothes WHERE type=:type")
    List<Clothes> findByType(String type);
    @Query("SELECT uid FROM clothes WHERE type=:type")
    List<Integer> findUidByType(String type);

    @Query("SELECT * FROM clothes WHERE spring=:spring")
    List<Clothes> findBySpring(String spring);
    @Query("SELECT uid FROM clothes WHERE spring=:spring")
    List<Integer> findUidBySpring(String spring);

    @Query("SELECT * FROM clothes WHERE summer=:summer")
    List<Clothes> findBySummer(String summer);
    @Query("SELECT uid FROM clothes WHERE summer=:summer")
    List<Integer> findUidBySummer(String summer);

    @Query("SELECT * FROM clothes WHERE autumn=:autumn")
    List<Clothes> findByAutumn(String autumn);
    @Query("SELECT uid FROM clothes WHERE autumn=:autumn")
    List<Integer> findUidByAutumn(String autumn);

    @Query("SELECT * FROM clothes WHERE winter=:winter")
    List<Clothes> findByWinter(String winter);
    @Query("SELECT uid FROM clothes WHERE winter=:winter")
    List<Integer> findUidByWinter(String winter);

    @Query("SELECT * FROM clothes WHERE liked=:liked")
    List<Clothes> findByLiked(boolean liked);
    @Query("SELECT uid FROM clothes WHERE liked=:liked")
    List<Integer> findUidByLiked(boolean liked);

    @Insert
    void insert(Clothes clothes);

    @Update
    void updateClothes(Clothes clothes);

    @Delete
    void delete(Clothes clothes); //내부에 값을 넣어서 삭제 가능(오버로딩)

    @Query("DELETE FROM clothes")
    void deleteAll();
}
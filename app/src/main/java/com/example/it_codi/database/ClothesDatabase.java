package com.example.it_codi.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Clothes.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class ClothesDatabase extends RoomDatabase {
    public abstract ClothesDao clothesDao();

    private static ClothesDatabase instance = null;

    public static ClothesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ClothesDatabase.class,
                    "clothes-database"
            ).allowMainThreadQueries().addTypeConverter(new Converters())
                    .build();
        }//

        return instance;
    }
}

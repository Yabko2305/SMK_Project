package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Data.class, Pill.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract DataDao dataDao();
    public abstract PillDao pillDao();

    public static volatile DataBase INSTANCE;

    public static DataBase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (DataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "Data").build();
                }
            }
        }
        return INSTANCE;
    }
}

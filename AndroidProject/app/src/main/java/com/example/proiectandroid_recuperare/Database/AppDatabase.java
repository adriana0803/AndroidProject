package com.example.proiectandroid_recuperare.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proiectandroid_recuperare.Dao.CursDao;
import com.example.proiectandroid_recuperare.Dao.ProfesorDao;
import com.example.proiectandroid_recuperare.Dao.ReviewDao;
import com.example.proiectandroid_recuperare.Dao.StudentDao;
import com.example.proiectandroid_recuperare.DataModels.AdaptedCurs;
import com.example.proiectandroid_recuperare.DataModels.Profesor;
import com.example.proiectandroid_recuperare.DataModels.Review;
import com.example.proiectandroid_recuperare.DataModels.Student;

@Database(entities = {Student.class, Profesor.class, AdaptedCurs.class, Review.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    public abstract ProfesorDao profesorDao();
    public abstract CursDao cursDao();
    public abstract ReviewDao reviewDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "App_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

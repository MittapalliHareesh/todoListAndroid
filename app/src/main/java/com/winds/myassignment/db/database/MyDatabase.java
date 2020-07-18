package com.winds.myassignment.db.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.winds.myassignment.AppConstants;
import com.winds.myassignment.db.dao.SavedTodoDao;
import com.winds.myassignment.db.entity.SavedTodoItem;

@Database(entities = {
    SavedTodoItem.class}, version = AppConstants.DATABASE_VERSION, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

  private static String DATABASE_NAME = "MyDatabaseName2";

  private static MyDatabase dbInstance = null;

  public abstract SavedTodoDao savedTodoDao();

  public static MyDatabase getDatabase(Context context) {
    if (dbInstance == null) {
      synchronized (MyDatabase.class) {
        if (dbInstance == null) {
          dbInstance = Room.databaseBuilder(context, MyDatabase.class, DATABASE_NAME).build();
        }
      }
    }
    return dbInstance;
  }
}

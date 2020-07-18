package com.winds.myassignment.db.dao;

import static com.winds.myassignment.db.entity.SavedTodoItem.SAVED_TODOS_TABLE;
import static com.winds.myassignment.db.entity.SavedTodoItem.COLUMN_NAME_TITLE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.winds.myassignment.db.entity.SavedTodoItem;
import java.util.List;

@Dao
public interface SavedTodoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(SavedTodoItem savedTodoItem);

  @Query("SELECT * from " + SAVED_TODOS_TABLE)
  LiveData<List<SavedTodoItem>> getAllTodoItems();

  @Delete
  void deleteItem(SavedTodoItem savedTodoItem);

  @Query("SELECT * from " + SAVED_TODOS_TABLE + " where " + COLUMN_NAME_TITLE + " = :enteredString")
  LiveData<List<SavedTodoItem>> getResultsBasedOnSearch(String enteredString);

}

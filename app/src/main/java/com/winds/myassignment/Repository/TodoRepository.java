package com.winds.myassignment.Repository;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.winds.myassignment.db.database.MyDatabase;
import com.winds.myassignment.db.entity.SavedTodoItem;
import java.util.List;

public class TodoRepository {

  private MyDatabase dbInstance;

  private LiveData<List<SavedTodoItem>> mAllTodoItems;

  public LiveData<List<SavedTodoItem>> getAllTodoItems(MyDatabase dbInstance) {
    this.dbInstance = dbInstance;
    mAllTodoItems = dbInstance.savedTodoDao().getAllTodoItems();
    return mAllTodoItems;
  }

  //Since it's a very minimal data we are inserting so using here async task.
  public void insert(final SavedTodoItem savedTodoItem) {
    new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... voids) {
        dbInstance.savedTodoDao().insert(savedTodoItem);
        return null;
      }
    }.execute();
  }

  public void delete(final SavedTodoItem savedTodoItem) {
    new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... voids) {
        dbInstance.savedTodoDao().deleteItem(savedTodoItem);
        return null;
      }
    }.execute();
  }

  public void fetchSearchResults(final String enteredString) {
    new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... voids) {
        dbInstance.savedTodoDao().getResultsBasedOnSearch(enteredString);
        return null;
      }
    }.execute();
  }
}

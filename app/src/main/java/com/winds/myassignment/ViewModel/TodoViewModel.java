package com.winds.myassignment.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.winds.myassignment.Repository.TodoRepository;
import com.winds.myassignment.db.database.MyDatabase;
import com.winds.myassignment.db.entity.SavedTodoItem;
import java.util.List;

//ViewModel acts as a communication center between the repository and UI.
public class TodoViewModel extends ViewModel {

  /* Here we should never use context reference. And we shouldn't depend
   on Activity / Fragment reference. Whenever activity changes we have to destroy activity reference
   otherwise it may lead to memory leak. */

  private MyDatabase dbInstance = null;

  private TodoRepository mTodoRepository;

  private LiveData<List<SavedTodoItem>> mAllTodoItems;

  public void setDatabaseInstance(MyDatabase dbInstance) {
    this.dbInstance = dbInstance;
    mAllTodoItems = mTodoRepository.getAllTodoItems(dbInstance);
  }

  public TodoViewModel() {
    mTodoRepository = new TodoRepository();
  }

  public LiveData<List<SavedTodoItem>> getAllTodoItems() {
    return mAllTodoItems;
  }

  public void insert(SavedTodoItem savedTodoItem) {
    mTodoRepository.insert(savedTodoItem);
  }

  public void deleteItem(SavedTodoItem savedTodoItem) {
    mTodoRepository.delete(savedTodoItem);
  }

  public void fetchSearchResults(String enteredString) {
    mTodoRepository.fetchSearchResults(enteredString);
  }
}

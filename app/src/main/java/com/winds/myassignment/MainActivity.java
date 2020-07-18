package com.winds.myassignment;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.winds.myassignment.Adapter.ToDoAdapter;
import com.winds.myassignment.ViewModel.TodoViewModel;
import com.winds.myassignment.db.database.MyDatabase;
import com.winds.myassignment.db.entity.SavedTodoItem;
import java.util.List;

/*
 * 1) Preferred Java language to implement this app.
 * 2) To store TODO list items used ROOM database.
 * 3) Followed MVVM design pattern.
 * 4) In "New TODO Screen" If user presses Cancel button clearing title and
 *    description and showing toast message to user.
 * 5) I could not able to understand the "Search" logic which is why i implemented partially. Wrote Query logic and all.
 */

public class MainActivity extends AppCompatActivity implements OnClickListener,
    OnCheckBoxClickedListener {

  private Button addButton;
  private EditText searchTxt;
  private RecyclerView rcv_todoList;
  private TodoViewModel todoViewModel;
  private MyDatabase dbInstance = null;
  private ToDoAdapter toDoAdapter = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();
    dbInstance = MyDatabase.getDatabase(MainActivity.this);
    todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
    todoViewModel.setDatabaseInstance(dbInstance);
    todoViewModel.getAllTodoItems().observe(this, new Observer<List<SavedTodoItem>>() {
      @Override
      public void onChanged(List<SavedTodoItem> savedTodoItems) {
        if (savedTodoItems.size() > 0) {
          findViewById(R.id.ViewPuller).setVisibility(View.VISIBLE);
          toDoAdapter = new ToDoAdapter(savedTodoItems, MainActivity.this, MainActivity.this);
          rcv_todoList.setAdapter(toDoAdapter);
        }
      }
    });
  }

  private void initViews() {

    addButton = findViewById(R.id.add_button);
    addButton.setOnClickListener(this);

    rcv_todoList = findViewById(R.id.rcv_todoList);
    rcv_todoList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    rcv_todoList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    searchTxt = findViewById(R.id.search_edt);
    searchTxt.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        todoViewModel.fetchSearchResults(searchTxt.getText().toString());
      }
    });
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.add_button) {
      searchTxt.setText(null);
      Intent newTodoIntent = new Intent(this, NewTodoActivity.class);
      startActivityForResult(newTodoIntent, AppConstants.REQUEST_CODE);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == AppConstants.REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        SavedTodoItem todoItem = new SavedTodoItem();
        todoItem.setTitle(data.getStringExtra(AppConstants.TITLE));
        todoItem.setDescription(data.getStringExtra(AppConstants.DESCRIPTION));
        todoItem.setChecked(false);
        todoViewModel.insert(todoItem);
        Toast.makeText(this, R.string.item_added_To_list, Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  public void onCheckBoxClick(SavedTodoItem todoItem) {
    todoViewModel.deleteItem(todoItem);
    //Since we can't insert same object, because primary key will persist same.
    SavedTodoItem item = new SavedTodoItem();
    item.setTitle(todoItem.getTitle());
    item.setDescription(todoItem.getDescription());
    item.setChecked(true);
    todoViewModel.insert(item);
  }
}

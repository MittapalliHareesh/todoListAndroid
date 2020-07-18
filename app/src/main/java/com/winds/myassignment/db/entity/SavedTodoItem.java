package com.winds.myassignment.db.entity;


import static com.winds.myassignment.db.entity.SavedTodoItem.SAVED_TODOS_TABLE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SAVED_TODOS_TABLE)
public class SavedTodoItem {

  public static final String SAVED_TODOS_TABLE = "saved_todo_table";
  public static final String COLUMN_NAME_ID = "ID";
  public static final String COLUMN_NAME_TITLE = "title";
  public static final String COLUMN_NAME_DESCRIPTION = "description";
  public static final String COLUMN_TODO_ITEM_CHECKED = "isCheckBoxSelected";


  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = COLUMN_NAME_ID)
  private long ID;

  public long getID() {
    return ID;
  }

  public void setID(long ID) {
    this.ID = ID;
  }

  @ColumnInfo(name = COLUMN_NAME_TITLE)
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @ColumnInfo(name = COLUMN_NAME_DESCRIPTION)
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @ColumnInfo(name = COLUMN_TODO_ITEM_CHECKED)
  private boolean isChecked;

  public boolean getChecked() {
    return isChecked;
  }

  public void setChecked(boolean checked) {
    isChecked = checked;
  }

  public SavedTodoItem() {

  }
}

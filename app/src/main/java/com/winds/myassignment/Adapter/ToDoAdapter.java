package com.winds.myassignment.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.winds.myassignment.Adapter.ToDoAdapter.ToDoViewHolder;
import com.winds.myassignment.OnCheckBoxClickedListener;
import com.winds.myassignment.R;
import com.winds.myassignment.db.entity.SavedTodoItem;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

  private List<SavedTodoItem> savedTodoItems;
  private OnCheckBoxClickedListener clickListener;
  private Context context;


  public ToDoAdapter(List<SavedTodoItem> todoItems, OnCheckBoxClickedListener listener,
      Context context) {
    this.savedTodoItems = todoItems;
    this.clickListener = listener;
    this.context = context;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.todo_list_item, parent, false);
    return new ToDoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ToDoViewHolder holder, final int position) {
    final SavedTodoItem savedTodoItem = savedTodoItems.get(position);
    holder.title.setText(savedTodoItem.getTitle());
    holder.description.setText(savedTodoItem.getDescription());
    holder.checkBox.setChecked(savedTodoItem.getChecked());
    if (savedTodoItem.getChecked() == true) {
      holder.todoListItem.setAlpha(0.75f);
      holder.todoListItem.setBackgroundColor(Color.GRAY);
    }
    holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        clickListener.onCheckBoxClick(savedTodoItem);
      }
    });
  }

  @Override
  public int getItemCount() {
    return savedTodoItems.size();
  }

  public static class ToDoViewHolder extends RecyclerView.ViewHolder {

    public TextView title, description;
    public CheckBox checkBox;
    public View todoListItem;

    public ToDoViewHolder(@NonNull View itemView) {
      super(itemView);
      this.title = itemView.findViewById(R.id.title_txtView);
      this.description = itemView.findViewById(R.id.description_txtView);
      this.checkBox = itemView.findViewById(R.id.checkbox);
      this.todoListItem = itemView.findViewById(R.id.todoListItem);
    }
  }
}

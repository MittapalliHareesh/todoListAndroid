package com.winds.myassignment;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/*
 *  Considering here Title field as mandatory And Description field as optional.
 *  Because all users may not enter description.
 * */

public class NewTodoActivity extends AppCompatActivity implements OnClickListener {

  EditText title, description;
  Button btn_cancel, btn_done;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_todo);
    title = findViewById(R.id.title_editTxt);
    description = findViewById(R.id.description_editTxt);
    btn_cancel = findViewById(R.id.button_cancel);
    btn_done = findViewById(R.id.button_done);
    btn_done.setOnClickListener(this);
    btn_cancel.setOnClickListener(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.button_cancel) {
      title.setText(null);
      description.setText(null);
      Toast.makeText(this, R.string.not_added, Toast.LENGTH_SHORT).show();
    } else if (view.getId() == R.id.button_done) {
      if (isTitleIsEmpty()) {
        Toast.makeText(this, R.string.tittle_empty, Toast.LENGTH_SHORT).show();
      } else {
        Intent intent = new Intent();
        intent.putExtra(AppConstants.TITLE, title.getText().toString());
        intent.putExtra(AppConstants.DESCRIPTION, description.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
      }
    }
  }

  private boolean isTitleIsEmpty() {
    if (title.getText().toString().isEmpty()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}

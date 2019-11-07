package com.example.mvvm_test.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.mvvm_test.R;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.mvvm_test.view.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.mvvm_test.view.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.mvvm_test.view.EXTRA_PRIORITY";


    private EditText title;
    private EditText description;
    private NumberPicker priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.edit_text_title);
        description = findViewById(R.id.edit_text_description);
        priority = findViewById(R.id.number_picker_priority);

        priority.setMinValue(1);
        priority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        setTitle("Add Note");
    }

    private void saveNote() {
        if (title.getText().toString().trim().isEmpty()
                || description.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please insert a Title and Description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title.getText().toString().trim());
        data.putExtra(EXTRA_DESCRIPTION, description.getText().toString().trim());
        data.putExtra(EXTRA_PRIORITY, priority.getValue());

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

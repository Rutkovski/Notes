package com.demo.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDayOfWeek;
    private RadioGroup radioGroupPriority;
    private NotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        database=NotesDatabase.getInstance(this);
        ActionBar actionBar =getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }


        editTextTitle=findViewById(R.id.editTextTitle);
        editTextDescription=findViewById(R.id.editTextDescription);
        spinnerDayOfWeek=findViewById(R.id.spinnerDaysOfWeek);
       radioGroupPriority = findViewById(R.id.radioGroupPriority);
    }

    public void onClickSaveNote(View view) {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int dayOfWeek = spinnerDayOfWeek.getSelectedItemPosition();
        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());

        if (isField(title,description)) {
           Note note = new Note(title,description,dayOfWeek,priority);
           database.notesDao().insertNote(note);



            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.warning_fill_fields, Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isField (String title, String description){

        return !title.isEmpty()&&!description.isEmpty();
    }
}

package com.demo.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private final ArrayList<Note> notes = new ArrayList<>();
    private MainViewModel viewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar =getSupportActionBar();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        if (actionBar!=null){
            actionBar.hide();
        }

        getData();

        recyclerView = findViewById(R.id.recyclerview);


        adapter = new NoteAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setNoteOnClickListener(new NoteAdapter.NoteOnClickListener() {
            @Override
            public void click(int i) {

                adapter.notifyDataSetChanged();
            }

            @Override
            public void longClick(int i) {


                adapter.notifyDataSetChanged();

            }
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {



               remove(viewHolder.getAdapterPosition());


            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }


    public void onClickAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    private void getData(){
       LiveData<List<Note>> notesFromDB = viewModel.getNotes();
       notesFromDB.observe(this, new Observer<List<Note>>() {
           @Override
           public void onChanged(List<Note> notesFromLiveData) {
             adapter.setNotes(notesFromLiveData);
           }
       });


    }

    private void remove(int position){
        Note note = adapter.getNotes().get(position);
        viewModel.deleteNote(note);
    }



}


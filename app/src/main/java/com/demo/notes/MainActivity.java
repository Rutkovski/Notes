package com.demo.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    public static final ArrayList<Note> notes = new ArrayList<>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);

        if (notes.isEmpty()) {
            notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
            notes.add(new Note("Баскетбол", "Игра со школьной командой", "Вторник", 3));
            notes.add(new Note("Магазин", "Купить новые джинсы", "Понедельник", 3));
            notes.add(new Note("Стоматолог", "Вылечить зубы", "Понедельник", 2));
            notes.add(new Note("Парикмахер", "Сделать прическу к выпускному", "Среда", 1));
            notes.add(new Note("Баскетбол", "Игра со школьной командой", "Вторник", 3));
            notes.add(new Note("Магазин", "Купить новые джинсы", "Понедельник", 3));
        }


        adapter = new NoteAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setNoteOnClickListener(new NoteAdapter.NoteOnClickListener() {
            @Override
            public void click(int i) {
                notes.add(notes.get(i));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void longClick(int i) {
                notes.remove(i);
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

                adapter.notifyDataSetChanged();

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }


    public void onClickAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

}


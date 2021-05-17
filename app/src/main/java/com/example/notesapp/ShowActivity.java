package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;
import com.example.notesapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    ListView noteListView;
    ArrayList<String> noteArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        noteListView = findViewById(R.id.noteList);
        noteArrayList = new ArrayList<>();

        DatabaseHelper db = new DatabaseHelper(ShowActivity.this);

        List<Note> noteList = db.fetchAllNotes();

        for (Note note : noteList) {
            noteArrayList.add(note.getNote_desc());
        }
        //set adapter of list

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,noteArrayList);

        noteListView.setAdapter(adapter);

        //use setOnItemClickListener

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent EditorIntent = new Intent(ShowActivity.this, EditNoteActivity.class);

                EditorIntent.putExtra(Util.NOTE_ID, noteList.get(position).getNote_id());
                EditorIntent.putExtra(Util.DESCRIPTION, noteList.get(position).getNote_desc());

                startActivityForResult(EditorIntent, 1);

                finish();
            }
        });


    }






}
package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;
import com.example.notesapp.util.Util;


public class EditNoteActivity extends AppCompatActivity {

    EditText noteContentEditText;
    Button btn_update, btn_delete;
    DatabaseHelper db;
    String content;
    Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteContentEditText = findViewById(R.id.noteEditor);
        btn_update = findViewById(R.id.updateButton);
        btn_delete = findViewById(R.id.deleteButton);
        db = new DatabaseHelper(this);

        Intent getContentIntent = getIntent();

        String note_Content = getContentIntent.getStringExtra(Util.DESCRIPTION);

        Integer note_ID = getContentIntent.getIntExtra(Util.NOTE_ID,0);

        noteContentEditText.setText(note_Content);

        //create update button click event
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = noteContentEditText.getText().toString();
                id = note_ID;

                int updateIndex = db.updateNote(new Note(id, content));
                if (updateIndex > 0) {
                    Toast.makeText(EditNoteActivity.this, "You update notes successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditNoteActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EditNoteActivity.this,ShowActivity.class);
                startActivity(intent);
                finish();


            }
        });

        //create delete button click event
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = noteContentEditText.getText().toString();
                id = note_ID;

                int deleteIndex = db.deleteNote(new Note(id, content));
                if (deleteIndex > 0) {
                    Toast.makeText(EditNoteActivity.this, "You delete notes successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditNoteActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(EditNoteActivity.this,ShowActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }
}
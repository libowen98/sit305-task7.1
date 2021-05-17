package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;

public class CreateActivity extends AppCompatActivity {

    EditText noteInput;
    Button saveButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        noteInput = findViewById(R.id.note_content);
        saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = noteInput.getText().toString();

                if (content.length() != 0){
                    long result = db.insertNote(new Note(content));

                    if (result > 0){
                        Toast.makeText(CreateActivity.this, "You create note successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(CreateActivity.this, "Create note unsuccessfully!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(CreateActivity.this, "The content of note can not be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
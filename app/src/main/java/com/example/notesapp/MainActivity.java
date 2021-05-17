package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createNote, showNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNote = findViewById(R.id.button);
        showNote = findViewById(R.id.button2);
    }

    public void Create(View view){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);
    }

    public void Show(View view){
        Intent intent = new Intent(this,ShowActivity.class);
        startActivity(intent);
    }
}
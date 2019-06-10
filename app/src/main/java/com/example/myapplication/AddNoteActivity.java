package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHelper;
import com.example.myapplication.Database.Note;

import static android.widget.Toast.makeText;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etNote;
    Button btnAddPhoto;
    Button btnAddVoiceNote;
    Button btnSave;
    String title;
    String noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etTitle=findViewById(R.id.etTitle);
        etNote=findViewById(R.id.etNote);
        btnAddPhoto=findViewById(R.id.btnAddPhoto);
        btnAddVoiceNote=findViewById(R.id.btnAddVoiceNote);
        btnSave=findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=etTitle.getText().toString();
                noteText=etNote.getText().toString();
               Note notes=new Note(title,noteText);
               DatabaseHelper databaseHelper=new DatabaseHelper(getBaseContext(),"notes",null,1);
               long rows=databaseHelper.addNote(notes);
                Log.d("AddNote","Number of notes is "+rows);
//                Note note=new Note (title,noteText);

//                Toast.makeText(getApplicationContext(),"you have clicked a save button",Toast.LENGTH_LONG).show();


            }
        });

    }









    }



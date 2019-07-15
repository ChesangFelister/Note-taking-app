package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.Database.DatabaseHelper;
import com.example.myapplication.Database.Note;

public class EditNoteActivity extends AppCompatActivity {
    int noteId;
    EditText etTitle;
    EditText etNote;
    Button btnEditNote;
    String title;
    String noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getNoteId();
        etTitle=findViewById(R.id.etTitle);
        etNote=findViewById(R.id.etNote);
        displayNote();


        btnEditNote=findViewById(R.id.btnEditNote);


        btnEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=etTitle.getText().toString();
                noteText=etNote.getText().toString();
                Note notes=new Note(title,noteText);
                DatabaseHelper databaseHelper=new DatabaseHelper(getBaseContext(),"notes",null,1);
                long rows=databaseHelper.addNote(notes);
                Log.d("AddNote","Number of notes is "+rows);
                Note note=new Note (noteId,title,noteText);
                finish();



            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void getNoteId(){
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            noteId=bundle.getInt("Note_Id",0);
        }
    }
    public  void displayNote(){
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext(),"notes",null,1);
        Note note=databaseHelper.getNoteById(noteId);
        etTitle.setText(note.getTitle());
        etNote.setText(note.getNoteText());
    }

}

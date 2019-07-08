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
    int noteid;
    Button btnEditnote;
    EditText etTitle;
    EditText etNote;
    Button btnAddPhoto;
    Button btnAddVoiceNote;
    Button btnSave;
    String title;
    String noteText;
    ImageView imgView;
    private static final int CAPTURE_IMAGE_REQUEST_CODE=500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etTitle=findViewById(R.id.etTitle);
        etNote=findViewById(R.id.etNote);
        btnAddPhoto=findViewById(R.id.btnAddPhoto);
        btnAddVoiceNote=findViewById(R.id.btnAddVoiceNote);
        btnSave=findViewById(R.id.btnSave);
        imgView =findViewById(R.id.imgView);
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAPTURE_IMAGE_REQUEST_CODE);
            }
        });

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




        btnEditnote=findViewById(R.id.btnEditNote);
        btnEditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databasehelper=new DatabaseHelper(getApplicationContext(),"notes",null,1);
                databasehelper.updateNote(noteid);
                finish();
//                startActivity(getIntent(),);
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

}

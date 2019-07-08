package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Database.DatabaseHelper;
import com.example.myapplication.Database.Note;

public class ViewNote extends AppCompatActivity {
   int noteid;
   TextView tvtitle;
   TextView tvNoteText;
    Button btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvtitle=findViewById(R.id.tvtitle);
        tvNoteText=findViewById(R.id.tvNoteText);
        btndelete=findViewById(R.id.btndelete);
        displayNote();

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databasehelper=new DatabaseHelper(getApplicationContext(),"notes",null,1);
                databasehelper.deleteNote(noteid);
                finish();
//                startActivity(getIntent(),);
            }
        });
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getNoteId();
    }
    public void getNoteId(){
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            noteid=bundle.getInt("NOTE_ID",0);
        }
    }
    public  void displayNote(){
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext(),"note",null,1);
        Note note=databaseHelper.getNoteById(noteid);
        tvtitle.setText(note.getTitle());
        tvNoteText.setText(note.getNoteText());
    }

}

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.Database.DatabaseHelper;
import com.example.myapplication.Database.Note;

import java.util.Map;

public class EditNoteActivity extends AppCompatActivity {
  private   int noteId;
  private   EditText etTitle;
  private   EditText etNote;
  private   Button btnEditNote;
  private   String title;
  private   String noteText;
  private   String NOTE_API_URL="https://akirachixnotesapi.herokuapp.com/api/v1/notes";
  private String TAG="NOTES_API_RESPONSE";

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
private  void  PostNote(String title,String noteText){
    StringRequest stringRequest=new StringRequest(Request.Method.POST, NOTE_API_URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return super.getParams();
        }
    };

}
}

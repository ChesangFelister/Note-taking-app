package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT ,noteTEXT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

public long addNote(Note note){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",note.getTitle());
        contentValues.put("noteTEXT",note.getNoteText());
        long insert =sqLiteDatabase.insert("notes",null,contentValues);
        sqLiteDatabase.close();
        return insert;

}
public List<Note>getNotes(){
        List<Note>notesList=new ArrayList<Note>();
        String query="SELECT*FROM notes";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
    Cursor cursor=sqLiteDatabase.rawQuery(query,null);
    if (cursor.moveToFirst()==true) {
        do {
            Note note=new Note();

            note.setId(cursor.getInt(cursor.getColumnIndex("id")));
            note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            note.setNoteText(cursor.getString(cursor.getColumnIndex("noteTEXT")));
            notesList.add(note);

        }
        while (cursor.moveToNext()==true);

    }
    sqLiteDatabase.close();
    return notesList;
}

public Note getNoteById(int id){
    Note note=new Note();
    String query="SELECT*  FROM  notes WHERE id=?";
    SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
    Cursor cursor=sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(id)});
    if (cursor.moveToFirst()==true){
        note.setId(cursor.getInt(cursor.getColumnIndex("id")));
        note.setTitle(cursor.getColumnName(cursor.getColumnIndex("title")));
        note.setNoteText(cursor.getString(cursor.getColumnIndex("noteTEXT")));

    }
    sqLiteDatabase.close();
    return note;

}
public void deleteNote(int id){
      SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
      String tableName="notes";
      String whereClause="id=?";
      String[]whereArgs=new String[]{String.valueOf(id)};
      sqLiteDatabase.delete(tableName,whereClause,whereArgs);


}



        }
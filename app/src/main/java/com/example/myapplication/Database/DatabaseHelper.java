package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper<Intent> extends SQLiteOpenHelper {
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

    public long addNote(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("noteTEXT", note.getNoteText());
        long insert = sqLiteDatabase.insert("notes", null, contentValues);
        sqLiteDatabase.close();
        return insert;

    }

    public List<Note> getNotes() {
        List<Note> notesList = new ArrayList<Note>();
        String query = "SELECT*FROM notes";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst() == true) {
            do {
                Note note = new Note();

                note.setId(cursor.getInt(cursor.getColumnIndex("id")));
                note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                note.setNoteText(cursor.getString(cursor.getColumnIndex("noteTEXT")));
                notesList.add(note);

            }
            while (cursor.moveToNext() == true);

        }
        sqLiteDatabase.close();
        return notesList;
    }

    public Note getNoteById(int id) {
        Note note = new Note();
        String query = "SELECT*  FROM  notes WHERE id=?";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst() == true) {
            note.setId(cursor.getInt(cursor.getColumnIndex("id")));
            note.setTitle(cursor.getColumnName(cursor.getColumnIndex("title")));
            note.setNoteText(cursor.getString(cursor.getColumnIndex("noteTEXT")));

        }
        sqLiteDatabase.close();
        return note;

    }

    public void deleteNote(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String tableName = "notes";
        String whereClause = "id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        sqLiteDatabase.delete(tableName, whereClause, whereArgs);


    }


    public int updateNote(Note note) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("noteText", note.getNoteText());
        contentValues.put("title", note.getTitle());

        return sqLiteDatabase.update("notes", contentValues, "Id " + "=?",
                new String[]{String.valueOf(note.getId())});


    }



   public File saveimage(String filename) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;

        File file = new File(filename + ".png");
        if (file.exists()) {
            file = new File(extStorageDirectory, filename + ".png");
            Log.e("file exist", "" + file + ",Bitmap= " + filename);
        }
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getName());

            outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("file", "" + file);
        return file;
    }
}
//  public void saveImage(Bitmap bitmap, String name) {
//        File filesDir = getAppContext().getFilesDir();
//        File imageFile = new File(filesDir, name + ".jpg");
//
//        OutputStream os;
//        try {
//            os = new FileOutputStream(imageFile);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
//        }
//    }
//
//}

package mynotes.dyamoon.com.github.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mynotes.dyamoon.com.github.database.NoteCursorWrapper;
import mynotes.dyamoon.com.github.database.NoteDbHelper;

import static mynotes.dyamoon.com.github.database.NoteDbSchema.*;

public class NoteLab {

     private static NoteLab sNoteLab;

     private Context mContext;
     private SQLiteDatabase mSQLiteDatabase;




     private NoteLab(Context context){

         mContext = context.getApplicationContext();
         mSQLiteDatabase = new NoteDbHelper(context).getWritableDatabase();

     }

     public static NoteLab get(Context context){
         if (sNoteLab == null){ //we call just once conc!!!
             sNoteLab = new NoteLab(context);
         }
         return sNoteLab;
     }

     public List<Note> getNotes(){
         List<Note> notes = new ArrayList<>();
         NoteCursorWrapper cursor = queryNotes(null,null); //all rows
         try {
             cursor.moveToFirst();
             while (!cursor.isAfterLast()){
                 notes.add(cursor.getNote());
                 cursor.moveToNext();
             }
         } finally {
             cursor.close();
         }
         return notes;
     }


     public Note getNote(UUID id){
        NoteCursorWrapper cursor = queryNotes(NOTE_TABLE.COLS.UUID + "= ?",
                new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0 ){
                return  null;
            }

            cursor.moveToFirst();
            return cursor.getNote();
        } finally {
            cursor.close();
        }

     }


     public void updateNote(Note note){
         String uuidString = note.getUUID().toString();
         ContentValues contentValues = getContentValues(note);
         mSQLiteDatabase.update(NOTE_TABLE.NAME, contentValues,
                 NOTE_TABLE.COLS.UUID + " = ? ", new String[]{  uuidString  }  );
     }


     public void addNote(Note note){
       ContentValues contentValues = getContentValues(note);
       mSQLiteDatabase.insert(NOTE_TABLE.NAME, null, contentValues);
     }

     private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs){
         Cursor cursor = mSQLiteDatabase.query(
                 NOTE_TABLE.NAME,
                 null, //all cloumns
                 whereClause,
                 whereArgs,
                 null,
                 null,
                 null);

         return new NoteCursorWrapper(cursor);
     }


     private static ContentValues getContentValues(Note note){
         ContentValues contentValues = new ContentValues();
         contentValues.put(NOTE_TABLE.COLS.UUID, note.getUUID().toString());
         contentValues.put(NOTE_TABLE.COLS.TITLE, note.getTitle());
         contentValues.put(NOTE_TABLE.COLS.DETAILS, note.getDetails());
         return contentValues;
     }

     //return 1 if succesfully, return 0 if unsucsessfulyy
     public int deleteNote(UUID uuid){
         return mSQLiteDatabase.delete(NOTE_TABLE.NAME, NOTE_TABLE.COLS.UUID + " = ? ",     new String[]{   uuid.toString() });
     }




}

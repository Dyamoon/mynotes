package mynotes.dyamoon.com.github.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static mynotes.dyamoon.com.github.database.NoteDbSchema.*;

public class NoteDbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    public static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteBase.db";

    public NoteDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        //db = this.getWritableDatabase(); //if db isn't created, onCreate will be called;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + NOTE_TABLE.NAME + " (" + NOTE_TABLE.COLS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOTE_TABLE.COLS.UUID + ", " +
                NOTE_TABLE.COLS.TITLE + ", " +
                NOTE_TABLE.COLS.DETAILS
                +")"
        );

        //CREATE TABLE notes(_id integer primary key autoincrement, uuid, name, details)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

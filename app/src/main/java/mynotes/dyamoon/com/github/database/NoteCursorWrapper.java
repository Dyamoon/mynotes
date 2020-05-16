package mynotes.dyamoon.com.github.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import mynotes.dyamoon.com.github.Model.Note;

import static mynotes.dyamoon.com.github.database.NoteDbSchema.*;

public class NoteCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote(){
        String uuid = getString(getColumnIndex(NOTE_TABLE.COLS.UUID));
        String title = getString(getColumnIndex(NOTE_TABLE.COLS.TITLE));
        String details = getString(getColumnIndex(NOTE_TABLE.COLS.DETAILS));

        Note note = new Note(UUID.fromString(uuid));
        note.setTitle(title);
        note.setDetails(details);

        return note;
    }
}

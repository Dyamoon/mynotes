package mynotes.dyamoon.com.github.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteLab {
     private List<Note> mNotes;
     private static NoteLab sNoteLab;

     private NoteLab(Context context){
         mNotes = new ArrayList<>();

                for (int i=0;i<100;i++){
                    Note note = new Note();
                    note.setTitle("Title number - " + i);
                    note.setDetails("Note describes smth");
                    mNotes.add(note);
                }
     }

     public static NoteLab get(Context context){
         if (sNoteLab == null){
             sNoteLab = new NoteLab(context);
         }
         return sNoteLab;
     } //I should know for what is context here

     public List<Note> getNotes(){
         return mNotes;
     }


     public Note getNote(UUID id){
         for (Note note : mNotes){
             if (note.getId().equals(id)){
                 return note;
             }
         }
         return null;
     }

}

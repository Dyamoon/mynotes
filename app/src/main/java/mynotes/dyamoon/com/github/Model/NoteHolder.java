package mynotes.dyamoon.com.github.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mynotes.dyamoon.com.github.R;

public class NoteHolder extends RecyclerView.ViewHolder {

    private Note mNote;
    private TextView mTitle;
    private TextView mDetails;



    public NoteHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater.inflate(R.layout.single_note_list_item, parent, false));
        mTitle =(TextView) itemView.findViewById(R.id.note_title);
        mDetails = (TextView)itemView.findViewById(R.id.note_details);
    }

   public void bind(Note note){
        mNote = note;
       mTitle.setText(note.getTitle());
       mDetails.setText(note.getDetails());

    }

}

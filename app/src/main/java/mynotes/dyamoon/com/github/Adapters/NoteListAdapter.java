package mynotes.dyamoon.com.github.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mynotes.dyamoon.com.github.Model.Note;
import mynotes.dyamoon.com.github.Model.NoteHolder;
import mynotes.dyamoon.com.github.R;

public class NoteListAdapter extends RecyclerView.Adapter<NoteHolder> {

    private List<Note> mNotes;


    public NoteListAdapter(List<Note> notes) {
        mNotes = notes;
    }

    /*@NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    return new NoteHolder(layoutInflater, parent);
    }*/ //old version of onCreateViewHolder and NoteHolder conc

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note_list_item, parent, false);

        NoteHolder noteHolder = new NoteHolder(view);

        return noteHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.bind(note);

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void setNotes(List<Note> notes){
        mNotes= notes;
    }

}

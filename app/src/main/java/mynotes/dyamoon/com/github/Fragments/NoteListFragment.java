package mynotes.dyamoon.com.github.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.github.clans.fab.FloatingActionButton;

import java.util.List;



import mynotes.dyamoon.com.github.Acitivities.NoteActivity;
import mynotes.dyamoon.com.github.Model.Note;
import mynotes.dyamoon.com.github.Model.NoteLab;
import mynotes.dyamoon.com.github.R;

public class NoteListFragment extends Fragment {


    private static final String TAG = "NoteListFragment.class";


    private int mLastUpdatedNoteId = -1;


   private RecyclerView mRecyclerView;
   private NoteListAdapter mNoteListAdapter;


   private FloatingActionButton mFloatingActionButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        Log.d(TAG, "onCreateView()");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_note_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        initFloatingButton(view);



        updateUI();


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notes = noteLab.getNotes();

        if (mNoteListAdapter == null) { //if app is running first time
            mNoteListAdapter = new NoteListAdapter(notes);
            mRecyclerView.setAdapter(mNoteListAdapter);
        } else if(mLastUpdatedNoteId > -1){ //if we returned from notefragment and changed smth. We notify not all notelist, but just particular item

            mNoteListAdapter.notifyItemChanged(mLastUpdatedNoteId);
            mLastUpdatedNoteId=-1;
        }  else { //if we
            mNoteListAdapter.notifyDataSetChanged();
        }


    }

    private void initFloatingButton(View view){
        mFloatingActionButton = view.findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note();
                NoteLab noteLab = NoteLab.get(getActivity());
                noteLab.addNote(note);
                Intent intent = NoteActivity.newIntent(getActivity(), note.getId());
                startActivity(intent);
            }
        });
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private static final String TAG = "NoteHolder.java";



        private Note mNote;
        private TextView mTitle;
        private TextView mDetails;



   /*public NoteHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater.inflate(R.layout.single_note_list_item, parent, false));
        mTitle =(TextView) itemView.findViewById(R.id.note_title);
        mDetails = (TextView)itemView.findViewById(R.id.note_details);
    } *///old version of onCreateViewHolder and NoteHolder conc

        public NoteHolder( View itemView) {
            super(itemView);
            Log.d(TAG, "conc NoteHOlder");

            itemView.setOnClickListener(this); //without this doesn't work onClick() method

            mTitle =(TextView) itemView.findViewById(R.id.note_title);
            mDetails = (TextView )itemView.findViewById(R.id.note_details);
        }


        public void bind(Note note) {
            Log.d(TAG, "bind()");
            mNote = note;
            mTitle.setText(note.getTitle());
            mDetails.setText(note.getDetails());
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick()");

            //mLastUpdatedNoteId = NoteLab.get(getActivity()).getNotes().indexOf(mNote);  it should work too
            mLastUpdatedNoteId = getAdapterPosition();
            Log.d(TAG, "onClick(), mLastUpdatedNoteId = " + mLastUpdatedNoteId);

            Intent intent = NoteActivity.newIntent(getActivity(), mNote.getId());
            startActivity(intent);
        }

    }
    private class NoteListAdapter extends RecyclerView.Adapter<NoteHolder> {
        public static final String TAG= "NoteListAdapter.class";
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
            Log.d(TAG, "onCreateViewHolder");
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










}

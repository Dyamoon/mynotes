package mynotes.dyamoon.com.github.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;
import java.util.UUID;

import mynotes.dyamoon.com.github.Acitivities.NoteActivity;
import mynotes.dyamoon.com.github.Acitivities.NoteListActivity;
import mynotes.dyamoon.com.github.Model.Note;

import mynotes.dyamoon.com.github.Model.NoteLab;
import mynotes.dyamoon.com.github.R;

public class NoteFragment extends Fragment{

    private static final String TAG = "NoteFragment.class";

    private static final String ARG_NOTE_ID = "note_id";




    private Note mNote;
    private EditText mNoteTitle;
    private EditText mNoteDetails;




    public static Fragment newInstance(UUID id){
        NoteFragment fragment = new NoteFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_NOTE_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true); //for toolbars


        //Bundle bundle = getArguments(); instead of getArguments
       UUID id = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
       mNote = NoteLab.get(getActivity()).getNote(id);

        Log.d(TAG, "onCreate()");
    }


    @Override
    public void onPause() { //updating notelab when user leaves noteactivity
        super.onPause();
        NoteLab noteLab = NoteLab.get(getActivity());

        if (noteLab.getNote(mNote.getUUID()) != null){ //if we get null, it means that we deleted note in NoreFragment in toolbar
            NoteLab.get(getActivity()).updateNote(mNote);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        mNoteTitle = view.findViewById(R.id.note_title);

        mNoteTitle.setText(mNote.getTitle());

        mNoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNoteDetails = view.findViewById(R.id.note_details);
        mNoteDetails.setText(mNote.getDetails());
        mNoteDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setDetails(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Log.d(TAG, "onCreateView()");

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { //creating toolbar
        inflater.inflate(R.menu.fragment_note, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_note:

                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(getActivity())
                        // set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete")


                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                NoteLab noteLab = NoteLab.get(getActivity()); //get object of class NoteLab
                                noteLab.deleteNote(mNote.getUUID()); //delete note note row from DB
                                dialog.dismiss();
                                Objects.requireNonNull(getActivity()).onBackPressed();
                            }

                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                myQuittingDialogBox.show();

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

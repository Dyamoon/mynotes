package mynotes.dyamoon.com.github.Acitivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

import mynotes.dyamoon.com.github.Fragments.NoteFragment;
import mynotes.dyamoon.com.github.R;

public class NoteActivity extends AppCompatActivity {
    private static final String TAG = "NoteActivity.class";

    private static final String EXTRA_NOTE_ID="note_id";


    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, id);
        return intent;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Log.d(TAG, "onCreate()");


        UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID); ////when we call noteacitivity, it gets note_id by tag EXTRA_NOTE_ID


        Log.d(TAG, "onCreate() called");


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_fragment_note);

        if (fragment == null){
            fragment = NoteFragment.newInstance(id); //here wa call this method and passing noteId, which we got from notelistfragment
            fragmentManager.beginTransaction().add(R.id.container_fragment_note, fragment).commit();
        }

    }
}

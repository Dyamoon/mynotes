package mynotes.dyamoon.com.github.Acitivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import mynotes.dyamoon.com.github.Fragments.NoteFragment;
import mynotes.dyamoon.com.github.Fragments.NoteListFragment;
import mynotes.dyamoon.com.github.R;
import mynotes.dyamoon.com.github.database.NoteDbHelper;
import mynotes.dyamoon.com.github.database.NoteDbSchema;

public class NoteListActivity extends AppCompatActivity {
        public static final String TAG = "NoteListActivity.class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        Log.d(TAG, "onCreate()");


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_fragment_note_list);

        if (fragment == null){
            fragment = new NoteListFragment();
            fragmentManager.beginTransaction().add(R.id.container_fragment_note_list, fragment).commit();
        }


    }

}

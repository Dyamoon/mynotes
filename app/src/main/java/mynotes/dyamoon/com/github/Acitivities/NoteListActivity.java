package mynotes.dyamoon.com.github.Acitivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import mynotes.dyamoon.com.github.Fragments.NoteFragment;
import mynotes.dyamoon.com.github.Fragments.NoteListFragment;
import mynotes.dyamoon.com.github.R;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_fragment_note_list);

        if (fragment == null){
            fragment = new NoteListFragment();
            fragmentManager.beginTransaction().add(R.id.container_fragment_note_list, fragment).commit();
        }


    }

}

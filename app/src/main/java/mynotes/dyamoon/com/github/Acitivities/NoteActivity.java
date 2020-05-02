package mynotes.dyamoon.com.github.Acitivities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import mynotes.dyamoon.com.github.Fragments.NoteFragment;
import mynotes.dyamoon.com.github.R;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity.class";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Log.d(TAG, "onCreate()");

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_fragment_note);

        if (fragment == null){
            fragment = new NoteFragment();
            fragmentManager.beginTransaction().add(R.id.container_fragment_note, fragment).commit();
        }

    }
}

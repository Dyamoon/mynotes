package mynotes.dyamoon.com.github.utils;


import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import mynotes.dyamoon.com.github.R;

public class ShareText extends AppCompatActivity {

    private static final String TAG = "ShareText.java";

    public String getShareText(String title, String details, Context context){ //cause i didnt pass context i couldn't use getString(), cuase getString is from Resources class which needs Context or activity!
         String report = "";
        Log.d(TAG, "getShareText() started");


                 if ( title == null && details == null){
          Log.d(TAG, "first if");
          report = context.getResources().getString(R.string.share_note_empty); //idk why but just that works

                 } else  if(title == null && details != null) {
             report = context.getResources().getString(R.string.share_note_one_empty, details);

         } else  if(title != null && details == null) {
          report = context.getResources().getString(R.string.share_note_one_empty, title);

      } else {
          report = context.getResources().getString(R.string.share_note, title, details); // context here is very essential
        }

        Log.d(TAG, report+ " :::");
        return report;
    }

}

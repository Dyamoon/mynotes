package mynotes.dyamoon.com.github.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.UUID;

import mynotes.dyamoon.com.github.Model.NoteLab;
//I dont use this one right now
//
//
public class DeleteAlertDialog {

    public static AlertDialog deleteAlertDialog(final Context context, final UUID uuid){

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        NoteLab noteLab = NoteLab.get(context); //get object of class NoteLab
                        noteLab.deleteNote(uuid); //delete note note row from DB
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        myQuittingDialogBox.show();
        return myQuittingDialogBox;
    }







}

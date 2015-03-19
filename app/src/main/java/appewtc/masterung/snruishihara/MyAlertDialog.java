package appewtc.masterung.snruishihara;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by masterUNG on 3/19/15 AD.
 */
public class MyAlertDialog {

    public void showDialog(Context context) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle("Please Answer");
        objBuilder.setMessage("Please Answer in Choice");
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.setCancelable(false);
        objBuilder.show();

    }

}   //Main Class

/**
 * DialogUtils - Utility class for managing progress dialogs.
 * This class provides methods to show and hide loading dialogs.
 *
 * @author MD. Jahid Hasan
 * @date November 21, 2023
 */

package com.example.helloworld.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {

    //Shows a loading dialog with the specified message.
    public static ProgressDialog showLoadingDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    //Hides the provided loading dialog if it is currently showing.
    public static void hideLoadingDialog(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
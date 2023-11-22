/**
 * DialogUtils - Utility class for managing progress dialogs.
 * This class provides methods to show and hide loading dialogs.
 *
 * @author MD. Jahid Hasan
 * @date November 21, 2023
 */

package com.example.helloworld.utils;

import android.app.Activity;
import android.app.AlertDialog;
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

    // Shows a dialog indicating that ads failed to load.
    public static void showAdsLoadFailedDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Ads Load Failed")
                .setMessage("Sorry, the ads failed to load. Please try again later.")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    activity.finish();
                })
                .show();
    }
}
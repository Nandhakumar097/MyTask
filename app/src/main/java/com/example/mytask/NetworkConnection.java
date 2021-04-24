package com.example.mytask;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Retrofit;

public class NetworkConnection extends AppCompatActivity {

    public static ProgressDialog progressDialog;

    public NetworkConnection(Activity activity) {
        progressDialog = new ProgressDialog(activity);
    }


    public static boolean isNetworkAvailable(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }



    public static void noNetworkError(final Activity activity, final Class aClass) {

        new AlertDialog.Builder(activity)
                .setTitle("No Internet Connection")
                .setMessage("Please Check Your Internet Connection")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.startActivity(new Intent(activity, aClass));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }



    public static void showLoader(final Activity activity) {
        if (progressDialog == null) {

            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("Loading....");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
        } else {
            try {
                progressDialog.dismiss();

                progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Loading....");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

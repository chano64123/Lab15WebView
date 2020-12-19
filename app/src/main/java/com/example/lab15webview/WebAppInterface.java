package com.example.lab15webview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;

public class WebAppInterface {
    Context context;
    View view;

    public WebAppInterface(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    @JavascriptInterface
    public void showSnackBarNormal(String message){
        Snackbar.make(view,message,Snackbar.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void showSnackBarAction(String message){
        Snackbar snackbar = Snackbar.make(view,message,Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).show();
    }

    @JavascriptInterface
    public void showSnackBarCustom(String message){
        Snackbar snackbar = Snackbar.make(view,message,Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Open App in Web", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(MainActivity.DIRECTION);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                context.startActivity(intent);
            }
        }).setActionTextColor(context.getColor(R.color.teal_200)).setBackgroundTint(context.getColor(R.color.purple_700)).show();
    }

    @JavascriptInterface
    public void showAlertMessage(String message, String title){
        showAlert(message, title);
    }

    private void showAlert(String message, String title) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setCancelable(false);

        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

}

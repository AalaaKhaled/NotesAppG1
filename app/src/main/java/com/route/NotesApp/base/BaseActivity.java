package com.route.NotesApp.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Mohamed Nabil Mohamed on 9/7/2019.
 * m.nabil.fci2015@gmail.com
 */
public class BaseActivity extends AppCompatActivity {

    public AppCompatActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
    }

    public void showMessage(String message,String posActionname){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionname, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public AlertDialog showMessage(String message, String posActionName,
                                   DialogInterface.OnClickListener onClickListener,
                                   boolean isCancelable){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName,onClickListener );
        builder.setCancelable(isCancelable);
        return builder.show();
    }

    public void showMessage(String message,String posActionname,
                            DialogInterface.OnClickListener posAction){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionname,posAction);
        builder.show();
    }
    public void showMessage(int messageId,int posActionStringId){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(messageId);
        builder.setPositiveButton(posActionStringId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public void showMessage(int message,int posActionname,
                            DialogInterface.OnClickListener posAction){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionname,posAction);
        builder.show();
    }

    public AlertDialog showMessage(String message, String posActionName,
                                   DialogInterface.OnClickListener onClickListenerPos,
                                   boolean isCancelable , String neutral , DialogInterface.OnClickListener onClickListenerNeu){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(posActionName,onClickListenerPos );
        builder.setNeutralButton(neutral,onClickListenerNeu);
        builder.setCancelable(isCancelable);
        return builder.show();
    }


    ProgressDialog progressDialog;
    public void showProgressDialog(int messageId){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(messageId));
        progressDialog.show();
    }
    public void hideProgressDialog(){
        if(progressDialog!=null&&progressDialog.isShowing())
            progressDialog.dismiss();
    }
}

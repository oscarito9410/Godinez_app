package com.oscarito.godinez.Helpers;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;

import com.oscarito.godinez.R;

/**
 * Created by oemy9 on 03/01/2017.
 */

public class Permisos {


    public  static  final  int CODIGO_LOCATION=100;


    public  static void abrirGpsConfiguracion(final Activity mActivity){

            final AlertDialog.Builder builder =  new AlertDialog.Builder(mActivity);
            final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            builder.setTitle( R.string.titulo_gps);
            builder.setMessage(mActivity.getString(R.string.mensaje_gps))
                    .setPositiveButton(mActivity.getString(R.string.aceptar),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface d, int id) {
                                    mActivity.startActivity(new Intent(action));
                                    d.dismiss();
                                }
                            })
                    .setNegativeButton(mActivity.getString(R.string.cancelar),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface d, int id) {
                                    d.cancel();
                                }
                            });
            builder.create().show();
        }


    public static boolean  checkIfPermiso(Activity mActivity)
    {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(PermissionChecker.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},CODIGO_LOCATION);
                return false;
            }
           else if(PermissionChecker.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},CODIGO_LOCATION);
                return false;
            }
           else{
                return  true;
            }

        }
        else{
            return true;
        }


    }
}

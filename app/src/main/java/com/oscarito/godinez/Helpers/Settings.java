package com.oscarito.godinez.Helpers;
import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class Settings {

    private Activity currentActitity;
    private final String MY_PREF="MYPREF";
    public Settings(Activity currentActitity) {
        this.currentActitity = currentActitity;
    }
    public void add(String key, String value)
    {
        if(!TextUtils.isEmpty(value)) {

            if(currentActitity!=null) {
                SharedPreferences pref = currentActitity.getSharedPreferences(this.MY_PREF, 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(key, value);
                editor.commit();
            }
        }
    }
    public  void add (String key, float value){
        if(currentActitity!=null) {
            SharedPreferences pref = currentActitity.getSharedPreferences(this.MY_PREF, 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }
    public void add(String key,int value)
    {
        if(currentActitity!=null) {
            SharedPreferences pref = currentActitity.getSharedPreferences(this.MY_PREF, 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public void add(String key, Boolean value)
    {
        if(currentActitity!=null) {
            SharedPreferences pref = currentActitity.getSharedPreferences(this.MY_PREF, 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public boolean get(String key)
    {
        SharedPreferences pref = currentActitity.getSharedPreferences(this.MY_PREF, 0);
        return pref.getBoolean(key,false);
    }

    public String getString(String key)
    {
        SharedPreferences pref = currentActitity.getSharedPreferences(this.MY_PREF, 0);
        return pref.getString(key,null);
    }
    public int getInt(String key){
        SharedPreferences pref = currentActitity.getSharedPreferences(this.MY_PREF, 0);
        return pref.getInt(key,200);
    }

    public float getFloat(String key){
        SharedPreferences pref = currentActitity.getSharedPreferences(this.MY_PREF, 0);
        return pref.getFloat(key,200);
    }


}
package com.oscarito.godinez.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.oscarito.godinez.IO.Model.AroundResponse;
import com.oscarito.godinez.IO.Model.CategoryResponse;
import com.oscarito.godinez.IO.Model.DetailsResponse;

import java.util.ArrayList;

/**
 * Created by oemy9 on 29/01/2017.
 */
public class SQLiteCrudHelper {
    private SQLiteDatabase db;
    public static final String  DB_NAME="GODINEZDB";
    public static final int  VERSION=1;
    public  static  final  String TABLA_FAVORITO="favorito";
    public  SQLiteCrudHelper(Context ctx){
        SQLiteHelper helper=new SQLiteHelper(ctx,DB_NAME,null,VERSION);
        this.db=helper.getWritableDatabase();
    }
    public  boolean delete(int id){
        db.execSQL("DELETE FROM favorito WHERE id='"+id+"'");
        db.close();
        return true;
    }
    public ArrayList<AroundResponse>getFavoritos(){
        ArrayList<AroundResponse>favoritos=new ArrayList<AroundResponse>();
        Cursor c = db.rawQuery("SELECT*FROM favorito",null);
        if(c.moveToFirst()){
            do{
                //  Float laltitude=c.getFloat(c.getColumnIndex("laltitude"));
                // Float longitude=c.getFloat(c.getColumnIndex("longitude"));
                // establecimiento.setLatitude(String.valueOf(laltitude));
                // establecimiento.setLongitude(String.valueOf(longitude));
                CategoryResponse category=new CategoryResponse();
                category.setType(c.getString(c.getColumnIndex("category")));
                AroundResponse establecimiento=new AroundResponse();
                establecimiento.setId(c.getInt(c.getColumnIndex("id")));
                establecimiento.setName(c.getString(c.getColumnIndex("name")));
                establecimiento.setAddress(c.getString(c.getColumnIndex("address")));
                establecimiento.setLogo(c.getString(c.getColumnIndex("logo")));
                establecimiento.setRating(c.getColumnIndex("rating"));
                establecimiento.setCategory(category);
                favoritos.add(establecimiento);
            }while (c.moveToNext());
        }
        return favoritos;
    }
    public  boolean insetFavorito(DetailsResponse establecimiento){

        if(!existe(establecimiento.getId())) {
            ContentValues nuevo = new ContentValues();
            nuevo.put("id", establecimiento.getId());
            nuevo.put("name", establecimiento.getName());
            nuevo.put("laltitude", establecimiento.getLatitude());
            nuevo.put("longitude", establecimiento.getLongitude());
            nuevo.put("address", establecimiento.getAddress());
            nuevo.put("category", establecimiento.getCategory().getName());
            nuevo.put("logo", establecimiento.getLogo());
            nuevo.put("rating", establecimiento.getRanking());
            this.db.insertOrThrow(TABLA_FAVORITO, null, nuevo);
            return true;
        }
        else{
            return  false;
        }
    }

    public  boolean existe(int id){
        Cursor c=db.rawQuery("SELECT* FROM favorito WHERE id='"+String.valueOf(id)+"'",null);
        return c.getCount()>0;
    }




}

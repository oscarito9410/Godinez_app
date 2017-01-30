package com.oscarito.godinez.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oemy9 on 29/01/2017.
 */

public class SQLiteHelper  extends SQLiteOpenHelper {
        public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS favorito( id INTEGER PRIMARY KEY,name TEXT, laltitude FLOAT NOT NULL, longitude FLOAT NOT NULL, address TEXT, category TEXT, logo TEXT,  rating FLOAT,  UNIQUE(id) ON CONFLICT REPLACE);");

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

    }


package com.example.administrator.getrssi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/12/5.
 */

public class Collect extends SQLiteOpenHelper {
    public Collect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE person(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "ID VARCHAR(10),Rssi VARCHAR(10),Distance VARCHAR(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE daily ADD weather Varchar");
    }
}

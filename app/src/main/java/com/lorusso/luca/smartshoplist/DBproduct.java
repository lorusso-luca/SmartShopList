package com.lorusso.luca.smartshoplist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luca on 15/03/2018.
 */

public class DBproduct extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyProduct.db";
    public static final String CONTACTS_TABLE_NAME = "category";
    public static final String CONTACTS_COLUMN_ID = "idCategory";
    public static final String CONTACTS_COLUMN_NAME = "nameCategory";

    public DBproduct(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table category " +
                        "(idCategory integer primary key, nameCategory text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}

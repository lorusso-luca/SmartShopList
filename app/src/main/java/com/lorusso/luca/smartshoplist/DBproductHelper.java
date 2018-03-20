package com.lorusso.luca.smartshoplist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luca on 15/03/2018.
 */

public class DBproductHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Data-warehouse";
    private static final int DATABASE_VERSION = 1;

    public static final String PRODUCT_TABLE = "product";
    public static final String CATEGORY_TABLE = "category";


    public static final String PRODUCT_COLUMN_ID = "idProduct";
    public static final String PRODUCT_COLUMN_NAME = "nameProduct";
    public static final String PRODUCT_COLUMN_QUANTITY = "quantity";
    public static final String PRODUCT_COLUMN_DESCRIPTION = "description";
    public static final String PRODUCT_COLUMN_CATEGORY = "idCategory";

    public static final String CATEGORY_COLUMN_ID = "idCategory";
    public static final String CATEGORY_COLUMN_NAME = "nameCategory";


    public static final String CREATE_PRODUCT_TABLE =
            "CREATE TABLE " + PRODUCT_TABLE + "("
                    + PRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PRODUCT_COLUMN_NAME + " TEXT,"
                    + PRODUCT_COLUMN_QUANTITY + " INTEGER"
                    + PRODUCT_COLUMN_DESCRIPTION + " TEXT"
                    + "FOREIGN KEY(" + PRODUCT_COLUMN_CATEGORY + ") REFERENCES " + CATEGORY_TABLE + "(idCategory) "
                    + ")";

    public static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + CATEGORY_TABLE + "("
                    + CATEGORY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CATEGORY_COLUMN_NAME + " TEXT,"
                    + ")";

    public static DBproductHelper instance;

    public static synchronized DBproductHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBproductHelper(context);
        return instance;
    }
    private DBproductHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
        onCreate(db);
    }

    public void insertNote(String p) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        //TODO FIX THIS
        //values.put(PRODUCT_TABLE, note);
        // insert row
        //long id = db.insert(Product.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
       // return id;
    }
}
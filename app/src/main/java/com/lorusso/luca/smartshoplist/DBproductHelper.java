package com.lorusso.luca.smartshoplist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    public static final String PRODUCT_COLUMN_CATEGORY = "idCategoryProd";

    public static final String CATEGORY_COLUMN_ID = "idCategory";
    public static final String CATEGORY_COLUMN_NAME = "nameCategory";


    public static final String CREATE_PRODUCT_TABLE =
            "CREATE TABLE " + PRODUCT_TABLE + " ("
                    + PRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PRODUCT_COLUMN_NAME + " TEXT,"
                    + PRODUCT_COLUMN_DESCRIPTION + " TEXT, "
                    + PRODUCT_COLUMN_QUANTITY + " INTEGER, "
                    + PRODUCT_COLUMN_CATEGORY + " INTEGER, "
                    + "FOREIGN KEY(" + PRODUCT_COLUMN_CATEGORY + ") REFERENCES " + CATEGORY_TABLE + "(" + CATEGORY_COLUMN_ID + ")"
                    + ");";

    public static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + CATEGORY_TABLE + "("
                    + CATEGORY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CATEGORY_COLUMN_NAME + " TEXT"
                    + ");";

    public static DBproductHelper instance;

    public static synchronized DBproductHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBproductHelper(context);
        return instance;
    }

    public DBproductHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);

        onCreate(db);
    }


    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public long insertProduct(String p, int idF) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them

        values.put(PRODUCT_COLUMN_NAME, p);
        values.put(PRODUCT_COLUMN_CATEGORY, idF);
        // insert row
        long id = db.insert(PRODUCT_TABLE, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertCategory(String c) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them

        values.put(CATEGORY_COLUMN_NAME, c);
        // insert row
        long id = db.insert(CATEGORY_TABLE, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public void initialCategory(ArrayList c) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i <= c.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(CATEGORY_COLUMN_NAME, (String) c.get(i));
            db.insert(CATEGORY_TABLE, null, values);
        }
        db.close();
    }

    public Product getProductByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{PRODUCT_COLUMN_ID, PRODUCT_COLUMN_NAME, PRODUCT_COLUMN_DESCRIPTION, PRODUCT_COLUMN_QUANTITY, PRODUCT_COLUMN_CATEGORY};
        Cursor c = db.query(PRODUCT_TABLE, columns, PRODUCT_COLUMN_NAME + "=?", new String[]{name}, null, null, null);
        c.moveToFirst();
        int id = Integer.parseInt(c.getString(c.getColumnIndex(PRODUCT_COLUMN_ID)));
        String nameP = c.getString(c.getColumnIndex(PRODUCT_COLUMN_NAME));
        String description = c.getString(c.getColumnIndex(PRODUCT_COLUMN_DESCRIPTION));
        int quantity;
        if (c.getString(c.getColumnIndex(PRODUCT_COLUMN_QUANTITY)) != null) {
            quantity = Integer.parseInt(c.getString(c.getColumnIndex(PRODUCT_COLUMN_QUANTITY)));
        } else {
            quantity = 0;
        }

        int cat = Integer.parseInt(c.getString(c.getColumnIndex(PRODUCT_COLUMN_CATEGORY)));
        Category ca = getCategory(cat);
        Product p = new Product(id, nameP, description, quantity, ca);
        return p;
    }

    public Category getCategory(int id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CATEGORY_TABLE,
                new String[]{CATEGORY_COLUMN_ID, CATEGORY_COLUMN_NAME},
                CATEGORY_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Category category = new Category(
                cursor.getInt(cursor.getColumnIndex(CATEGORY_COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(CATEGORY_COLUMN_NAME)));

        // close the db connection
        cursor.close();

        return category;
    }
}

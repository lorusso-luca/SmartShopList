package com.lorusso.luca.smartshoplist;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Luca on 20/03/2018.
 */

public class DBproduct {

    protected SQLiteDatabase database;
    private DBproductHelper dbHelper;
    private Context mContext;

    public DBproduct(Context context) {
        this.mContext = context;
        dbHelper = DBproductHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DBproductHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

}

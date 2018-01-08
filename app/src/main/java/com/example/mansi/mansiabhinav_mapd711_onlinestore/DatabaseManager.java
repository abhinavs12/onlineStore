package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mansi on 1/7/18.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "onlineeeeeshopp.db";

    public static final String TABLE_NAME = "customer_table";

    public static final String TABLE1_NAME = "order_table";


    public static final String COL_1 = "_id";
    public static final String COL_2 = "firstname";
    public static final String COL_3 = "lastname";
    public static final String COL_4 = "username";
    public static final String COL_5 = "password";
    public static final String COL_6 = "gender";
    public static final String COL_7 = "dob";

    public static final String COLM_1 = "_orderid";
    public static final String COLM_2 = "username";
    public static final String COLM_3 = "productName";
    public static final String COLM_4 = "status";
    public static final String COLM_5 = "date";
    public static final String COLM_6 = "price";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, username TEXT, password TEXT,gender TEXT, dob TEXT)");
      db.execSQL("create table " + TABLE1_NAME + " (_orderid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, productName TEXT, status TEXT, date TEXT, price TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE If EXISTS" + TABLE_NAME);
        db.execSQL("DROP TABLE If EXISTS" + TABLE1_NAME);
        onCreate(db);
    }

    public boolean insertCustomer(String firstname,String lastname,String username, String password, String gender, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,firstname);
        contentValues.put(COL_3,lastname);
        contentValues.put(COL_4,username);
        contentValues.put(COL_5,password);
        contentValues.put(COL_6,gender);
        contentValues.put(COL_7,dob);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public boolean insertData_table1(String username,String productName, String status, String date, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLM_2,username);
        contentValues.put(COLM_3,productName);
        contentValues.put(COLM_4,status);
        contentValues.put(COLM_5,date);
        contentValues.put(COLM_6,price);

        long result = db.insert(TABLE1_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData_table1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE1_NAME,null);
        return res;
    }

    public Cursor getDataByUsername_table1(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE1_NAME+ " where username = \""+ username+"\"",null);
        return res;
    }

    public Cursor getDataByID_table1(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE1_NAME+ " where _orderid = \""+ id+"\"",null);
        return res;
    }


    public boolean updateOrder(String id,String username,String productName,String status, String date, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLM_1,id);
        contentValues.put(COLM_2,username);
        contentValues.put(COLM_3,productName);
        contentValues.put(COLM_4,status);
        contentValues.put(COLM_5,date);
        contentValues.put(COLM_6,price);
        db.update(TABLE1_NAME, contentValues, "_orderid = ?",new String[] { id });
        return true;
    }

    public Integer deleteOrderByID (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE1_NAME, "_orderid = ?",new String[] {id});
    }
}

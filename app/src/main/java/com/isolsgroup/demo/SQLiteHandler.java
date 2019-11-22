package com.isolsgroup.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "my_demo";
    private static final String TABLE_USER = "user_info"; // User Table
    private static final String TABLE_ADD_TO_CART = "cart";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_PASSWORD = "password";

    // Fields for table
    public static final String Nid = "Nid";
    public static final String Title = "title";
    public static final String Thumb_images = "thumb_images";
    public static final String Qty = "qty";
    public static final String Price = "price";
    public static final String Size_id = "size_id";
    public static final String Total_stock = "total_stock";
    private static final String ID = "id";

    Context context;
    private ContentValues cValues;
    private SQLiteDatabase dataBase = null;
    private Cursor cursor;

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_PHONE_NUMBER + " TEXT, "
                + KEY_PASSWORD + " TEXT"
                + ")";

        String CREATE_TABLE1 = "CREATE TABLE " + TABLE_ADD_TO_CART + "(" + ID
                + " INTEGER PRIMARY KEY autoincrement, " + Nid + " long, "
                + Title + " TEXT, " + Thumb_images + " TEXT, " + Qty
                + " TEXT, " + Price + " TEXT, "
                + Total_stock + " TEXT)";

        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_TO_CART);
        // Create tables again
        onCreate(db);
    }

    public void addUser(String name, String phone_number, String password){
        SQLiteDatabase db = this.getWritableDatabase ();

        ContentValues values = new ContentValues ();
        values.put (KEY_NAME, name);
        values.put (KEY_PHONE_NUMBER, phone_number);
        values.put (KEY_PASSWORD, password);

        long id = db.insert (TABLE_USER, null, values);
        db.close ();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery (selectQuery, null);
        cursor.moveToFirst ();
        if (cursor.getCount ()> 0){
            user.put(KEY_NAME, cursor.getString(1));
            user.put(KEY_PHONE_NUMBER, cursor.getString(2));
            user.put(KEY_PASSWORD, cursor.getString(3));

        }
        cursor.close();
        db.close();

        return user;

    }

    public void deleteUser(){
        SQLiteDatabase database = this.getWritableDatabase ();
        database.delete (TABLE_USER, null, null);
        database.close ();
    }

    public void inserRecord(long l, String title, String thumb_images, int i,
                            String bigDecimal, String total_stock) {
        dataBase = getWritableDatabase();
        cursor = dataBase.rawQuery("select count(*) from " + TABLE_ADD_TO_CART
                + " WHERE Nid =" + l + " and Title=" + "'" + title + "'", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        //    Log.v("size_id", size_id);
        if (count <= 0) {
            Log.v("", "Not Found");
            cValues = new ContentValues();
            cValues.put(Nid, l);
            cValues.put(Title, title);
            cValues.put(Thumb_images, thumb_images);
            cValues.put(Qty, i);
            cValues.put(Price, bigDecimal);
            cValues.put(Total_stock, total_stock);
            dataBase.insert(TABLE_ADD_TO_CART, null, cValues);
        } else {
            Log.v("", "Found");
            cursor = dataBase.rawQuery("select Qty from " + TABLE_ADD_TO_CART
                    + " WHERE Nid = " + l, null);
            cursor.moveToFirst();
            int count1 = cursor.getInt(0);
            updateRecord(l, i, total_stock, count1);
        }
        dataBase.close();
    }

    public void updateRecord(long nid, int Quan, String total, int preqty) {
        dataBase = getWritableDatabase();
        cValues = new ContentValues();
        if (Quan + preqty <= Integer.parseInt(total)) {
            cValues.put(Qty, Quan + preqty);
        } else {
            cValues.put(Qty, Integer.parseInt(total));
            Log.v("total", total);
        }
        if (Quan + preqty > 0) {
            dataBase.update(SQLiteHandler.TABLE_ADD_TO_CART, cValues, "Nid=" + nid, null);
        }
        dataBase.close();
    }

    public void updateRecordgift(long nid, int Quan, String total, int preqty, String title) {
        dataBase = getWritableDatabase();
        cValues = new ContentValues();
        if (Quan + preqty <= Integer.parseInt(total)) {
            cValues.put(Qty, Quan + preqty);
        } else {
            cValues.put(Qty, Integer.parseInt(total));
            Log.v("total", total);
        }
        if (Quan + preqty > 0) {
            dataBase.update(SQLiteHandler.TABLE_ADD_TO_CART, cValues, "Nid=" + nid
                    + " and Title='" + title + "'", null);
        }
        dataBase.close();
    }

    public void DeleteRecord(long nid, String string) {
        dataBase = getWritableDatabase();
        dataBase.delete(SQLiteHandler.TABLE_ADD_TO_CART, "nid=? and Size_id=? ",
                new String[] { String.valueOf(nid), String.valueOf(string) });
        dataBase.close();
    }
    public void DeleteRecordgift(long nid, String string) {
        dataBase = getWritableDatabase();
        dataBase.delete(SQLiteHandler.TABLE_ADD_TO_CART, "nid=? and Title=? ",
                new String[] { String.valueOf(nid), String.valueOf(string) });
        dataBase.close();
    }

    public int countRecord() {
        int count = 0;
        dataBase = getReadableDatabase();
        cursor = dataBase.rawQuery("select sum(Qty) from " + TABLE_ADD_TO_CART, null);
        cursor.moveToFirst();
        count = cursor.getInt(0);
        dataBase.close();
        return count;
    }

    public int countRecordbynid(long l) {
        int count = 0;
        dataBase = getReadableDatabase();
        cursor = dataBase.rawQuery("select Qty from " + TABLE_ADD_TO_CART + " WHERE Nid = " + l  , null);
        cursor.moveToFirst();
        count = cursor.getInt(0);
        dataBase.close();
        return count;
    }

    public int gettotalprice() {
        int count = 0;
        dataBase = getReadableDatabase();
        cursor = dataBase.rawQuery("select sum(Qty*Price) from " + TABLE_ADD_TO_CART,
                null);
        cursor.moveToFirst();
        count = cursor.getInt(0);
        dataBase.close();
        return count;
    }

    public Cursor selectRecords() {
        dataBase = getReadableDatabase();
        // Getting data from database table
        cursor = dataBase.rawQuery("select * from " + TABLE_ADD_TO_CART, null);
        return cursor;
    }

    public void deleteRecord() {
        dataBase = getWritableDatabase();
        // Deleting all records from database table
        dataBase.delete(TABLE_ADD_TO_CART, null, null);
        dataBase.close();
    }

}

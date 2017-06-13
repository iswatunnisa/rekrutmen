package id.ac.nisaamikom.rekrutmen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "rekrutmen1.db";
    public static final int DB_VERSION = 2;

    public static final String ADMIN_TABLE = "admin";
    public static final String ADMIN_ID = "_id";
    public static final String ADMIN_EMAIL = "email";
    public static final String ADMIN_PASS = "password";
    public static final String ADMIN_HP = "hp";

    public static final String PEGAWAI_TABLE = "pegawai";
    public static final String PEGAWAI_ID = "peg_id";
    public static final String PEGAWAI_EMAIL = "peg_email";
    public static final String PEGAWAI_PASS = "peg_password";
    public static final String PEGAWAI_HP = "peg_hp";


    public static final String ITEMS_TABLE = "items";
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_NAME = "item_name";

    public static final String CREATE_TABLE_ADMIN = "CREATE TABLE " + ADMIN_TABLE + "("
            + ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ADMIN_EMAIL + " TEXT,"
            + ADMIN_PASS + " TEXT),"
            + ADMIN_HP + " TEXT);";


    public static final String CREATE_TABLE_PEGAWAI= "CREATE TABLE " + PEGAWAI_TABLE + "("
            + PEGAWAI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PEGAWAI_EMAIL + " TEXT,"
            + PEGAWAI_PASS + " TEXT),"
            + PEGAWAI_HP + " TEXT);";


    public static final String CREATE_TABLE_ITEMS = "CREATE TABLE " + ITEMS_TABLE + "("
            + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ITEM_NAME + " TEXT);";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_PEGAWAI);
        db.execSQL(CREATE_TABLE_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PEGAWAI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(db);
    }

    public void addSeller(String email, String password, String hp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADMIN_EMAIL, email);
        values.put(ADMIN_PASS, password);
        values.put(ADMIN_HP, hp);

        long id = db.insert(ADMIN_TABLE, null, values);
        db.close();

        Log.d(TAG, "seller inserted" + id);
    }

    public boolean getAdmin(String email, String pass) {
        String selectQuery = "select * from  " + ADMIN_TABLE + " where " +
                ADMIN_EMAIL + " = " + "'" + email + "'" + " and " + ADMIN_PASS + " = " + "'" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public void addBuyer(String email, String password, String peg_hp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PEGAWAI_EMAIL, email);
        values.put(PEGAWAI_PASS, password);
        values.put(PEGAWAI_HP, peg_hp);

        long id = db.insert(PEGAWAI_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public boolean getPegawai(String email, String pass) {
        String selectQuery = "select * from  " + PEGAWAI_TABLE + " where " +
                PEGAWAI_EMAIL + " = " + "'" + email + "'" + " and " + PEGAWAI_PASS + " = " + "'" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public void addItem(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, name);
        long id = db.insert(ITEMS_TABLE, null, values);
        db.close();

        Log.d(TAG, "item inserted" + id);
    }

    public ArrayList<String> getAllCategorys() {
        ArrayList<String> categoryList = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + ITEMS_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.query(ITEMS_TABLE,
                null, null, null, null, null, null);
        cur.moveToFirst();
        while (cur.isAfterLast() == false) {
            categoryList.add(cur.getString(1));
            cur.moveToNext();
        }
        cur.close();

        return categoryList;
    }
}

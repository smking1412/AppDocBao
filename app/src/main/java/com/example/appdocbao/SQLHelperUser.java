package com.example.appdocbao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.URL;
import java.util.ArrayList;

public class SQLHelperUser extends SQLiteOpenHelper {
    final static String DB_NAME = "UserNews";
    final static  int DB_VERSION = 1;
    private static final String DB_NAME_TABLE = "User";

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHelperUser(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySQL = "CREATE TABLE User (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "email TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "numberphone TEXT NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(querySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_NAME_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public void insertNewUser(String email, String password, String name, String numberphone){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("numberphone", numberphone);

        sqLiteDatabase.insert(DB_NAME_TABLE, null, contentValues);
        closeDB();
    }

    public int deleteNewUser(String id) {
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(DB_NAME_TABLE, " id=?",
                new String[]{String.valueOf(id)});
    }

    public ArrayList<User> getAllUserAdvanced() {
        ArrayList<User> userList = new ArrayList<>();
        User user;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_NAME_TABLE, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String numberphone = cursor.getString(cursor.getColumnIndex("numberphone"));
            user = new User(email, password, name, numberphone);
            userList.add(user);
        }
        closeDB();
        return userList;
    }

    private void closeDB() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }
}

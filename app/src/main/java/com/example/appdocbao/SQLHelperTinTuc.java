package com.example.appdocbao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class SQLHelperTinTuc extends SQLiteOpenHelper{
    final static String DB_NAME = "TinTuc";
    final static  int DB_VERSION = 2;
    private static final String DB_NAME_TABLE = "NewHistory";

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHelperTinTuc(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySQL = "CREATE TABLE NewHistory (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "img TEXT NOT NULL," +
                "cmt TEXT NOT NULL," +
                "pubDate TEXT NOT NULL" +
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

    public void insertNewHistory(String title, String img, String cmt, String pubDate){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("img", img);
        contentValues.put("cmt", cmt);
        contentValues.put("pubDate", pubDate);

        sqLiteDatabase.insert(DB_NAME_TABLE, null, contentValues);
        closeDB();
    }

    public int deleteNew(String id) {
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(DB_NAME_TABLE, " id=?",
                new String[]{String.valueOf(id)});
    }

    public ArrayList<ItemTinTuc> getAllNewAdvanced() {
        ArrayList<ItemTinTuc> tinTucHistoryList = new ArrayList<>();
        ItemTinTuc tinTucHistory;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_NAME_TABLE, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String img = cursor.getString(cursor.getColumnIndex("img"));
            String cmt = cursor.getString(cursor.getColumnIndex("cmt"));
            String pubDate = cursor.getString(cursor.getColumnIndex("pubDate"));
            tinTucHistory = new ItemTinTuc(title, img, cmt, pubDate);
            tinTucHistoryList.add(tinTucHistory);
        }
        closeDB();
        return tinTucHistoryList;
    }

    private void closeDB() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }
}

package com.example.simple_app;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login3.db";


    public DBHelper(Context context) {
        super(context, "Login3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key,nameSurname TEXT,email TEXT, password TEXT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password, String email, String nameSurname, String date){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("nameSurname", nameSurname);
        contentValues.put("date", date);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public HashMap<String, String> getInfo(String username) {
        HashMap<String, String> wordList = new HashMap<String, String>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM users where username='"+username+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                wordList.put("nameSurname", cursor.getString(1));
                wordList.put("email", cursor.getString(2));
                wordList.put("password", cursor.getString(3));
                wordList.put("date", cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return wordList;
    }

    public boolean updateUsername(String val, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", val);
        int rows = MyDB.update("users", contentValues,  "username = ?" , new String[] {username} );
        MyDB.close();
        if(rows == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean updateNameSurname(String nameSurname, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameSurname", nameSurname);
        int rows = MyDB.update("users", contentValues,  "username = ?" , new String[] {username} );
        MyDB.close();
        return (rows > 0);
    }
    public boolean updateEmail(String email, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        int rows = MyDB.update("users", contentValues,  "username = ?" , new String[] {username} );
        MyDB.close();
        return (rows > 0);
    }

    public boolean updatePassword(String password, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        int rows = MyDB.update("users", contentValues,  "username = ?" , new String[] {username} );
        MyDB.close();
        return (rows > 0);
    }

}
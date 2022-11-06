package com.example.ueeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(name TEXT, mobile TEXT, email TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String name, String mobile, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("name",name);
        contentValues.put("mobile", mobile);

        long result = MyDB.insert("users",null,contentValues);
        if(result==-1)return false;
        else
            return true;
    }

    public Boolean checkemail(String email)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?",new String[] {email});
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public Boolean checkemailpassword(String email,String password){
        SQLiteDatabase MyBD = this.getWritableDatabase();
        Cursor cursor = MyBD.rawQuery("select * from users where email = ? and password = ?",new String[] {email,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

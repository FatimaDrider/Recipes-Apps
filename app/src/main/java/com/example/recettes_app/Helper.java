package com.example.recettes_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="db";
    public  static final String TABLE_NAME="User";
    public static  final String COL_1="USERNAME";
    public static final String COL_2="EMAIL";
    public static  final String COL_3="PHONE";
    private static final String COL_4 = "PASSWORD";


    public Helper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT ,EMAIL TEXT,PHONE TEXT,PASSWORD TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
onCreate(sqLiteDatabase);
    }


    public boolean insertData(String username, String email,String phone,String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,phone);
        contentValues.put(COL_4,password);
        long result = db.insert("User",null,contentValues);
        if (result == -1){
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor login_user(String email,String password){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME + " where EMAIL='"+email+"' AND PASSWORD='"+password+"'",null );
        return res;
    }



    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return cursor;
    }



}

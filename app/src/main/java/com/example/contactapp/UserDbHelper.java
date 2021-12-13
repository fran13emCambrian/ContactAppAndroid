package com.example.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="userinfo.db";
    private static final int DATABASE_VERSION=1;
    private static final String QUERY1="CREATE TABLE "+UserContract.Info.TABLE_NAME+" ( "+
            UserContract.Info.USER_NAME+" TEXT  , "+
            UserContract.Info.USER_MOB+" TEXT  , "+
            UserContract.Info.USER_EMAIL+" TEXT );"   ;


    public UserDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("Database operation: ","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY1);
        Log.e("Database operation: "," Table created");


    }
    //for inserting data
    public void addinformation(String name, String mob,String email,SQLiteDatabase db){
        ContentValues c= new ContentValues();
        c.put(UserContract.Info.USER_NAME,name);
        c.put(UserContract.Info.USER_MOB,mob);
        c.put(UserContract.Info.USER_EMAIL,email);
        db.insert(UserContract.Info.TABLE_NAME,null,c);
        Log.e("Database operation: ","Data entry done- 1 row affected");
    }

    //for displaying data
    public Cursor getinformation(SQLiteDatabase sqLiteDatabase){
        Cursor cursor;
        String display[]={UserContract.Info.USER_NAME,UserContract.Info.USER_MOB,UserContract.Info.USER_EMAIL};
        cursor=sqLiteDatabase.query(UserContract.Info.TABLE_NAME,display,null,null, null,
                null,UserContract.Info.USER_NAME);
        return cursor;
    }


    //for searching contact
    public Cursor searchinformation(String user_name,SQLiteDatabase sqLiteDatabase){
        Cursor cursor;
        String[] projection={UserContract.Info.USER_NAME,UserContract.Info.USER_MOB,UserContract.Info.USER_EMAIL};
        String selection=UserContract.Info.USER_NAME+" LIKE ?";
        String selection_args[]={user_name};
        cursor=sqLiteDatabase.query(UserContract.Info.TABLE_NAME,projection,selection,selection_args,null,null,null);
        return cursor;
    }
    //for updating contact
    public int updateinformation(String user_name,String new_name,String new_mobile,String new_mail,SQLiteDatabase sqLiteDatabase){
        ContentValues c= new ContentValues();
        c.put(UserContract.Info.USER_NAME,new_name);
        c.put(UserContract.Info.USER_MOB,new_mobile);
        c.put(UserContract.Info.USER_EMAIL,new_mail);
        String selection=UserContract.Info.USER_NAME+" LIKE ?";
        String selection_args[]={user_name};
        int count=sqLiteDatabase.update(UserContract.Info.TABLE_NAME,c,selection,selection_args);
        return count;
    }
    //for deleting contact
    public void deleteinformation(String user_name,SQLiteDatabase sqLiteDatabase){

        String selection=UserContract.Info.USER_NAME+" LIKE ?";
        String selection_args[]={user_name};
        sqLiteDatabase.delete(UserContract.Info.TABLE_NAME,selection,selection_args);
        Log.e("Database opertion: ","One row deleted...");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

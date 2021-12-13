package com.example.contactapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contactapp.DataListAdapter;
import com.example.contactapp.DataProvider;
import com.example.contactapp.R;
import com.example.contactapp.UserDbHelper;

public class View_activity extends AppCompatActivity {
    ListView listView;
    Cursor cursor;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    DataProvider dataProvider;
    DataListAdapter dataListAdapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_activity);
        listView = findViewById(R.id.listview);
        dataListAdapter = new DataListAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(dataListAdapter);
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getinformation(sqLiteDatabase);


        if (cursor.moveToFirst()) {
            do {
                String name, mobile, email;
                name = cursor.getString(0);
                mobile = cursor.getString(1);
                email = cursor.getString(2);
                dataProvider = new DataProvider(name, mobile, email);
                dataListAdapter.add(dataProvider);
                // Toast.makeText(getApplicationContext(),name+" "+mobile+" "+email,Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());

        } else {

        }

    }





}

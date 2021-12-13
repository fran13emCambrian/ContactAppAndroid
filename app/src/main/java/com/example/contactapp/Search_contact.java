package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactapp.R;
import com.example.contactapp.UserDbHelper;

public class Search_contact extends AppCompatActivity {
    Button search,delete;
    EditText search_name;
    TextView mob,email;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    String searched_name;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        search=findViewById(R.id.search);
        delete=findViewById(R.id.delete);
        search_name=findViewById(R.id.search_name);
        mob=findViewById(R.id.mob);
        email=findViewById(R.id.email);
        delete.setVisibility(View.GONE);
        mob.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchfunction(v);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletefunction(v);
            }

        } );
    }
    public void searchfunction(View v){
        searched_name=search_name.getText().toString();
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        cursor=userDbHelper.searchinformation(searched_name, sqLiteDatabase);


        if (cursor.moveToFirst()) {
            do {

                if (searched_name.trim().equalsIgnoreCase(cursor.getString(0).trim())) {
                    mob.setVisibility(View.VISIBLE);
                    email.setVisibility(View.VISIBLE);
                    String mobile = cursor.getString(1);
                    String mail = cursor.getString(2);

                    mob.setText("MOBILE: " + mobile);
                    email.setText("EMAIL:" + mail);
                    delete.setVisibility(View.VISIBLE);


                }
            } while (cursor.moveToNext());
        }
        else{
            Toast.makeText(getApplicationContext(),"Contact not found ",Toast.LENGTH_LONG).show();
        }
    }
    public void deletefunction(View v){
        searched_name=search_name.getText().toString();

        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        userDbHelper.deleteinformation(searched_name,sqLiteDatabase);

        if (cursor.moveToFirst()) {
            do {
                if(searched_name.trim().equals(cursor.getString(0).trim()))
                {
                    Toast.makeText(getApplicationContext(),"Contact deleted ",Toast.LENGTH_LONG).show();
                }

            } while (cursor.moveToNext());
        }
        else{
            Toast.makeText(getApplicationContext(),"Contact does not exsist",Toast.LENGTH_LONG).show();
        }
    }
}

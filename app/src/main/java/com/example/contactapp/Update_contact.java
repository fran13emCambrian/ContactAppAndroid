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

public class Update_contact extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,display;
    Button search_button,update_button;
    EditText search,new_name,new_mobile,new_email;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    String searched_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv1);
        tv1=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        display=findViewById(R.id.display);
        search=findViewById(R.id.search);
        new_name=findViewById(R.id.new_name);
        new_mobile=findViewById(R.id.new_mobile);
        new_email=findViewById(R.id.new_email);
        search_button=findViewById(R.id.search_button);
        update_button=findViewById(R.id.update_button);


        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        tv4.setVisibility(View.GONE);
        new_name.setVisibility(View.GONE);
        new_email.setVisibility(View.GONE);
        new_mobile.setVisibility(View.GONE);
        update_button.setVisibility(View.GONE);
        display.setVisibility(View.GONE);


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_con_toBeUpdated();
            }
        });
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_contact();
            }
        });
    }
    public void search_con_toBeUpdated(){
        searched_name=search.getText().toString();

        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        Cursor cursor=userDbHelper.searchinformation(searched_name,sqLiteDatabase);

        if(cursor.moveToFirst()) {
            do {
                if (searched_name.trim().equalsIgnoreCase(cursor.getString(0).trim())) {
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.VISIBLE);
                    tv4.setVisibility(View.VISIBLE);
                    new_name.setVisibility(View.VISIBLE);
                    new_email.setVisibility(View.VISIBLE);
                    new_mobile.setVisibility(View.VISIBLE);
                    update_button.setVisibility(View.VISIBLE);


                    Toast.makeText(getApplicationContext(), "Press update button after updation", Toast.LENGTH_LONG);

                    String name = cursor.getString(0);
                    String mob = cursor.getString(1);
                    String mail = cursor.getString(2);

                    new_name.setText(name);
                    new_mobile.setText(mob);
                    new_email.setText(mail);
                }

            } while (cursor.moveToNext());
        }
        else{
            Toast.makeText(getApplicationContext(),"Contact not found ",Toast.LENGTH_LONG).show();
        }
    }
    public void update_contact(){
        searched_name=search.getText().toString();


        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getWritableDatabase();
        String NAME=new_name.getText().toString();
        String MOBILE=new_mobile.getText().toString();
        String MAIL=new_email.getText().toString();
        int rows= userDbHelper.updateinformation(searched_name,NAME,MOBILE,MAIL,sqLiteDatabase);
        if(rows==1) {
            Toast.makeText(getApplicationContext(), rows + " CONTACT UPDATED ", Toast.LENGTH_LONG).show();
            display.setVisibility(View.GONE);
        }
        else {
            display.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), rows + " CONTACTS UPDATED ", Toast.LENGTH_LONG).show();
            display.setText("You have multiple contacts with same name.\n *Both updated*");
        }
    }
}

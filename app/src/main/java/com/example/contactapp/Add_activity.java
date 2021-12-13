package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.contactapp.CheckWhileAdding.checkMail;
import static com.example.contactapp.CheckWhileAdding.checkPhone;
import static com.example.contactapp.CheckWhileAdding.empty;

public class Add_activity extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    Button save;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= tv1.getText().toString();
                String mobile=  tv2.getText().toString();
                String mail=tv3.getText().toString();
                if(checkPhone(mobile) && checkMail(mail) && empty(name,mobile)){
                    addContact();
                }
                else if (checkPhone(mobile)==false){
                    Toast.makeText(getApplicationContext(), "Phone number must contain 10 digits ", Toast.LENGTH_LONG).show();
                }

                if (empty(name,mobile)==false){
                    Toast.makeText(getApplicationContext(), "No blank spaces allowed", Toast.LENGTH_LONG).show();
                }
                if (checkMail(mail)==false){
                    Toast.makeText(getApplicationContext(), "Mail incorrect correct format: name@email.com ", Toast.LENGTH_LONG).show();
                }
            }


        });
    }
    public void addContact() {
        String   name1  = tv1.getText().toString();
        String   mob1=  tv2.getText().toString();
        String email1= tv3.getText().toString();

        userDbHelper = new UserDbHelper(context);

        sqLiteDatabase = userDbHelper.getWritableDatabase();

        userDbHelper.addinformation(name1 , mob1 , email1 , sqLiteDatabase);


        Toast.makeText(getApplicationContext(),"Data saved",Toast.LENGTH_LONG).show();
        userDbHelper.close();

    }


}

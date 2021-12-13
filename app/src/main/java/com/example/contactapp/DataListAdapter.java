package com.example.contactapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contactapp.R.id;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataListAdapter extends ArrayAdapter {
    Uri u;
    List list=new ArrayList();

    public DataListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
    public void add(Object object){
        super.add(object);
        list.add(object);
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    public static class LayoutHandler{
        TextView NAME,MOB,EMAIL;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        LayoutHandler layoutHandler;
        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);

            layoutHandler=new LayoutHandler();
            layoutHandler.NAME= row.findViewById(R.id.user_name);
            layoutHandler.MOB= row.findViewById(R.id.user_mob);
            layoutHandler.EMAIL= row.findViewById(R.id.user_email);


            row.setTag(layoutHandler);

        }
        else{
            layoutHandler= (LayoutHandler)row.getTag();
        }

        final DataProvider dataProvider= (DataProvider)this.getItem(position);

        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.MOB.setText(dataProvider.getMobile());
        layoutHandler.MOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u= Uri.parse("tel: "+dataProvider.getMobile());
                Intent intent = new Intent(Intent.ACTION_DIAL,u);
                try
                {
                    getContext().startActivity(intent);
                }
                catch ( SecurityException s)
                {
                    Toast.makeText(getContext(), "Can not copy to dialer", Toast.LENGTH_LONG).show();
                }
            }
        });
        layoutHandler.EMAIL.setText(dataProvider.getEmail());
        layoutHandler.EMAIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",dataProvider.getEmail(), null));
                getContext().startActivity(Intent.createChooser(intent, "Bhoomika's email facility"));

            }
        });



        return row;
        //return super.getView(position, convertView, parent);   REMOVE THIS RETURN VALUE
    }
}

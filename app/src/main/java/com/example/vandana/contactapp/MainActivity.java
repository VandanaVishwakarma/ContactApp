package com.example.vandana.contactapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import static android.provider.ContactsContract.*;

public class MainActivity extends AppCompatActivity {

private ListView listView;
private Cursor cursor;
private ArrayList<String> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=(ListView)findViewById(R.id.contactlist);

        allContacts(this.getContentResolver());

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(arrayAdapter);
        Log.d("Debug van adapter", String.valueOf(contacts));

        }
    private void allContacts(ContentResolver contentResolver) {
       cursor=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,Contacts.DISPLAY_NAME+" ASC ");

       contacts=new ArrayList<String>();
       while(cursor.moveToNext())
       {
           String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
           String phno=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
           contacts.add(name+phno);

       }
       cursor.close();
    }
}

package com.javaorigin.test.apk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by gul on 5/28/15.
 */
public class BlockList extends Activity implements android.view.View.OnClickListener {
    Button btnok;
    EditText edit1;
    EditText edit2;
    Button Select_Contacts;
    ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    ListView list1;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.block_list);
        btnok = (Button)findViewById(R.id.okbtn);
        Select_Contacts=(Button)findViewById(R.id.select_contacts);

        list1=(ListView)findViewById(R.id.listView);
        edit1=(EditText)findViewById(R.id.editText2);
        edit2=(EditText)findViewById(R.id.editText3);
        Select_Contacts.setOnClickListener(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        list1.setAdapter(adapter);
        btnok.setOnClickListener(this);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onClick(View v){
          if(v.getId()==R.id.okbtn){
              String number=edit1.getText().toString();
              Anonymous a=new Anonymous(number);
              list.add(a.getName()+" : "+a.getPhoneNumber());
              String TableNAME=edit2.getText().toString();
              //pushing to database
                DatabaseHandler db=new DatabaseHandler(this);
                db.addContact(a);

          }

    }


}

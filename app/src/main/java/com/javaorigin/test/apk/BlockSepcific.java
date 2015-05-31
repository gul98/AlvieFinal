package com.javaorigin.test.apk;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by gul on 5/27/15.
 */
public class BlockSepcific extends Activity implements android.view.View.OnClickListener {
    EditText edit;
    Button ok;
    Button button;
    String number;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //final EditText edittext = (EditText) findViewById(R.id.edittext);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.block_specific_contact);


        edit=(EditText)findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button2);
        ok=(Button)findViewById(R.id.button3);
        button.setOnClickListener( this);
        ok.setOnClickListener(this);
        number=edit.getText().toString();

    }

    @Override

    public void onClick(View v){
        if(v.getId()==R.id.button2){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            startActivityForResult(intent, 1);
        }
        if(v.getId()==R.id.button3){


            String number=edit.getText().toString();
           // Anonymous c1= new Anonymous(number);

             /* Pushing numbers to database
            DatabaseHandler db=new DatabaseHandler(this);
            db.addContact(c1);
           */
            try {
                FileOutputStream fOut = openFileOutput("Specificnumber.txt", Context.MODE_PRIVATE);

                fOut.write(number.getBytes());
                fOut.close();
            }
            catch(IOException e) {
                Log.d("Input/output", "Exception occured in BlockSpecifc.java");
            }

            finish();



        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


            if (resultCode == RESULT_OK) {
                Uri contactData = data.getData();
                Cursor cursor = managedQuery(contactData, null, null, null, null);
                cursor.moveToFirst();

                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String name=cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                Log.d("Name",name+number);
                try {
                    FileOutputStream fOut = openFileOutput("Specificnumber.txt", Context.MODE_PRIVATE);

                    fOut.write(number.getBytes());
                    fOut.close();
                }
                catch(IOException e) {
                    Log.d("Input/output", "Exception occured in BlockSpecifc.java");
                }

                /*TableData t=new TableData(name,number);
                DatabaseHandler db=new DatabaseHandler(this);
                db.addContact(t);
                */

            }
        }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}

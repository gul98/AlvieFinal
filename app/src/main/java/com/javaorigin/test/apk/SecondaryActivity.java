package com.javaorigin.test.apk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

/**
 * Created by gul on 5/28/15.
 */
public class SecondaryActivity extends Activity implements android.view.View.OnClickListener{
   ImageButton btn;
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity);
        btn = (ImageButton)findViewById(R.id.imageButton);
        btn.setOnClickListener(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.imageButton){
            Intent intent = new Intent(this, BlockList.class);
            startActivity(intent);
        }
    }
}

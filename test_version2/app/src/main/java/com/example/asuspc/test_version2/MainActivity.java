package com.example.asuspc.test_version2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ASUS PC on 07-Jun-15.
 */
public class MainActivity extends Activity {

    EditText search = null;
    EditText userid = null;
    EditText pwd = null;
    Button submit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);

        //There is EditTest to enter the keyword to be searched
        //And a submit button
        userid = (EditText)findViewById(R.id.edittext);
        pwd = (EditText)findViewById(R.id.edittext2);
        submit= (Button)findViewById(R.id.button);

        //Setting a listener on the button
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                //Getting the keyword which was input to the EditText
                //String search_text=search.getText().toString();
                String[] user_details = new String[2];
                user_details[0] = userid.getText().toString();
                user_details[1] = pwd.getText().toString();
                //Now this is an intent to the android system which starts SearchResult Activity
                //We are using putExtra to add the keywords to the intent
                Intent intent =
                        new Intent(
                                MainActivity.this,
                                Search_Result.class );
                intent.putExtra( "SEARCH", user_details );
                //System.out.println("Search text:" + search_text);
                System.out.println("starting activity");
                startActivity( intent );
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Yuhan on 8/31/15.
 */

public class CreateIdActivity extends ActionBarActivity {

    dataBaseHelper helper = new dataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createid);

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
//            return true;
            Intent i = new Intent(CreateIdActivity.this,SettingsActivity.class);
            CreateIdActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void button_CIdOnClick (View view)
    {
        if(view.getId()==R.id.createdId)
        {
            EditText userid = (EditText)findViewById(R.id.useridc);
            EditText pass1 = (EditText)findViewById(R.id.pwd);
            EditText pass2 = (EditText)findViewById(R.id.conpwd);

            String useridstr = userid.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();



           if(helper.findExistId(useridstr))
            {
                Toast pass = Toast.makeText(CreateIdActivity.this,"The ID has already existed!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else  if(!pass1str.equals(pass2str))
           {
               //popup msg
               Toast pass = Toast.makeText(CreateIdActivity.this,"Password don't match!", Toast.LENGTH_SHORT);
               pass.show();
           }
            else
            {
                Contact c = new Contact();


                c.setId(useridstr);
                c.setPwd(pass1str);

//                ParseUser user = new ParseUser();
//                user.setUsername(useridstr);
//                user.setPassword(pass1str);

                helper.insertColumn(c);


                Intent i = new Intent(CreateIdActivity.this,SleepActivity.class);
                i.putExtra("userid",useridstr);
                CreateIdActivity.this.startActivity(i);

            }

        }

        if(view.getId() == R.id.cancel_c)
        {
            Intent i = new Intent(CreateIdActivity.this,MainActivity.class);
            CreateIdActivity.this.startActivity(i);
        }



    }
}
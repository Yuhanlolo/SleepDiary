package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;
import com.parse.Parse;
import com.parse.ParseObject;
/**
 * Created by Yuhan on 9/9/15.
 */
public class LoginActivity extends ActionBarActivity{

    dataBaseHelper helper = new dataBaseHelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
            Intent i = new Intent(LoginActivity.this,SettingsActivity.class);
            LoginActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void BLoginOnClick(View view)
    {
        if(view.getId() == R.id.Logined)
        {
            EditText account = (EditText)findViewById(R.id.useridl);
            String stracc = account.getText().toString();
            EditText pwd = (EditText)findViewById(R.id.password);
            String strpass = pwd.getText().toString();

            String password = helper.searchpass(stracc);
            System.out.println(password);
            if(strpass.equals(password))
            {
            Intent i = new Intent(LoginActivity.this,SleepActivity.class);
            i.putExtra("userid",stracc);
            i.putExtra("userpwd",strpass);
            LoginActivity.this.startActivity(i);
            }

            else
            {
                //popup msg
                Toast errorlogin = Toast.makeText(LoginActivity.this,"User ID and Password don't match!", Toast.LENGTH_SHORT);
                errorlogin.show();
            }
        }

        if(view.getId() == R.id.cancel_l)
        {
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            LoginActivity.this.startActivity(i);
        }
    }
}

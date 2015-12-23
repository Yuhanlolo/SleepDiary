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

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import android.view.WindowManager;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;
/**
 * Created by Yuhan on 9/9/15.
 */
public class LoginActivity extends ActionBarActivity{

    dataBaseHelper helper = new dataBaseHelper(this);
    EditText account;
    EditText pwd;
    String stracc  = "";
    String strpass = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
//        );
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

            account = (EditText)findViewById(R.id.useridl);
            pwd = (EditText)findViewById(R.id.password);

            stracc = account.getText().toString();
            strpass = pwd.getText().toString();

            //String password = helper.searchpass(stracc);
            //System.out.println(password);

            ParseUser.logInInBackground(stracc, strpass, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e != null)
                    {
                        Toast pass = Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT);
                        pass.show();
                    }
                    else
                    {
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        i.putExtra("loginstatus",true);
                        i.putExtra("userid",stracc);
                        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK|i.FLAG_ACTIVITY_NEW_TASK);
                        LoginActivity.this.startActivity(i);
                    }
                }
            });

//            if(strpass.equals(password))
//            {
//            Intent i = new Intent(LoginActivity.this,SleepActivity.class);
//            i.putExtra("userid",stracc);
//            i.putExtra("userpwd",strpass);
//            LoginActivity.this.startActivity(i);
//            }
//
//            else
//            {
//                //popup msg
//                Toast errorlogin = Toast.makeText(LoginActivity.this,"User ID and Password don't match!", Toast.LENGTH_SHORT);
//                errorlogin.show();
//            }
        }

        if(view.getId() == R.id.cancel_l)
        {
            Intent i = new Intent(LoginActivity.this,SettingsActivity.class);
            LoginActivity.this.startActivity(i);
        }
    }

    public void onBackPressed() {
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        //i.putExtra("lastpage",lastpage);
        //startService(j);
        startActivity(i);

    }
}

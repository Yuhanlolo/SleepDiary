package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;


/**
 * Created by apple on 9/9/15.
 */
public class LoginActivity extends ActionBarActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void BLoginOnClick(View view)
    {
        if(view.getId() == R.id.Logined)
        {
            EditText account = (EditText)findViewById(R.id.useridl);
            String stracc = account.getText().toString();
            EditText pwd = (EditText)findViewById(R.id.password);
            String strpass = pwd.getText().toString();
            Intent i = new Intent(LoginActivity.this,SleepActivity.class);
            i.putExtra("useridl",stracc);
            i.putExtra("userpwd",strpass);

            //startActivity(i);
            LoginActivity.this.startActivity(i);
        }

        if(view.getId() == R.id.cancel_l)
        {
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            LoginActivity.this.startActivity(i);
        }
    }
}

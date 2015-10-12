package app.sleepdiary.com.sleepdiary;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    public void button_createIDOnClick(View view)
    {
        if(view.getId() == R.id.CreateId)
        {
            Intent i = new Intent(MainActivity.this,CreateIdActivity.class);
            MainActivity.this.startActivity(i);
        }
    }

    public void button_LoginOnClick(View view)
    {
        if(view.getId() == R.id.Login)
        {
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            //startActivity(i);
            MainActivity.this.startActivity(i);
        }
    }
}

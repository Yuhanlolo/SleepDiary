package app.sleepdiary.com.sleepdiary;

/**
 * Created by Yuhan on 9/11/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SleepActivity extends ActionBarActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

       // String userid = getIntent().getStringExtra("userid");
        //TextView tv = (TextView)findViewById(R.id.title);
        //tv.setText("Welcome"+userid);
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
            Intent i = new Intent(SleepActivity.this,SettingsActivity.class);
            SleepActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void bedtimeOnClick(View view)
    {
       if(view.getId() == R.id.bedtime)
        {
            Intent i = new Intent(SleepActivity.this,SleepDiaryActivity.class);
            SleepActivity.this.startActivity(i);
        }

     }
}

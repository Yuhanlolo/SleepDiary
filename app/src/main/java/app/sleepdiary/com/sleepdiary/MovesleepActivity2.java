package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by ypl5142 on 10/25/15.
 */
public class MovesleepActivity2 extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movesleep);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
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
            Intent i = new Intent(MovesleepActivity2.this,SettingsActivity.class);
            MovesleepActivity2.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void BLoginOnClick(View view)
    {
        if(view.getId() == R.id.save_ms2)
        {
            Intent i = new Intent(MovesleepActivity2.this,SettingsActivity.class);
            MovesleepActivity2.this.startActivity(i);
        }
    }
}

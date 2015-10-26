package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by ypl5142 on 10/25/15.
 */
public class MovesleepActivity extends ActionBarActivity {

    int walk =0;
    int change = 0;
    int usehand = 0;
    int uncontrol = 0;

    ImageView wscopa0, wscopa1, wscopa2, wscoopa3, cscopa0,cscopa1, cscopa2, cscoopa3,uhscopa0, uhscopa1, uhscopa2, uhscoopa3;
    ImageView wsscopa0, wsscopa1, wsscopa2, wsscoopa3, csscopa0,csscopa1, csscopa2, csscoopa3,uhsscopa0, uhsscopa1, uhsscopa2, uhsscoopa3;
    ImageView umscopa0,umscopa1, umscopa2, umscoopa3;
    ImageView umsscopa0,umsscopa1, umsscopa2, umsscoopa3;

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
            Intent i = new Intent(MovesleepActivity.this,SettingsActivity.class);
            MovesleepActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void BLoginOnClick(View view)
    {
        if(view.getId() == R.id.save_ms)
        {
            Intent i = new Intent(MovesleepActivity.this,MovesleepActivity2.class);
            MovesleepActivity.this.startActivity(i);
        }
    }
}

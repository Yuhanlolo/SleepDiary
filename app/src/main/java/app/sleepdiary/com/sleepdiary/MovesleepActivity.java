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


    ImageView wsscopa0, wsscopa1, wsscopa2, wsscopa3, csscopa0,csscopa1, csscopa2, csscopa3,uhsscopa0, uhsscopa1, uhsscopa2, uhsscoopa3;
    ImageView umsscopa0,umsscopa1, umsscopa2, umsscoopa3;
    int wss = -1;
    int css = -1;
    int uhss = -1;
    int umss = -1;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movesleep);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        wsscopa0 = (ImageView)findViewById(R.id.wsscopa1);
        wsscopa1 = (ImageView)findViewById(R.id.wsscopa2);
        wsscopa2 = (ImageView)findViewById(R.id.wsscopa3);
        wsscopa3 = (ImageView)findViewById(R.id.wsscopa4);

        csscopa0 = (ImageView)findViewById(R.id.csscopa1);
        csscopa1 = (ImageView)findViewById(R.id.csscopa2);
        csscopa2 = (ImageView)findViewById(R.id.csscopa3);
        csscopa3 = (ImageView)findViewById(R.id.csscopa4);
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


    public void button_msdOnClick(View view)
    {

        if(view.getId() == R.id.wscopa1)
        {
            wss = 0;
            wsscopa0.setVisibility(View.VISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.wscopa2)
        {
            wss = 1;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.VISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.wscopa3)
        {
            wss = 2;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.VISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.wscopa4)
        {
            wss = 3;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.cscopa1)
        {
            css = 0;
            csscopa0.setVisibility(View.VISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.cscopa2)
        {
            css = 1;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.VISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.cscopa3)
        {
           css = 2;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.VISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.cscopa4)
        {
            css = 3;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.save_ms)
        {
            Intent i = new Intent(MovesleepActivity.this,MovesleepActivity2.class);
            MovesleepActivity.this.startActivity(i);
        }
    }
}

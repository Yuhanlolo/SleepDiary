package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ypl5142 on 10/25/15.
 */
public class MainActivity  extends ActionBarActivity {

    boolean M30 = false;
    boolean A_DOPA1= false;
    boolean A_DOPA = false;
    boolean E = false;

    boolean login_status = false;

    ImageView finish_M30,finish_adi,finish_bdi,finish_E;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finish_M30 = (ImageView)findViewById(R.id.fmt);
        finish_adi = (ImageView)findViewById(R.id.fadi);
        finish_bdi = (ImageView)findViewById(R.id.fbdi);
        finish_E = (ImageView)findViewById(R.id.fbt);

        login_status = getIntent().getBooleanExtra("loginstatus",false);

        M30 = getIntent().getBooleanExtra("f3",false);
        if(M30)
        {
            finish_M30.setVisibility(View.VISIBLE);
        }
        if(A_DOPA1)
        {
            finish_adi.setVisibility(View.VISIBLE);
        }
        if(A_DOPA)
        {
            finish_bdi.setVisibility(View.VISIBLE);
        }
        if(E)
        {
            finish_E.setVisibility(View.VISIBLE);
        }

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
            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            MainActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void CoverOnClick(View view) {
        if (view.getId() == R.id.mt)
        {
            if(!login_status)
            {
                Toast pass = Toast.makeText(MainActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
            Intent i = new Intent(MainActivity.this, SleepActivity.class);
            MainActivity.this.startActivity(i);
            }
        }
    }

}

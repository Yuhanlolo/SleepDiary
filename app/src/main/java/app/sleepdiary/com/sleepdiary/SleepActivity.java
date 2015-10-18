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
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import com.parse.Parse;

public class SleepActivity extends ActionBarActivity{

    boolean finish_braintest = false;
    boolean finish_sleepdiary = false;
    boolean finish_movesleep = false;
    boolean finish_scopa = false;

    boolean tempbraintest = false;

//    String finish_braintest ="";
//    String finish_sleepdiary = "";
//    String finish_movesleep = "";
//    String finish_scopa = "";

    ImageView f1;
    ImageView f2;
    ImageView f3;
    ImageView f4;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        f1 = (ImageView)findViewById(R.id.finish_braintest);
        f2 = (ImageView)findViewById(R.id.finish_sleepdiary);

        finish_sleepdiary = getIntent().getBooleanExtra("f2",false);
        finish_braintest =  getIntent().getBooleanExtra("f1",false);

        if(finish_braintest)
        {
            f1.setVisibility(View.VISIBLE);
        }

        if(finish_sleepdiary)
        {
            f2.setVisibility(View.VISIBLE);
        }
        //Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
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

        if(view.getId() == R.id.brain_test)
        {

            Uri uri = Uri.parse("http://www.braintaptest.com/"); // missing 'http://' will cause crashed
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            tempbraintest = true;
            i.putExtra("f1",tempbraintest);
            startActivity(i);
            //f1.setVisibility(View.VISIBLE);
        }

     }
}

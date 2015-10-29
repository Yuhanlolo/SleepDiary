package app.sleepdiary.com.sleepdiary;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ypl5142 on 10/28/15.
 */
public class ADIActivity extends ActionBarActivity {

    boolean finish_braintest = false;
    boolean finish_movesleep = false;

    boolean tempbraintest = false;

    ImageView f1;
    ImageView f3;
    String userid= "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        f1 = (ImageView)findViewById(R.id.finish_braintestadi);

        f3 = (ImageView)findViewById(R.id.finish_movesleepadi);

        finish_braintest =  getIntent().getBooleanExtra("f1",false);
        finish_movesleep = getIntent().getBooleanExtra("f3", false);

        if(finish_braintest)
        {
            f1.setVisibility(View.VISIBLE);
        }


        if(finish_movesleep)
        {
            f3.setVisibility(View.VISIBLE);
        }
        //Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
        userid = getIntent().getStringExtra("userid");
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
            Intent i = new Intent(ADIActivity.this,SettingsActivity.class);
            ADIActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void bedtimeOnClick(View view)
    {


        if(view.getId() == R.id.movesleepadi)
        {
            Intent i = new Intent(ADIActivity.this,MovesleepActivity.class);
            ADIActivity.this.startActivity(i);
        }
        if(view.getId() == R.id.brain_testadi)
        {

            Uri uri = Uri.parse("http://www.braintaptest.com/"); // missing 'http://' will cause crashed
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            i.putExtra("userid",userid);
            tempbraintest = true;
            i.putExtra("f1",tempbraintest);
            startActivity(i);
            //f1.setVisibility(View.VISIBLE);
        }

    }
}

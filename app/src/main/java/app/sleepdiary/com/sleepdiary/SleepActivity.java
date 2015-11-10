package app.sleepdiary.com.sleepdiary;

/**
 * Created by Yuhan on 9/11/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Calendar;

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

    String userid= "";
    String token = "";
    ParseUser currentUser;
    String link = "";

    final Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;
    int date = cal.get(Calendar.DATE);
    int year = cal.get(Calendar.YEAR);
    String today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);

    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TaskCheckList");
    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Token");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

//        Intent i_getvalue = getIntent();
//        String action = i_getvalue.getAction();
//
//        if(Intent.ACTION_VIEW.equals(action)){
//            Uri uri = i_getvalue.getData();
//            if(uri != null){
//                String value = uri.getQueryParameter("key");
//                String value= uri.getQueryParameter("value");
//            }
//        }

        f1 = (ImageView)findViewById(R.id.finish_braintest);
        f2 = (ImageView)findViewById(R.id.finish_sleepdiary);
        f3 = (ImageView)findViewById(R.id.finish_movesleep);

        finish_sleepdiary = getIntent().getBooleanExtra("f2",false);
        finish_braintest =  getIntent().getBooleanExtra("f1",false);
        finish_movesleep = getIntent().getBooleanExtra("f3", false);

        currentUser = ParseUser.getCurrentUser();

        if(currentUser != null) {
            userid = ParseUser.getCurrentUser().getUsername();
            query1.whereEqualTo("User_ID", userid);
            query1.whereEqualTo("Date", today);

            query1.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (object == null) {
                        Log.d("User_ID", "The getFirst request failed.");

                    } else {
                        //Log.d("score", "Retrieved the object.");
                        //if(object.getInt("MBraintest")== 0 || object.getInt("MBraintest") ==1)

                        if (object.getInt("MSleepdiary") == 1 ) {
                            finish_sleepdiary = true;
                            f2.setVisibility(View.VISIBLE);
                        }

                        if ( object.getInt("AdiMovesleep") == 1) {
                            finish_movesleep = true;
                            f3.setVisibility(View.VISIBLE);
                        }

                        if (object.getInt("BdiBraintest") == 1 ) {
                            finish_braintest = true;
                            f1.setVisibility(View.VISIBLE);
                        }


                    }
                }
            });
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
            Intent i = new Intent(SleepActivity.this,SettingsActivity.class);
            SleepActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void bedtimeOnClick(View view)
    {

        ParseUser currentUser1 = ParseUser.getCurrentUser();

        if(currentUser1 == null)
        {
            Toast pass = Toast.makeText(SleepActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
            pass.show();
        }
       else if(view.getId() == R.id.bedtime)
        {
            if(!finish_sleepdiary){
            Intent i = new Intent(SleepActivity.this,SleepDiaryActivity.class);
            i.putExtra("userid",userid);
            SleepActivity.this.startActivity(i);}
            else
            {
                Toast pass = Toast.makeText(SleepActivity.this, " You have already finished this part! " , Toast.LENGTH_SHORT);
                pass.show();
            }
        }

        else if(view.getId() == R.id.movesleep)
        {
            if(!finish_sleepdiary){
                Toast pass = Toast.makeText(SleepActivity.this, " Please finish Sleep Diary first! " , Toast.LENGTH_SHORT);
                pass.show();
            }
            else {
            Intent i = new Intent(SleepActivity.this,MovesleepActivity.class);
            SleepActivity.this.startActivity(i);}
        }
        else if(view.getId() == R.id.brain_test)
        {

            currentUser = ParseUser.getCurrentUser();

//            if(currentUser != null) {
//                String username = ParseUser.getCurrentUser().getUsername();
//                query2.whereEqualTo("User_ID", username);
//
//                query2.getFirstInBackground(new GetCallback<ParseObject>() {
//                    public void done(ParseObject object, ParseException e) {
//                        if (object == null) {
//                            Log.d("User_ID", "The getFirst request failed.");
//
//                        } else {
//                            //Log.d("score", "Retrieved the object.");
//                            //if(object.getInt("MBraintest")== 0 || object.getInt("MBraintest") ==1)
//
//                           token = object.getString("Token");
//                           link = "http://www.braintaptest.com/en/direct-client/login?token="+token;
////                            Toast pass = Toast.makeText(SleepActivity.this, link, Toast.LENGTH_LONG);
////                            pass.show();
//                            Uri uri = Uri.parse("http://www.braintaptest.com/"); // missing 'http://' will cause crashed
//                            Intent i = new Intent(Intent.ACTION_VIEW, uri);
//                            startActivity(i);
//                        }
//                    }
//                });
//            }

            Uri uri = Uri.parse("http://www.braintaptest.com/"); // missing 'http://' will cause crashed
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            Intent j = new Intent(SleepActivity.this,SleepActivity.class);
            startService(j);
            startActivity(i);

        }

     }
}
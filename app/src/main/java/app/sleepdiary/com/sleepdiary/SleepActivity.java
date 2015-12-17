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
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import android.view.ViewGroup.LayoutParams;
import java.util.Calendar;
import java.util.List;

import android.widget.LinearLayout;
import android.widget.GridLayout;


public class SleepActivity extends ActionBarActivity{

    boolean finish_braintest = false;
    boolean finish_sleepdiary = false;
    boolean finish_movesleep = false;

    boolean tempbraintest = false;


    ImageView bt, sd, ms;

    ImageView f1;
    ImageView f2;
    ImageView f3;

    String userid= "";
    String token = "";
    ParseUser currentUser;
    String link = "";
    String lastpage="";
    final Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;
    int date = cal.get(Calendar.DATE);
    int year = cal.get(Calendar.YEAR);
    String today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);

    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TaskCheckList");
    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Token");

    ParseObject lp  = new ParseObject("Lastpage");
    ParseQuery<ParseObject> querylp = ParseQuery.getQuery("Lastpage");
    ParseQuery<ParseObject> querylp2 = ParseQuery.getQuery("Lastpage");
    ParseQuery<ParseObject> querylp3 = ParseQuery.getQuery("Lastpage");

    Uri uri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();



        lastpage = i_getvalue.getStringExtra("lastpage");
//        Toast pass = Toast.makeText(SleepActivity.this, "lastpage:"+lastpage, Toast.LENGTH_SHORT);
//        pass.show();



        bt = (ImageView)findViewById(R.id.brain_test);
        sd = (ImageView)findViewById(R.id.bedtime);
        ms = (ImageView)findViewById(R.id.movesleep);

        f1 = (ImageView)findViewById(R.id.finish_braintest);
        f2 = (ImageView)findViewById(R.id.finish_sleepdiary);
        f3 = (ImageView)findViewById(R.id.finish_movesleep);




        currentUser = ParseUser.getCurrentUser();

        if(lastpage == null)
        {

//            if(Intent.ACTION_VIEW.equals(action)){
//                uri = i_getvalue.getData();
//                String struri = uri.toString();}

//                if (struri.equals("sleep://diary.com/m30"))
//                    lastpage = "M30";
//                else if (struri.equals("sleep://diary.com/mdopa1"))
//                    lastpage = "MDOPA1";
//                else if (struri.equals("sleep://diary.com/adopa"))
//                    lastpage = "A_DOPA";
//                else if (struri.equals("sleep://diary.com/e"))
//                    lastpage = "E";
//                else if (struri.equals("sleep://diary.com/nap"))
//                    lastpage = "Nap";
            if(currentUser != null) {
                userid = currentUser.getUsername();
                querylp3.whereEqualTo("User_ID", userid);
                querylp3.whereEqualTo("Date", today);
                querylp3.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object ==null){
                            Toast pass = Toast.makeText(SleepActivity.this,"The page is outdated, please start over!", Toast.LENGTH_SHORT);
                            pass.show();
                            Intent i = new Intent(SleepActivity.this,MainActivity.class);
                            //startService(j);
                            startActivity(i);
                        }
                        else
                        {
                            lastpage = object.getString("lastpage");
                            Log.d(userid, lastpage);
                            buttonlayout(lastpage);
                        }
                    }
                });

            }



        }

        else
        {

            buttonlayout(lastpage);
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

    public void buttonlayout(String lastpage)
    {
        if (lastpage.equals("practice"))
        {
            Intent i = new Intent(SleepActivity.this,SettingsActivity.class);
            SleepActivity.this.startActivity(i);
        }
        else if(lastpage.equals("M30"))
        {
//            GridLayout.LayoutParams llp = new GridLayout.LayoutParams();
//            f3.setLayoutParams(llp);
//            f3.setVisibility(View.VISIBLE);

            sd.setVisibility(View.VISIBLE);

            if(currentUser != null) {
                userid = currentUser.getUsername();
                query1.whereEqualTo("User_ID", userid);
                query1.whereEqualTo("Date", today);

                query1.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "The getFirst request failed.");

                        } else {
                            //Log.d("score", "Retrieved the object.");
                            //if(object.getInt("MBraintest")== 0 || object.getInt("MBraintest") ==1)

                            if (object.getInt("M30_Sleepdiary") == 1 ) {
                                finish_sleepdiary = true;
                                f2.setVisibility(View.VISIBLE);
                            }

                            if ( object.getInt("M30_Movesleep") == 1) {
                                finish_movesleep = true;
                                f3.setVisibility(View.VISIBLE);
                            }

                            if (object.getInt("M30_Braintest") == 1 ) {
                                finish_braintest = true;
                                f1.setVisibility(View.VISIBLE);
                            }


                        }
                    }
                });
            }

        }
        else if (lastpage.equals("MDOPA1"))
        {
            sd.setVisibility(View.INVISIBLE);

            if(currentUser != null) {
                userid = ParseUser.getCurrentUser().getUsername();
                query1.whereEqualTo("User_ID", userid);
                query1.whereEqualTo("Date", today);

                query1.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "The getFirst request failed.");

                        } else {

                            if ( object.getInt("MDOPA1_Movesleep") == 1) {
                                finish_movesleep = true;
                                f3.setVisibility(View.VISIBLE);
                            }

                            if (object.getInt("MDOPA1_Braintest") == 1 ) {
                                finish_braintest = true;
                                f1.setVisibility(View.VISIBLE);
                            }


                        }
                    }
                });
            }
        }

        else if (lastpage.equals("A_DOPA"))
        {
            sd.setVisibility(View.INVISIBLE);

            if(currentUser != null) {
                userid = ParseUser.getCurrentUser().getUsername();
                query1.whereEqualTo("User_ID", userid);
                query1.whereEqualTo("Date", today);

                query1.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "The getFirst request failed.");

                        } else {

                            if ( object.getInt("ADOPA_Movesleep") == 1) {
                                finish_movesleep = true;
                                f3.setVisibility(View.VISIBLE);
                            }

                            if (object.getInt("ADOPA_Braintest") == 1 ) {
                                finish_braintest = true;
                                f1.setVisibility(View.VISIBLE);
                            }


                        }
                    }
                });
            }
        }

        else if (lastpage.equals("E"))
        {
            sd.setVisibility(View.INVISIBLE);

            if(currentUser != null) {
                userid = ParseUser.getCurrentUser().getUsername();
                query1.whereEqualTo("User_ID", userid);
                query1.whereEqualTo("Date", today);

                query1.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "The getFirst request failed.");

                        } else {

                            if ( object.getInt("E_Movesleep") == 1) {
                                finish_movesleep = true;
                                f3.setVisibility(View.VISIBLE);
                            }

                            if (object.getInt("E_Braintest") == 1 ) {
                                finish_braintest = true;
                                f1.setVisibility(View.VISIBLE);
                            }


                        }
                    }
                });
            }
        }

        else if (lastpage.equals("Nap"))
        {
            sd.setVisibility(View.INVISIBLE);

            if(currentUser != null) {
                userid = ParseUser.getCurrentUser().getUsername();
                query1.whereEqualTo("User_ID", userid);
                query1.whereEqualTo("Date", today);

                query1.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "The getFirst request failed.");

                        } else {

                            if ( object.getInt("Nap_Movesleep") == 1) {
                                finish_movesleep = true;
                                f3.setVisibility(View.VISIBLE);
                            }

                            if (object.getInt("Nap_Braintest") == 1 ) {
                                finish_braintest = true;
                                f1.setVisibility(View.VISIBLE);
                            }


                        }
                    }
                });
            }
        }

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
                i.putExtra("lastpage",lastpage);
                SleepActivity.this.startActivity(i);}
            else
            {
                Toast pass = Toast.makeText(SleepActivity.this, " You have already finished this part! " , Toast.LENGTH_SHORT);
                pass.show();
            }
        }

        else if(view.getId() == R.id.movesleep)
        {
            if(lastpage.equals("M30")&&(!finish_sleepdiary)){

                Toast pass = Toast.makeText(SleepActivity.this, " Please finish Sleep Diary first! " , Toast.LENGTH_SHORT);
                pass.show();
            }
            else if(lastpage.equals("Nap"))
            {
                Intent i = new Intent(SleepActivity.this,NapMoveSleepActivity.class);
                i.putExtra("lastpage",lastpage);
                SleepActivity.this.startActivity(i);
            }

            else
            {
                Intent i = new Intent(SleepActivity.this,MovesleepActivity.class);
                i.putExtra("lastpage",lastpage);
                SleepActivity.this.startActivity(i);
            }

        }

        else if(view.getId() == R.id.brain_test)
        {

            currentUser = ParseUser.getCurrentUser();

            if(currentUser != null) {
                userid =  currentUser .getUsername();
                querylp.whereEqualTo("User_ID", userid);
                querylp.whereEqualTo("Date", today);
                querylp.setLimit(1);

                querylp.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "create task list."+userid);
                            lp.put("User_ID", userid);
                            lp.put("Date", today);
                            lp.put("lastpage",lastpage);
                            lp.saveInBackground();
                        }
                        else
                        {
                            querylp2.whereEqualTo("User_ID", userid);
                            querylp2.whereEqualTo("Date", today);
                            querylp2.setLimit(1);
                            querylp2.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> scoreList, ParseException e) {
                                    if (e ==null){
                                        scoreList.get(0).put("lastpage", lastpage);
                                        scoreList.get(0).saveInBackground();
                                    }
                                }
                            });
                        }
                    }
                });


                String username =currentUser.getUsername();
                query2.whereEqualTo("User_ID", username);

                query2.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "The getFirst request failed.");
                            Toast pass = Toast.makeText(SleepActivity.this, "Sorry you don't have a token for brain tap test..", Toast.LENGTH_LONG);
                            pass.show();

                        } else {
                            //Log.d("score", "Retrieved the object.");
                            //if(object.getInt("MBraintest")== 0 || object.getInt("MBraintest") ==1)

                           token = object.getString("token");
                           link = "http://www.braintaptest.com/en/direct-client/login?token="+token;
                            Toast pass = Toast.makeText(SleepActivity.this, link, Toast.LENGTH_LONG);
                            pass.show();
                            Uri uri = Uri.parse("http://www.braintaptest.com/"); // missing 'http://' will cause crashed
                            Intent i = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(i);
                        }
                    }
                });
            }

            Uri uri = Uri.parse("http://www.braintaptest.com/"); // missing 'http://' will cause crashed
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            //Intent j = new Intent(SleepActivity.this,SleepActivity.class);
            i.putExtra("lastpage",lastpage);
            //startService(j);
            startActivity(i);

        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SleepActivity.this,MainActivity.class);
        i.putExtra("lastpage",lastpage);
        //startService(j);
        startActivity(i);

    }
}
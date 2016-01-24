package app.sleepdiary.com.sleepdiary;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.support.v7.app.ActionBarActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ypl5142 on 10/5/15.
 */
public class SleepDiaryActivity3 extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {

    SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);
    RadioGroup  rg0, rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;

    boolean f = false;
    int month;
    int date;
    int year;
    String today = "";
    String yesterday = "";
    TextView lastnight;

    RadioButton rg0_1, rg0_0;
    RadioButton rg1_1, rg1_0;
    RadioButton rg2_1, rg2_0;
    RadioButton rg3_1, rg3_0;
    RadioButton rg4_1, rg4_0;
    RadioButton rg5_1, rg5_0;
    RadioButton rg6_1, rg6_0;
    RadioButton rg7_1, rg7_0;
    RadioButton rg8_1, rg8_0;

    int rbid01 =-1;
    int rbid00 =-1;

    int rbid11 =-1;
    int rbid10 =-1;

    int rbid21 =-1;
    int rbid20 =-1;

    int rbid31 =-1;
    int rbid30=-1;

    int rbid41 =-1;
    int rbid40 =-1;

    int rbid51 =-1;
    int rbid50 =-1;

    int rbid61 =-1;
    int rbid60 =-1;

    int rbid71 =-1;
    int rbid70 =-1;

    int rbid81 =-1;
    int rbid80=-1;

    String a_urge = "";
    String a_muscle = "";
    String a_tobed = "";
    String a_pain = "";
    String a_dream = "";
    String a_hall = "";
    String a_breath = "";
    String a_urine = "";
    String a_distur = "";

    String objectID = "";

    String lastpage = "";
    ParseQuery<ParseObject> query;
    ParseObject TaskCheckList  = new ParseObject("TaskCheckList");
    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TaskCheckList");

    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("TaskCheckList");
    //ParseObject userActivity = new ParseObject("UserActivity");

    final long delayMillis=1000;
    Handler h=null;
    Runnable r;
    int starttime;
    final Calendar c = Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3sleepdiary);

//        View myView = getWindow().getDecorView();
//        myView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        query = ParseQuery.getQuery("Sleep_Diary");
        objectID = getIntent().getStringExtra("objectID");
        lastpage = getIntent().getStringExtra("lastpage");
//        Toast pass = Toast.makeText(SleepDiaryActivity3.this,"3 "+objectID, Toast.LENGTH_SHORT);
//        pass.show();

        rg0 = (RadioGroup)findViewById(R.id.group0);
        rg1 = (RadioGroup)findViewById(R.id.group1);
        rg2 = (RadioGroup)findViewById(R.id.group2);
        rg3 = (RadioGroup)findViewById(R.id.group3);
        rg4 = (RadioGroup)findViewById(R.id.group4);
        rg5 = (RadioGroup)findViewById(R.id.group5);
        rg6 = (RadioGroup)findViewById(R.id.group6);
        rg7 = (RadioGroup)findViewById(R.id.group7);
        rg8 = (RadioGroup)findViewById(R.id.group8);

        rg0_1 = (RadioButton)findViewById(R.id.yes_urge);
        rg0_0 = (RadioButton)findViewById(R.id.no_urge);
        rbid01 = rg0_1.getId();
        rbid00 = rg0_0.getId();

        rg1_1 = (RadioButton)findViewById(R.id.yes_mu);
        rg1_0 = (RadioButton)findViewById(R.id.no_mu);
        rbid11 = rg1_1.getId();
        rbid10 = rg1_0.getId();

        rg2_1 = (RadioButton)findViewById(R.id.yes_bed);
        rg2_0 = (RadioButton)findViewById(R.id.no_bed);
        rbid21 = rg2_1.getId();
        rbid20 = rg2_0.getId();

        rg3_1 = (RadioButton)findViewById(R.id.yes_pain);
        rg3_0 = (RadioButton)findViewById(R.id.no_pain);
        rbid31 = rg3_1.getId();
        rbid30 = rg3_0.getId();

        rg4_1 = (RadioButton)findViewById(R.id.yes_dream);
        rg4_0 = (RadioButton)findViewById(R.id.no_dream);
        rbid41 = rg4_1.getId();
        rbid40 = rg4_0.getId();

        rg5_1 = (RadioButton)findViewById(R.id.yes_hall);
        rg5_0 = (RadioButton)findViewById(R.id.no_hall);
        rbid51 = rg5_1.getId();
        rbid50 = rg5_0.getId();

        rg6_1 = (RadioButton)findViewById(R.id.yes_breath);
        rg6_0 = (RadioButton)findViewById(R.id.no_breath);
        rbid61 = rg6_1.getId();
        rbid60 = rg6_0.getId();

        rg7_1 = (RadioButton)findViewById(R.id.yes_urine);
        rg7_0 = (RadioButton)findViewById(R.id.no_urine);
        rbid71 = rg7_1.getId();
        rbid70 = rg7_0.getId();

        rg8_1 = (RadioButton)findViewById(R.id.yes_distur);
        rg8_0 = (RadioButton)findViewById(R.id.no_distur);
        rbid81 = rg8_1.getId();
        rbid80 = rg8_0.getId();

        rg0.setOnCheckedChangeListener(this);
        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
        rg3.setOnCheckedChangeListener(this);
        rg4.setOnCheckedChangeListener(this);
        rg5.setOnCheckedChangeListener(this);
        rg6.setOnCheckedChangeListener(this);
        rg7.setOnCheckedChangeListener(this);
        rg8.setOnCheckedChangeListener(this);

        final Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DATE);
        year = cal.get(Calendar.YEAR);

        today = String.valueOf(month) + "/" + String.valueOf(date) + "/" + String.valueOf(year);

        int hr = cal.get(Calendar.HOUR_OF_DAY);
        int m=cal.get(Calendar.MINUTE);

        starttime = hr*60+m;

        h = new Handler(Looper.getMainLooper());
        r = new Runnable() {

            public void run() {

                //current time
                Calendar c = Calendar.getInstance();
                int mon = c.get(Calendar.MONTH) + 1;
                int day = c.get(Calendar.DATE);
                int yr = c.get(Calendar.YEAR);
                String temptoday = String.valueOf(mon)+"/"+String.valueOf(day)+"/"+String.valueOf(yr);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int min=c.get(Calendar.MINUTE);
                //int sec=c.get(Calendar.SECOND);
                int currenttime = 60*hour + min;
                //String currenttime= String.valueOf(hour)+" : "+String.valueOf(min)+" : "+String.valueOf(sec);

                //comparing current time with 12:00pm
                if(currenttime-starttime>60||!temptoday.equals(today)){

                    //restarting the activity
                    Toast pass = Toast.makeText(SleepDiaryActivity3.this,"the page is invalid, please start over!", Toast.LENGTH_SHORT);
                    pass.show();
                    Intent intent = new Intent(SleepDiaryActivity3.this,MainActivity.class);

                    startActivity(intent);
                    starttime = 2400;
                    temptoday = today;

                    finish();
                }
                h.postDelayed(this, delayMillis);

            }
        };

        h.post(r);

        if (date == 1){
            month = month -1;
            if(month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12)
            {
                date = 31;
            }
            else
            {
                date = 30;
            }
        }
        else
        {
            date = date -1;
        }

        yesterday = String.valueOf(month) + "/" + String.valueOf(date) + "/" + String.valueOf(year);
        lastnight = (TextView)findViewById(R.id.lastnight3);
        lastnight.setText("Sleep Diary for Yesterday (" + String.valueOf(month) + "/" + String.valueOf(date) + "/" + String.valueOf(year) + ")");


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
            Intent i = new Intent(SleepDiaryActivity3.this,SettingsActivity.class);
            SleepDiaryActivity3.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void button_SD3dOnClick(View view)
    {
        if (isNetworkAvailable()) {
            // Do your stuff here.
            if(view.getId() == R.id.save_s3)
            {

                final ParseUser currentUser1 = ParseUser.getCurrentUser();

                if(currentUser1 == null)
                {
                    Toast pass = Toast.makeText(SleepDiaryActivity3.this,"Please Login in first!", Toast.LENGTH_SHORT);
                    pass.show();
                }


                else if(a_urge.isEmpty()||a_muscle.isEmpty()||a_tobed.isEmpty()||a_pain.isEmpty()||a_dream.isEmpty()||a_hall.isEmpty()||a_breath.isEmpty()||a_urine.isEmpty()||a_distur.isEmpty())
                {
                    //popup msg
                    Toast errormsg = Toast.makeText(SleepDiaryActivity3.this,"Please finish all the questions!", Toast.LENGTH_SHORT);
                    errormsg.show();

                }

                else
                {

//                SleepdiaryInfo s = new SleepdiaryInfo();
//                s.setNo_coffee(Integer.parseInt(no_coffee));
//                s.setNo_coffee(no_coffee);
//                s.setNo_wine(no_wine);
//
//                s.setNo_smoke(no_smoke);
//                s.setNo_naptime(no_naptime)
                    //               s.setSleepdurationday(sleepduration);
//                s.setPilltime(pilltime);
//                s.setPillname(pillname);
//                sleephelper.insertColumn(s);


                    query.whereEqualTo("User_ID", currentUser1.getUsername());
                    query.whereEqualTo("Date", today);
                    query.setLimit(1);

                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {

                                scoreList.get(0).put("A16_Urge_move", a_urge);
                                scoreList.get(0).put("A17_Cramps", a_muscle);
                                scoreList.get(0).put("A18_Bed_turning", a_tobed);
                                scoreList.get(0).put("A19_Pain", a_pain);
                                scoreList.get(0).put("A20_Dreams", a_dream);
                                scoreList.get(0).put("A21_Visual_hallucinations", a_hall);
                                scoreList.get(0).put("A22_Difficulty_Breath", a_breath);
                                scoreList.get(0).put("A23_Urine", a_urine);
                                scoreList.get(0).put("A24_Enviro_Disturbance", a_distur);


                                if (getIntent().getBooleanExtra("yesterd",false)){
                                    scoreList.get(0).put("Date", yesterday);
                                }
                                //userActivity.pinInBackground();

                                scoreList.get(0).saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e != null) {
                                            Toast pass = Toast.makeText(SleepDiaryActivity3.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
                                            pass.show();
                                        } else {


                                        }
                                    }
                                });

                            } else {
                                Toast pass = Toast.makeText(SleepDiaryActivity3.this, "The page is outdated, Please start over!" , Toast.LENGTH_LONG);
                                pass.show();
                                Intent i = new Intent(SleepDiaryActivity3.this, MainActivity.class);
                                SleepDiaryActivity3.this.startActivity(i);
                                Log.d("score", "Error: " + e.getMessage());
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

//                query.getInBackground(objectID, new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                        if (e != null)
//                        {
//                            Toast pass = Toast.makeText(SleepDiaryActivity3.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT);
//                            pass.show();
//                        }
//                        else
//                        {
//                            object.put("Urge_move",a_urge);
//                            object.put("Muscle_cramp",a_muscle);
//                            object.put("Difficulty_turn_bed",a_tobed);
//                            object.put("Pain",a_pain);
//                            object.put("distressDream",a_dream);
//                            object.put("Visual_hallucinations",a_hall);
//                            object.put("Difficulty_Breath", a_breath);
//                            object.put("Pass_Urine",a_urine);
//                            object.put("Enviro_Disturbance", a_distur);
//                            //userActivity.pinInBackground();
//                            object.saveInBackground();
//
//                        }
//                    }
//                });

                    query2.whereEqualTo("User_ID", currentUser1.getUsername());
                    query2.whereEqualTo("Date", today);
                    query2.setLimit(1);
                    query2.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (object == null) {
                                Log.d("User_ID", "create task list." + currentUser1.getUsername());
                                TaskCheckList.put("User_ID", currentUser1.getUsername());
                                TaskCheckList.put("Date", today);
                                TaskCheckList.put("M30_Sleepdiary", 1);
                                TaskCheckList.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {

                                            //f = true;
                                            Intent i = new Intent(SleepDiaryActivity3.this, SleepActivity.class);
                                            i.putExtra("lastpage", "M30");
                                            //i.putExtra("loginstatus",f);
                                            i.putExtra("f2", f);
                                            SleepDiaryActivity3.this.startActivity(i);
                                        }
                                        else{
                                            Toast pass = Toast.makeText(SleepDiaryActivity3.this, "The page is outdated, Please start over!" , Toast.LENGTH_LONG);
                                            pass.show();
                                            Intent i = new Intent(SleepDiaryActivity3.this, MainActivity.class);
                                            SleepDiaryActivity3.this.startActivity(i);
                                            Log.d("score", "Error: " + e.getMessage());
                                        }
                                    }
                                });

                            } else {
                                query1.whereEqualTo("User_ID",currentUser1.getUsername());
                                query1.whereEqualTo("Date", today);
                                query1.setLimit(1);
                                query1.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> scoreList, ParseException e) {
                                        if (e == null) {
                                            Log.d("score", "Retrieved " + scoreList.size() + " scores");
                                            scoreList.get(0).put("M30_Sleepdiary", 1);
                                            scoreList.get(0).saveInBackground(new SaveCallback() {
                                                @Override
                                                public void done(ParseException e) {
                                                    if (e == null) {

                                                        //f = true;
                                                        Intent i = new Intent(SleepDiaryActivity3.this, SleepActivity.class);
                                                        i.putExtra("lastpage", "M30");
                                                        //i.putExtra("loginstatus",f);
                                                        i.putExtra("f2", f);
                                                        SleepDiaryActivity3.this.startActivity(i);
                                                    }
                                                }
                                            });
                                        } else {
                                            Toast pass = Toast.makeText(SleepDiaryActivity3.this, "The page is outdated, Please start over!" , Toast.LENGTH_LONG);
                                            pass.show();
                                            Intent i = new Intent(SleepDiaryActivity3.this, MainActivity.class);
                                            SleepDiaryActivity3.this.startActivity(i);
                                            Log.d("score", "Error: " + e.getMessage());
                                            Log.d("score", "Error: " + e.getMessage());
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }

        }
        else {
            Toast pass = Toast.makeText(SleepDiaryActivity3.this, "Network is not available, please check your network.", Toast.LENGTH_LONG);
            pass.show();
        }

//        if(view.getId() == R.id.cancel_s3)
//        {
//            Intent i = new Intent(SleepDiaryActivity3.this,SleepActivity.class);
//            i.putExtra("lastpage",lastpage);
//            SleepDiaryActivity3.this.startActivity(i);
//        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group == rg0)
        {
            if(checkedId == rbid01)
            {
                a_urge = "Y";
            }
            else
            {
                a_urge = "N";
            }
        }
        if(group == rg1)
        {
            if(checkedId == rbid11)
            {
                a_muscle = "Y";
            }
            else
            {
                a_muscle = "N";
            }
        }
        if(group == rg2)
        {
            if(checkedId == rbid21)
            {
                a_tobed = "Y";
            }
            else
            {
                a_tobed ="N";
            }
        }
        if(group == rg3)
        {
            if(checkedId == rbid31)
            {
                a_pain = "Y";
            }
            else
            {
                a_pain = "N";
            }
        }
        if(group == rg4)
        {
            if(checkedId == rbid41)
            {
                a_dream = "Y";
//                Toast msg = Toast.makeText(SleepDiaryActivity3.this,"Yes", Toast.LENGTH_SHORT);
//                msg.show();
            }
            else
            {
                a_dream = "N";
//                Toast msg = Toast.makeText(SleepDiaryActivity3.this,"No", Toast.LENGTH_SHORT);
//                msg.show();
            }
        }
        if(group == rg5)
        {
            if(checkedId == rbid51)
            {
             a_hall = "Y";
            }
            else
            {
              a_hall = "N";
            }
        }
        if(group == rg6)
        {
            if(checkedId == rbid61)
            {
               a_breath = "Y";
            }
            else
            {
               a_breath = "N";
            }
        }
        if(group == rg7)
        {
            if(checkedId == rbid71)
            {
               a_urine = "Y";
            }
            else
            {
             a_urine = "N";
            }
        }
        if(group == rg8)
        {
            if(checkedId == rbid81)
            {
               a_distur = "Y";
            }
            else
            {
               a_distur = "N";
            }
        }
    }

//    @Override
//    public void onBackPressed() {
//    }
}

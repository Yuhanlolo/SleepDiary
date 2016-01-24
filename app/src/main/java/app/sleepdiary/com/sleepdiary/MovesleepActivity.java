package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ypl5142 on 10/25/15.
 */
public class MovesleepActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

//    int walk =0;
//    int change = 0;
//    int usehand = 0;
//    int uncontrol = 0;

    int movep = -1;
    ImageView green5;
    ImageView wsscopa0, wsscopa1, wsscopa2, wsscopa3, csscopa0,csscopa1, csscopa2, csscopa3,uhsscopa0, uhsscopa1, uhsscopa2, uhsscopa3;
    ImageView umsscopa0,umsscopa1, umsscopa2, umsscopa3;
    int wss = -1;
    int css = -1;
    int uhss = -1;
    int umss = -1;
    SeekBar movescale;
    String objectID = "";
    String lastpage  = "";
    String currenttask = "";
    TextView currentpage;

    String today = "";
    ParseObject movesleep;

    final Calendar cal = Calendar.getInstance();
    String yesterdaystr ="";

    int month;
    int date;
    int year;

    List<ImageView> q14_s = new ArrayList<ImageView>(22);
    private ImageView green140;
    private ImageView green141;
    private ImageView green142;
    private ImageView green143;
    private ImageView green144;
    private ImageView green145;
    private ImageView green146;
    private ImageView green147;
    private ImageView green148;
    private ImageView green149;
    private ImageView green1410;
    //
    private ImageView empty140;
    private ImageView empty141;
    private ImageView empty142;
    private ImageView empty143;
    private ImageView empty144;
    private ImageView empty145;
    private ImageView empty146;
    private ImageView empty147;
    private ImageView empty148;
    private ImageView empty149;
    private ImageView empty1410;

    ParseQuery<ParseObject> query3 ;
    ParseQuery<ParseObject> query4 ;

    final long delayMillis=1000;
    Handler h=null;
    Runnable r;
    int starttime;
    final Calendar c = Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movesleep);

//        View myView = getWindow().getDecorView();
//        myView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        Intent i_getvalue = getIntent();
        lastpage = i_getvalue.getStringExtra("lastpage");

        if(lastpage.equals("M30"))
        {
            movesleep  = new ParseObject("M30");
            query3 = ParseQuery.getQuery("M30");
            query4 = ParseQuery.getQuery("M30");
            currenttask = "Morning";
        }
        else if (lastpage.equals("MDOPA1"))
        {

            movesleep  = new ParseObject("MDOPA1");
            query3 = ParseQuery.getQuery("MDOPA1");
            query4 = ParseQuery.getQuery("MDOPA1");
            currenttask = "Morning, after drug intake";
        }
        else if (lastpage.equals("A_DOPA"))
        {
            movesleep  = new ParseObject("ADOPA");
            query3 = ParseQuery.getQuery("ADOPA");
            query4 = ParseQuery.getQuery("ADOPA");
            currenttask = "Afternoon, before drug-intake";
        }
        else if(lastpage.equals("E"))
        {
            movesleep  = new ParseObject("E");
            query3 = ParseQuery.getQuery("E");
            query4 = ParseQuery.getQuery("E");
            currenttask = "Bed Time";
        }
//        else if(lastpage.equals("Nap"))
//        {
//            movesleep  = new ParseObject("NAP_MoveSleep");
//        }

        currentpage = (TextView)findViewById(R.id.lastpagem);
        currentpage.setText(currenttask);

        wsscopa0 = (ImageView)findViewById(R.id.wsscopa1);
        wsscopa1 = (ImageView)findViewById(R.id.wsscopa2);
        wsscopa2 = (ImageView)findViewById(R.id.wsscopa3);
        wsscopa3 = (ImageView)findViewById(R.id.wsscopa4);

        csscopa0 = (ImageView)findViewById(R.id.csscopa1);
        csscopa1 = (ImageView)findViewById(R.id.csscopa2);
        csscopa2 = (ImageView)findViewById(R.id.csscopa3);
        csscopa3 = (ImageView)findViewById(R.id.csscopa4);

        uhsscopa0 = (ImageView)findViewById(R.id.usscopa1);
        uhsscopa1 = (ImageView)findViewById(R.id.usscopa2);
        uhsscopa2 = (ImageView)findViewById(R.id.usscopa3);
        uhsscopa3 = (ImageView)findViewById(R.id.usscopa4);

        umsscopa0 = (ImageView)findViewById(R.id.umsscopa1);
        umsscopa1 = (ImageView)findViewById(R.id.umsscopa2);
        umsscopa2 = (ImageView)findViewById(R.id.umsscopa3);
        umsscopa3 = (ImageView)findViewById(R.id.umsscopa4);

        movescale = (SeekBar)findViewById(R.id.s_move);
        movescale.setOnSeekBarChangeListener(this);

        green140 = (ImageView)findViewById(R.id.green50);
        q14_s.add(green140);
        green141 = (ImageView)findViewById(R.id.green51);
        q14_s.add(green141);
        green142 = (ImageView)findViewById(R.id.green52);
        q14_s.add(green142);
        green143 = (ImageView)findViewById(R.id.green53);
        q14_s.add(green143);
        green144 = (ImageView)findViewById(R.id.green54);
        q14_s.add(green144);
        green145 = (ImageView)findViewById(R.id.green55);
        q14_s.add(green145);
        green146 = (ImageView)findViewById(R.id.green56);
        q14_s.add(green146);
        green147 = (ImageView)findViewById(R.id.green57);
        q14_s.add(green147);
        green148 = (ImageView)findViewById(R.id.green58);
        q14_s.add(green148);
        green149 = (ImageView)findViewById(R.id.green59);
        q14_s.add(green149);
        green1410 = (ImageView)findViewById(R.id.green510);
        q14_s.add(green1410);

        empty140 = (ImageView)findViewById(R.id.empty50);
        q14_s.add(empty140);
        empty141 = (ImageView)findViewById(R.id.empty51);
        q14_s.add(empty141);
        empty142 = (ImageView)findViewById(R.id.empty52);
        q14_s.add(empty142);
        empty143 = (ImageView)findViewById(R.id.empty53);
        q14_s.add(empty143);
        empty144 = (ImageView)findViewById(R.id.empty54);
        q14_s.add(empty144);
        empty145 = (ImageView)findViewById(R.id.empty55);
        q14_s.add(empty145);
        empty146 = (ImageView)findViewById(R.id.empty56);
        q14_s.add(empty146);
        empty147 = (ImageView)findViewById(R.id.empty57);
        q14_s.add(empty147);
        empty148 = (ImageView)findViewById(R.id.empty58);
        q14_s.add(empty148);
        empty149 = (ImageView)findViewById(R.id.empty59);
        q14_s.add(empty149);
        empty1410 = (ImageView)findViewById(R.id.empty510);
        q14_s.add(empty1410);

        /** Get the current time */

        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DATE);
        year = cal.get(Calendar.YEAR);

        today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);

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
                    Toast pass = Toast.makeText(MovesleepActivity.this,"the page is invalid, please start over!", Toast.LENGTH_SHORT);
                    pass.show();
                    Intent intent = new Intent(MovesleepActivity.this,MainActivity.class);

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

        yesterdaystr = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);
        //yesterday.setText("Sleep Diary for Yesterday (" + String.valueOf(month) + "/" + String.valueOf(date) + "/" + String.valueOf(year) + ")");
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

        if(view.getId() == R.id.uscopa1)
        {
            uhss = 0;
            uhsscopa0.setVisibility(View.VISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.uscopa2)
        {
            uhss = 1;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.VISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.uscopa3)
        {
            uhss = 2;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.VISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.uscopa4)
        {
            uhss = 3;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.umscopa1)
        {
            umss = 0;
            umsscopa0.setVisibility(View.VISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.umscopa2)
        {
            umss = 1;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.VISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.umscopa3)
        {
            umss = 2;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.VISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.umscopa4)
        {
            umss = 3;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.save_ms)
        {
            final ParseUser currentUser1 = ParseUser.getCurrentUser();

            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(MovesleepActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else if (wss == -1){

                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 1!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (css == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 2!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (uhss == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 3!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (umss == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 4!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (movep == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 5!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else
            {

                query3.whereEqualTo("User_ID", currentUser1.getUsername());
                query3.whereEqualTo("Date", today);

                query3.setLimit(1);
                query3.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            //Log.d("User_ID", "The getFirst request failed.");
                            movesleep.put("User_ID", ParseUser.getCurrentUser().getUsername());
                            movesleep.put("Date", today);

                            if (lastpage.equals("M30"))
                            {
                                movesleep.put("A26_M30_SCOPA_walking", wss);
                                movesleep.put("A27_M30_SCOPA_change", css);
                                movesleep.put("A28_M30_SCOPA_hands", uhss);
                                movesleep.put("A29_M30_SCOPA_involuntary", umss);
                                movesleep.put("A30_M30_VAS_motor", movep);
                            }

                           else if (lastpage.equals("MDOPA1"))
                            {
                                movesleep.put("A32_MDOPA1_SCOPA_walking", wss);
                                movesleep.put("A33_MDOPA1_SCOPA_change", css);
                                movesleep.put("A34_MDOPA1_SCOPA_hands", uhss);
                                movesleep.put("A35_MDOPA1_SCOPA_involuntary", umss);
                                movesleep.put("A36_MDOPA1_VAS_motor", movep);
                            }

                           else if (lastpage.equals("A_DOPA"))
                            {
                                movesleep.put("A38_ADOPA_SCOPA_walking", wss);
                                movesleep.put("A39_ADOPA_SCOPA_change", css);
                                movesleep.put("A40_ADOPA_SCOPA_hands", uhss);
                                movesleep.put("A41_ADOPA_SCOPA_involuntary", umss);
                                movesleep.put("A42_ADOPA_VAS_motor", movep);
                            }

                          else  if (lastpage.equals("E"))
                            {
                                movesleep.put("A44_E_SCOPA_walking", wss);
                                movesleep.put("A45_E_SCOPA_change", css);
                                movesleep.put("A46_E_SCOPA_hands", uhss);
                                movesleep.put("A47_E_SCOPA_involuntary", umss);
                                movesleep.put("A48_E_VAS_motor", movep);
                            }


                            movesleep.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast pass = Toast.makeText(MovesleepActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
                            objectID = movesleep.getObjectId();
                            Intent i = new Intent(MovesleepActivity.this, MovesleepActivity2.class);
                            i.putExtra("objectID", objectID);
                            i.putExtra("lastpage",lastpage);
                            MovesleepActivity.this.startActivity(i);


                        }
                    }
                });


                        } else {
                            query4.whereEqualTo("User_ID", currentUser1.getUsername());
                            query4.whereEqualTo("Date", today);
                            query4.setLimit(1);
                            query4.findInBackground(new FindCallback<ParseObject>() {

                                public void done(List<ParseObject> scoreList, ParseException e) {

                                    if (e == null) {

                                        if (lastpage == "M30")
                                        {
                                            scoreList.get(0).put("A26_M30_SCOPA_walking", wss);
                                            scoreList.get(0).put("A27_M30_SCOPA_change", css);
                                            scoreList.get(0).put("A28_M30_SCOPA_hands", uhss);
                                            scoreList.get(0).put("A29_M30_SCOPA_involuntary", umss);
                                            scoreList.get(0).put("A30_M30_VAS_motor", movep);
                                        }

                                        else if (lastpage == "MDOPA1")
                                        {
                                            scoreList.get(0).put("A32_MDOPA1_SCOPA_walking", wss);
                                            scoreList.get(0).put("A33_MDOPA1_SCOPA_change", css);
                                            scoreList.get(0).put("A34_MDOPA1_SCOPA_hands", uhss);
                                            scoreList.get(0).put("A35_MDOPA1_SCOPA_involuntary", umss);
                                            scoreList.get(0).put("A36_MDOPA1_VAS_motor", movep);
                                        }

                                       else if (lastpage == "A_DOPA")
                                        {
                                            scoreList.get(0).put("A38_ADOPA_SCOPA_walking", wss);
                                            scoreList.get(0).put("A39_ADOPA_SCOPA_change", css);
                                            scoreList.get(0).put("A40_ADOPA_SCOPA_hands", uhss);
                                            scoreList.get(0).put("A41_ADOPA_SCOPA_involuntary", umss);
                                            scoreList.get(0).put("A42_ADOPA_VAS_motor", movep);
                                        }

                                        else if (lastpage == "E")
                                        {
                                            scoreList.get(0).put("A44_E_SCOPA_walking", wss);
                                            scoreList.get(0).put("A45_E_SCOPA_change", css);
                                            scoreList.get(0).put("A46_E_SCOPA_hands", uhss);
                                            scoreList.get(0).put("A47_E_SCOPA_involuntary", umss);
                                            scoreList.get(0).put("A48_E_VAS_motor", movep);
                                        }



                                        scoreList.get(0).saveInBackground(new SaveCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                if (e != null) {
                                                    Toast pass = Toast.makeText(MovesleepActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
                                                    pass.show();
                                                } else {
                                                    objectID = movesleep.getObjectId();
                                                    Intent i = new Intent(MovesleepActivity.this, MovesleepActivity2.class);
                                                    i.putExtra("lastpage", lastpage);
                                                    i.putExtra("objectID", objectID);
                                                    MovesleepActivity.this.startActivity(i);
                                                }
                                            }
                                        });

                                    } else {
                                        Log.d("score", "Error: " + e.getMessage());
                                    }
                                }
                            });
                        }
                    }
                });



//                movesleep.put("User_ID", ParseUser.getCurrentUser().getUsername());
//                movesleep.put("Date",yesterdaystr);
//                movesleep.put("SCOPA_walking",wss);
//                movesleep.put("SCOPA_change_position",css);
//                movesleep.put("SCOPA_use_hands",uhss);
//                movesleep.put("SCOPA_uncontrollable_movement",umss);
//
//                movesleep.put("Move_Capability", movep);
//                //movesleep.put("Move_Capability",0);
//                movesleep.put("Sleepiness_Scale",0);
//
//                movesleep.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if (e != null) {
//                            Toast pass = Toast.makeText(MovesleepActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
//                            pass.show();
//                        } else {
//                            objectID = movesleep.getObjectId();
//                            Intent i = new Intent(MovesleepActivity.this, MovesleepActivity2.class);
//                            i.putExtra("objectID", objectID);
//                            i.putExtra("lastpage",lastpage);
//                            MovesleepActivity.this.startActivity(i);
//
//
//                        }
//                    }
//                });



//            Intent i = new Intent(MovesleepActivity.this,MovesleepActivity2.class);
//            MovesleepActivity.this.startActivity(i);
            }
        }

//        if(view.getId() == R.id.cancel_ms)
//        {
//            Intent i = new Intent(MovesleepActivity.this,SleepActivity.class);
//            i.putExtra("lastpage",lastpage);
//            MovesleepActivity.this.startActivity(i);
//        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == movescale)
        {
            movep = progress;
            for (int i = 0; i<22;i++)
            {
//
                if (i== movep )
                {

                    q14_s.get(i).setVisibility(View.VISIBLE);
                    q14_s.get(i+11).setVisibility(View.VISIBLE);
                }
                else
                {
                    if(i != movep+11)
                        q14_s.get(i).setVisibility(View.INVISIBLE);

                }

            }

        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

package app.sleepdiary.com.sleepdiary;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

/**
 * Created by ypl5142 on 10/25/15.
 */
public class NapMoveSleepActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener,TextView.OnEditorActionListener {


    int tempbedh = -1, tempbedm = -1;
    String bedtime = "";
    String asleeptime = "";

    String temp_h = "";
    String temp_m= "";

    int dHour =-1;
    int dMinute = -1;

    EditText bedh_edt, bedm_edt,fallh_edt, fallm_edt;
    TextView fallh, fallm, t_awake;

    //Button Bnaptime, Bnapdu;
    final Calendar cal = Calendar.getInstance();
    String yesterdaystr ="";

    int month;
    int date;
    int year;

    String[] minuteValues = {"0","5","10","15","20","25","30","35","40","45","50","55"};
    int movep = 0;

    ImageView wsscopa0, wsscopa1, wsscopa2, wsscopa3, csscopa0,csscopa1, csscopa2, csscopa3,uhsscopa0, uhsscopa1, uhsscopa2, uhsscopa3;
    ImageView umsscopa0,umsscopa1, umsscopa2, umsscopa3;
    int wss = -1;
    int css = -1;
    int uhss = -1;
    int umss = -1;
    //SeekBar movescale;
    String objectID = "";
    String lastpage  = "";
    String currenttask = "";
    TextView currentpage;
    String naptime = "";
    String napdu = "";

    ParseObject movesleep;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_napmovesleep);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        Intent i_getvalue = getIntent();
        lastpage = i_getvalue.getStringExtra("lastpage");

            movesleep  = new ParseObject("NAP_MoveSleep");
            currenttask = "After nap";

        currentpage = (TextView)findViewById(R.id.lastpagem);
        currentpage.setText(currenttask);

        bedh_edt = (EditText)findViewById(R.id.naph);
        //napduh_edt.setInputType(InputType.TYPE_NULL);
        //bedh_edt.setEnabled(false);
        bedh_edt.setOnEditorActionListener(this);

        bedm_edt = (EditText)findViewById(R.id.napm);
        //napduh_edt.setInputType(InputType.TYPE_NULL);
        //bedm_edt.setEnabled(false);
        bedm_edt.setOnEditorActionListener(this);

        fallh_edt = (EditText)findViewById(R.id.nap_h);
        //napdum_edt.setInputType(InputType.TYPE_NULL);
        fallh_edt.setOnEditorActionListener(this);

        fallm_edt = (EditText)findViewById(R.id.nap_m);
        //napdum_edt.setInputType(InputType.TYPE_NULL);
        fallm_edt.setOnEditorActionListener(this);

        fallh = (TextView)findViewById(R.id.napuh);
        fallm = (TextView)findViewById(R.id.napum);

        wsscopa0 = (ImageView)findViewById(R.id.napwsscopa1);
        wsscopa1 = (ImageView)findViewById(R.id.napwsscopa2);
        wsscopa2 = (ImageView)findViewById(R.id.napwsscopa3);
        wsscopa3 = (ImageView)findViewById(R.id.napwsscopa4);

        csscopa0 = (ImageView)findViewById(R.id.napcsscopa1);
        csscopa1 = (ImageView)findViewById(R.id.napcsscopa2);
        csscopa2 = (ImageView)findViewById(R.id.napcsscopa3);
        csscopa3 = (ImageView)findViewById(R.id.napcsscopa4);

        uhsscopa0 = (ImageView)findViewById(R.id.napusscopa1);
        uhsscopa1 = (ImageView)findViewById(R.id.napusscopa2);
        uhsscopa2 = (ImageView)findViewById(R.id.napusscopa3);
        uhsscopa3 = (ImageView)findViewById(R.id.napusscopa4);

        umsscopa0 = (ImageView)findViewById(R.id.napumsscopa1);
        umsscopa1 = (ImageView)findViewById(R.id.napumsscopa2);
        umsscopa2 = (ImageView)findViewById(R.id.napumsscopa3);
        umsscopa3 = (ImageView)findViewById(R.id.napumsscopa4);

//        movescale = (SeekBar)findViewById(R.id.naps_move);
//        movescale.setOnSeekBarChangeListener(this);

        /** Get the current time */

        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DATE);
        year = cal.get(Calendar.YEAR);

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
            Intent i = new Intent(NapMoveSleepActivity.this,SettingsActivity.class);
            NapMoveSleepActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void button_napmsdOnClick(View view)
    {

        if(view.getId() == R.id.napwscopa1)
        {
            wss = 0;
            wsscopa0.setVisibility(View.VISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napwscopa2)
        {
            wss = 1;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.VISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napwscopa3)
        {
            wss = 2;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.VISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napwscopa4)
        {
            wss = 3;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.napcscopa1)
        {
            css = 0;
            csscopa0.setVisibility(View.VISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napcscopa2)
        {
            css = 1;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.VISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napcscopa3)
        {
            css = 2;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.VISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napcscopa4)
        {
            css = 3;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.napuscopa1)
        {
            uhss = 0;
            uhsscopa0.setVisibility(View.VISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napuscopa2)
        {
            uhss = 1;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.VISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napuscopa3)
        {
            uhss = 2;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.VISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napuscopa4)
        {
            uhss = 3;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.napumscopa1)
        {
            umss = 0;
            umsscopa0.setVisibility(View.VISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napumscopa2)
        {
            umss = 1;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.VISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napumscopa3)
        {
            umss = 2;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.VISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.napumscopa4)
        {
            umss = 3;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.VISIBLE);
        }



        if(view.getId() == R.id.napsave_ms)
        {
            ParseUser currentUser1 = ParseUser.getCurrentUser();
            boolean bedhour1 = false, bedmin1 = false;
            boolean fallhour1 = false, fallmin1 = false;

            if (!(bedh_edt.getText().toString()).isEmpty() && !(bedm_edt.getText().toString()).isEmpty()) {
                tempbedh = Integer.parseInt(bedh_edt.getText().toString());
                tempbedm = Integer.parseInt(bedm_edt.getText().toString());
                if (tempbedh < 0 || tempbedh > 23) {
                    bedhour1 = true;
                } else if (tempbedm < 0 || tempbedm > 59) {

                    bedmin1 = true;
                } else {
                    bedtime = pad(tempbedh) + ":" + pad(tempbedm);
                }
            }

            if (!(fallh_edt.getText().toString()).isEmpty() && !(fallm_edt.getText().toString()).isEmpty()) {
                dHour = Integer.parseInt(fallh_edt.getText().toString());
                dMinute = Integer.parseInt(fallm_edt.getText().toString());
                if (dHour < 0 || dHour > 23) {

                    fallhour1 = true;
                } else if (dMinute < 0 || dMinute > 59) {

                    fallmin1 = true;
                } else {
                    asleeptime = pad(dHour) + ":" + pad(dMinute);
                }
            }

            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(NapMoveSleepActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else if (bedhour1)
            {
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this, "For Question 1, Please input hour of time between 0-23!"+tempbedh, Toast.LENGTH_LONG);
                errormsg.show();
            }

            else if (bedmin1)
            {
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this, "For Question 1, Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                errormsg.show();
            }


            else if (bedtime.isEmpty()) {
                //popup msg
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this, "Please finish Question 1!", Toast.LENGTH_SHORT);
                errormsg.show();

            }

            else if (fallhour1)
            {
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this, "For Question 2, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                errormsg.show();
            }

            else if (fallmin1)
            {
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this, "For Question 2, Please input minute of time between 0-59", Toast.LENGTH_LONG);
                errormsg.show();
            }

            else if (asleeptime.isEmpty()) {
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this, "Please finish Question 2!", Toast.LENGTH_SHORT);
                errormsg.show();
            }

            else if (wss == -1){

                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this,"Please finish Question 3!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (css == -1){
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this,"Please finish Question 4!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (uhss == -1){
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this,"Please finish Question 5!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (umss == -1){
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this,"Please finish Question 6!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else
            {
                movesleep.put("User_ID", ParseUser.getCurrentUser().getUsername());
                movesleep.put("Date",yesterdaystr);
                movesleep.put("NAP_time",bedtime);
                movesleep.put("NAP_duration",asleeptime);
                movesleep.put("SCOPA_walking",wss);
                movesleep.put("SCOPA_change_position",css);
                movesleep.put("SCOPA_use_hands",uhss);
                movesleep.put("SCOPA_uncontrollable_movement",umss);

                //movesleep.put("Move_Capability", movep);
                //movesleep.put("Move_Capability",0);
                movesleep.put("Sleepiness_Scale",0);

                movesleep.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast pass = Toast.makeText(NapMoveSleepActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
                            objectID = movesleep.getObjectId();
//                             Toast pass = Toast.makeText(NapMoveSleepActivity.this,"id 1: "+objectID, Toast.LENGTH_SHORT);
//                             pass.show();
                            Intent i = new Intent(NapMoveSleepActivity.this, NapMoveSleepActivity2.class);
                            i.putExtra("objectID", objectID);
                            i.putExtra("lastpage",lastpage);
                            NapMoveSleepActivity.this.startActivity(i);


                        }
                    }
                });

//            Intent i = new Intent(MovesleepActivity.this,MovesleepActivity2.class);
//            MovesleepActivity.this.startActivity(i);
            }
        }


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        if (seekBar == movescale)
//        {
//            movep = progress;
//
//        }

    }

    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (v.getId() == R.id.naph) {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(bedh_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(bedh_edt.getText().toString()) > 23 || Integer.parseInt(bedh_edt.getText().toString()) < 0) {
                    bedh_edt.setText("");
                    Toast pass = Toast.makeText(NapMoveSleepActivity.this, "Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    pass.show();
                    bedh_edt.requestFocus();
                } else {

                    tempbedh = Integer.parseInt(bedh_edt.getText().toString());
                    if (tempbedh < 10) {
                        bedh_edt.setText("0" + tempbedh);
                    }

                    if (tempbedh != -1 && tempbedm != -1) {
                        bedtime = pad(tempbedh) + ":" + pad(tempbedm);
                    }
                    bedm_edt.requestFocus();
                }

            }


        }

        if (v.getId() == R.id.napm) {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(bedm_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(bedm_edt.getText().toString()) > 59 || Integer.parseInt(bedm_edt.getText().toString()) < 0) {
                    bedm_edt.setText("");
                    Toast pass = Toast.makeText(NapMoveSleepActivity.this, "Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                    pass.show();
                    bedm_edt.requestFocus();
                } else {
                    tempbedm = Integer.parseInt(bedm_edt.getText().toString());
                    if (tempbedm < 10) {
                        bedm_edt.setText("0" + tempbedm);
                    }
                    if (tempbedh != -1 && tempbedm != -1) {
                        bedtime = pad(tempbedh) + ":" + pad(tempbedm);
                    }
                    fallh_edt.requestFocus();
                }

            }

        }


        if (v.getId() == R.id.nap_h) {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(fallh_edt.getText().toString().isEmpty())) {
                dHour = Integer.parseInt(fallh_edt.getText().toString());
                if (dHour > 23 || dHour < 0) {
                    fallh_edt.setText("");
                    Toast pass = Toast.makeText(NapMoveSleepActivity.this, "Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    pass.show();
                    fallh_edt.requestFocus();
                } else {

                    if (dHour == 1) {
                        fallh.setText("hr");
                        temp_h = "hr";
                    } else {
                        fallh.setText("hrs");
                        temp_h = "hrs";
                    }

                    if (dHour != -1 && dMinute != -1) {
                        asleeptime = pad(dHour) + temp_h + pad(dMinute) + temp_m;
                    }

                    fallm_edt.requestFocus();
                }

            }


        }

        if (v.getId() == R.id.nap_m) {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(fallm_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(fallm_edt.getText().toString()) > 59 || Integer.parseInt(fallm_edt.getText().toString()) < 0) {
                    fallm_edt.setText("");
                    Toast pass = Toast.makeText(NapMoveSleepActivity.this, "Please input nap minute of time between 0-59!", Toast.LENGTH_LONG);
                    pass.show();
                    fallm_edt.requestFocus();
                } else {
                    dMinute = Integer.parseInt(fallm_edt.getText().toString());


                    if (dMinute == 1) {
                        fallm.setText("min");
                        temp_m = "min";
                    } else {
                        fallm.setText("mins");
                        temp_m = "mins";
                    }

                    if (dHour != -1 && dMinute != -1) {
                        asleeptime = pad(dHour) + temp_h + pad(dMinute) + temp_m;
                    }
                    //wakeh_edt.requestFocus();
                }

            }


        }
        return false;
    }
}


package app.sleepdiary.com.sleepdiary;


/**
 * Created by ypl5142 on 10/4/15.
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.view.Menu;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class SleepDiaryActivity2 extends ActionBarActivity implements OnSeekBarChangeListener, TextView.OnEditorActionListener {

//    private Button Bbedtime;
//    private Button Basleeptime;
//    private Button Bwoketime;
//    private Button Bouttime;


    EditText bedh_edt, bedm_edt,fallh_edt, fallm_edt, wakeh_edt, wakem_edt,outh_edt, outm_edt, awake_edt;
    TextView fallh, fallm, t_awake;

    int tempbedh = -1, tempbedm = -1,  tempwakeh = -1, tempwakem = -1, tempouth = -1, tempoutm = -1;
    int dHour = 0, dMinute = 0;
    String lastpage = "";

    String temp_h = "", temp_m = "";

    SeekBar Snowake, SleepQ, AwakeQ;
    private TextView waket;

    TextView lastnight;
    int month;
    int date;
    int year;

//    int temp_bed_h = 22;
//    int temp_bed_m = 0;
//    int temp_wake_h = -1;
//    int temp_wake_m = -1;
//    int temp_asleep_h = -1;
//    int temp_asleep_m = -1;

    int temp_wake = 0;
    int temp_out = 2400;

//    private ImageView SQ1_g;
//    private ImageView SQ1;
//    private ImageView SQ2_g;
//    private ImageView SQ2;
//    private ImageView SQ3_g;
//    private ImageView SQ3;
//    private ImageView SQ4_g;
//    private ImageView SQ4;
//    private ImageView SQ5_g;
//    private ImageView SQ5;
//
//    private ImageView AWQ1_g;
//    private ImageView AWQ1;
//    private ImageView AWQ2_g;
//    private ImageView AWQ2;
//    private ImageView AWQ3_g;
//    private ImageView AWQ3;
//    private ImageView AWQ4_g;
//    private ImageView AWQ4;
//    private ImageView AWQ5_g;
//    private ImageView AWQ5;

    int sq = -1;
    int awq = -1;

    String bedtime = "";
    String asleeptime = "";
    String woketime = "";
    String outtime = "";
    int no_wake = 0;

    String objectID = "";

    String[] hrValues = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
    String[] minuteValues = {"0","5","10","15","20","25","30","35","40","45","50","55"};
    final Calendar cal = Calendar.getInstance();

    int bedhour = 22;
    int bedmin = 0;

    int wokehour= 6;
    int wokemin = 0;

    int outhour = 6;
    int outmin = 0;

    List<ImageView> awim = new ArrayList<ImageView>(10);
    //SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);
    //ParseObject userActivity = new ParseObject("UserActivity");
    ParseQuery <ParseObject>  query;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2sleepdiary);

//        View myView = getWindow().getDecorView();
//        myView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        query = ParseQuery.getQuery("Sleep_Diary");
        objectID = getIntent().getStringExtra("objectID");

        lastpage = getIntent().getStringExtra("lastpage");

//        Toast pass = Toast.makeText(SleepDiaryActivity2.this, "id 2: "+objectID, Toast.LENGTH_SHORT);
//        pass.show();
        awake_edt = (EditText)findViewById(R.id.no_awake);
        //nap_edt.setText("0");
        awake_edt.setOnEditorActionListener(this);
        t_awake = (TextView)findViewById(R.id.d_awake);

        bedh_edt = (EditText)findViewById(R.id.bedh);
        //napduh_edt.setInputType(InputType.TYPE_NULL);
        //bedh_edt.setEnabled(false);
        bedh_edt.setOnEditorActionListener(this);

        bedm_edt = (EditText)findViewById(R.id.bedm);
        //napduh_edt.setInputType(InputType.TYPE_NULL);
        //bedm_edt.setEnabled(false);
        bedm_edt.setOnEditorActionListener(this);

        fallh_edt = (EditText)findViewById(R.id.fall_h);
        //napdum_edt.setInputType(InputType.TYPE_NULL);
        fallh_edt.setOnEditorActionListener(this);

        fallm_edt = (EditText)findViewById(R.id.fall_m);
        //napdum_edt.setInputType(InputType.TYPE_NULL);
        fallm_edt.setOnEditorActionListener(this);

        wakeh_edt = (EditText)findViewById(R.id.wakeh);
        wakeh_edt.setOnEditorActionListener(this);

        wakem_edt = (EditText)findViewById(R.id.wakem);
        wakem_edt.setOnEditorActionListener(this);

        outh_edt = (EditText)findViewById(R.id.outh);
        outh_edt.setOnEditorActionListener(this);

        outm_edt = (EditText)findViewById(R.id.outm);
        outm_edt.setOnEditorActionListener(this);

        fallh = (TextView)findViewById(R.id.falluh);
        fallm = (TextView)findViewById(R.id.fallum);
        t_awake =(TextView)findViewById(R.id.d_awake);

//        Snowake = (SeekBar)findViewById(R.id.wakeno);
//        Snowake.setOnSeekBarChangeListener(this);
//
//        awim.add((ImageView) findViewById(R.id.aw0));
//        awim.add((ImageView)findViewById(R.id.aw1));
//        awim.add((ImageView)findViewById(R.id.aw2));
//        awim.add((ImageView)findViewById(R.id.aw3));
//        awim.add((ImageView)findViewById(R.id.aw4));
//        awim.add((ImageView)findViewById(R.id.aw5));
//        awim.add((ImageView)findViewById(R.id.aw6));
//        awim.add((ImageView)findViewById(R.id.aw7));
//        awim.add((ImageView)findViewById(R.id.aw8));
//        awim.add((ImageView)findViewById(R.id.aw9));
//        awim.add((ImageView)findViewById(R.id.aw10));

        SleepQ = (SeekBar)findViewById(R.id.sleepquality);
        SleepQ.setOnSeekBarChangeListener(this);

        AwakeQ = (SeekBar)findViewById(R.id.awakequality);
        AwakeQ.setOnSeekBarChangeListener(this);

        //waket = (TextView)findViewById(R.id.d_wake);


//        SQ1_g = (ImageView)findViewById(R.id.SQquality1_gray);
//        SQ1 = (ImageView)findViewById(R.id.SQquality1);
//        SQ2_g = (ImageView)findViewById(R.id.SQquality2_gray);
//        SQ2 = (ImageView)findViewById(R.id.SQquality2);
//        SQ3_g = (ImageView)findViewById(R.id.SQquality3_gray);
//        SQ3 = (ImageView)findViewById(R.id.SQquality3);
//        SQ4_g = (ImageView)findViewById(R.id.SQquality4_gray);
//        SQ4 = (ImageView)findViewById(R.id.SQquality4);
//        SQ5_g = (ImageView)findViewById(R.id.SQquality5_gray);
//        SQ5 = (ImageView)findViewById(R.id.SQquality5);
//
//
//        AWQ1_g = (ImageView)findViewById(R.id.AWQquality1_gray);
//        AWQ1 = (ImageView)findViewById(R.id.AWQquality1);
//        AWQ2_g = (ImageView)findViewById(R.id.AWQquality2_gray);
//        AWQ2 = (ImageView)findViewById(R.id.AWQquality2);
//        AWQ3_g = (ImageView)findViewById(R.id.AWQquality3_gray);
//        AWQ3 = (ImageView)findViewById(R.id.AWQquality3);
//        AWQ4_g = (ImageView)findViewById(R.id.AWQquality4_gray);
//        AWQ4 = (ImageView)findViewById(R.id.AWQquality4);
//        AWQ5_g = (ImageView)findViewById(R.id.AWQquality5_gray);
//        AWQ5 = (ImageView)findViewById(R.id.AWQquality5);


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

        lastnight = (TextView)findViewById(R.id.lastnight);
        lastnight.setText("Sleep Diary for Yesterday (" + String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year)+")");

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
            Intent i = new Intent(SleepDiaryActivity2.this,SettingsActivity.class);
            SleepDiaryActivity2.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



    public void button_SD2dOnClick(View view)
    {
//        if(view.getId() == SQ1_g.getId()){
//            sq =1;
//            SQ1.setVisibility(View.VISIBLE);
//            SQ2.setVisibility(View.INVISIBLE);
//            SQ3.setVisibility(View.INVISIBLE);
//            SQ4.setVisibility(View.INVISIBLE);
//            SQ5.setVisibility(View.INVISIBLE);
//
//        }
//        else if(view.getId() == SQ2_g.getId()){
//            sq =2;
//            SQ2.setVisibility(View.VISIBLE);
//            SQ1.setVisibility(View.INVISIBLE);
//            SQ3.setVisibility(View.INVISIBLE);
//            SQ4.setVisibility(View.INVISIBLE);
//            SQ5.setVisibility(View.INVISIBLE);
//
//        }
//
//        else if(view.getId() == SQ3_g.getId()){
//            sq =3;
//            SQ3.setVisibility(View.VISIBLE);
//            SQ2.setVisibility(View.INVISIBLE);
//            SQ1.setVisibility(View.INVISIBLE);
//            SQ4.setVisibility(View.INVISIBLE);
//            SQ5.setVisibility(View.INVISIBLE);
//
//        }
//        else if(view.getId() == SQ4_g.getId()){
//            sq =4;
//            SQ4.setVisibility(View.VISIBLE);
//            SQ2.setVisibility(View.INVISIBLE);
//            SQ3.setVisibility(View.INVISIBLE);
//            SQ1.setVisibility(View.INVISIBLE);
//            SQ5.setVisibility(View.INVISIBLE);
//
//        }
//        else if(view.getId() == SQ5_g.getId()){
//            sq =5;
//            SQ5.setVisibility(View.VISIBLE);
//            SQ2.setVisibility(View.INVISIBLE);
//            SQ3.setVisibility(View.INVISIBLE);
//            SQ4.setVisibility(View.INVISIBLE);
//            SQ1.setVisibility(View.INVISIBLE);
//
//        }
//
//        else if(view.getId() == AWQ1_g.getId())
//            {
//                awq = 1;
//                AWQ1.setVisibility(View.VISIBLE);
//                AWQ2.setVisibility(View.INVISIBLE);
//                AWQ3.setVisibility(View.INVISIBLE);
//                AWQ4.setVisibility(View.INVISIBLE);
//                AWQ5.setVisibility(View.INVISIBLE);
//            }
//            else if(view.getId() == AWQ2_g.getId())
//            {
//                awq = 2;
//                AWQ2.setVisibility(View.VISIBLE);
//                AWQ1.setVisibility(View.INVISIBLE);
//                AWQ3.setVisibility(View.INVISIBLE);
//                AWQ4.setVisibility(View.INVISIBLE);
//                AWQ5.setVisibility(View.INVISIBLE);
//            }
//            else if(view.getId() == AWQ3_g.getId())
//            {
//                awq = 3;
//                AWQ3.setVisibility(View.VISIBLE);
//                AWQ2.setVisibility(View.INVISIBLE);
//                AWQ1.setVisibility(View.INVISIBLE);
//                AWQ4.setVisibility(View.INVISIBLE);
//                AWQ5.setVisibility(View.INVISIBLE);
//            }
//            else if(view.getId() == AWQ4_g.getId())
//            {
//                awq = 4;
//                AWQ4.setVisibility(View.VISIBLE);
//                AWQ2.setVisibility(View.INVISIBLE);
//                AWQ3.setVisibility(View.INVISIBLE);
//                AWQ1.setVisibility(View.INVISIBLE);
//                AWQ5.setVisibility(View.INVISIBLE);
//            }
//            else if(view.getId() == AWQ5_g.getId())
//            {
//                awq = 5;
//                AWQ5.setVisibility(View.VISIBLE);
//                AWQ2.setVisibility(View.INVISIBLE);
//                AWQ3.setVisibility(View.INVISIBLE);
//                AWQ4.setVisibility(View.INVISIBLE);
//                AWQ1.setVisibility(View.INVISIBLE);
//            }

        if(view.getId() == R.id.save_s2) {

            ParseUser currentUser1 = ParseUser.getCurrentUser();
            boolean bedhour1 = false, bedmin1 = false;
            boolean fallhour1 = false, fallmin1 = false;
            boolean wakehour1 = false, wakemin1 = false;
            boolean outhour1 = false, outmin1 = false;
            boolean early1 = false, late1 = false;

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

            if (!(wakeh_edt.getText().toString()).isEmpty() && !(wakem_edt.getText().toString()).isEmpty()) {
                tempwakeh = Integer.parseInt(wakeh_edt.getText().toString());
                tempwakem = Integer.parseInt(wakem_edt.getText().toString());
                if (tempwakeh < 0 || tempwakeh > 23) {
//                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 11, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
//                    errormsg.show();
                    wakehour1 = true;
                } else if (tempwakem < 0 || tempwakem > 60) {
//                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 11, Please input minute of time between 0-59!", Toast.LENGTH_LONG);
//                    errormsg.show();
                    wakemin1 = true;
                }
                else if (temp_out<temp_wake)
                {
                    early1 = true;
                }
                else {
                    woketime = pad(tempwakeh) + ":" + pad(tempwakem);
                    temp_wake = 100*tempwakeh+tempwakem;
                }
            }

            if (!(outh_edt.getText().toString()).isEmpty() && !(outm_edt.getText().toString()).isEmpty()) {
                tempouth = Integer.parseInt(outh_edt.getText().toString());
                tempoutm = Integer.parseInt(outm_edt.getText().toString());
                if (tempouth < 0 || tempouth > 23) {
//                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 11, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
//                    errormsg.show();
                    outhour1 = true;
                } else if (tempoutm < 0 || tempoutm > 60) {
//                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 11, Please input minute of time between 0-59!", Toast.LENGTH_LONG);
//                    errormsg.show();
                    outmin1 = true;
                }
                else if (temp_out<temp_wake)
                {
                    late1 = true;
                }
                else {
                    outtime = pad(tempouth) + ":" + pad(tempoutm);
                    temp_out = 100*tempouth+tempoutm;
                }
            }


                if (currentUser1 == null) {
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please Login in first!", Toast.LENGTH_SHORT);
                    pass.show();
                }

                else if (bedhour1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 9, Please input hour of time between 0-23!"+tempbedh, Toast.LENGTH_LONG);
                    errormsg.show();
                }

                else if (bedmin1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 9, Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                    errormsg.show();
                }



                else if (bedtime.isEmpty()) {
                    //popup msg
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "Please finish Question 9!", Toast.LENGTH_SHORT);
                    errormsg.show();

                }

                else if (fallhour1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 10, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    errormsg.show();
                }

                else if (fallmin1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 10, Please input minute of time between 0-59", Toast.LENGTH_LONG);
                    errormsg.show();
                }

                else if (asleeptime.isEmpty()) {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "Please finish Question 10!", Toast.LENGTH_SHORT);
                    errormsg.show();
                }

                else if (wakehour1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 11, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    errormsg.show();
                }

                else if (wakemin1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 11, Please input minute of time between 0-59", Toast.LENGTH_LONG);
                    errormsg.show();
                }

                else if (early1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 11, Woke up time should earlier than the time out of bed!", Toast.LENGTH_LONG);
                    errormsg.show();
                }
                else if (woketime.isEmpty()) {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "Please finish Question 11!", Toast.LENGTH_SHORT);
                    errormsg.show();
                }

                else if (outhour1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 12, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    errormsg.show();
                }

                else if (outmin1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 12, Please input minute of time between 0-59", Toast.LENGTH_LONG);
                    errormsg.show();
                }

                else if (late1)
                {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "For Question 12, the time out of bed should later than woke up time", Toast.LENGTH_LONG);
                    errormsg.show();
                }
                else if (outtime.isEmpty()) {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "Please finish Question 12!", Toast.LENGTH_SHORT);
                    errormsg.show();
                } else if (sq == -1) {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "Please finish Question 13!", Toast.LENGTH_SHORT);
                    errormsg.show();
                } else if (awq == -1) {
                    Toast errormsg = Toast.makeText(SleepDiaryActivity2.this, "Please finish Question 14!", Toast.LENGTH_SHORT);
                    errormsg.show();
                } else {
                    //query.whereEqualTo("User_ID",objectID);
                    query.getInBackground(objectID, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if (e != null) {
                                Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
                                pass.show();
                            } else {
                                object.put("Bed_Time", bedtime);
                                object.put("Sleep_Duration", asleeptime);
                                object.put("Wake_Time", woketime);
                                object.put("OutofBed_Time", outtime);
                                object.put("No_Awakenings", no_wake);
                                object.put("Sleep_Quality", sq);
                                object.put("Awake_Quality", awq);
                                //userActivity.pinInBackground();
                                object.saveInBackground();
                            }
                        }
                    });

//                userActivity.put("Bed_Time", bedtime);
//                userActivity.put("Sleep_Duration",asleeptime);
//                userActivity.put("Wake_Time",woketime);
//                userActivity.put("OutofBed_Time", outtime);
//                userActivity.put("No_Awakenings",no_wake);
//                userActivity.put("Sleep_Quality",sq);
//                userActivity.put("Awake_Quality", awq);
                    //userActivity.pinInBackground();
                    //userActivity.saveInBackground();
//                userActivity.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if(e!=null)
//                        {
//                            Toast pass = Toast.makeText(SleepDiaryActivity2.this,"error"+e.getMessage(), Toast.LENGTH_SHORT);
//                            pass.show();
//                        }
//                        else{
//                            objectID = userActivity.getObjectId();
//                        }
//                    }
//                });

                    Intent i = new Intent(SleepDiaryActivity2.this, SleepDiaryActivity3.class);
                    i.putExtra("objectID", objectID);
                    i.putExtra("lastpage", lastpage);
                    SleepDiaryActivity2.this.startActivity(i);

                }
            }

//        else if(view.getId() == R.id.cancel_s2)
//        {
//            Intent i = new Intent(SleepDiaryActivity2.this,SleepActivity.class);
//            i.putExtra("lastpage",lastpage);
//            SleepDiaryActivity2.this.startActivity(i);
//        }


        }



    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar == Snowake)
        {

            no_wake = progress;
            for (int bc = 0; bc<11;bc++)
            {
                if(bc == no_wake)
                    continue;
                awim.get(bc).setVisibility(View.INVISIBLE);
            }

               awim.get(progress).setVisibility(View.VISIBLE);

            if (no_wake==10){
                waket.setText(no_wake+"or more times");
                waket.setTextSize(16);
            }
            else if(no_wake == 1)
            {
                waket.setTextSize(22);
                waket.setText(no_wake + " time");
            }
            else
            {
                waket.setText(no_wake + " times");
                waket.setTextSize(22);

            }

        }

        if (seekBar == SleepQ)
        {
            sq = progress;
        }

        if (seekBar == AwakeQ)
        {
            awq = progress;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (v.getId()== R.id.bedh)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(bedh_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(bedh_edt.getText().toString()) > 23 || Integer.parseInt(bedh_edt.getText().toString()) < 0) {
                    bedh_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    pass.show();
                    bedh_edt.requestFocus();
                } else {

                    tempbedh = Integer.parseInt(bedh_edt.getText().toString());
                    if (tempbedh<10)
                    {
                        bedh_edt.setText("0"+tempbedh);
                    }

                    if (tempbedh != -1 && tempbedm != -1) {
                        bedtime = pad(tempbedh) + ":" + pad(tempbedm);
                    }
                    bedm_edt.requestFocus();
                }

            }


        }

        if (v.getId()== R.id.bedm)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(bedm_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(bedm_edt.getText().toString()) > 59 || Integer.parseInt(bedm_edt.getText().toString()) < 0) {
                    bedm_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                    pass.show();
                    bedm_edt.requestFocus();
                } else {
                    tempbedm = Integer.parseInt(bedm_edt.getText().toString());
                    if (tempbedm<10)
                    {
                        bedm_edt.setText("0"+tempbedm);
                    }
                    if (tempbedh != -1 && tempbedm != -1) {
                        bedtime = pad(tempbedh) + ":" + pad(tempbedm);
                    }
                    fallh_edt.requestFocus();
                }

            }

        }


        if (v.getId()== R.id.fall_h )
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(fallh_edt.getText().toString().isEmpty()))
            {
                dHour = Integer.parseInt(fallh_edt.getText().toString());
                if(dHour>23 || dHour<0)
                {
                    fallh_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    pass.show();
                    fallh_edt.requestFocus();
                }
                else
                {

                    if (dHour == 1)
                    {
                        fallh.setText("hr");
                        temp_h = "hr";
                    }
                    else
                    {
                        fallh.setText("hrs");
                        temp_h = "hrs";
                    }

                    if(dHour!= -1 && dMinute!= -1)
                    {
                        asleeptime = pad(dHour) + temp_h + pad(dMinute)+temp_m;
                    }

                    fallm_edt.requestFocus();
                }

            }


        }

        if (v.getId()== R.id.fall_m)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !( fallm_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt( fallm_edt.getText().toString()) > 59 || Integer.parseInt( fallm_edt.getText().toString()) < 0) {
                    fallm_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please input nap minute of time between 0-59!", Toast.LENGTH_LONG);
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
                    wakeh_edt.requestFocus();
                }

            }


        }

        if (v.getId()== R.id.wakeh)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(wakeh_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(wakeh_edt.getText().toString()) > 23 || Integer.parseInt(wakeh_edt.getText().toString()) < 0) {
                    wakeh_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    pass.show();
                    wakeh_edt.requestFocus();
                } else {
                    wakem_edt.requestFocus();
                    tempwakeh = Integer.parseInt(wakeh_edt.getText().toString());
                    if (tempwakeh<10)
                    {
                        wakeh_edt.setText("0"+tempwakeh);
                    }

                    if (tempwakeh != -1 && tempwakem != -1) {
                        woketime = pad(tempwakeh) + ":" + pad(tempwakem);
                        temp_wake = 100*tempwakeh+tempwakem;

                        if(temp_out-temp_wake<0)
                        {
                            Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Question 11 should be earlier than the time out of bed!", Toast.LENGTH_LONG);
                            pass.show();
                            wakeh_edt.setText("");
                            wakem_edt.setText("");
                            temp_wake = 0;
                            tempwakeh = -1;
                            tempwakem = -1;
                            wakeh_edt.requestFocus();
                        }
                    }

                }

            }

        }

        if (v.getId()== R.id.wakem)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(wakem_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(wakem_edt.getText().toString()) > 59 || Integer.parseInt(wakem_edt.getText().toString()) < 0) {
                    wakem_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                    pass.show();
                    wakem_edt.requestFocus();
                } else {
                    tempwakem = Integer.parseInt(wakem_edt.getText().toString());
                    outh_edt.requestFocus();
                    if (tempwakem<10)
                    {
                        wakem_edt.setText("0"+tempwakem);
                    }
                    if (tempwakeh != -1 && tempwakem != -1) {
                        woketime = pad(tempwakeh) + ":" + pad(tempwakem);
                        temp_wake = 100*tempwakeh+tempwakem;

                        if(temp_out-temp_wake<0)
                        {
                            Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Question 11 should be earlier than the time out of bed!", Toast.LENGTH_LONG);
                            pass.show();
                            wakeh_edt.setText("");
                            wakem_edt.setText("");
                            wakeh_edt.requestFocus();
                            temp_wake = 0;
                            tempwakeh = -1;
                            tempwakem = -1;
                        }
                    }

                }

            }
        }

        if (v.getId()== R.id.outh)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(outh_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(outh_edt.getText().toString()) > 23 || Integer.parseInt(outh_edt.getText().toString()) < 0) {
                    outh_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    pass.show();
                    outh_edt.requestFocus();
                } else {
                    outm_edt.requestFocus();
                    tempouth = Integer.parseInt(outh_edt.getText().toString());
                    if (tempouth<10)
                    {
                        outh_edt.setText("0"+tempouth);
                    }

                    if (tempouth != -1 && tempoutm != -1) {
                        outtime = pad(tempouth) + ":" + pad(tempoutm);
                        temp_out = 100*tempouth+tempoutm;

                        if(temp_out-temp_wake<0)
                        {
                            Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Question 12 should be later than woke up time!", Toast.LENGTH_LONG);
                            pass.show();
                            outh_edt.setText("");
                            outm_edt.setText("");
                            temp_out = 2400;
                            tempouth = -1;
                            tempoutm = -1;
                            outh_edt.requestFocus();
                        }
                    }

                }

            }
        }

        if (v.getId()== R.id.outm)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(wakem_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(outm_edt.getText().toString()) > 59 || Integer.parseInt(outm_edt.getText().toString()) < 0) {
                    outm_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                    pass.show();
                    outm_edt.requestFocus();
                } else {
                    tempoutm = Integer.parseInt(outm_edt.getText().toString());
                    awake_edt.requestFocus();
                    if (tempoutm<10)
                    {
                        outm_edt.setText("0"+tempoutm);
                    }
                    if (tempouth != -1 && tempoutm != -1) {
                        outtime = pad(tempouth) + ":" + pad(tempoutm);
                        temp_out = 100*tempouth+tempoutm;

                        if(temp_out-temp_wake<0)
                        {
                            Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Question 12 should be later than woke up time!", Toast.LENGTH_LONG);
                            pass.show();
                            outh_edt.setText("");
                            outm_edt.setText("");
                            temp_out = 2400;
                            tempouth = -1;
                            tempoutm = -1;
                            outh_edt.requestFocus();
                        }
                    }

                }

            }
        }

        if (v.getId()== R.id.no_awake) {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(awake_edt.getText().toString().isEmpty())) {
                no_wake = Integer.parseInt(awake_edt.getText().toString());

                if (no_wake == 1) {
                    t_awake.setText("time");
                } else {
                    t_awake.setText("times");
                }
            }
        }
        return false;
    }


//    @Override
//    public void onBackPressed() {
//    }

}



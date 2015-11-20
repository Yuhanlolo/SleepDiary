package app.sleepdiary.com.sleepdiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import android.widget.NumberPicker;
import android.support.v7.app.ActionBarActivity;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.os.Build;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import android.text.format.DateFormat;
import android.app.AlertDialog;
import android.app.DialogFragment;

/**
 * Created by Yuhan on 9/13/15.
 */
public class SleepDiaryActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    int no_coffee = 0;
    int no_wine = 0;
    int no_smoke = 0;
    int no_nap = 0;
    String sleepduration = "";
    String pilltime = "";
    String pillname = "";
    String yesterdaystr ="";
   // Spinner coffee, wine, smoke, naptime;
    SeekBar coffee,wine,nap;
    private Button  smoke;
    //private TextView displayTime;
    //private Button timeperiod;
    private Button pickTime, timeperiod;
    private TextView t_coffe;
    private TextView t_wine;
    private TextView t_nap;
    int month;
    int date;
    int year;

    private TextView yesterday;
    private EditText edtView;

    String objectID = "";
    String lastpage = "";
    RadioButton rg_1, rg_0;
    RadioGroup rg;
    int rbid1, rbid0;
    boolean pill = false;

    int dHour = 0, dMinute = 0;
    String[] hrValues = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
    String[] minuteValues = {"0","5","10","15","20","25","30","35","40","45","50","55"};
//
//    String[] time1 = {"Pick","0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
//
//    String[] time2 = {"Time","0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
//            "21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40",
//            "41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};


    final Calendar cal = Calendar.getInstance();

    int pHour = cal.get(Calendar.HOUR_OF_DAY);
    int pMinute = cal.get(Calendar.MINUTE);

    String[] tobastr = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20 or more"};

    List <ImageView>  bever = new ArrayList<ImageView>(10);
    List <ImageView>  ad = new ArrayList<ImageView>(10);
    List <ImageView>  napim = new ArrayList<ImageView>(10);

    //SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);
    ParseObject userActivity;
    ParseQuery<ParseObject> query;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepdiary);

        View myView = getWindow().getDecorView();
        //myView.setSystemUiVisibility(View.);


        // Hide the back button
        //getActionBar().setHomeAsUpIndicator(null);

//        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
//            mBaseLayout.setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
//        else if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
//            mBaseLayout.setSystemUiVisibility( View.STATUS_BAR_HIDDEN );

        Intent i_getvalue = getIntent();
        lastpage = i_getvalue.getStringExtra("lastpage");

        userActivity  = new ParseObject("Sleep_Diary");

        edtView=(EditText)findViewById(R.id.pillname);
        edtView.setInputType(InputType.TYPE_NULL);
        edtView.setActivated(false);
        edtView.setOnClickListener(this);

        rg = (RadioGroup)findViewById(R.id.group_pill);
        rg_1 = (RadioButton)findViewById(R.id.yes_pill);
        rg_0 = (RadioButton)findViewById(R.id.no_pill);
        rbid1 = rg_1.getId();
        rbid0 = rg_0.getId();

        rg.setOnCheckedChangeListener(this);

        coffee = (SeekBar)findViewById(R.id.s_coffee);
        bever.add((ImageView)findViewById(R.id.beverg0));
        bever.add((ImageView)findViewById(R.id.beverg1));
        bever.add((ImageView)findViewById(R.id.beverg2));
        bever.add((ImageView)findViewById(R.id.beverg3));
        bever.add((ImageView)findViewById(R.id.beverg4));
        bever.add((ImageView)findViewById(R.id.beverg5));
        bever.add((ImageView)findViewById(R.id.beverg6));
        bever.add((ImageView)findViewById(R.id.beverg7));
        bever.add((ImageView)findViewById(R.id.beverg8));
        bever.add((ImageView)findViewById(R.id.beverg9));
        bever.add((ImageView)findViewById(R.id.beverg10));



        wine = ( SeekBar)findViewById(R.id.s_wine);
        ad.add((ImageView) findViewById(R.id.ad0));
        ad.add((ImageView)findViewById(R.id.ad1));
        ad.add((ImageView)findViewById(R.id.ad2));
        ad.add((ImageView)findViewById(R.id.ad3));
        ad.add((ImageView)findViewById(R.id.ad4));
        ad.add((ImageView)findViewById(R.id.ad5));
        ad.add((ImageView)findViewById(R.id.ad6));
        ad.add((ImageView)findViewById(R.id.ad7));
        ad.add((ImageView)findViewById(R.id.ad8));
        ad.add((ImageView)findViewById(R.id.ad9));
        ad.add((ImageView)findViewById(R.id.ad10));

        nap = ( SeekBar)findViewById(R.id.s_nap);
        napim.add((ImageView) findViewById(R.id.nap0));
        napim.add((ImageView)findViewById(R.id.nap1));
        napim.add((ImageView)findViewById(R.id.nap2));
        napim.add((ImageView)findViewById(R.id.nap3));
        napim.add((ImageView)findViewById(R.id.nap4));
        napim.add((ImageView)findViewById(R.id.nap5));
        napim.add((ImageView)findViewById(R.id.nap6));
        napim.add((ImageView) findViewById(R.id.nap7));
        napim.add((ImageView)findViewById(R.id.nap8));
        napim.add((ImageView)findViewById(R.id.nap9));
        napim.add((ImageView)findViewById(R.id.nap10));

        smoke = (Button)findViewById(R.id.d_smoke);

        t_coffe = (TextView)findViewById(R.id.d_coffee);
        t_wine = (TextView)findViewById(R.id.d_wine);
        t_nap = (TextView)findViewById(R.id.d_nap);


        coffee.setOnSeekBarChangeListener(this);
        wine.setOnSeekBarChangeListener(this);

        nap.setOnSeekBarChangeListener(this);

        smoke.setOnClickListener(new View.OnClickListener() {

            //int numpipe = 0;
            public void onClick(View v) {

                final Dialog dialog_toba = new Dialog(SleepDiaryActivity.this);
                dialog_toba.setTitle("I used tobacco: ");

                dialog_toba.setContentView(R.layout.activity_tobacco);
                dialog_toba.show();

                NumberPicker tobaccopicker = (NumberPicker)dialog_toba.findViewById(R.id.tobaccopicker);

                tobaccopicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
                tobaccopicker.setMinValue(0);
                tobaccopicker.setMaxValue(20);
                tobaccopicker.setValue(no_smoke);
                tobaccopicker.setDisplayedValues(tobastr);

                tobaccopicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
                    @Override
                    public void onScrollStateChange(NumberPicker view, int scrollState) {
                        no_smoke = view.getValue();
                    }
                });

                Button cdt = (Button)dialog_toba.findViewById(R.id.cancel_dt);
                Button sdt = (Button)dialog_toba.findViewById(R.id.ok_dt);

                cdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog_toba.cancel();
                    }
                });


                sdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //no_smoke = numpipe ;
                        if (no_smoke == 20)
                        {
                            smoke.setText("20 or more units");
                            smoke.setTextSize(16);
                        }

                        else if (no_smoke == 1)
                        {
                            smoke.setText(no_smoke+" unit");
                            smoke.setTextSize(22);
                        }
                        else
                        {
                            smoke.setText(no_smoke+" units");
                            smoke.setTextSize(22);
                        }

                        dialog_toba.cancel();
                    }
                });


            }
        });

        //no_smoke = getIntent().getIntExtra("pipenum", 0);

        /** Listener for click event of the button */
        //displayTime = (TextView) findViewById(R.id.timeDisplay);
        pickTime = (Button) findViewById(R.id.pilltime);
        pickTime.setText("Pick Time");
        pickTime.setEnabled(false);
        pickTime.setTextColor(0xFF808080);
        //pickTime.setOnClickListener(this);


        timeperiod = (Button) findViewById(R.id.sleepdu);
        timeperiod.setText("Time Duration");
        timeperiod.setTextColor(0xFF808080);
        timeperiod.setEnabled(false);


        yesterday = (TextView)findViewById(R.id.yesterday);
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
        yesterday.setText("Sleep Diary for Yesterday (" + String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year)+")");

        /** Display the saved data */
//        ParseUser currentUser = ParseUser.getCurrentUser();
//
//        if(currentUser != null) {
//            String userid = currentUser.getUsername();
//            query.whereEqualTo("User_ID", userid);
//            query.whereEqualTo("Date", yesterday);
//
//            query.getFirstInBackground(new GetCallback<ParseObject>() {
//                public void done(ParseObject object, ParseException e) {
//                    if (object == null) {
//                        //Log.d("User_ID", "The getFirst request failed.");
//
//                    } else {
//                        //Log.d("score", "Retrieved the object.");
//                        //if(object.getInt("MBraintest")== 0 || object.getInt("MBraintest") ==1)
//
//
//                            no_coffee = object.getInt("No_Coffee");
//                            t_coffe.setText(no_coffee);
//                            coffee.setProgress(no_coffee);
//
//                            no_wine = object.getInt("No_Alcohol");
//                            t_wine.setText(no_wine);
//                            t_wine.setText(no_wine);
//                            wine.setProgress(no_wine);
//
//                            no_smoke = object.getInt("No_Tobacco");
//                            smoke.setText(no_smoke);
//
//                            no_nap = object.getInt("Nap_Time");
//                            t_nap.setText(no_nap);
//                            nap.setProgress(no_nap);
//
//
//                    }
//                }
//            });
//        }




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
            Intent i = new Intent(SleepDiaryActivity.this,SettingsActivity.class);
            SleepDiaryActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void button_SDdOnClick(View view)
    {
        if(view.getId() == R.id.save_s)
        {

            ParseUser currentUser1 = ParseUser.getCurrentUser();
            pillname = edtView.getText().toString();
            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }
//            EditText pill = (EditText)findViewById(R.id.pillname);

            else if(pill&&(pilltime.isEmpty()))
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish Question 6!", Toast.LENGTH_SHORT);
            errormsg.show();

        }
            else if (pill&&(pillname.isEmpty()))
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish Question 7!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
//            else if ((no_nap != 0)&&(sleepduration.isEmpty()))
//            {
//                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish Question 5!", Toast.LENGTH_SHORT);
//                errormsg.show();
//            }

            else
            {

//                SleepdiaryInfo s = new SleepdiaryInfo();
//                s.setNo_coffee(no_coffee);
//                s.setNo_wine(no_wine);
//
//                s.setNo_smoke(no_smoke);
//                s.setNo_naptime(no_nap)
 //               s.setSleepdurationday(sleepduration);
//                s.setPilltime(pilltime);
//                s.setPillname(pillname);
//                sleephelper.insertColumn(s);


                userActivity.put("User_ID",ParseUser.getCurrentUser().getUsername());
                userActivity.put("Date",yesterdaystr);
                userActivity.put("No_Coffee",no_coffee);
                userActivity.put("No_Alcohol",no_wine);
                userActivity.put("No_Tobacco",no_smoke);
                userActivity.put("Nap_Time",no_nap);
                userActivity.put("Nap_Duration",sleepduration);
                userActivity.put("Pill_Time",pilltime);
                userActivity.put("Pill_Name",pillname);

                userActivity.put("Bed_Time", "");
                userActivity.put("Sleep_Duration","");
                userActivity.put("Wake_Time","");
                userActivity.put("OutofBed_Time","");
                userActivity.put("No_Awakenings",0);
                userActivity.put("Sleep_Quality",0);
                userActivity.put("Awake_Quality",0);

                userActivity.put("Urge_move","");
                userActivity.put("Muscle_cramp", "");
                userActivity.put("Difficulty_turn_bed", "");
                userActivity.put("Pain", "");
                userActivity.put("distressDream", "");
                userActivity.put("Visual_hallucinations", "");
                userActivity.put("Difficulty_Breath", "");
                userActivity.put("Pass_Urine", "");
                userActivity.put("Enviro_Disturbance", "");
                userActivity.pinInBackground();

                //userActivity.saveInBackground();

                userActivity.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e!=null)
                        {
                            Toast pass = Toast.makeText(SleepDiaryActivity.this,"Error: "+e.getMessage(), Toast.LENGTH_SHORT);
                            pass.show();
                        }
                        else{
                             objectID = userActivity.getObjectId();
//                             Toast pass = Toast.makeText(SleepDiaryActivity.this,"id 1: "+objectID, Toast.LENGTH_SHORT);
//                             pass.show();
                            Intent i = new Intent(SleepDiaryActivity.this,SleepDiaryActivity2.class);
                            i.putExtra("lastpage",lastpage);
                            i.putExtra("objectID",objectID);
                            SleepDiaryActivity.this.startActivity(i);
                        }
                    }
                });



//                Intent i = new Intent(SleepDiaryActivity.this,SleepDiaryActivity2.class);
//                i.putExtra("objectID",objectID);
//                SleepDiaryActivity.this.startActivity(i);

            }
        }

//        if(view.getId() == R.id.cancel_s)
//        {
//            Intent i = new Intent(SleepDiaryActivity.this,SleepActivity.class);
//            i.putExtra("lastpage",lastpage);
//            SleepDiaryActivity.this.startActivity(i);
//        }


    }



    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar == coffee)
        {
            no_coffee =progress;
            for (int bc = 0; bc<11;bc++)
            {
                if(bc == no_coffee)
                    continue;
                bever.get(bc).setVisibility(View.INVISIBLE);
            }

            bever.get(no_coffee).setVisibility(View.VISIBLE);

            if(no_coffee ==10)
        {
            t_coffe.setTextSize(16);
            t_coffe.setText("10 or more glasses");

        }
            else if(no_coffee == 1)
            {
                t_coffe.setText(no_coffee + " glass");
                t_coffe.setTextSize(22);
            }

            else
            {
                t_coffe.setText(no_coffee + " glasses");
                t_coffe.setTextSize(22);

            }
        }

        else if(seekBar == wine)
        {
            no_wine = progress;
            for (int bc = 0; bc<11;bc++)
            {
                if(bc == no_wine)
                    continue;
                ad.get(bc).setVisibility(View.INVISIBLE);
            }
            ad.get(progress).setVisibility(View.VISIBLE);

            if(no_wine ==10)
            {
                t_wine.setTextSize(16);
                t_wine.setText("10 or more glasses");

            }
            else if(no_wine ==1)
            {
                t_wine.setText(no_wine + " glass");
                t_wine.setTextSize(22);

            }
            else
            {
                t_wine.setText(no_wine + " glasses");
                t_wine.setTextSize(22);
            }
        }

        else if(seekBar == nap)
        {
            no_nap = progress;
            for (int bc = 0; bc<11;bc++)
            {
                if(bc == no_nap)
                    continue;
                napim.get(bc).setVisibility(View.INVISIBLE);
            }
            napim.get(progress).setVisibility(View.VISIBLE);

            if(no_nap ==10)
            {
                t_nap.setTextSize(16);
                t_nap.setText("10 or more glasses");

            }
            else if(no_nap == 1)
            {
                t_nap.setText(no_nap + " time");
                t_nap.setTextSize(22);

            } else
            {
                t_nap.setText(no_nap + " times");
                t_nap.setTextSize(22);
            }

            if(no_nap>0)
            {
            timeperiod.setTextColor(0xFF000000);
            timeperiod.setEnabled(true);
            }
            else
            {
                timeperiod.setTextColor(0xFF808080);
                timeperiod.setText("Time Duration");
                sleepduration  = "";
                timeperiod.setEnabled(false);
            }
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }



//    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
//            new TimePickerDialog.OnTimeSetListener() {
//                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    pHour = hourOfDay;
//                    pMinute = minute;
//                    edtView.setInputType(1);
//                    edtView.setActivated(true);
//                    updateDisplay();
//                    //displayToast();
//
//                }
//
//            };
//
//    private TimePickerDialog.OnTimeSetListener dTimeSetListener =
//            new TimePickerDialog.OnTimeSetListener() {
//                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    pHour = hourOfDay;
//                   // if (pHour == 0){pHour = 12;}
//                    pMinute = minute;
//                    updateDisplay2();
//                   // displayToast2();
//
//                }
//
//            };

    /** Updates the time in the TextView */
    private void updateDisplay() {

        pilltime = pad(pHour) + ":" + pad(pMinute);

        //Toast pass = Toast.makeText(SleepDiaryActivity.this, am_pm, Toast.LENGTH_SHORT);
        //pass.show();
            pickTime.setText(
                    new StringBuilder()
                            .append(pad(pHour)).append(":")
                            .append(pad(pMinute)));


    }

    private void updateDisplay2() {

            sleepduration = String.valueOf(dHour) + ":" + Integer.toString(dMinute);
            int temp_duration = 100*dHour + dMinute;
//            if(temp_duration>1200)
//            {
//                Toast pass = Toast.makeText(SleepDiaryActivity.this,"You napped over 12 hours!", Toast.LENGTH_SHORT);
//                timeperiod.setText("Time Period");
//                pass.show();
//            }

                String ast = "";
                if(dHour >1 && dMinute>1)
                {
                    ast= Integer.toString(dHour)+ " hrs"+" "+Integer.toString(dMinute)+" mins";
                }
                else if (dHour==0 && (dMinute ==0)||(dMinute==1))
                {
                    ast= Integer.toString(dMinute)+" min";
                }
                else if (dHour ==0 && dMinute>1)
                {
                    ast= Integer.toString(dMinute)+" mins";
                }

                else if (dHour==1 &&(dMinute ==0))
                {
                    ast= Integer.toString(dHour)+ " hr";
                }

                else if (dHour==1 &&(dMinute ==1))
                {
                    ast= Integer.toString(dHour)+ " hr"+" "+Integer.toString(dMinute)+" min";
                }
                else if (dHour==1 &&dMinute>1)
                {
                    ast= Integer.toString(dHour)+ " hr"+" "+Integer.toString(dMinute)+" mins";
                }
                else if (dHour>1 &&(dMinute ==0))
                {
                    ast= Integer.toString(dHour)+ " hrs";
                }
                else if (dHour>1 &&(dMinute ==1))
                {
                    ast= Integer.toString(dHour)+ " hrs"+" "+Integer.toString(dMinute)+" min";
                }
                timeperiod.setTextSize(18);
                timeperiod.setText(ast);

    }

    /** Displays a notification when the time is updated */
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(pickTime.getText()),   Toast.LENGTH_SHORT).show();

    }




    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    /** Create a new dialog for time picker */

//    @Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//
//            case TIME_PERIOD:
//            {
//                final Dialog Timepicker2 = new Dialog(SleepDiaryActivity.this);
//                Timepicker2.setTitle("");
//
//                Timepicker2.setContentView(R.layout.scrolltimepicker);
//                //Timepicker2.show();
//            }
//        }
//        return null;
//    }

    public void button_tpOnClick(View v) {

        if(v.getId()==R.id.pilltime)
        {
            final Dialog Numberpicker1 = new Dialog(SleepDiaryActivity.this);
            Numberpicker1.setTitle("I took pills at: ");

            Numberpicker1.setContentView(R.layout.numberpicker);
            Numberpicker1.setCanceledOnTouchOutside(false);
            Numberpicker1.show();



            NumberPicker td1 = (NumberPicker)Numberpicker1.findViewById(R.id.td1);
            NumberPicker td2 = (NumberPicker)Numberpicker1.findViewById(R.id.td2);

            td1.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
            td2.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);

            td1.setMaxValue(23);
            td1.setMinValue(0);
            td1.setValue(pHour);
            td1.setDisplayedValues(hrValues);

            td2.setMaxValue(11);
            td2.setMinValue(0);
            td2.setDisplayedValues(minuteValues);
            td2.setValue(pMinute/5);


            Button ctd = (Button)Numberpicker1.findViewById(R.id.cancel_td);
            Button otd = (Button)Numberpicker1.findViewById(R.id.ok_td);

            td1.setOnScrollListener(new NumberPicker.OnScrollListener() {
                @Override
                public void onScrollStateChange(NumberPicker view, int scrollState) {
                    pHour = view.getValue();

                }
            });

            td2.setOnScrollListener(new NumberPicker.OnScrollListener() {
                @Override
                public void onScrollStateChange(NumberPicker view, int scrollState) {

                    pMinute = view.getValue()*5;

                }
            });

            ctd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Numberpicker1.cancel();
                }
            });


            otd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    updateDisplay();

                    Numberpicker1.cancel();
                }
            });

        }

        if (v.getId() == R.id.sleepdu)
        {
            final Dialog Numberpicker1 = new Dialog(SleepDiaryActivity.this);


            Numberpicker1.setContentView(R.layout.numberpicker);
            Numberpicker1.setCanceledOnTouchOutside(false);
            Numberpicker1.setTitle("In total, I napped for: ");
            Numberpicker1.show();



            NumberPicker td1 = (NumberPicker)Numberpicker1.findViewById(R.id.td1);
            NumberPicker td2 = (NumberPicker)Numberpicker1.findViewById(R.id.td2);

            td1.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
            td2.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);

            td1.setMaxValue(23);
            td1.setMinValue(0);
            td1.setValue(dHour);

            td2.setMaxValue(11);
            td2.setMinValue(0);
            td2.setDisplayedValues(minuteValues);
            td2.setValue(dMinute/5);

//            td1.setDisplayedValues(time1);
//            td2.setDisplayedValues(time2);

            Button ctd = (Button)Numberpicker1.findViewById(R.id.cancel_td);
            Button otd = (Button)Numberpicker1.findViewById(R.id.ok_td);

           td1.setOnScrollListener(new NumberPicker.OnScrollListener() {
               @Override
               public void onScrollStateChange(NumberPicker view, int scrollState) {
                   dHour = view.getValue();
//                   NumberPicker td2_temp = (NumberPicker)Numberpicker1.findViewById(R.id.td2);
//                   td2_temp.setValue(0);

               }
           });

            td2.setOnScrollListener(new NumberPicker.OnScrollListener() {
                @Override
                public void onScrollStateChange(NumberPicker view, int scrollState) {
                    //dMinute = Integer.parseInt(minuteValues[view.getMinValue()]);
                    dMinute = view.getValue()*5;
//                    NumberPicker td1_temp = (NumberPicker)Numberpicker1.findViewById(R.id.td1);
//                    td1_temp.setValue(0);
                }
            });

            ctd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Numberpicker1.cancel();
                }
            });


            otd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        updateDisplay2();

                    Numberpicker1.cancel();
                }
            });


        }

    }


    @Override
    public void onClick(View v) {

        if (v.getId()== R.id.pillname)
        {
            if (!pill)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this, "Did you take sleeping pill? Please select yes in Question 5 if you did", Toast.LENGTH_LONG);
                pass.show();
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group == rg)
        {
            if(checkedId == rbid1)
            {
                pickTime.setEnabled(true);
                pickTime.setTextColor(Color.BLACK);
                edtView.setInputType(1);
                edtView.setActivated(true);
                pill = true;
            }
            else {
                pill = false;
                pickTime.setEnabled(false);
                pickTime.setTextColor(0xFF808080);
                pilltime = "";
                pillname= "";
                pickTime.setText("Pick Time");
                edtView.setText("");
                edtView.setInputType(0);

            }
        }
    }


}


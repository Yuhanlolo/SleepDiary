package app.sleepdiary.com.sleepdiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import android.text.format.DateFormat;
import android.app.AlertDialog;
import android.app.DialogFragment;

/**
 * Created by Yuhan on 9/13/15.
 */
public class SleepDiaryActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener{

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
    private Spinner spinner_td ;
    private Button  smoke;
    //private TextView displayTime;
    private Button timeperiod;
    private Button pickTime;
    private TextView t_coffe;
    private TextView t_wine;
    private TextView t_smoke;
    private TextView t_nap;
    private int pHour;
    private int pMinute;
    int month;
    int date;
    int year;

    private TextView yesterday;
    private EditText edtView;

    String objectID = "";
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_ID = 0;
    static final int TIME_PERIOD = 1;

    final Context context = this;

    List <ImageView>  bever = new ArrayList<ImageView>(10);
    //ImageView bever0, bever1,bever2, bever3,bever4, bever5,bever6, bever7,bever8, bever9,bever10;

    //SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);
    ParseObject userActivity  = new ParseObject("UserActivity");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepdiary);

        edtView=(EditText)findViewById(R.id.pillname);
        edtView.setInputType(InputType.TYPE_NULL);
        edtView.setActivated(false);


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

//        bever1 = (ImageView)findViewById(R.id.beverg1);
//        bever2 = (ImageView)findViewById(R.id.beverg2);
//        bever3 = (ImageView)findViewById(R.id.beverg3);
//        bever4 = (ImageView)findViewById(R.id.beverg4);
//        bever5 = (ImageView)findViewById(R.id.beverg5);
//        bever6 = (ImageView)findViewById(R.id.beverg6);
//        bever7 = (ImageView)findViewById(R.id.beverg7);
//        bever8 = (ImageView)findViewById(R.id.beverg8);
//        bever9 = (ImageView)findViewById(R.id.beverg9);
//        bever10 = (ImageView)findViewById(R.id.beverg10);


        wine = ( SeekBar)findViewById(R.id.s_wine);

        nap = ( SeekBar)findViewById(R.id.s_nap);
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
                dialog_toba.setTitle("");

                dialog_toba.setContentView(R.layout.activity_tobacco);
                dialog_toba.show();

                spinner_td = (Spinner)dialog_toba.findViewById(R.id.tdspinner);

                spinner_td.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        no_smoke = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

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
                        if (no_smoke > 0)
                        {
                            smoke.setText(no_smoke+" Pipes");
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
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        timeperiod = (Button) findViewById(R.id.sleepdu);
        timeperiod.setText("Time Period");
        timeperiod.setTextColor(0xFF808080);
        timeperiod.setEnabled(false);
        timeperiod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_PERIOD);
            }
        });




        yesterday = (TextView)findViewById(R.id.yesterday);
        /** Get the current time */
        final Calendar cal = Calendar.getInstance();
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

        /** Display the current time in the TextView */
       // updateDisplay();


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

//            EditText pill = (EditText)findViewById(R.id.pillname);
             pillname = edtView.getText().toString();

            if((!pilltime.isEmpty()&&pillname.isEmpty()))
            {

                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish Question 5!", Toast.LENGTH_SHORT);
                errormsg.show();

            }
            else if ((no_nap != 0)&&(sleepduration.isEmpty()))
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish Question 7!", Toast.LENGTH_SHORT);
                errormsg.show();
            }

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

        if(view.getId() == R.id.cancel_s)
        {
            Intent i = new Intent(SleepDiaryActivity.this,SleepActivity.class);
            SleepDiaryActivity.this.startActivity(i);
        }


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
            else if(no_coffee>1)
            {
                t_coffe.setText(no_coffee + " glasses");
                t_coffe.setTextSize(22);

            }

            else
            {
                t_coffe.setText(no_coffee + " glass");
                t_coffe.setTextSize(22);

            }
        }

        else if(seekBar == wine)
        {
            no_wine = progress;

            if(no_wine ==10)
            {
                t_wine.setTextSize(16);
                t_wine.setText("10 or more glasses");

            }
            if(no_wine>1)
            {
                t_wine.setText(no_wine + " glasses");
                t_wine.setTextSize(22);
            }
            else
            {
                t_wine.setText(no_wine + " glass");
                t_wine.setTextSize(22);
            }
        }
//        else if(seekBar == smoke)
//        {
//            no_smoke = progress;
//            if(no_smoke>1)
//            {
//                t_smoke.setText(no_smoke + " pipes");
//            }
//            else
//            {
//            t_smoke.setText(no_smoke + " pipe");
//            }
//        }
        else if(seekBar == nap)
        {
            no_nap = progress;

            if(no_nap ==10)
            {
                t_nap.setTextSize(16);
                t_nap.setText("10 or more glasses");

            }
            if(no_nap>1)
            {
                t_nap.setText(no_nap + " times");
                t_nap.setTextSize(22);
            } else if(no_nap == 0 || no_nap == 1)
            {
                t_nap.setText(no_nap + " time");
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
                timeperiod.setText("Time Period");
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



    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    edtView.setInputType(1);
                    edtView.setActivated(true);
                    updateDisplay();
                    //displayToast();

                }

            };

    private TimePickerDialog.OnTimeSetListener dTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                   // if (pHour == 0){pHour = 12;}
                    pMinute = minute;
                    updateDisplay2();
                   // displayToast2();

                }

            };

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

            sleepduration = String.valueOf(pHour) + ":" + pad(pMinute);
            int temp_duration = 100*pHour + pMinute;
            if(temp_duration>1200)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this,"You napped over 12 hours!", Toast.LENGTH_SHORT);
                timeperiod.setText("Time Period");
                pass.show();
            }
            else {
                String ast = "";
                if(pHour >1 && pMinute>1)
                {
                    ast= pad(pHour)+ " hrs"+" "+pad(pMinute)+" mins";
                }
                if (pHour==0 && (pMinute ==0)||(pMinute==1))
                {
                    ast= pad(pMinute)+" min";
                }
                else if (pHour ==0 && pMinute>1)
                {
                    ast= pad(pMinute)+" mins";
                }

                else if (pHour==1 &&(pMinute ==0))
                {
                    ast= pad(pHour)+ " hr";
                }

                else if (pHour==1 &&(pMinute ==1))
                {
                    ast= pad(pHour)+ " hr"+" "+pad(pMinute)+" min";
                }
                else if (pHour==1 &&pMinute>1)
                {
                    ast= pad(pHour)+ " hr"+" "+pad(pMinute)+" mins";
                }
                else if (pHour>1 &&(pMinute ==0))
                {
                    ast= pad(pHour)+ " hrs";
                }
                else if (pHour>1 &&(pMinute ==1))
                {
                    ast= pad(pHour)+ " hrs"+" "+pad(pMinute)+" min";
                }
                timeperiod.setTextSize(18);
                timeperiod.setText(ast);
            }
    }

    /** Displays a notification when the time is updated */
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(pickTime.getText()),   Toast.LENGTH_SHORT).show();

    }


    private void displayToast2() {
        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(timeperiod.getText()),   Toast.LENGTH_SHORT).show();

    }


    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    /** Create a new dialog for time picker */

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, true);
            case TIME_PERIOD:
                return new TimePickerDialog(this,
                        dTimeSetListener, pHour, pMinute, true);
        }
        return null;
    }



}


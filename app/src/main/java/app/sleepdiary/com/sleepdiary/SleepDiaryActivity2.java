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
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
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

public class SleepDiaryActivity2 extends ActionBarActivity implements OnSeekBarChangeListener{

    private Button Bbedtime;
    private Button Basleeptime;
    private Button Bwoketime;
    private Button Bouttime;
    SeekBar Snowake, SleepQ, AwakeQ;
    private TextView waket;

    TextView lastnight;
    int month;
    int date;
    int year;

    int temp_bed_h = 22;
    int temp_bed_m = 0;
    int temp_wake_h = -1;
    int temp_wake_m = -1;
    int temp_asleep_h = -1;
    int temp_asleep_m = -1;

      //int temp_bed = -1;
      //int temp_asleep = -1;
      int temp_wake= -1;
      int temp_out = 2400;

    int dHour = 0, dMinute = 0;


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

    String bedtime = "22:00";
    String asleeptime = "";
    String woketime = "";
    String outtime = "";
    int no_wake = 0;

    String objectID = "";

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

        query = ParseQuery.getQuery("Sleep_Diary");
        objectID = getIntent().getStringExtra("objectID");

//        Toast pass = Toast.makeText(SleepDiaryActivity2.this, "id 2: "+objectID, Toast.LENGTH_SHORT);
//        pass.show();

        Bbedtime = (Button) findViewById(R.id.bedt);
        Bbedtime.setText("22:00");
//        Bbedtime.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                showDialog(TIME_DIALOG_0);
//            }
//        });

        Basleeptime = (Button) findViewById(R.id.asleept);
        Basleeptime.setText("Time Period");


        //Basleeptime.setEnabled(false);
        //Basleeptime.setTextColor(0xFF808080);
//        Basleeptime.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                showDialog(TIME_DIALOG_1);
//            }
//        });

        Bwoketime = (Button) findViewById(R.id.woket);
        Bwoketime.setText("Pick Time");
        //Bwoketime.setEnabled(false);
        //Bwoketime.setTextColor(0xFF808080);
//        Bwoketime.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                showDialog(TIME_DIALOG_2);
//            }
//        });

        Bouttime = (Button) findViewById(R.id.ofbed);
        Bouttime.setText("Pick Time");
        //Bouttime.setEnabled(false);
        //Bouttime.setTextColor(0xFF808080);
//        Bouttime.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                showDialog(TIME_DIALOG_3);
//            }
//        });

        Snowake = (SeekBar)findViewById(R.id.wakeno);
        Snowake.setOnSeekBarChangeListener(this);

        awim.add((ImageView) findViewById(R.id.aw0));
        awim.add((ImageView)findViewById(R.id.aw1));
        awim.add((ImageView)findViewById(R.id.aw2));
        awim.add((ImageView)findViewById(R.id.aw3));
        awim.add((ImageView)findViewById(R.id.aw4));
        awim.add((ImageView)findViewById(R.id.aw5));
        awim.add((ImageView)findViewById(R.id.aw6));
        awim.add((ImageView)findViewById(R.id.aw7));
        awim.add((ImageView)findViewById(R.id.aw8));
        awim.add((ImageView)findViewById(R.id.aw9));
        awim.add((ImageView)findViewById(R.id.aw10));

        SleepQ = (SeekBar)findViewById(R.id.sleepquality);
        SleepQ.setOnSeekBarChangeListener(this);

        AwakeQ = (SeekBar)findViewById(R.id.awakequality);
        AwakeQ.setOnSeekBarChangeListener(this);

        waket = (TextView)findViewById(R.id.d_wake);


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

        if(view.getId() == R.id.save_s2)
        {

            ParseUser currentUser1 = ParseUser.getCurrentUser();
            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity2.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else if(bedtime.isEmpty())
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 8!", Toast.LENGTH_SHORT);
                errormsg.show();

            }
            else if (asleeptime.isEmpty())
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 8!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (woketime.isEmpty())
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 9!", Toast.LENGTH_SHORT);
                errormsg.show();
            }

            else if (outtime.isEmpty())
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 10!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (sq==-1)
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 12!", Toast.LENGTH_SHORT);
                errormsg.show();
            }

            else if (awq==-1)
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 13!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else
            {
                //query.whereEqualTo("User_ID",objectID);
                query.getInBackground(objectID, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e != null) {
                            Toast pass = Toast.makeText(SleepDiaryActivity2.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
                            object.put("Bed_Time", bedtime);
                            object.put("Sleep_Duration",asleeptime);
                            object.put("Wake_Time",woketime);
                            object.put("OutofBed_Time",outtime);
                            object.put("No_Awakenings",no_wake);
                            object.put("Sleep_Quality",sq);
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

                Intent i = new Intent(SleepDiaryActivity2.this,SleepDiaryActivity3.class);
                i.putExtra("objectID",objectID);
                SleepDiaryActivity2.this.startActivity(i);

            }
        }

        else if(view.getId() == R.id.cancel_s2)
        {
            Intent i = new Intent(SleepDiaryActivity2.this,SleepActivity.class);
            SleepDiaryActivity2.this.startActivity(i);
        }


    }



//    private TimePickerDialog.OnTimeSetListener bedTimeSetListener =
//            new TimePickerDialog.OnTimeSetListener() {
//                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    pHour = hourOfDay;
//                    pMinute = minute;
//                    updateDisplay0();
//                }
//
//            };
//
//    private TimePickerDialog.OnTimeSetListener asleepTimeSetListener=
//            new TimePickerDialog.OnTimeSetListener() {
//                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    pHour = hourOfDay;
//                    pMinute = minute;
//                   // if (pHour == 0){pHour = 12;}
//                    updateDisplay1();
//                }
//
//            };
//
//    private TimePickerDialog.OnTimeSetListener wokeTimeSetListener =
//            new TimePickerDialog.OnTimeSetListener() {
//                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    pHour = hourOfDay;
//                    pMinute = minute;
//                    updateDisplay2();
//
//                }
//
//            };
//
//    private TimePickerDialog.OnTimeSetListener ofbedTimeSetListener=
//            new TimePickerDialog.OnTimeSetListener() {
//                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    pHour = hourOfDay;
//                    pMinute = minute;
//                    updateDisplay3();
//                }
//
//            };

    /** Updates the time in the TextView */
    private void updateDisplay0() {
        temp_bed_h = bedhour;
        temp_bed_m = bedmin;


        //temp_bed = 100*pHour + pMinute;
        bedtime = pad(bedhour) + ":" + pad(bedmin);

            Bbedtime.setText(
                    new StringBuilder()
                            .append(pad(bedhour)).append(":")
                            .append(pad(bedmin)));


        Basleeptime.setEnabled(true);
        Basleeptime.setTextColor(0xFF000000);


    }
    private void updateDisplay1() {
        asleeptime = String.valueOf(dHour) + ":" + Integer.toString(dMinute);
        Basleeptime.setTextSize(20);
        temp_asleep_h = dHour;
        temp_asleep_m = dMinute;
        //temp_asleep = 100*pHour + pMinute;

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

        Basleeptime.setText(ast);
        Basleeptime.setTextSize(18);

        Bwoketime.setEnabled(true);
        Bwoketime.setTextColor(0xFF000000);

    }

    private void updateDisplay2() {

        woketime = pad(wokehour) + ":" + pad(wokemin);

        temp_wake_h = wokehour;
        temp_wake_m = wokemin;
        temp_wake = 100*wokehour+wokemin;

        if(temp_bed_h == 0)
        {
            temp_bed_h = 24;
        }

        if (temp_wake>temp_out)
        {
            Toast pass = Toast.makeText(SleepDiaryActivity2.this,"Question 10 should be earlier than the time you get out of bed!", Toast.LENGTH_LONG);
            Bwoketime.setText("Pick Time");
            //for(int x = 0; x<2000;x++)//delay the notification
            pass.show();
        }

        else if((temp_bed_h < 12)&&((temp_wake_h-temp_bed_h >12)||(temp_wake_h-temp_bed_h ==12)&&(temp_wake_m>temp_bed_m)))
        {
            Toast pass = Toast.makeText(SleepDiaryActivity2.this,"You slept over 12 hours!", Toast.LENGTH_SHORT);
            Bwoketime.setText("Pick Time");
            pass.show();
        }


        else if(((temp_bed_h>12)||(temp_bed_h==12))&&((temp_wake_h+24-temp_bed_h>12)||(temp_wake_h+24-temp_bed_h ==12)&&(temp_wake_m>temp_bed_m)))
        {

            Toast pass = Toast.makeText(SleepDiaryActivity2.this,"You slept over 12 hours!", Toast.LENGTH_SHORT);
            Bwoketime.setText("Pick Time");
            pass.show();

        }


        else {
                    Bwoketime.setText(
                            new StringBuilder()
                                    .append(pad(wokehour)).append(":")
                                    .append(pad(wokemin)));


                Bouttime.setEnabled(true);
                Bouttime.setTextColor(0xFF000000);
            }
    }

    private void updateDisplay3() {

        outtime = pad(outhour) + ":" + pad(outmin);
        temp_out = 100*outhour + outmin;

        if(temp_out<temp_wake)
        {
            Toast pass = Toast.makeText(SleepDiaryActivity2.this,"Question 11 should be later than the wake up time!", Toast.LENGTH_LONG);
            Bouttime.setText("Pick Time");
            //for(int x = 0; x<2000;x++)//delay the notification
            pass.show();
        }

        else {

            outtime = pad(outhour) + ":" + pad(outmin);

                Bouttime.setText(
                        new StringBuilder()
                                .append(pad(outhour)).append(":")
                                .append(pad(outmin)));

        }
    }


    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case TIME_DIALOG_0:
//                return new TimePickerDialog(this,
//                        bedTimeSetListener, pHour, pMinute, true);
//            case TIME_DIALOG_1:
//                return new TimePickerDialog(this,
//                        asleepTimeSetListener, pHour, pMinute, true);
//            case TIME_DIALOG_2:
//                return new TimePickerDialog(this,
//                        wokeTimeSetListener, pHour, pMinute, true);
//            case TIME_DIALOG_3:
//                return new TimePickerDialog(this,
//                        ofbedTimeSetListener, pHour, pMinute, true);
//        }
//        return null;
//    }

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
            else if(no_wake>1)
            {
                waket.setText(no_wake + " times");
                waket.setTextSize(22);}
            else
            {
                waket.setTextSize(22);
                waket.setText(no_wake + " time");
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

    public void button_sl2OnClick(View view)
    {
        if (view.getId() == R.id.bedt)
        {
            final Dialog Timepicker1 = new Dialog(SleepDiaryActivity2.this);
            Timepicker1.setTitle("I went to bed at:");

            Timepicker1.setContentView(R.layout.scrolltimepicker);
            Timepicker1.setCanceledOnTouchOutside(false);
            Timepicker1.show();

            TimePicker tp = (TimePicker)Timepicker1.findViewById(R.id.tp);
            tp.setIs24HourView(true);
            Button ctp = (Button)Timepicker1.findViewById(R.id.cancel_tp);
            Button otp = (Button)Timepicker1.findViewById(R.id.ok_tp);



            tp.setCurrentHour(bedhour);
            tp.setCurrentMinute(bedmin);
            //Set a TimeChangedListener for TimePicker widget
            tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    bedhour = hourOfDay;
                    bedmin = minute;
                }
            });

            ctp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Timepicker1.cancel();
                }
            });


            otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    updateDisplay0();

                    Timepicker1.cancel();
                }
            });

        }

        if (view.getId() == R.id.asleept)
        {
            final Dialog Numberpicker1 = new Dialog(SleepDiaryActivity2.this);
            Numberpicker1.setTitle("Time to fall asleep:");

            Numberpicker1.setContentView(R.layout.numberpicker);
            Numberpicker1.setCanceledOnTouchOutside(false);
            Numberpicker1.show();

//            Numberpicker1.getWindow().setSoftInputMode(
//                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
//            );

            NumberPicker td1 = (NumberPicker)Numberpicker1.findViewById(R.id.td1);
            NumberPicker td2 = (NumberPicker)Numberpicker1.findViewById(R.id.td2);

            td1.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
            td2.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);

            td1.setMaxValue(24);
            td1.setMinValue(0);
            td1.setValue(dHour);

            td2.setMaxValue(11);
            td2.setMinValue(0);
            td2.setValue(dMinute / 5);

            td2.setDisplayedValues(minuteValues);

            Button ctd = (Button)Numberpicker1.findViewById(R.id.cancel_td);
            Button otd = (Button)Numberpicker1.findViewById(R.id.ok_td);

            td1.setOnScrollListener(new NumberPicker.OnScrollListener() {
                @Override
                public void onScrollStateChange(NumberPicker view, int scrollState) {
                    dHour = view.getValue();

                }
            });

            td2.setOnScrollListener(new NumberPicker.OnScrollListener() {
                @Override
                public void onScrollStateChange(NumberPicker view, int scrollState) {
                    //dMinute = Integer.parseInt(minuteValues[view.getMinValue()]);
                    dMinute = view.getValue()*5;
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


                    updateDisplay1();

                    Numberpicker1.cancel();
                }
            });
        }

        if (view.getId() == R.id.woket)
        {
            final Dialog Timepicker1 = new Dialog(SleepDiaryActivity2.this);
            Timepicker1.setTitle("I woke up at: ");

            Timepicker1.setContentView(R.layout.scrolltimepicker);
            Timepicker1.setCanceledOnTouchOutside(false);
            Timepicker1.show();

            TimePicker tp = (TimePicker)Timepicker1.findViewById(R.id.tp);
            tp.setIs24HourView(true);
            Button ctp = (Button)Timepicker1.findViewById(R.id.cancel_tp);
            Button otp = (Button)Timepicker1.findViewById(R.id.ok_tp);


            tp.setCurrentHour(wokehour);
            tp.setCurrentMinute(wokemin);
            //Set a TimeChangedListener for TimePicker widget
            tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    wokehour = hourOfDay;
                    wokemin = minute;
                }
            });



            ctp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Timepicker1.cancel();
                }
            });


            otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    updateDisplay2();
                    outhour = wokehour;
                    outmin = wokemin;

                    Timepicker1.cancel();
                }
            });

        }

        if (view.getId() == R.id.ofbed)
        {
            final Dialog Timepicker1 = new Dialog(SleepDiaryActivity2.this);
            Timepicker1.setTitle("I got out of bed at: ");

            Timepicker1.setContentView(R.layout.scrolltimepicker);
            Timepicker1.setCanceledOnTouchOutside(false);
            Timepicker1.show();

            TimePicker tp = (TimePicker)Timepicker1.findViewById(R.id.tp);
            tp.setIs24HourView(true);
            Button ctp = (Button)Timepicker1.findViewById(R.id.cancel_tp);
            Button otp = (Button)Timepicker1.findViewById(R.id.ok_tp);

            tp.setCurrentHour(outhour);
            tp.setCurrentMinute(outmin);


            //Set a TimeChangedListener for TimePicker widget
            tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    outhour = hourOfDay;
                    outmin = minute;
                }
            });

            ctp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Timepicker1.cancel();
                }
            });


            otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    updateDisplay3();

                    Timepicker1.cancel();
                }
            });

        }


    }

//    @Override
//    public void onBackPressed() {
//    }

}



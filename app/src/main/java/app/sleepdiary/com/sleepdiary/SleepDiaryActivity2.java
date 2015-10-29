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
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
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
    SeekBar Snowake;
    private TextView waket;
    private int pHour;
    private int pMinute;
    TextView lastnight;
    int month;
    int date;
    int year;

    int temp_bed_h = -1;
    int temp_bed_m = -1;
    int temp_wake_h = -1;
    int temp_wake_m = -1;
    int temp_asleep_h = -1;
    int temp_asleep_m = -1;

      //int temp_bed = -1;
      //int temp_asleep = -1;
      int temp_wake = -1;
      int temp_out = -1;



    private ImageView SQ1_g;
    private ImageView SQ1;
    private ImageView SQ2_g;
    private ImageView SQ2;
    private ImageView SQ3_g;
    private ImageView SQ3;
    private ImageView SQ4_g;
    private ImageView SQ4;
    private ImageView SQ5_g;
    private ImageView SQ5;

    private ImageView AWQ1_g;
    private ImageView AWQ1;
    private ImageView AWQ2_g;
    private ImageView AWQ2;
    private ImageView AWQ3_g;
    private ImageView AWQ3;
    private ImageView AWQ4_g;
    private ImageView AWQ4;
    private ImageView AWQ5_g;
    private ImageView AWQ5;

    int sq = 0;
    int awq = 0;

    String bedtime = "22:00";
    String asleeptime = "";
    String woketime = "06:00";
    String outtime = "";
    int no_wake = 0;

    String objectID = "";
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_0 = 0;
    static final int TIME_DIALOG_1 = 1;
    static final int TIME_DIALOG_2 = 2;
    static final int TIME_DIALOG_3 = 3;

    List<ImageView> awim = new ArrayList<ImageView>(10);
    //SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);
    //ParseObject userActivity = new ParseObject("UserActivity");
    ParseQuery <ParseObject>  query = ParseQuery.getQuery("UserActivity");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2sleepdiary);


        objectID = getIntent().getStringExtra("objectID");

//        Toast pass = Toast.makeText(SleepDiaryActivity2.this, "id 2: "+objectID, Toast.LENGTH_SHORT);
//        pass.show();

        Bbedtime = (Button) findViewById(R.id.bedt);
        Bbedtime.setText("22:00");
        Bbedtime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_0);
            }
        });

        Basleeptime = (Button) findViewById(R.id.asleept);
        Basleeptime.setText("Time Period");
        //Basleeptime.setEnabled(false);
        //Basleeptime.setTextColor(0xFF808080);
        Basleeptime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_1);
            }
        });

        Bwoketime = (Button) findViewById(R.id.woket);
        Bwoketime.setText("06:00");
        //Bwoketime.setEnabled(false);
        //Bwoketime.setTextColor(0xFF808080);
        Bwoketime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_2);
            }
        });

        Bouttime = (Button) findViewById(R.id.ofbed);
        Bouttime.setText("Pick Time");
        //Bouttime.setEnabled(false);
        //Bouttime.setTextColor(0xFF808080);
        Bouttime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_3);
            }
        });

        Snowake = (SeekBar)findViewById(R.id.wakeno);
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
        Snowake.setOnSeekBarChangeListener(this);

        waket = (TextView)findViewById(R.id.d_wake);


        SQ1_g = (ImageView)findViewById(R.id.SQquality1_gray);
        SQ1 = (ImageView)findViewById(R.id.SQquality1);
        SQ2_g = (ImageView)findViewById(R.id.SQquality2_gray);
        SQ2 = (ImageView)findViewById(R.id.SQquality2);
        SQ3_g = (ImageView)findViewById(R.id.SQquality3_gray);
        SQ3 = (ImageView)findViewById(R.id.SQquality3);
        SQ4_g = (ImageView)findViewById(R.id.SQquality4_gray);
        SQ4 = (ImageView)findViewById(R.id.SQquality4);
        SQ5_g = (ImageView)findViewById(R.id.SQquality5_gray);
        SQ5 = (ImageView)findViewById(R.id.SQquality5);


        AWQ1_g = (ImageView)findViewById(R.id.AWQquality1_gray);
        AWQ1 = (ImageView)findViewById(R.id.AWQquality1);
        AWQ2_g = (ImageView)findViewById(R.id.AWQquality2_gray);
        AWQ2 = (ImageView)findViewById(R.id.AWQquality2);
        AWQ3_g = (ImageView)findViewById(R.id.AWQquality3_gray);
        AWQ3 = (ImageView)findViewById(R.id.AWQquality3);
        AWQ4_g = (ImageView)findViewById(R.id.AWQquality4_gray);
        AWQ4 = (ImageView)findViewById(R.id.AWQquality4);
        AWQ5_g = (ImageView)findViewById(R.id.AWQquality5_gray);
        AWQ5 = (ImageView)findViewById(R.id.AWQquality5);

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
        if(view.getId() == SQ1_g.getId()){
            sq =1;
            SQ1.setVisibility(View.VISIBLE);
            SQ2.setVisibility(View.INVISIBLE);
            SQ3.setVisibility(View.INVISIBLE);
            SQ4.setVisibility(View.INVISIBLE);
            SQ5.setVisibility(View.INVISIBLE);

        }
        else if(view.getId() == SQ2_g.getId()){
            sq =2;
            SQ2.setVisibility(View.VISIBLE);
            SQ1.setVisibility(View.INVISIBLE);
            SQ3.setVisibility(View.INVISIBLE);
            SQ4.setVisibility(View.INVISIBLE);
            SQ5.setVisibility(View.INVISIBLE);

        }

        else if(view.getId() == SQ3_g.getId()){
            sq =3;
            SQ3.setVisibility(View.VISIBLE);
            SQ2.setVisibility(View.INVISIBLE);
            SQ1.setVisibility(View.INVISIBLE);
            SQ4.setVisibility(View.INVISIBLE);
            SQ5.setVisibility(View.INVISIBLE);

        }
        else if(view.getId() == SQ4_g.getId()){
            sq =4;
            SQ4.setVisibility(View.VISIBLE);
            SQ2.setVisibility(View.INVISIBLE);
            SQ3.setVisibility(View.INVISIBLE);
            SQ1.setVisibility(View.INVISIBLE);
            SQ5.setVisibility(View.INVISIBLE);

        }
        else if(view.getId() == SQ5_g.getId()){
            sq =5;
            SQ5.setVisibility(View.VISIBLE);
            SQ2.setVisibility(View.INVISIBLE);
            SQ3.setVisibility(View.INVISIBLE);
            SQ4.setVisibility(View.INVISIBLE);
            SQ1.setVisibility(View.INVISIBLE);

        }

        else if(view.getId() == AWQ1_g.getId())
            {
                awq = 1;
                AWQ1.setVisibility(View.VISIBLE);
                AWQ2.setVisibility(View.INVISIBLE);
                AWQ3.setVisibility(View.INVISIBLE);
                AWQ4.setVisibility(View.INVISIBLE);
                AWQ5.setVisibility(View.INVISIBLE);
            }
            else if(view.getId() == AWQ2_g.getId())
            {
                awq = 2;
                AWQ2.setVisibility(View.VISIBLE);
                AWQ1.setVisibility(View.INVISIBLE);
                AWQ3.setVisibility(View.INVISIBLE);
                AWQ4.setVisibility(View.INVISIBLE);
                AWQ5.setVisibility(View.INVISIBLE);
            }
            else if(view.getId() == AWQ3_g.getId())
            {
                awq = 3;
                AWQ3.setVisibility(View.VISIBLE);
                AWQ2.setVisibility(View.INVISIBLE);
                AWQ1.setVisibility(View.INVISIBLE);
                AWQ4.setVisibility(View.INVISIBLE);
                AWQ5.setVisibility(View.INVISIBLE);
            }
            else if(view.getId() == AWQ4_g.getId())
            {
                awq = 4;
                AWQ4.setVisibility(View.VISIBLE);
                AWQ2.setVisibility(View.INVISIBLE);
                AWQ3.setVisibility(View.INVISIBLE);
                AWQ1.setVisibility(View.INVISIBLE);
                AWQ5.setVisibility(View.INVISIBLE);
            }
            else if(view.getId() == AWQ5_g.getId())
            {
                awq = 5;
                AWQ5.setVisibility(View.VISIBLE);
                AWQ2.setVisibility(View.INVISIBLE);
                AWQ3.setVisibility(View.INVISIBLE);
                AWQ4.setVisibility(View.INVISIBLE);
                AWQ1.setVisibility(View.INVISIBLE);
            }

        else if(view.getId() == R.id.save_s2)
        {
            if(bedtime.isEmpty())
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 8!", Toast.LENGTH_SHORT);
                errormsg.show();

            }
            else if (asleeptime.isEmpty())
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 9!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (woketime.isEmpty())
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 10!", Toast.LENGTH_SHORT);
                errormsg.show();
            }

            else if (outtime.isEmpty())
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 11!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (sq==0)
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 13!", Toast.LENGTH_SHORT);
                errormsg.show();
            }

            else if (awq==0)
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish Question 14!", Toast.LENGTH_SHORT);
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



    private TimePickerDialog.OnTimeSetListener bedTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay0();
                }

            };

    private TimePickerDialog.OnTimeSetListener asleepTimeSetListener=
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                   // if (pHour == 0){pHour = 12;}
                    updateDisplay1();
                }

            };

    private TimePickerDialog.OnTimeSetListener wokeTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay2();

                }

            };

    private TimePickerDialog.OnTimeSetListener ofbedTimeSetListener=
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay3();
                }

            };

    /** Updates the time in the TextView */
    private void updateDisplay0() {
        temp_bed_h = pHour;
        temp_bed_m = pMinute;


        //temp_bed = 100*pHour + pMinute;
        bedtime = pad(pHour) + ":" + pad(pMinute);

            Bbedtime.setText(
                    new StringBuilder()
                            .append(pad(pHour)).append(":")
                            .append(pad(pMinute)));


        Basleeptime.setEnabled(true);
        Basleeptime.setTextColor(0xFF000000);


    }
    private void updateDisplay1() {
        asleeptime = String.valueOf(pHour) + ":" + pad(pMinute);
        Basleeptime.setTextSize(20);
        temp_asleep_h = pHour;
        temp_asleep_m = pMinute;
        //temp_asleep = 100*pHour + pMinute;

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

        Basleeptime.setText(ast);
        Basleeptime.setTextSize(18);

        Bwoketime.setEnabled(true);
        Bwoketime.setTextColor(0xFF000000);

    }

    private void updateDisplay2() {

        woketime = pad(pHour) + ":" + pad(pMinute);

        temp_wake_h = pHour;
        temp_wake_m = pMinute;

        if(temp_bed_h == 0)
        {
            temp_bed_h = 24;
        }

        temp_wake = 100*pHour + pMinute;

        if((temp_bed_h < 12)&&((temp_wake_h-temp_bed_h >12)||(temp_wake_h-temp_bed_h ==12)&&(temp_wake_m>temp_bed_m)))
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
                                    .append(pad(pHour)).append(":")
                                    .append(pad(pMinute)));


                Bouttime.setEnabled(true);
                Bouttime.setTextColor(0xFF000000);
            }
    }

    private void updateDisplay3() {

        outtime = pad(pHour) + ":" + pad(pMinute);
        temp_out = 100*pHour + pMinute;
        if(temp_out<temp_wake)
        {
            Toast pass = Toast.makeText(SleepDiaryActivity2.this,"Question 11 should be later than the wake up time!", Toast.LENGTH_SHORT);
            Bouttime.setText("Pick Time");
            for(int x = 0; x<2000;x++)//delay the notification
            pass.show();
        }

        else {

            outtime = pad(pHour) + ":" + pad(pMinute);

                Bouttime.setText(
                        new StringBuilder()
                                .append(pad(pHour)).append(":")
                                .append(pad(pMinute)));

        }
    }


    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_0:
                return new TimePickerDialog(this,
                        bedTimeSetListener, pHour, pMinute, true);
            case TIME_DIALOG_1:
                return new TimePickerDialog(this,
                        asleepTimeSetListener, pHour, pMinute, true);
            case TIME_DIALOG_2:
                return new TimePickerDialog(this,
                        wokeTimeSetListener, pHour, pMinute, true);
            case TIME_DIALOG_3:
                return new TimePickerDialog(this,
                        ofbedTimeSetListener, pHour, pMinute, true);
        }
        return null;
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

            if(no_wake>1)
            {
                waket.setText(no_wake + " times");
                waket.setTextSize(22);}
            else if (no_wake==10){
                waket.setText(no_wake+"or more times");
                waket.setTextSize(16);
            }
            else
            {
                waket.setTextSize(22);
                waket.setText(no_wake + " time");
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



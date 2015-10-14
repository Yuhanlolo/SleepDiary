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
import java.util.Calendar;
import android.view.Menu;

public class SleepDiaryActivity2 extends ActionBarActivity implements OnSeekBarChangeListener{

    private Button Bbedtime;
    private Button Basleeptime;
    private Button Bwoketime;
    private Button Bouttime;
    //Spinner Snowake;
    SeekBar Snowake;
    private TextView waket;
    private int pHour;
    private int pMinute;
    TextView lastnight;
    int month;
    int date;
    int year;
//    private RadioGroup SQ;
//    private RadioGroup AWQ;

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

    String bedtime = "";
    String asleeptime = "";
    String woketime = "";
    String outtime = "";
    int no_wake = 0;


    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_0 = 0;
    static final int TIME_DIALOG_1 = 1;
    static final int TIME_DIALOG_2 = 2;
    static final int TIME_DIALOG_3 = 3;

    SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2sleepdiary);

        Bbedtime = (Button) findViewById(R.id.bedt);
        Bbedtime.setText("Pick Time");
        Bbedtime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_0);
            }
        });

        Basleeptime = (Button) findViewById(R.id.asleept);
        Basleeptime.setText("Time Period");
        Basleeptime.setEnabled(false);
        Basleeptime.setTextColor(0xFF808080);
        Basleeptime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_1);
            }
        });

        Bwoketime = (Button) findViewById(R.id.woket);
        Bwoketime.setText("Pick Time");
        Bwoketime.setEnabled(false);
        Bwoketime.setTextColor(0xFF808080);
        Bwoketime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_2);
            }
        });

        Bouttime = (Button) findViewById(R.id.ofbed);
        Bouttime.setText("Pick Time");
        Bouttime.setEnabled(false);
        Bouttime.setTextColor(0xFF808080);
        Bouttime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_3);
            }
        });

        Snowake = (SeekBar)findViewById(R.id.wakeno);
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
            if(bedtime.isEmpty()||asleeptime.isEmpty()||woketime.isEmpty()||outtime.isEmpty()||sq==0||awq==0)
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish all the questions!", Toast.LENGTH_SHORT);
                errormsg.show();

            }

            else
            {
                Intent i = new Intent(SleepDiaryActivity2.this,SleepDiaryActivity3.class);
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
                    if (pHour == 0){pHour = 12;}
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

        bedtime = pad(pHour) + ":" + pad(pMinute);

        if(pHour>12)
        {
            pHour = pHour - 12;
            Bbedtime.setText(
                    new StringBuilder()
                            .append(pad(pHour)).append(":")
                            .append(pad(pMinute)).append(" pm"));
        }


        else
        {
            Bbedtime.setText(
                    new StringBuilder()
                            .append(pad(pHour)).append(":")
                            .append(pad(pMinute)).append(" am"));
        }

        Basleeptime.setEnabled(true);
        Basleeptime.setTextColor(0xFF000000);


    }
    private void updateDisplay1() {
        asleeptime = String.valueOf(pHour) + ":" + pad(pMinute);
        Basleeptime.setTextSize(20);
        Basleeptime.setText(
                new StringBuilder()
                        .append(String.valueOf(pHour)).append(" hrs ")
                        .append(pad(pMinute)).append(" mins"));
        Bwoketime.setEnabled(true);
        Bwoketime.setTextColor(0xFF000000);
    }

    private void updateDisplay2() {

//        int temp_bedtime = Integer.parseInt(bedtime);
//        if(pHour+(24-temp_bedtime)>12)
//        {
//            AlertDialog dlg = new AlertDialog.Builder(this).create();
//            dlg.setTitle("Input Alert");
//            dlg.setMessage("You slept over 12 hours!");
//            Toast pass = Toast.makeText(SleepDiaryActivity2.this,"You slept over 12 hours!", Toast.LENGTH_SHORT);
//            pass.show();
//        }

            woketime = pad(pHour) + ":" + pad(pMinute);

            if (pHour > 12) {
                pHour = pHour - 12;
                Bwoketime.setText(
                        new StringBuilder()
                                .append(pad(pHour)).append(":")
                                .append(pad(pMinute)).append(" pm"));
            } else {
                Bwoketime.setText(
                        new StringBuilder()
                                .append(pad(pHour)).append(":")
                                .append(pad(pMinute)).append(" am"));
            }

            Bouttime.setEnabled(true);
            Bouttime.setTextColor(0xFF000000);

    }
    private void updateDisplay3() {

       outtime = pad(pHour) + ":" + pad(pMinute);
        if(pHour>12)
        {
            pHour = pHour - 12;
            Bouttime.setText(
                    new StringBuilder()
                            .append(pad(pHour)).append(":")
                            .append(pad(pMinute)).append(" pm"));
        }

        else
        {
            Bouttime.setText(
                    new StringBuilder()
                            .append(pad(pHour)).append(":")
                            .append(pad(pMinute)).append(" am"));
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
                        bedTimeSetListener, pHour, pMinute, false);
            case TIME_DIALOG_1:
                return new TimePickerDialog(this,
                        asleepTimeSetListener, pHour, pMinute, true);
            case TIME_DIALOG_2:
                return new TimePickerDialog(this,
                        wokeTimeSetListener, pHour, pMinute, false);
            case TIME_DIALOG_3:
                return new TimePickerDialog(this,
                        ofbedTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar == Snowake)
        {
            no_wake = progress;
            waket.setText(no_wake + " time(s)");

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}



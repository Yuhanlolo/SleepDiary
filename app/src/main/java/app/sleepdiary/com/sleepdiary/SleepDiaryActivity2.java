package app.sleepdiary.com.sleepdiary;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.AdapterView;

/**
 * Created by ypl5142 on 10/4/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

public class SleepDiaryActivity2 extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private Button Bbedtime;
    private Button Basleeptime;
    private Button Bwoketime;
    private Button Bouttime;
    Spinner Snowake;
    private int pHour;
    private int pMinute;

    String bedtime = "";
    String asleeptime = "";
    String woketime = "";
    String outtime = "";
    String no_wake = "";
    int facesleep = 0;
    int facewake =0;


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
        Basleeptime.setText("Pick Time");
        Basleeptime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_1);
            }
        });

        Bwoketime = (Button) findViewById(R.id.woket);
        Bwoketime.setText("Pick Time");
        Bwoketime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_2);
            }
        });

        Bouttime = (Button) findViewById(R.id.ofbed);
        Bouttime.setText("Pick Time");
        Bouttime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_3);
            }
        });

        Snowake = (Spinner)findViewById(R.id.wakeno);
        Snowake.setOnItemSelectedListener(this);

    }

    public void button_SD2dOnClick(View view)
    {
        if(view.getId() == R.id.save_s2)
        {



            if(bedtime.isEmpty()||asleeptime.isEmpty()||woketime.isEmpty()||outtime.isEmpty()||no_wake.isEmpty())
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity2.this,"Please finish all the questions!", Toast.LENGTH_SHORT);
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

                Intent i = new Intent(SleepDiaryActivity2.this,SleepDiaryActivity3.class);

                SleepDiaryActivity2.this.startActivity(i);
//                Toast msg = Toast.makeText(SleepDiaryActivity.this,"Finished this page!", Toast.LENGTH_SHORT);
//                msg.show();
            }
        }

        if(view.getId() == R.id.cancel_s2)
        {
            Intent i = new Intent(SleepDiaryActivity2.this,SleepActivity.class);
            SleepDiaryActivity2.this.startActivity(i);
        }


    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        String[] numbers = getResources().getStringArray(R.array.number);
        if(parent == findViewById(R.id.wakeno))
        {
            no_wake = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose coffee"+no_coffee, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private TimePickerDialog.OnTimeSetListener bedTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;

                    //if (getParent().equals(findViewById(R.id.pilltime)))

                    updateDisplay0();


                }

            };

    private TimePickerDialog.OnTimeSetListener asleepTimeSetListener=
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;

                    //if (getParent().equals(findViewById(R.id.pilltime)))

                    updateDisplay1();


                }

            };

    private TimePickerDialog.OnTimeSetListener wokeTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;

                    //if (getParent().equals(findViewById(R.id.pilltime)))

                    updateDisplay2();


                }

            };

    private TimePickerDialog.OnTimeSetListener ofbedTimeSetListener=
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;

                    //if (getParent().equals(findViewById(R.id.pilltime)))

                    updateDisplay3();


                }

            };

    /** Updates the time in the TextView */
    private void updateDisplay0() {
        Bbedtime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));

        bedtime = pad(pHour) + ":" + pad(pMinute);


    }
    private void updateDisplay1() {
        Basleeptime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
        asleeptime = pad(pHour) + ":" + pad(pMinute);
    }

    private void updateDisplay2() {
        Bwoketime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));

        woketime = pad(pHour) + ":" + pad(pMinute);


    }
    private void updateDisplay3() {
        Bouttime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
       outtime = pad(pHour) + ":" + pad(pMinute);
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
                        asleepTimeSetListener, pHour, pMinute, false);
            case TIME_DIALOG_2:
                return new TimePickerDialog(this,
                        wokeTimeSetListener, pHour, pMinute, false);
            case TIME_DIALOG_3:
                return new TimePickerDialog(this,
                        ofbedTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }
}



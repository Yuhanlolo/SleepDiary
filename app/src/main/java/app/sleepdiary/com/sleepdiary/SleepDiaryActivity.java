package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;

/**
 * Created by Yuhan on 9/13/15.
 */
public class SleepDiaryActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{


    String no_coffee = "";
    String no_wine = "";
    String no_smoke = "";
    String no_naptime = "";
    String sleepduration = "";
    String pilltime = "";
    String pillname = "";
    Spinner coffee, wine, smoke, naptime;
    //private TextView displayTime;
    private Button timeperiod;
    private Button pickTime;
    private int pHour;
    private int pMinute;
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_ID = 0;
    static final int TIME_PERIOD = 1;

    SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepdiary);

       coffee = (Spinner)findViewById(R.id.coffee_spinner);
        //int no_coffee = 0;

        wine = (Spinner)findViewById(R.id.wine_spinner);
        // int no_tea = 0;

        smoke = (Spinner)findViewById(R.id.smoke_spinner);
        //int no_smoke = 0;
        naptime = (Spinner)findViewById(R.id.naptime_spinner);
        //int no_naptime = 0;

        coffee.setOnItemSelectedListener(this);
        wine.setOnItemSelectedListener(this);
        smoke.setOnItemSelectedListener(this);
        naptime.setOnItemSelectedListener(this);

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
        timeperiod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               showDialog(TIME_PERIOD);
            }
        });

        /** Get the current time */
//        final Calendar cal = Calendar.getInstance();
//        pHour = cal.get(Calendar.HOUR_OF_DAY);
//        pMinute = cal.get(Calendar.MINUTE);

        /** Display the current time in the TextView */
       // updateDisplay();


    }

    public void button_SDdOnClick(View view)
    {
        if(view.getId() == R.id.save_s)
        {

            EditText pill = (EditText)findViewById(R.id.pillname);
            pillname = pill.getText().toString();

            if(no_coffee.startsWith("-")||no_wine.startsWith("-")||no_smoke.startsWith("-")||no_naptime.startsWith("-")||pillname.isEmpty()||pilltime.isEmpty()||sleepduration.isEmpty())
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish all the questions!", Toast.LENGTH_SHORT);
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

                Intent i = new Intent(SleepDiaryActivity.this,SleepDiaryActivity2.class);

                SleepDiaryActivity.this.startActivity(i);
//                Toast msg = Toast.makeText(SleepDiaryActivity.this,"Finished this page!", Toast.LENGTH_SHORT);
//                msg.show();
            }
        }

        if(view.getId() == R.id.cancel_s)
        {
            Intent i = new Intent(SleepDiaryActivity.this,SleepActivity.class);
            SleepDiaryActivity.this.startActivity(i);
        }

//        if(view.getId() == R.id.pilltime)
//        {
//            Intent i = new Intent(SleepDiaryActivity.this,TimePickerActivity.class);
//            SleepDiaryActivity.this.startActivity(i);
//        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        String[] numbers = getResources().getStringArray(R.array.number);
        if(parent == findViewById(R.id.coffee_spinner))
        {
            no_coffee = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose coffee"+no_coffee, Toast.LENGTH_LONG).show();
        }
        else if(parent == findViewById(R.id.wine_spinner))
        {
            no_wine = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose tea"+no_wine, Toast.LENGTH_LONG).show();
        }
        else if(parent == findViewById(R.id.smoke_spinner))
        {
            no_smoke = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose smoke"+no_smoke, Toast.LENGTH_LONG).show();
        }
        else if(parent == findViewById(R.id.naptime_spinner))
        {
            no_naptime = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose naptime"+no_naptime, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;

                    //if (getParent().equals(findViewById(R.id.pilltime)))

                    updateDisplay();
                    displayToast();

                }

            };

    private TimePickerDialog.OnTimeSetListener dTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;

                    //if (getParent().equals(findViewById(R.id.pilltime)))

                    updateDisplay2();
                    displayToast2();

                }

            };

    /** Updates the time in the TextView */
    private void updateDisplay() {
        pickTime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));

        pilltime = pad(pHour) + ":" + pad(pMinute);


    }
    private void updateDisplay2() {
            timeperiod.setText(
                    new StringBuilder()
                            .append(pad(pHour)).append(":")
                            .append(pad(pMinute)));
            sleepduration = pad(pHour) + ":" + pad(pMinute);
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
                        mTimeSetListener, pHour, pMinute, false);
            case TIME_PERIOD:
                return new TimePickerDialog(this,
                        dTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }
}


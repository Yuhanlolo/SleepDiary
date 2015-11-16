package app.sleepdiary.com.sleepdiary;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

/**
 * Created by ypl5142 on 10/25/15.
 */
public class NapMoveSleepActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {


    int temp_bed_h = 0;
    int temp_bed_m = 0;

    int bedhour = 12, bedmin = 0;

    int temp_asleep_h = 0;
    int temp_asleep_m = 0;

    int dHour =0;
    int dMinute = 0;

    Button Bnaptime, Bnapdu;

    String[] minuteValues = {"0","5","10","15","20","25","30","35","40","45","50","55"};
    int movep = 0;

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

        Bnaptime = (Button)findViewById(R.id.napt);
        Bnapdu = (Button)findViewById(R.id.napd);

//        else if(lastpage.equals("Nap"))
//        {
//            movesleep  = new ParseObject("NAP_MoveSleep");
//        }

        currentpage = (TextView)findViewById(R.id.lastpagem);
        currentpage.setText(currenttask);

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

        movescale = (SeekBar)findViewById(R.id.naps_move);
        movescale.setOnSeekBarChangeListener(this);
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

    public void button_naptpOnClick(View view)
    {
        if(view.getId() == R.id.napt)
        {
            final Dialog Timepicker1 = new Dialog(NapMoveSleepActivity.this);
            Timepicker1.setTitle("I had nap at:");

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

        if(view.getId() == R.id.napd)
        {
            final Dialog Numberpicker1 = new Dialog(NapMoveSleepActivity.this);
            Numberpicker1.setTitle("I had nap for:");

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

            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(NapMoveSleepActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else if (wss == -1){

                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this,"Please finish Question 1!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (css == -1){
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this,"Please finish Question 2!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (uhss == -1){
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this,"Please finish Question 3!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (umss == -1){
                Toast errormsg = Toast.makeText(NapMoveSleepActivity.this,"Please finish Question 4!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else
            {
                movesleep.put("User_ID", ParseUser.getCurrentUser().getUsername());


                movesleep.put("NAP_time",naptime);
                movesleep.put("NAP_duration",napdu);
                movesleep.put("SCOPA_walking",wss);
                movesleep.put("SCOPA_change_position",css);
                movesleep.put("SCOPA_use_hands",uhss);
                movesleep.put("SCOPA_uncontrollable_movement",umss);

                movesleep.put("Move_Capability", movep);
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
//                             Toast pass = Toast.makeText(MovesleepActivity.this,"id 1: "+objectID, Toast.LENGTH_SHORT);
//                             pass.show();
                            Intent i = new Intent(NapMoveSleepActivity.this, MovesleepActivity2.class);
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

        if(view.getId() == R.id.napcancel_ms)
        {
            Intent i = new Intent(NapMoveSleepActivity.this,SleepActivity.class);
            i.putExtra("lastpage",lastpage);
            NapMoveSleepActivity.this.startActivity(i);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == movescale)
        {
            movep = progress;

        }

    }

    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    /** Updates the time in the TextView */
    private void updateDisplay0() {
        temp_bed_h = bedhour;
        temp_bed_m = bedmin;


        //temp_bed = 100*pHour + pMinute;
        naptime = pad(bedhour) + ":" + pad(bedmin);

        Bnaptime.setText(
                new StringBuilder()
                        .append(pad(bedhour)).append(":")
                        .append(pad(bedmin)));


        Bnaptime.setEnabled(true);
        Bnaptime.setTextColor(0xFF000000);


    }
    private void updateDisplay1() {
        napdu = String.valueOf(dHour) + ":" + Integer.toString(dMinute);
        Bnapdu.setTextSize(20);
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

        Bnapdu.setText(ast);
        Bnapdu.setTextSize(18);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}


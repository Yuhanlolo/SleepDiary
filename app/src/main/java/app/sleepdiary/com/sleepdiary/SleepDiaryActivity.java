package app.sleepdiary.com.sleepdiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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
import android.view.inputmethod.EditorInfo;
import android.text.Selection;
import android.view.View.OnKeyListener;
/**
 * Created by Yuhan on 9/13/15.
 */
public class SleepDiaryActivity extends ActionBarActivity implements  View.OnClickListener, RadioGroup.OnCheckedChangeListener, TextView.OnEditorActionListener {

    int no_coffee = 0;
    int no_wine = 0;
    int no_smoke = 0;
    int no_nap = 0;

    String sleepduration = "";
    String pilltime = "";
    String pillname = "";
    String yesterdaystr ="";
    String today= "";

    String temp_m = "";
    String temp_h = "";


    private Button pickTime, timeperiod;
    private TextView t_coffe;
    private TextView t_wine;
    private TextView t_smoke;
    private TextView t_nap;
    int month;
    int date;
    int year;

    private TextView yesterday, q7, q8, colon, q5, naph, napm;
    private EditText edtView, coffee_edt, alcohol_edt, nap_edt,smoke_edt;
    private EditText pillh_edt,pillm_edt,napduh_edt, napdum_edt ;

    String objectID = "";
    String lastpage = "";
    RadioButton rg_1, rg_0;
    RadioGroup rg;
    int rbid1, rbid0;
    boolean pill = false;

    int dHour = -1, dMinute = -1;
//    String[] hrValues = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
//    String[] minuteValues = {"0","5","10","15","20","25","30","35","40","45","50","55"};

    final Calendar cal = Calendar.getInstance();
    int pHour = -1;
    int pMinute = -1;

//    String[] tobastr = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20 or more"};

    //SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);
    ParseObject userActivity;
    ParseQuery<ParseObject> query;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepdiary);


                getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        /* Hide the back button*/
        //getActionBar().setHomeAsUpIndicator(null);

//        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
//            mBaseLayout.setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
//        else if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
//            mBaseLayout.setSystemUiVisibility( View.STATUS_BAR_HIDDEN );

        Intent i_getvalue = getIntent();
        lastpage = i_getvalue.getStringExtra("lastpage");
        userActivity  = new ParseObject("Sleep_Diary");

        rg = (RadioGroup)findViewById(R.id.group_pill);
        rg_1 = (RadioButton)findViewById(R.id.yes_pill);
        rg_0 = (RadioButton)findViewById(R.id.no_pill);
        rbid1 = rg_1.getId();
        rbid0 = rg_0.getId();

        rg.setOnCheckedChangeListener(this);

        coffee_edt = (EditText)findViewById(R.id.no_coffee);
        coffee_edt.setOnEditorActionListener(this);
        //coffee_edt.setInputType(InputType.);
        t_coffe = (TextView)findViewById(R.id.d_coffee);

        alcohol_edt = (EditText)findViewById(R.id.no_alcohol);
        alcohol_edt.setOnEditorActionListener(this);
        t_wine = (TextView)findViewById(R.id.d_wine);

        smoke_edt = (EditText)findViewById(R.id.no_pipe);
        smoke_edt.setOnEditorActionListener(this);
        t_smoke = (TextView)findViewById(R.id.d_smoke);

        nap_edt = (EditText)findViewById(R.id.no_nap);
        //nap_edt.setText("0");
        nap_edt.setOnEditorActionListener(this);
        t_nap = (TextView)findViewById(R.id.d_nap);

        napduh_edt = (EditText)findViewById(R.id.nap_du_h);
        napduh_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                myadapter.getFilter().filter(s);
//                listview.setAdapter(myadapter);

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (napduh_edt.getText().toString().length()==2)
                {
                    dHour = Integer.parseInt(napduh_edt.getText().toString());
                    if(dHour>23 || dHour<0)
                    {
                        napduh_edt.setText("");
                        Toast pass = Toast.makeText(SleepDiaryActivity.this, "Please input nap hours between 0-23!", Toast.LENGTH_LONG);
                        pass.show();
                        napduh_edt.requestFocus();
                    }
                    else
                    {

                        if (dHour == 1)
                        {
                            naph.setText("hr");
                            temp_h = " hr";
                        }
                        else
                        {
                            naph.setText("hrs");
                            temp_h = " hrs";
                        }
                        napdum_edt.requestFocus();
                    }

                }
            }
        });
        napduh_edt.setEnabled(false);
        napduh_edt.setOnClickListener(this);
        napduh_edt.setOnEditorActionListener(this);

        napdum_edt = (EditText)findViewById(R.id.nap_du_m);
        napdum_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                myadapter.getFilter().filter(s);
//                listview.setAdapter(myadapter);

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (napdum_edt.getText().toString().length()==2)
                {
                    //napdum_edt.requestFocus();
                    if (Integer.parseInt(napdum_edt.getText().toString()) > 59 || Integer.parseInt(napdum_edt.getText().toString()) < 0) {
                        napdum_edt.setText("");
                        Toast pass = Toast.makeText(SleepDiaryActivity.this, "Please input nap minutes between 0-59!", Toast.LENGTH_LONG);
                        pass.show();
                        napdum_edt.requestFocus();
                    } else {
                        dMinute = Integer.parseInt(napdum_edt.getText().toString());


                        if (dMinute == 1) {
                            napm.setText("min");
                            temp_m = " min";
                        } else {
                            napm.setText("mins");
                            temp_m = " mins";
                        }
                    }
                    }
            }
        });
        napdum_edt.setEnabled(false);
        napdum_edt.setOnClickListener(this);
        napdum_edt.setOnEditorActionListener(this);

        pillh_edt = (EditText)findViewById(R.id.pillh);
        //pillh_edt.setInputType(InputType.TYPE_NULL);
        pillh_edt.setEnabled(false);
        pillh_edt.setOnClickListener(this);
        pillh_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                myadapter.getFilter().filter(s);
//                listview.setAdapter(myadapter);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (pillh_edt.getText().toString().length() == 2) {
                    //pillm_edt.requestFocus();
                    if (Integer.parseInt(pillh_edt.getText().toString()) > 23 || Integer.parseInt(pillh_edt.getText().toString()) < 0) {
                        pillh_edt.setText("");
                        Toast pass = Toast.makeText(SleepDiaryActivity.this, "Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                        pass.show();
                        pillh_edt.requestFocus();
                    } else {

                        pillm_edt.requestFocus();
                    }
                }
            }
        });

        pillh_edt.setOnEditorActionListener(this);

        pillm_edt = (EditText)findViewById(R.id.pillm);
        //pillm_edt.setInputType(InputType.TYPE_NULL);
        pillm_edt.setEnabled(false);
        pillm_edt.setOnClickListener(this);
        pillm_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                myadapter.getFilter().filter(s);
//                listview.setAdapter(myadapter);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (pillm_edt.getText().toString().length() == 2) {

                    if (Integer.parseInt(pillm_edt.getText().toString()) > 59 || Integer.parseInt(pillm_edt.getText().toString()) < 0) {
                        pillm_edt.setText("");
                        Toast pass = Toast.makeText(SleepDiaryActivity.this, "Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                        pass.show();
                        pillm_edt.requestFocus();
                    }
                    else{
                        edtView.requestFocus();
                    }

                }
            }
        });
        pillm_edt.setOnEditorActionListener(this);

        q5 = (TextView)findViewById(R.id.sleep_totally);
        q7 = (TextView)findViewById(R.id.pillt);
        q8 = (TextView)findViewById(R.id.pilln);
        colon = (TextView)findViewById(R.id.colonpilltime);
        naph = (TextView)findViewById(R.id.naph);
        napm = (TextView)findViewById(R.id.napm);

        yesterday = (TextView)findViewById(R.id.yesterday);

        edtView=(EditText)findViewById(R.id.pillname);
        //edtView.setInputType(InputType.TYPE_NULL);
        edtView.setEnabled(false);
        edtView.setOnClickListener(this);

        /** Get the current time */

        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DATE);
        year = cal.get(Calendar.YEAR);
        today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);
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
        yesterday.setText("Sleep Diary for Yesterday (" + String.valueOf(month) + "/" + String.valueOf(date) + "/" + String.valueOf(year) + ")");

        /** Display the saved data */
//        ParseUser currentUser = ParseUser.getCurrentUser();
//        userActivity.put("Date",yesterdaystr);
//        userActivity.put("User_ID",currentUser.getUsername());
//        userActivity.saveInBackground();
//
//        query = ParseQuery.getQuery("Sleep_Diary");
//        if(currentUser != null) {
//            String userid = currentUser.getUsername();
//            query.whereEqualTo("User_ID", userid);
//            query.whereEqualTo("Date", yesterdaystr);
//
//            query.getFirstInBackground(new GetCallback<ParseObject>() {
//                public void done(ParseObject object, ParseException e) {
//                    if (object == null) {
//                        Log.d("User_ID", "The getFirst request failed.");
//
//                    } else {
//
//                        if(object.get("No_Coffee")!= null)
//                        {
//                            no_coffee = object.getInt("No_Coffee");
//                            if(no_coffee ==1)
//                                t_coffe.setText(no_coffee + "glass");
//                            else
//                                t_coffe.setText(no_coffee + "glasses");
//                            coffee.setProgress(no_coffee);
//                        }
//
////                            no_wine = object.getInt("No_Alcohol");
////                            t_wine.setText(no_wine);
////                            t_wine.setText(no_wine);
////                            wine.setProgress(no_wine);
////
////                            no_smoke = object.getInt("No_Tobacco");
////                            smoke.setText(no_smoke);
////
////                            no_nap = object.getInt("Nap_Time");
////                            t_nap.setText(no_nap);
////                            nap.setProgress(no_nap);
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
            boolean phour1 = false, pmin1 = false;
            boolean dhour1 = false, dmin1 = false;

            if (!(coffee_edt.getText().toString()).isEmpty())
                no_coffee = Integer.parseInt(coffee_edt.getText().toString());
            else
                coffee_edt.setText("0");

            if (!(alcohol_edt.getText().toString()).isEmpty())
                no_wine = Integer.parseInt(alcohol_edt.getText().toString());
            else
                alcohol_edt.setText("0");

            if (!(smoke_edt.getText().toString()).isEmpty())
                no_smoke = Integer.parseInt(smoke_edt.getText().toString());
            else
                smoke_edt.setText("0");

            if (!(nap_edt.getText().toString()).isEmpty())
            {
                no_nap = Integer.parseInt(nap_edt.getText().toString());
//                if(no_nap ==0)
//                {
//                    napduh_edt.setText("");
//                    napdum_edt.setText("");
//                }
            }
            else
                nap_edt.setText("0");

            if (no_nap>0)
            {
                napduh_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                napduh_edt.setEnabled(true);
                napdum_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                napdum_edt.setEnabled(true);

                q5.setTextColor(0xFF000000);
                naph.setTextColor(0xFF000000);
                napm.setTextColor(0xFF000000);

                napduh_edt.requestFocus();
            }
            else
            {
                //napduh_edt.setInputType(InputType.TYPE_NULL);
                napduh_edt.setEnabled(false);
                napduh_edt.clearFocus();
                napduh_edt.setText("");
                // napdum_edt.setInputType(InputType.TYPE_NULL);
                napdum_edt.setEnabled(false);
                napdum_edt.clearFocus();
                napdum_edt.setText("");

                q5.setTextColor(0xFFBABABA);
                naph.setTextColor(0xFFBABABA);
                napm.setTextColor(0xFFBABABA);
            }

            if (no_nap>0  && (!(napduh_edt.getText().toString()).isEmpty() || !(napdum_edt.getText().toString()).isEmpty()))
            {
                if (!(napduh_edt.getText().toString()).isEmpty() )
                {
                    dHour = Integer.parseInt(napduh_edt.getText().toString());
                    if (dHour<0 || dHour>23)
                    {
//                    Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"For Question 5, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
//                    errormsg.show();
                        dhour1 = true;
                    }
                    else
                    {
                        if (dHour ==1)
                            temp_h = " hr";
                        else
                            temp_h = " hrs";
                    }
                }

                if (!(napdum_edt.getText().toString()).isEmpty())
                {
                    dMinute = Integer.parseInt(napdum_edt.getText().toString());
                    if (dMinute<0 || dMinute>59)
                    {
//                    Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"For Question 5, Please input minute of time between 0-59!", Toast.LENGTH_LONG);
//                    errormsg.show();
                        dmin1 = true;
                    }
                    else
                    {
                        if (dMinute ==1)
                            temp_m = " min";
                        else
                            temp_m = " mins";
                    }
                }

                if (!(napduh_edt.getText().toString()).isEmpty()&& !(napdum_edt.getText().toString()).isEmpty())
                {
                    sleepduration =  pad(dHour) + temp_h + pad(dMinute) + temp_m;
                }
                else if (!(napduh_edt.getText().toString()).isEmpty()&& (napdum_edt.getText().toString()).isEmpty())
                {
                    sleepduration =  pad(dHour) + temp_h + "0 mins";
                    napdum_edt.setText("0");
                }

                else if ((napduh_edt.getText().toString()).isEmpty()&& !(napdum_edt.getText().toString()).isEmpty())
                {
                    sleepduration =  "0 hrs"+pad(dMinute) + temp_m;
                    napduh_edt.setText("0");
                }
            }

            else if (no_nap>0 && (napduh_edt.getText().toString()).isEmpty() && (napdum_edt.getText().toString()).isEmpty())
            {
                sleepduration = "";
            }

            if(pill && (!(pillh_edt.getText().toString()).isEmpty() && !(pillm_edt.getText().toString()).isEmpty()))
            {

                    pHour = Integer.parseInt(pillh_edt.getText().toString());
                    if (pHour<0 || pHour>23)
                    {
//                    Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"For Question 7, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
//                    errormsg.show();
                        phour1 = true;
                    }


                    pMinute = Integer.parseInt(pillm_edt.getText().toString());
                    if (pMinute<0 || pMinute>59)
                    {
//                    Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"For Question 7, Please input minute of time between 0-59!", Toast.LENGTH_LONG);
//                    errormsg.show();
                        pmin1 = true;
                    }

                pilltime =  pad(pHour) + ":" + pad(pMinute);

                if (!(edtView.getText().toString()).isEmpty())
                    pillname = edtView.getText().toString();
                else
                    pillname = "";
            }

            if(pill && ((pillh_edt.getText().toString()).isEmpty() || (pillm_edt.getText().toString()).isEmpty()))
            {
                pilltime = "";
            }

            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this,"Please Login in first!", Toast.LENGTH_LONG);
                pass.show();
            }

            else if ((no_nap>0)&&dhour1)
            {
                if(Integer.parseInt(napduh_edt.getText().toString())>23||Integer.parseInt(napduh_edt.getText().toString())<0){
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"For Question 5, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                errormsg.show();}
            }

            else if ((no_nap>0)&&dmin1)
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"For Question 5, Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                errormsg.show();
            }


            else if ((no_nap > 0)&& sleepduration.isEmpty())
            {

                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish Question 5!", Toast.LENGTH_LONG);
                errormsg.show();
            }

            else if ((pill)&&phour1)
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"For Question 7, Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                errormsg.show();
            }

            else if ((pill)&&pmin1)
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"For Question 7, Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                errormsg.show();
            }

            else if(pill&&pilltime.isEmpty())
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish Question 7!", Toast.LENGTH_LONG);
                errormsg.show();
            }

            else if (pill&&pillname.isEmpty())
            {
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish Question 8!", Toast.LENGTH_LONG);
                errormsg.show();
            }


            else
            {
//                dHour = Integer.parseInt(napduh_edt.getText().toString());
//                dMinute = Integer.parseInt(napdum_edt.getText().toString());
//                sleepduration =  pad(dHour) + ":" + pad(dMinute);
//
//                pHour = Integer.parseInt(pillh_edt.getText().toString());
//                pMinute = Integer.parseInt(pillm_edt.getText().toString());
//                pilltime =  pad(pHour) + ":" + pad(pMinute);

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


    @Override
    public void onClick(View v) {

        if (v.getId()== R.id.nap_du_h)
        {
            if (no_nap == 0)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this, "Did you nap during the day? Please input the times you napped in Question 4 first!", Toast.LENGTH_LONG);
                pass.show();
            }

        }

        if (v.getId()== R.id.nap_du_m)
        {
            if (no_nap == 0)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this, "Did you nap during the day? Please input the times you napped in Question 4 first!", Toast.LENGTH_LONG);
                pass.show();
            }
        }

        if (v.getId()== R.id.pillh)
        {
            if (!pill)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this, "Did you take sleeping pill? Please select yes in Question 6 if you did", Toast.LENGTH_LONG);
                pass.show();
            }
        }

        if (v.getId()== R.id.pillm)
        {
            if (!pill)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this, "Did you take sleeping pill? Please select yes in Question 6 if you did", Toast.LENGTH_LONG);
                pass.show();
            }
        }



        if (v.getId()== R.id.pillname)
        {
            if (!pill)
            {
                Toast pass = Toast.makeText(SleepDiaryActivity.this, "Did you take sleeping pill? Please select yes in Question 5 if you did", Toast.LENGTH_LONG);
                pass.show();
            }

            else
            {
                pillname = edtView.getText().toString();
            }
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group == rg)
        {
            if(checkedId == rbid1)
            {

                edtView.setInputType(1);
                edtView.setEnabled(true);
                pillh_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                pillh_edt.setEnabled(true);
                pillm_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                pillm_edt.setEnabled(true);
                pill = true;
                q7.setTextColor(0xFF000000);
                colon.setTextColor(0xFF000000);
                q8.setTextColor(0xFF000000);
                pillh_edt.requestFocus();

            }
            else {
                pill = false;
                pilltime = "";
                pillname= "";
                edtView.setText("");
                edtView.setInputType(0);
                edtView.setEnabled(false);
               // pillh_edt.setInputType(InputType.TYPE_NULL);
                pillh_edt.setEnabled(false);
                pillh_edt.setText("");
                //pillm_edt.setInputType(InputType.TYPE_NULL);
                pillm_edt.setEnabled(false);
                pillm_edt.setText("");
                q7.setTextColor(0xFFBABABA);
                colon.setTextColor(0xFFBABABA);
                q8.setTextColor(0xFFBABABA);
                pillh_edt.clearFocus();
                pillm_edt.clearFocus();
                edtView.clearFocus();

            }
        }
    }




    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getId()== R.id.no_coffee)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(coffee_edt.getText().toString().isEmpty()))
            {
                String coffee = coffee_edt.getText().toString();
                no_coffee = Integer.parseInt(coffee);
                if (no_coffee == 1)
                {
                    t_coffe.setText("glass");
                }

                else
                {
                    t_coffe.setText("glasses");
                }

            }
            else if (actionId == EditorInfo.IME_ACTION_DONE && (coffee_edt.getText().toString().isEmpty()))
            {
                coffee_edt.setText("0");
            }
            alcohol_edt.requestFocus();
        }

        if (v.getId()== R.id.no_alcohol) {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(alcohol_edt.getText().toString().isEmpty()))
            {
                no_wine = Integer.parseInt(alcohol_edt.getText().toString());
                if (no_wine == 1)
                {
                    t_wine.setText("glass");
                } else
                {
                    t_wine.setText("glasses");
                }
            }
            else if (actionId == EditorInfo.IME_ACTION_DONE && (alcohol_edt.getText().toString().isEmpty()))
            {
                alcohol_edt.setText("0");
            }
            smoke_edt.requestFocus();
        }

        if (v.getId()== R.id.no_pipe)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(smoke_edt.getText().toString().isEmpty()))
            {
            no_smoke = Integer.parseInt(smoke_edt.getText().toString());
            if (no_smoke == 1)
            {
                t_smoke.setText("unit");
            }

            else
            {
                t_smoke.setText("units");
            }

            }
            else if (actionId == EditorInfo.IME_ACTION_DONE && (smoke_edt.getText().toString().isEmpty()))
            {
                smoke_edt.setText("0");
            }
            nap_edt.requestFocus();
        }

        if (v.getId()== R.id.no_nap)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(nap_edt.getText().toString().isEmpty()))
            {
            no_nap = Integer.parseInt(nap_edt.getText().toString());

                if (no_nap == 1)
                {
                    t_nap.setText("time");
                }

                else
                {
                    t_nap.setText("times");
                }

            if (no_nap>0)
            {
                napduh_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                napduh_edt.setEnabled(true);
                napdum_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                napdum_edt.setEnabled(true);

                q5.setTextColor(0xFF000000);
                naph.setTextColor(0xFF000000);
                napm.setTextColor(0xFF000000);

                napduh_edt.requestFocus();
            }
            else
            {
                //napduh_edt.setInputType(InputType.TYPE_NULL);
                napduh_edt.setEnabled(false);
                napduh_edt.setText("");
               // napdum_edt.setInputType(InputType.TYPE_NULL);
                napdum_edt.setEnabled(false);
                napdum_edt.setText("");

                q5.setTextColor(0xFFBABABA);
                naph.setTextColor(0xFFBABABA);
                napm.setTextColor(0xFFBABABA);
            }
            }

            else if (actionId == EditorInfo.IME_ACTION_DONE && (nap_edt.getText().toString().isEmpty()))
            {
                nap_edt.setText("0");
            }
        }

        if (v.getId()== R.id.nap_du_h )
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(napduh_edt.getText().toString().isEmpty()))
            {
                dHour = Integer.parseInt(napduh_edt.getText().toString());
                if(dHour>23 || dHour<0)
                {
                    napduh_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity.this, "Please input nap hours between 0-23!", Toast.LENGTH_LONG);
                    pass.show();
                    napduh_edt.requestFocus();
                }
                else
                {

                    if (dHour == 1)
                    {
                        naph.setText("hr");
                        temp_h = " hr";
                    }
                    else
                    {
                        naph.setText("hrs");
                        temp_h = " hrs";
                    }

                    if(dHour!= -1 && dMinute!= -1)
                    {
                        sleepduration = pad(dHour) + temp_h + pad(dMinute)+temp_m;
                    }
                    else if (dHour!= -1 && dMinute == -1)
                    {
                        sleepduration = pad(dHour) + temp_h + "0 mins";
                    }
                    else if (dHour == -1 && dMinute != -1)
                    {
                        sleepduration = pad(dMinute)+temp_m;

                    }
                    else
                    {
                        sleepduration ="";
                    }
                }
            }

            else if (actionId == EditorInfo.IME_ACTION_DONE && (napduh_edt.getText().toString().isEmpty()))
            {
                napduh_edt.setText("0");
                sleepduration = "0 hrs"+pad(dMinute)+temp_m;
                dHour = 0;
            }
            napdum_edt.requestFocus();
        }

        if (v.getId()== R.id.nap_du_m)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(napdum_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(napdum_edt.getText().toString()) > 59 || Integer.parseInt(napdum_edt.getText().toString()) < 0) {
                    napdum_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity.this, "Please input nap minutes between 0-59!", Toast.LENGTH_LONG);
                    pass.show();
                    napdum_edt.requestFocus();
                } else {
                    dMinute = Integer.parseInt(napdum_edt.getText().toString());


                    if (dMinute == 1) {
                        napm.setText("min");
                        temp_m = " min";
                    } else {
                        napm.setText("mins");
                        temp_m = " mins";
                    }

                    if (dHour != -1 && dMinute != -1) {
                        sleepduration = pad(dHour) + temp_h + pad(dMinute) + temp_m;
                    }
                    else if (dHour!= -1 && dMinute == -1)
                    {
                        sleepduration = pad(dHour) + temp_h;
                        napdum_edt.setText("0");
                    }
                    else if (dHour == -1 && dMinute != -1)
                    {
                        sleepduration = "0 hrs"+pad(dMinute)+temp_m;
                        //napduh_edt.setText("0");
                    }
                    else
                    {
                        sleepduration ="";
                    }
                }
            }

            else if (actionId == EditorInfo.IME_ACTION_DONE && (napdum_edt.getText().toString().isEmpty()))
            {
                if (!(napduh_edt.getText().toString().isEmpty()))
                {
                    sleepduration = pad(dHour) + temp_h + "0 mins";
                    napdum_edt.setText("0");
                }

                dMinute = 0;
            }

//            napdum_edt.clearFocus();
//            coffee_edt.clearFocus();
        }

        if (v.getId()== R.id.pillh)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(pillh_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(pillh_edt.getText().toString()) > 23 || Integer.parseInt(pillh_edt.getText().toString()) < 0) {
                    pillh_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity.this, "Please input hour of time between 0-23!", Toast.LENGTH_LONG);
                    pass.show();
                    pillh_edt.requestFocus();
                } else {

                    pHour = Integer.parseInt(pillh_edt.getText().toString());
                    if (pHour<10)
                    {
                        pillh_edt.setText("0"+pHour);
                    }

                    if (pHour != -1 && pMinute != -1) {
                        pilltime = pad(pHour) + ":" + pad(pMinute);
                    }
                    pillm_edt.requestFocus();
                }
            }
            else if (actionId == EditorInfo.IME_ACTION_DONE && (pillh_edt.getText().toString().isEmpty()))
            {
                pHour = -1;
                pillh_edt.requestFocus();
            }

        }

        if (v.getId()== R.id.pillm)
        {
            if (actionId == EditorInfo.IME_ACTION_DONE && !(pillm_edt.getText().toString().isEmpty())) {
                if (Integer.parseInt(pillm_edt.getText().toString()) > 59 || Integer.parseInt(pillm_edt.getText().toString()) < 0) {
                    pillm_edt.setText("");
                    Toast pass = Toast.makeText(SleepDiaryActivity.this, "Please input minute of time between 0-59!", Toast.LENGTH_LONG);
                    pass.show();
                    pillm_edt.requestFocus();
                } else {
                    pMinute = Integer.parseInt(pillm_edt.getText().toString());
                    if (pMinute<10)
                    {
                        pillm_edt.setText("0"+pMinute);
                    }
                    if (pHour != -1 && pMinute != -1) {
                        pilltime = pad(pHour) + ":" + pad(pMinute);
                    }
                    edtView.requestFocus();
                }

            }

            else if (actionId == EditorInfo.IME_ACTION_DONE && (pillm_edt.getText().toString().isEmpty()))
            {
                pMinute = -1;
                pillm_edt.requestFocus();
            }

        }

        if(v.getId() == R.id.pillname){
            if(actionId ==EditorInfo.IME_ACTION_DONE && !(edtView.getText().toString().isEmpty()))
            {
                pillname = edtView.getText().toString();
                //edtView.clearFocus();
            }

            else if(actionId ==EditorInfo.IME_ACTION_DONE && (edtView.getText().toString().isEmpty()))
            {
                pillname = "";
                //edtView.requestFocus();
            }
        }

        return false;
    }
}


package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ypl5142 on 12/5/15.
 */
public class NapMoveSleepActivity2 extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener{

    SeekBar movescale;
    String objectID = "";
    String lastpage  = "Nap";
    String currenttask = "";
    TextView currentpage;
    int movep = -1;
    int sleepp = -1;
    GridLayout g1, g2, g3,g4,g5,g6,g7;
    ParseQuery<ParseObject> query;

    int month = 0;
    int date = 0;
    int year = 0;
    String today = "";

    List<TextView> sleepscale = new ArrayList<TextView>(7);
    List<RadioButton> sleepscaleb = new ArrayList<RadioButton>(7);
    List<GridLayout> gl = new ArrayList<GridLayout>(7);
    String end = "Thank you for completing the Nap test!";
    RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7;
    int rbsd1, rbsd2,rbsd3, rbsd4,rbsd5, rbsd6,rbsd7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_napmovesleep2);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        query = ParseQuery.getQuery("NAP_MoveSleep");
        currenttask = "After nap";

        currentpage = (TextView)findViewById(R.id.lastnappagem2);
        currentpage.setText(currenttask);

        movescale = (SeekBar)findViewById(R.id.naps_move);
        movescale.setOnSeekBarChangeListener(this);

        objectID = getIntent().getStringExtra("objectID");


        g1 = (GridLayout)findViewById(R.id.napstan1);
        //g1.setBackgroundColor(Color.parseColor("#FFFF9D"));
        gl.add(g1);
        g2 = (GridLayout)findViewById(R.id.napstan2);
        gl.add(g2);
        g3 = (GridLayout)findViewById(R.id.napstan3);
        gl.add(g3);
        g4 = (GridLayout)findViewById(R.id.napstan4);
        gl.add(g4);
        g5 = (GridLayout)findViewById(R.id.napstan5);
        gl.add(g5);
        g6 = (GridLayout)findViewById(R.id.napstan6);
        gl.add(g6);
        g7 = (GridLayout)findViewById(R.id.napstan7);
        gl.add(g7);


//        rgSD = (RadioGroup)findViewById(R.id.rgsd);
//        rgSD.setOnCheckedChangeListener(this);

        rbsd1 = findViewById(R.id.napds1).getId();
        rbsd2 = findViewById(R.id.napds2).getId();
        rbsd3 = findViewById(R.id.napds3).getId();
        rbsd4 = findViewById(R.id.napds4).getId();
        rbsd5 = findViewById(R.id.napds5).getId();
        rbsd6 = findViewById(R.id.napds6).getId();
        rbsd7 = findViewById(R.id.napds7).getId();


        TextView st1 = (TextView)findViewById(R.id.napstanford1);
        sleepscale.add(st1);
        TextView st2 = (TextView)findViewById(R.id.napstanford2);
        sleepscale.add(st2);
        TextView st3 = (TextView)findViewById(R.id.napstanford3);
        sleepscale.add(st3);
        TextView st4 = (TextView)findViewById(R.id.napstanford4);
        sleepscale.add(st4);
        TextView st5 = (TextView)findViewById(R.id.napstanford5);
        sleepscale.add(st5);
        TextView st6 = (TextView)findViewById(R.id.napstanford6);
        sleepscale.add(st6);
        TextView st7 = (TextView)findViewById(R.id.napstanford7);
        sleepscale.add(st7);


        rb1 = (RadioButton)findViewById(R.id.napds1);
        sleepscaleb.add(rb1);
        rb2 = (RadioButton)findViewById(R.id.napds2);
        sleepscaleb.add(rb2);
        rb3 = (RadioButton)findViewById(R.id.napds3);
        sleepscaleb.add(rb3);
        rb4 = (RadioButton)findViewById(R.id.napds4);
        sleepscaleb.add(rb4);
        rb5 = (RadioButton)findViewById(R.id.napds5);
        sleepscaleb.add(rb5);
        rb6 = (RadioButton)findViewById(R.id.napds6);
        sleepscaleb.add(rb6);
        rb7 = (RadioButton)findViewById(R.id.napds7);
        sleepscaleb.add(rb7);

        final Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DATE);
        year = cal.get(Calendar.YEAR);
        today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);


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
            Intent i = new Intent(NapMoveSleepActivity2.this,SettingsActivity.class);
            NapMoveSleepActivity2.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == movescale)
        {
            movep = progress;

        }

    }

    public void Itemclick(View view)
    {
        if(view.getId() == R.id.napstanford1||view.getId() == R.id.napds1)
        {
            rb1.setChecked(true);
            g1.setBackgroundColor(Color.parseColor("#FFFF9D"));
            sleepp = 1;
            for (int i = 0; i<gl.size();i++)
            {

                if(i != 0)
                {
                    gl.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    sleepscaleb.get(i).setChecked(false);
                }

            }
        }

        if(view.getId() == R.id.napstanford2||view.getId() == R.id.napds2)
        {
            rb2.setChecked(true);
            g2.setBackgroundColor(Color.parseColor("#FFFF9D"));
            sleepp = 2;
            for (int i = 0; i<gl.size();i++)
            {
                if(i != 1)
                {
                    gl.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    sleepscaleb.get(i).setChecked(false);
                }

            }
        }

        if(view.getId() == R.id.napstanford3||view.getId() == R.id.napds3)
        {
            rb3.setChecked(true);
            g3.setBackgroundColor(Color.parseColor("#FFFF9D"));
            sleepp = 3;
            for (int i = 0; i<gl.size();i++)
            {
                if(i != 2)
                {
                    gl.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    sleepscaleb.get(i).setChecked(false);
                }

            }
        }

        if(view.getId() == R.id.napstanford4||view.getId() == R.id.napds4)
        {
            rb4.setChecked(true);
            g4.setBackgroundColor(Color.parseColor("#FFFF9D"));
            sleepp = 4;
            for (int i = 0; i<gl.size();i++)
            {
                if(i != 3)
                {
                    gl.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    sleepscaleb.get(i).setChecked(false);
                }

            }
        }

        if(view.getId() == R.id.napstanford5||view.getId() == R.id.napds5)
        {
            rb5.setChecked(true);
            g5.setBackgroundColor(Color.parseColor("#FFFF9D"));
            sleepp = 5;
            for (int i = 0; i<gl.size();i++)
            {
                if(i != 4)
                {
                    gl.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    sleepscaleb.get(i).setChecked(false);
                }

            }
        }

        if(view.getId() == R.id.napstanford6||view.getId() == R.id.napds6)
        {
            rb6.setChecked(true);
            g6.setBackgroundColor(Color.parseColor("#FFFF9D"));
            sleepp = 6;
            for (int i = 0; i<gl.size();i++)
            {
                if(i != 5)
                {
                    gl.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    sleepscaleb.get(i).setChecked(false);
                }

            }
        }
        if(view.getId() == R.id.napstanford7||view.getId() == R.id.napds7)
        {
            rb7.setChecked(true);
            g7.setBackgroundColor(Color.parseColor("#FFFF9D"));
            sleepp = 7;
            for (int i = 0; i<gl.size();i++)
            {
                if(i != 6)
                {
                    gl.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    sleepscaleb.get(i).setChecked(false);
                }

            }
        }
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void button_ms2dOnClick(View view)
    {
        if(view.getId() == R.id.savenapms2)
        {
            ParseUser currentUser1 = ParseUser.getCurrentUser();

            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(NapMoveSleepActivity2.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else if(movep == -1){
                Toast errormsg = Toast.makeText(NapMoveSleepActivity2.this,"Please finish Question 7!", Toast.LENGTH_SHORT);
                errormsg.show();
            }

            else if(sleepp == -1){
                Toast errormsg = Toast.makeText(NapMoveSleepActivity2.this,"Please finish Question 8!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else
            {

//                Toast pass = Toast.makeText(NapMoveSleepActivity2.this,"id 2: "+objectID, Toast.LENGTH_SHORT);
//                pass.show();
                query.getInBackground(objectID, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e != null) {
                            Toast pass = Toast.makeText(NapMoveSleepActivity2.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
                            object.put("Move_Capability", movep);
                            object.put("Sleepiness_Scale", sleepp);

                            //userActivity.pinInBackground();
                            object.saveInBackground();

                        }
                    }
                });

                Intent i = new Intent(NapMoveSleepActivity2.this, MainActivity.class);
                i.putExtra("endstr", end);
                //i.putExtra("lastpage",lastpage);
                //i.putExtra("loginstatus",f);
                NapMoveSleepActivity2.this.startActivity(i);

            }
        }

//        if(view.getId() == R.id.cancel_ms2)
//        {
//            Intent i = new Intent(MovesleepActivity2.this,SleepActivity.class);
//            i.putExtra("lastpage",lastpage);
//            MovesleepActivity2.this.startActivity(i);
//        }
    }

    }
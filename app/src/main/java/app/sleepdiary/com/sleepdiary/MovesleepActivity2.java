package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.FindCallback;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.util.Log;
import android.graphics.Color;
/**
 * Created by ypl5142 on 10/25/15.
 */
public class MovesleepActivity2 extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    List<TextView> sleepscale = new ArrayList<TextView>(7);
    List<RadioButton> sleepscaleb = new ArrayList<RadioButton>(7);

//    SeekBar movescale;
    int sleepp = -1;
    int movep = 0;
    TextView sleepptext;

    boolean f = false;
    String lastpage = "";
    String objectID = "";

    ParseQuery<ParseObject> query;
    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TaskCheckList");

    int month = 0;
    int date = 0;
    int year = 0;
    String today = "";
    String currenttask = "";
    TextView currentpage;

    RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7;
    int rbsd1, rbsd2,rbsd3, rbsd4,rbsd5, rbsd6,rbsd7;
    RadioGroup rgSD;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movesleep2);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );


        Intent i_getvalue = getIntent();
        lastpage = i_getvalue.getStringExtra("lastpage");

        if(lastpage.equals("M30"))
        {
            query = ParseQuery.getQuery("M30_MoveSleep");
            currenttask = "30 minutes after morning awakening";
        }
        else if (lastpage.equals("A_DOPA1"))
        {
            query = ParseQuery.getQuery("A_DOPA1_MoveSleep");
            currenttask = "After last dopaminergic drug intake";
        }
        else if (lastpage.equals("A_DOPA"))
        {
            query = ParseQuery.getQuery("A_DOPA_MoveSleep");
            currenttask = "Before dinner";
        }
        else if(lastpage.equals("E"))
        {
            query = ParseQuery.getQuery("E_MoveSleep");
            currenttask = "Bed Time in the evening";
        }
        else if(lastpage.equals("Nap"))
        {
            query = ParseQuery.getQuery("NAP_MoveSleep");
            currenttask = "After nap";
        }

        currentpage = (TextView)findViewById(R.id.lastpagem2);
        currentpage.setText(currenttask);


        objectID = getIntent().getStringExtra("objectID");

        rgSD = (RadioGroup)findViewById(R.id.rgsd);
        rgSD.setOnCheckedChangeListener(this);
        rbsd1 = findViewById(R.id.ds1).getId();
        rbsd2 = findViewById(R.id.ds2).getId();
        rbsd3 = findViewById(R.id.ds3).getId();
        rbsd4 = findViewById(R.id.ds4).getId();
        rbsd5 = findViewById(R.id.ds5).getId();
        rbsd6 = findViewById(R.id.ds6).getId();
        rbsd7 = findViewById(R.id.ds7).getId();


        TextView st1 = (TextView)findViewById(R.id.stanford1);
        sleepscale.add(st1);
        TextView st2 = (TextView)findViewById(R.id.stanford2);
        sleepscale.add(st2);
        TextView st3 = (TextView)findViewById(R.id.stanford3);
        sleepscale.add(st3);
        TextView st4 = (TextView)findViewById(R.id.stanford4);
        sleepscale.add(st4);
        TextView st5 = (TextView)findViewById(R.id.stanford5);
        sleepscale.add(st5);
        TextView st6 = (TextView)findViewById(R.id.stanford6);
        sleepscale.add(st6);
        TextView st7 = (TextView)findViewById(R.id.stanford7);
        sleepscale.add(st7);

        TextView n1 = (TextView)findViewById(R.id.n1);
        sleepscale.add(n1);
        TextView n2 = (TextView)findViewById(R.id.n2);
        sleepscale.add(n2);
        TextView n3 = (TextView)findViewById(R.id.n3);
        sleepscale.add(n3);
        TextView n4 = (TextView)findViewById(R.id.n4);
        sleepscale.add(n4);
        TextView n5 = (TextView)findViewById(R.id.n5);
        sleepscale.add(n5);
        TextView n6 = (TextView)findViewById(R.id.n6);
        sleepscale.add(n6);
        TextView n7 = (TextView)findViewById(R.id.n7);
        sleepscale.add(n7);

        rb1 = (RadioButton)findViewById(R.id.ds1);
        sleepscaleb.add(rb1);
        rb2 = (RadioButton)findViewById(R.id.ds2);
        sleepscaleb.add(rb2);
        rb3 = (RadioButton)findViewById(R.id.ds3);
        sleepscaleb.add(rb3);
        rb4 = (RadioButton)findViewById(R.id.ds4);
        sleepscaleb.add(rb4);
        rb5 = (RadioButton)findViewById(R.id.ds5);
        sleepscaleb.add(rb5);
        rb6 = (RadioButton)findViewById(R.id.ds6);
        sleepscaleb.add(rb6);
        rb7 = (RadioButton)findViewById(R.id.ds7);
        sleepscaleb.add(rb7);




//        Toast pass = Toast.makeText(MovesleepActivity2.this,"id 2: "+objectID, Toast.LENGTH_SHORT);
//        pass.show();

//        sleepptext = (TextView)findViewById(R.id.sleepscaletext);
//        sleeppoint = (SeekBar)findViewById(R.id.scale_sleep);
//        sleepscale.add((ImageView) findViewById(R.id.po0));
//        sleepscale.add((ImageView)findViewById(R.id.po1));
//        sleepscale.add((ImageView)findViewById(R.id.po2));
//        sleepscale.add((ImageView)findViewById(R.id.po3));
//        sleepscale.add((ImageView)findViewById(R.id.po4));
//        sleepscale.add((ImageView)findViewById(R.id.po5));
//        sleepscale.add((ImageView)findViewById(R.id.po6));
//        sleepscale.add((ImageView) findViewById(R.id.po7));
//
//
//        sleeppoint.setOnSeekBarChangeListener(this);

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
            Intent i = new Intent(MovesleepActivity2.this,SettingsActivity.class);
            MovesleepActivity2.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void Itemclick(View view)
    {
        if(view.getId() == R.id.stanford1||view.getId() == R.id.n1)
        {
            rb1.setChecked(true);
            for (int i = 0; i<sleepscale.size();i++)
            {
                if(i == 0 || i == 7)
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    if(i<7)
                    {
                    sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    }
                }
                else
                {
                sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    }
                }
            }
        }

        if(view.getId() == R.id.stanford2||view.getId() == R.id.n2)
        {
            rb2.setChecked(true);
            for (int i = 0; i<sleepscale.size();i++)
            {
                if(i == 1 || i == 8)
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    }
                }
                else
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    }
                }
            }
        }

        if(view.getId() == R.id.stanford3||view.getId() == R.id.n3)
        {
            rb3.setChecked(true);
            for (int i = 0; i<sleepscale.size();i++)
            {
                if(i == 2 || i == 9)
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    }
                }
                else
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    }
                }
            }
        }

        if(view.getId() == R.id.stanford4||view.getId() == R.id.n4)
        {
            rb4.setChecked(true);
            for (int i = 0; i<sleepscale.size();i++)
            {
                if(i == 3 || i == 10)
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    }
                }
                else
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    }
                }
            }
        }

        if(view.getId() == R.id.stanford5||view.getId() == R.id.n5)
        {
            rb5.setChecked(true);
            for (int i = 0; i<sleepscale.size();i++)
            {
                if(i == 4 || i == 11)
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    }
                }
                else
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    }
                }
            }
        }

        if(view.getId() == R.id.stanford6||view.getId() == R.id.n6)
        {
            rb6.setChecked(true);
            for (int i = 0; i<sleepscale.size();i++)
            {
                if(i == 5 || i == 12)
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    }
                }
                else
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    }
                }
            }
        }
        if(view.getId() == R.id.stanford7||view.getId() == R.id.n7)
        {
            rb7.setChecked(true);

            for (int i = 0; i<sleepscale.size();i++)
            {
                if(i == 6 || i == 13)
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                    }
                }
                else
                {
                    sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    if(i<7)
                    {
                        sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                    }
                }
            }
        }
    }

    public void button_ms2dOnClick(View view)
    {
        if(view.getId() == R.id.save_ms2)
        {
            ParseUser currentUser1 = ParseUser.getCurrentUser();

            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(MovesleepActivity2.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }

             else if(sleepp == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity2.this,"Please finish Question 6!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else
            {

                query.getInBackground(objectID, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e != null) {
                            Toast pass = Toast.makeText(MovesleepActivity2.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
//                            object.put("Move_Capability", movep);
                            object.put("Sleepiness_Scale",sleepp);

                            //userActivity.pinInBackground();
                            object.saveInBackground();
                        }
                    }
                });

                query1.whereEqualTo("User_ID", ParseUser.getCurrentUser().getUsername());
                query1.whereEqualTo("Date",today);
                query1.setLimit(1);
                query1.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            //Log.d("score", "Retrieved " + scoreList.size() + " scores");
//                            Toast pass = Toast.makeText(MovesleepActivity2.this, "size: " + scoreList.size(), Toast.LENGTH_SHORT);
//                            pass.show();
                            if(lastpage.equals("M30"))
                            {
                            scoreList.get(0).put("M30_Movesleep", 1);
                            }

                            if(lastpage.equals("A_DOPA1"))
                            {
                                scoreList.get(0).put("A_DOPA1_Movesleep", 1);
                            }
                            if(lastpage.equals("A_DOPA"))
                            {
                                scoreList.get(0).put("A_DOPA_Movesleep", 1);
                            }
                            if(lastpage.equals("E"))
                            {
                                scoreList.get(0).put("E_Movesleep", 1);
                            }

                            scoreList.get(0).saveInBackground();
                        } else {
//                            Toast pass = Toast.makeText(MovesleepActivity2.this, "Error: " + "not found!", Toast.LENGTH_SHORT);
//                            pass.show();
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });


                f = true;
                Intent i = new Intent(MovesleepActivity2.this,MainActivity.class);
                i.putExtra("lastpage",lastpage);
                i.putExtra("loginstatus",f);
                MovesleepActivity2.this.startActivity(i);
            }
        }

        if(view.getId() == R.id.cancel_ms2)
        {
            Intent i = new Intent(MovesleepActivity2.this,SleepActivity.class);
            i.putExtra("lastpage",lastpage);
            MovesleepActivity2.this.startActivity(i);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//        if (seekBar == movescale)
//        {
//            movep = progress;
//
//        }

//        if (seekBar == sleeppoint)
//        {
//            sleepp = progress + 1;
//            for (int bc = 0; bc<8;bc++)
//            {
//                if(bc == progress)
//                    continue;
//                sleepscale.get(bc).setVisibility(View.INVISIBLE);
//            }
//
//            sleepscale.get(progress).setVisibility(View.VISIBLE);
//
//            if(sleepp>1)
//            {
//                sleepptext.setText(sleepp + " points");
//
//
//            }
//
//            else
//            {
//                sleepptext.setText(sleepp + " point");
//
//
//            }
//        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group == rgSD)
        {
            if (checkedId == rbsd1)
            {
                sleepp = 1;
                for (int i = 0; i<sleepscale.size();i++)
                {
                    if(i == 0 || i == 7)
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        }
                    }
                    else
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        }
                    }
                }
            }

            if (checkedId == rbsd2)
            {
                sleepp = 2;
                for (int i = 0; i<sleepscale.size();i++)
                {
                    if(i == 1 || i == 8)
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        }
                    }
                    else
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        }
                    }
                }
            }
            if (checkedId == rbsd3)
            {
                sleepp = 3;
                for (int i = 0; i<sleepscale.size();i++)
                {
                    if(i == 2 || i == 9)
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        }
                    }
                    else
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        }
                    }
                }
            }
            if (checkedId == rbsd4)
            {
                sleepp = 4;
                for (int i = 0; i<sleepscale.size();i++)
                {
                    if(i == 3 || i == 10)
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        }
                    }
                    else
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        }
                    }
                }
            }
            if (checkedId == rbsd5)
            {
                sleepp = 5;
                for (int i = 0; i<sleepscale.size();i++)
                {
                    if(i == 4 || i == 11)
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        }
                    }
                    else
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        }
                    }
                }
            }
            if (checkedId == rbsd6)
            {
                sleepp = 6;
                for (int i = 0; i<sleepscale.size();i++)
                {
                    if(i == 5 || i == 12)
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        }
                    }
                    else
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        }
                    }
                }
            }
            if (checkedId == rbsd7)
            {
                sleepp = 7;
                for (int i = 0; i<sleepscale.size();i++)
                {
                    if(i == 6 || i == 13)
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FFFF9D"));
                        }
                    }
                    else
                    {
                        sleepscale.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        if(i<7)
                        {
                            sleepscaleb.get(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
                        }
                    }
                }
            }

        }
    }
}

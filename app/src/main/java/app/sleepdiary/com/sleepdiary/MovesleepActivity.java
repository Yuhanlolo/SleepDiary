package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

/**
 * Created by ypl5142 on 10/25/15.
 */
public class MovesleepActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

//    int walk =0;
//    int change = 0;
//    int usehand = 0;
//    int uncontrol = 0;

    int movep = -1;
    ImageView green5;
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

    ParseObject movesleep;

    final Calendar cal = Calendar.getInstance();
    String yesterdaystr ="";

    int month;
    int date;
    int year;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movesleep);

//        View myView = getWindow().getDecorView();
//        myView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        Intent i_getvalue = getIntent();
        lastpage = i_getvalue.getStringExtra("lastpage");

        if(lastpage.equals("M30"))
        {
            movesleep  = new ParseObject("M30_MoveSleep");
            currenttask = "30 minutes after morning awakening";
        }
        else if (lastpage.equals("A_DOPA1"))
        {

            movesleep  = new ParseObject("A_DOPA1_MoveSleep");
            currenttask = "After last dopaminergic drug intake";
        }
        else if (lastpage.equals("A_DOPA"))
        {
            movesleep  = new ParseObject("A_DOPA_MoveSleep");
            currenttask = "Before dinner";
        }
        else if(lastpage.equals("E"))
        {
            movesleep  = new ParseObject("E_MoveSleep");
            currenttask = "Bed Time in the evening";
        }
//        else if(lastpage.equals("Nap"))
//        {
//            movesleep  = new ParseObject("NAP_MoveSleep");
//        }

        currentpage = (TextView)findViewById(R.id.lastpagem);
        currentpage.setText(currenttask);

        wsscopa0 = (ImageView)findViewById(R.id.wsscopa1);
        wsscopa1 = (ImageView)findViewById(R.id.wsscopa2);
        wsscopa2 = (ImageView)findViewById(R.id.wsscopa3);
        wsscopa3 = (ImageView)findViewById(R.id.wsscopa4);

        csscopa0 = (ImageView)findViewById(R.id.csscopa1);
        csscopa1 = (ImageView)findViewById(R.id.csscopa2);
        csscopa2 = (ImageView)findViewById(R.id.csscopa3);
        csscopa3 = (ImageView)findViewById(R.id.csscopa4);

        uhsscopa0 = (ImageView)findViewById(R.id.usscopa1);
        uhsscopa1 = (ImageView)findViewById(R.id.usscopa2);
        uhsscopa2 = (ImageView)findViewById(R.id.usscopa3);
        uhsscopa3 = (ImageView)findViewById(R.id.usscopa4);

        umsscopa0 = (ImageView)findViewById(R.id.umsscopa1);
        umsscopa1 = (ImageView)findViewById(R.id.umsscopa2);
        umsscopa2 = (ImageView)findViewById(R.id.umsscopa3);
        umsscopa3 = (ImageView)findViewById(R.id.umsscopa4);

        movescale = (SeekBar)findViewById(R.id.s_move);
        movescale.setOnSeekBarChangeListener(this);
        green5 = (ImageView)findViewById(R.id.green5);
        /** Get the current time */

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
        //yesterday.setText("Sleep Diary for Yesterday (" + String.valueOf(month) + "/" + String.valueOf(date) + "/" + String.valueOf(year) + ")");
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
            Intent i = new Intent(MovesleepActivity.this,SettingsActivity.class);
            MovesleepActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void button_msdOnClick(View view)
    {

        if(view.getId() == R.id.wscopa1)
        {
            wss = 0;
            wsscopa0.setVisibility(View.VISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.wscopa2)
        {
            wss = 1;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.VISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.wscopa3)
        {
            wss = 2;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.VISIBLE);
            wsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.wscopa4)
        {
            wss = 3;
            wsscopa0.setVisibility(View.INVISIBLE);
            wsscopa1.setVisibility(View.INVISIBLE);
            wsscopa2.setVisibility(View.INVISIBLE);
            wsscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.cscopa1)
        {
            css = 0;
            csscopa0.setVisibility(View.VISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.cscopa2)
        {
            css = 1;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.VISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.cscopa3)
        {
           css = 2;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.VISIBLE);
            csscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.cscopa4)
        {
            css = 3;
            csscopa0.setVisibility(View.INVISIBLE);
            csscopa1.setVisibility(View.INVISIBLE);
            csscopa2.setVisibility(View.INVISIBLE);
            csscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.uscopa1)
        {
            uhss = 0;
            uhsscopa0.setVisibility(View.VISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.uscopa2)
        {
            uhss = 1;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.VISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.uscopa3)
        {
            uhss = 2;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.VISIBLE);
            uhsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.uscopa4)
        {
            uhss = 3;
            uhsscopa0.setVisibility(View.INVISIBLE);
            uhsscopa1.setVisibility(View.INVISIBLE);
            uhsscopa2.setVisibility(View.INVISIBLE);
            uhsscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.umscopa1)
        {
            umss = 0;
            umsscopa0.setVisibility(View.VISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.umscopa2)
        {
            umss = 1;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.VISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.umscopa3)
        {
            umss = 2;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.VISIBLE);
            umsscopa3.setVisibility(View.INVISIBLE);
        }

        if(view.getId() == R.id.umscopa4)
        {
            umss = 3;
            umsscopa0.setVisibility(View.INVISIBLE);
            umsscopa1.setVisibility(View.INVISIBLE);
            umsscopa2.setVisibility(View.INVISIBLE);
            umsscopa3.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.save_ms)
        {
            ParseUser currentUser1 = ParseUser.getCurrentUser();

            if(currentUser1 == null)
            {
                Toast pass = Toast.makeText(MovesleepActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else if (wss == -1){

                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 1!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (css == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 2!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (uhss == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 3!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (umss == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 4!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if (movep == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity.this,"Please finish Question 5!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else
            {
                movesleep.put("User_ID", ParseUser.getCurrentUser().getUsername());
                movesleep.put("Date",yesterdaystr);
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
                            Toast pass = Toast.makeText(MovesleepActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
                            objectID = movesleep.getObjectId();
//                             Toast pass = Toast.makeText(MovesleepActivity.this,"id 1: "+objectID, Toast.LENGTH_SHORT);
//                             pass.show();
                            Intent i = new Intent(MovesleepActivity.this, MovesleepActivity2.class);
                            i.putExtra("objectID", objectID);
                            i.putExtra("lastpage",lastpage);
                            MovesleepActivity.this.startActivity(i);


                        }
                    }
                });

//            Intent i = new Intent(MovesleepActivity.this,MovesleepActivity2.class);
//            MovesleepActivity.this.startActivity(i);
            }
        }

//        if(view.getId() == R.id.cancel_ms)
//        {
//            Intent i = new Intent(MovesleepActivity.this,SleepActivity.class);
//            i.putExtra("lastpage",lastpage);
//            MovesleepActivity.this.startActivity(i);
//        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == movescale)
        {
            movep = progress;
            if(movep == 4)
            {
                green5.setVisibility(View.VISIBLE);
            }
            else
            {
                green5.setVisibility(View.INVISIBLE);
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

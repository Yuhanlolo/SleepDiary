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
/**
 * Created by ypl5142 on 10/25/15.
 */
public class MovesleepActivity2 extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

    List<ImageView> sleepscale = new ArrayList<ImageView>(10);
    SeekBar sleeppoint, movescale;
    int sleepp = -1;
    int movep = 0;
    TextView sleepptext;

    boolean f = false;

    String objectID = "";

    ParseQuery<ParseObject> query = ParseQuery.getQuery("MoveSleepActivity");
    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TaskCheckList");

    int month = 0;
    int date = 0;
    int year = 0;
    String today = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movesleep2);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        movescale = (SeekBar)findViewById(R.id.s_move);
        movescale.setOnSeekBarChangeListener(this);

        objectID = getIntent().getStringExtra("objectID");

//        Toast pass = Toast.makeText(MovesleepActivity2.this,"id 2: "+objectID, Toast.LENGTH_SHORT);
//        pass.show();

        sleepptext = (TextView)findViewById(R.id.sleepscaletext);
        sleeppoint = (SeekBar)findViewById(R.id.scale_sleep);
        sleepscale.add((ImageView) findViewById(R.id.po0));
        sleepscale.add((ImageView)findViewById(R.id.po1));
        sleepscale.add((ImageView)findViewById(R.id.po2));
        sleepscale.add((ImageView)findViewById(R.id.po3));
        sleepscale.add((ImageView)findViewById(R.id.po4));
        sleepscale.add((ImageView)findViewById(R.id.po5));
        sleepscale.add((ImageView)findViewById(R.id.po6));
        sleepscale.add((ImageView) findViewById(R.id.po7));


        sleeppoint.setOnSeekBarChangeListener(this);

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


    public void button_ms2dOnClick(View view)
    {
        if(view.getId() == R.id.save_ms2)
        {
//            if(movep == -1){
//                Toast errormsg = Toast.makeText(MovesleepActivity2.this,"Please finish Question 5!", Toast.LENGTH_SHORT);
//                errormsg.show();
//            }
             if(sleepp == -1){
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
                            object.put("Move_Capability", movep);
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
                            scoreList.get(0).put("MMovesleep", 1);
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
                i.putExtra("loginstatus",f);
                MovesleepActivity2.this.startActivity(i);
            }
        }

        if(view.getId() == R.id.cancel_ms2)
        {
            Intent i = new Intent(MovesleepActivity2.this,SleepActivity.class);
            MovesleepActivity2.this.startActivity(i);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar == movescale)
        {
            movep = progress;

        }

        if (seekBar == sleeppoint)
        {
            sleepp = progress + 1;
            for (int bc = 0; bc<8;bc++)
            {
                if(bc == progress)
                    continue;
                sleepscale.get(bc).setVisibility(View.INVISIBLE);
            }

            sleepscale.get(progress).setVisibility(View.VISIBLE);

            if(sleepp>1)
            {
                sleepptext.setText(sleepp + " points");


            }

            else
            {
                sleepptext.setText(sleepp + " point");


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

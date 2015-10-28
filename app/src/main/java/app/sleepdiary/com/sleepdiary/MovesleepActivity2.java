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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ypl5142 on 10/25/15.
 */
public class MovesleepActivity2 extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

    List<ImageView> sleepscale = new ArrayList<ImageView>(10);
    SeekBar sleeppoint, movescale;
    int sleepp = -1;
    int movep = -1;
    TextView sleepptext;

    boolean f = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movesleep2);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        movescale = (SeekBar)findViewById(R.id.s_move);
        movescale.setOnSeekBarChangeListener(this);

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
            if(movep == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity2.this,"Please finish Question 5!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else if(sleepp == -1){
                Toast errormsg = Toast.makeText(MovesleepActivity2.this,"Please finish Question 6!", Toast.LENGTH_SHORT);
                errormsg.show();
            }
            else
            {
                f = true;
                Intent i = new Intent(MovesleepActivity2.this,CoverActivity.class);
                i.putExtra("f3",f);
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

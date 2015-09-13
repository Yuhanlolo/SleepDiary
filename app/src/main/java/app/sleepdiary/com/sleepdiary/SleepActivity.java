package app.sleepdiary.com.sleepdiary;

/**
 * Created by apple on 9/11/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SleepActivity extends ActionBarActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        String userid = getIntent().getStringExtra("userid");
        TextView tv = (TextView)findViewById(R.id.title);
        tv.setText(userid);
    }

    public void bedtimeOnClick(View view)
    {
        if(view.getId() == R.id.settings)
        {
            Intent i = new Intent(SleepActivity.this,SettingsActivity.class);
            SleepActivity.this.startActivity(i);
        }

        else if(view.getId() == R.id.bedtime)
        {
            Intent i = new Intent(SleepActivity.this,SleepDiaryActivity.class);
            SleepActivity.this.startActivity(i);
        }

     }
}

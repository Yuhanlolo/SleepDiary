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

        String userid = getIntent().getStringExtra("useridl");
        TextView tv = (TextView)findViewById(R.id.title);
        tv.setText(userid);

    }
}

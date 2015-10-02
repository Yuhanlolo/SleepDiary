package app.sleepdiary.com.sleepdiary;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Yuhan on 9/13/15.
 */
public class SleepDiaryActivity extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepdiary);

        Spinner spinner = (Spinner) findViewById(R.id.coffee_spinner);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.number);
               // Toast.makeText(SleepDiaryActivity.this, "你点击的是:"+languages[pos], 2000).show();
                // int numberofcoffee = getIntent().getStringExtra("userid");
                //TextView tv = (TextView)findViewById(R.id.title);
                //tv.setText("Welcome"+userid);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    }


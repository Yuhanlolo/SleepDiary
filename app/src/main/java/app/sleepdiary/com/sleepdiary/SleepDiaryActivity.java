package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
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


    SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepdiary);

    }

    public void button_CIdOnClick(View view)
    {
        if(view.getId() == R.id.save_s)
        {
            Spinner coffee = (Spinner)findViewById(R.id.coffee_spinner);
            int no_coffee = 0;
            Spinner tea = (Spinner)findViewById(R.id.tea_spinner);
            int no_tea = 0;


            if(true)
            {
                SleepdiaryInfo s = new SleepdiaryInfo();
                s.setNo_coffee(no_coffee);
                s.setNo_tea(no_tea);

                sleephelper.insertColumn(s);

                Intent i = new Intent(SleepDiaryActivity.this,SleepActivity.class);
                i.putExtra("coffee",no_coffee);
                SleepDiaryActivity.this.startActivity(i);

            }

            else
            {
                //popup msg
                Toast errorlogin = Toast.makeText(SleepDiaryActivity.this,"Please finish all the questions!", Toast.LENGTH_SHORT);
                errorlogin.show();
            }
        }

        if(view.getId() == R.id.cancel_s)
        {
            Intent i = new Intent(SleepDiaryActivity.this,SleepActivity.class);
            SleepDiaryActivity.this.startActivity(i);
        }
    }
    }


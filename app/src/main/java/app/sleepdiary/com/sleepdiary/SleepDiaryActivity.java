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
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

/**
 * Created by Yuhan on 9/13/15.
 */
public class SleepDiaryActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{


    String no_coffee = "";
    String no_wine = "";
    String no_smoke = "";
    String no_naptime = "";
    Spinner coffee, wine, smoke, naptime;


    SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepdiary);

       coffee = (Spinner)findViewById(R.id.coffee_spinner);
        //int no_coffee = 0;

        wine = (Spinner)findViewById(R.id.wine_spinner);
        // int no_tea = 0;

        smoke = (Spinner)findViewById(R.id.smoke_spinner);
        //int no_smoke = 0;
        naptime = (Spinner)findViewById(R.id.naptime_spinner);
        //int no_naptime = 0;

        coffee.setOnItemSelectedListener(this);
        wine.setOnItemSelectedListener(this);
        smoke.setOnItemSelectedListener(this);
        naptime.setOnItemSelectedListener(this);

    }

    public void button_SDdOnClick(View view)
    {
        if(view.getId() == R.id.save_s)
        {


            if(no_coffee.startsWith("-")||no_wine.startsWith("-")||no_smoke.startsWith("-")||no_naptime.startsWith("-"))
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity.this,"Please finish all the questions!", Toast.LENGTH_SHORT);
                errormsg.show();

            }

            else
            {
//                SleepdiaryInfo s = new SleepdiaryInfo();
//                s.setNo_coffee(Integer.parseInt(no_coffee));
//                s.setNo_wine(Integer.parseInt(no_wine));
//
//                s.setNo_smoke(Integer.parseInt(no_smoke));
//                s.setNo_naptime(Integer.parseInt(no_naptime));
//
//                sleephelper.insertColumn(s);

//                Intent i = new Intent(SleepDiaryActivity.this,SleepActivity.class);
//                i.putExtra("coffee",no_coffee);
//                SleepDiaryActivity.this.startActivity(i);
//                Toast msg = Toast.makeText(SleepDiaryActivity.this,"Finished this page!", Toast.LENGTH_SHORT);
//                msg.show();
            }
        }

        if(view.getId() == R.id.cancel_s)
        {
            Intent i = new Intent(SleepDiaryActivity.this,SleepActivity.class);
            SleepDiaryActivity.this.startActivity(i);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        String[] numbers = getResources().getStringArray(R.array.number);
        if(parent == findViewById(R.id.coffee_spinner))
        {
            no_coffee = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose coffee"+no_coffee, Toast.LENGTH_LONG).show();
        }
        else if(parent == findViewById(R.id.wine_spinner))
        {
            no_wine = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose tea"+no_wine, Toast.LENGTH_LONG).show();
        }
        else if(parent == findViewById(R.id.smoke_spinner))
        {
            no_smoke = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose smoke"+no_smoke, Toast.LENGTH_LONG).show();
        }
        else if(parent == findViewById(R.id.naptime_spinner))
        {
            no_naptime = numbers[pos];
            //Toast.makeText(SleepDiaryActivity.this, "you choose naptime"+no_naptime, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by ypl5142 on 10/5/15.
 */
public class SleepDiaryActivity3 extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3sleepdiary);
    }

    public void button_SD3dOnClick(View view)
    {
        if(view.getId() == R.id.save_s3)
        {


            boolean a = false;

            if(a)
            {
                //popup msg
                Toast errormsg = Toast.makeText(SleepDiaryActivity3.this,"Please finish all the questions!", Toast.LENGTH_SHORT);
                errormsg.show();

            }

            else
            {

//                SleepdiaryInfo s = new SleepdiaryInfo();
//                s.setNo_coffee(Integer.parseInt(no_coffee));
//                s.setNo_coffee(no_coffee);
//                s.setNo_wine(no_wine);
//
//                s.setNo_smoke(no_smoke);
//                s.setNo_naptime(no_naptime)
                //               s.setSleepdurationday(sleepduration);
//                s.setPilltime(pilltime);
//                s.setPillname(pillname);
//                sleephelper.insertColumn(s);

                Intent i = new Intent(SleepDiaryActivity3.this,SleepDiaryActivity3.class);

                SleepDiaryActivity3.this.startActivity(i);
//                Toast msg = Toast.makeText(SleepDiaryActivity.this,"Finished this page!", Toast.LENGTH_SHORT);
//                msg.show();
            }
        }

        if(view.getId() == R.id.cancel_s3)
        {
            Intent i = new Intent(SleepDiaryActivity3.this,SleepActivity.class);
            SleepDiaryActivity3.this.startActivity(i);
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

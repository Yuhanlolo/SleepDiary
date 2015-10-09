package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;

/**
 * Created by ypl5142 on 10/5/15.
 */
public class SleepDiaryActivity3 extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {

    SleepdiaryDBHepler sleephelper = new SleepdiaryDBHepler(this);
    RadioGroup  rg0, rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;
//    int index_urge = -1;
//    int index_muscle = -1;
//    int index_turnbed = -1;
//    int index_pain = -1;
//    int index_dream = -1;
//    int index_hall = -1;
//    int index_breath = -1;
//    int index_urine = -1;
//    int index_distur = -1;

    RadioButton rg0_1, rg0_0;
    RadioButton rg1_1, rg1_0;
    RadioButton rg2_1, rg2_0;
    RadioButton rg3_1, rg3_0;
    RadioButton rg4_1, rg4_0;
    RadioButton rg5_1, rg5_0;
    RadioButton rg6_1, rg6_0;
    RadioButton rg7_1, rg7_0;
    RadioButton rg8_1, rg8_0;

    int rbid01 =-1;
    int rbid00 =-1;

    int rbid11 =-1;
    int rbid10 =-1;

    int rbid21 =-1;
    int rbid20 =-1;

    int rbid31 =-1;
    int rbid30=-1;

    int rbid41 =-1;
    int rbid40 =-1;

    int rbid51 =-1;
    int rbid50 =-1;

    int rbid61 =-1;
    int rbid60 =-1;

    int rbid71 =-1;
    int rbid70 =-1;

    int rbid81 =-1;
    int rbid80=-1;

    String a_urge = "";
    String a_muscle = "";
    String a_tobed = "";
    String a_pain = "";
    String a_dream = "";
    String a_hall = "";
    String a_breath = "";
    String a_urine = "";
    String a_distur = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3sleepdiary);

        rg0 = (RadioGroup)findViewById(R.id.group0);
        rg1 = (RadioGroup)findViewById(R.id.group1);
        rg2 = (RadioGroup)findViewById(R.id.group2);
        rg3 = (RadioGroup)findViewById(R.id.group3);
        rg4 = (RadioGroup)findViewById(R.id.group4);
        rg5 = (RadioGroup)findViewById(R.id.group5);
        rg6 = (RadioGroup)findViewById(R.id.group6);
        rg7 = (RadioGroup)findViewById(R.id.group7);
        rg8 = (RadioGroup)findViewById(R.id.group8);

        rg0_1 = (RadioButton)findViewById(R.id.yes_urge);
        rg0_0 = (RadioButton)findViewById(R.id.no_urge);
        rbid01 = rg0_1.getId();
        rbid00 = rg0_0.getId();

        rg1_1 = (RadioButton)findViewById(R.id.yes_mu);
        rg1_0 = (RadioButton)findViewById(R.id.no_mu);
        rbid11 = rg1_1.getId();
        rbid10 = rg1_0.getId();

        rg2_1 = (RadioButton)findViewById(R.id.yes_bed);
        rg2_0 = (RadioButton)findViewById(R.id.no_bed);
        rbid21 = rg2_1.getId();
        rbid20 = rg2_0.getId();

        rg3_1 = (RadioButton)findViewById(R.id.yes_pain);
        rg3_0 = (RadioButton)findViewById(R.id.no_pain);
        rbid31 = rg3_1.getId();
        rbid30 = rg3_0.getId();

        rg4_1 = (RadioButton)findViewById(R.id.yes_dream);
        rg4_0 = (RadioButton)findViewById(R.id.no_dream);
        rbid41 = rg4_1.getId();
        rbid40 = rg4_0.getId();

        rg5_1 = (RadioButton)findViewById(R.id.yes_hall);
        rg5_0 = (RadioButton)findViewById(R.id.no_hall);
        rbid51 = rg5_1.getId();
        rbid50 = rg5_0.getId();

        rg6_1 = (RadioButton)findViewById(R.id.yes_breath);
        rg6_0 = (RadioButton)findViewById(R.id.no_breath);
        rbid61 = rg6_1.getId();
        rbid60 = rg6_0.getId();

        rg7_1 = (RadioButton)findViewById(R.id.yes_urine);
        rg7_0 = (RadioButton)findViewById(R.id.no_urine);
        rbid71 = rg7_1.getId();
        rbid70 = rg7_0.getId();

        rg8_1 = (RadioButton)findViewById(R.id.yes_distur);
        rg8_0 = (RadioButton)findViewById(R.id.no_distur);
        rbid81 = rg8_1.getId();
        rbid80 = rg8_0.getId();

        rg0.setOnCheckedChangeListener(this);
        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
        rg3.setOnCheckedChangeListener(this);
        rg4.setOnCheckedChangeListener(this);
        rg5.setOnCheckedChangeListener(this);
        rg6.setOnCheckedChangeListener(this);
        rg7.setOnCheckedChangeListener(this);
        rg8.setOnCheckedChangeListener(this);


    }

    public void button_SD3dOnClick(View view)
    {
        if(view.getId() == R.id.save_s3)
        {




            if(a_urge.isEmpty()||a_muscle.isEmpty()||a_tobed.isEmpty()||a_pain.isEmpty()||a_dream.isEmpty()||a_hall.isEmpty()||a_breath.isEmpty()||a_urine.isEmpty()||a_distur.isEmpty())
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

                Intent i = new Intent(SleepDiaryActivity3.this,SleepActivity.class);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group == rg0)
        {
            if(checkedId == rbid01)
            {
                a_urge = "Y";
            }
            else
            {
                a_urge = "N";
            }
        }
        if(group == rg1)
        {
            if(checkedId == rbid11)
            {
                a_muscle = "Y";
            }
            else
            {
                a_muscle = "N";
            }
        }
        if(group == rg2)
        {
            if(checkedId == rbid21)
            {
                a_tobed = "Y";
            }
            else
            {
                a_tobed ="N";
            }
        }
        if(group == rg3)
        {
            if(checkedId == rbid31)
            {
                a_pain = "Y";
            }
            else
            {
                a_pain = "N";
            }
        }
        if(group == rg4)
        {
            if(checkedId == rbid41)
            {
                a_dream = "Y";
//                Toast msg = Toast.makeText(SleepDiaryActivity3.this,"Yes", Toast.LENGTH_SHORT);
//                msg.show();
            }
            else
            {
                a_dream = "N";
//                Toast msg = Toast.makeText(SleepDiaryActivity3.this,"No", Toast.LENGTH_SHORT);
//                msg.show();
            }
        }
        if(group == rg5)
        {
            if(checkedId == rbid51)
            {
             a_hall = "Y";
            }
            else
            {
              a_hall = "N";
            }
        }
        if(group == rg6)
        {
            if(checkedId == rbid61)
            {
               a_breath = "Y";
            }
            else
            {
               a_breath = "N";
            }
        }
        if(group == rg7)
        {
            if(checkedId == rbid71)
            {
               a_urine = "Y";
            }
            else
            {
             a_urine = "N";
            }
        }
        if(group == rg8)
        {
            if(checkedId == rbid81)
            {
               a_distur = "Y";
            }
            else
            {
               a_distur = "N";
            }
        }
    }
}

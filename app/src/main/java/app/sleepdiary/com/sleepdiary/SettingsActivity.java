package app.sleepdiary.com.sleepdiary;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.support.v7.app.ActionBarActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseAnalytics;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class SettingsActivity extends ActionBarActivity implements View.OnClickListener {

   Button CreateID, Login;
    ImageView Logout;
    ImageView restart;
    int researcher = 0;
    String lastpage = "practice";
    ParseUser currentUser;
    ParseQuery<ParseObject> query0 = ParseQuery.getQuery("TaskCheckList");
    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Sleep_Diary");
    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("MDOPA1");
    ParseQuery<ParseObject> query3 = ParseQuery.getQuery("ADOPA");

    ParseQuery<ParseObject> query4 = ParseQuery.getQuery("E");
    ParseQuery<ParseObject> query5 = ParseQuery.getQuery("Nap_1");
    ParseQuery<ParseObject> query6 = ParseQuery.getQuery("Nap_2");
    ParseQuery<ParseObject> query7 = ParseQuery.getQuery("Nap_3");
    ParseQuery<ParseObject> query8 = ParseQuery.getQuery("Nap_4");
    ParseQuery<ParseObject> query9 = ParseQuery.getQuery("Nap_5");
    ParseQuery<ParseObject> query10 = ParseQuery.getQuery("Nap_");


    //ParseObject lp  = new ParseObject("Lastpage");
    //ParseQuery<ParseObject> query11 = ParseQuery.getQuery("Lastpage");
    //ParseQuery<ParseObject> querylp2 = ParseQuery.getQuery("Lastpage");
    String userid = "";
    String today = "";
    //String  link = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        CreateID = (Button)findViewById(R.id.CreateId);
        Login = (Button)findViewById(R.id.Login);
        Logout = (ImageView)findViewById(R.id.Logout);

        restart = (ImageView)findViewById(R.id.restart);
        restart.setOnClickListener(this);


        ParseUser currentUser = ParseUser.getCurrentUser();



        if (currentUser == null)
        {
            CreateID.setVisibility(View.VISIBLE);
            Login.setVisibility(View.VISIBLE);
            Logout.setVisibility(View.INVISIBLE);
            //btp.setVisibility(View.INVISIBLE);
        }

        else
        {
            CreateID.setVisibility(View.INVISIBLE);
            Login.setVisibility(View.INVISIBLE);
            Logout.setVisibility(View.VISIBLE);
            //btp.setVisibility(View.VISIBLE);
            userid = currentUser.getUsername();
            researcher =currentUser.getInt("Researcher");
            if (researcher == 1)
            {
                restart.setVisibility(View.VISIBLE);

            }
            else
            {
                restart.setVisibility(View.INVISIBLE);
            }

        }

        final Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

 //   @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
////            return true;
//            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
//            MainActivity.this.startActivity(i);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    public void button_createIDOnClick(View view)
    {
        if(view.getId() == R.id.CreateId)
        {
            Intent i = new Intent(SettingsActivity.this,CreateIdActivity.class);
            SettingsActivity.this.startActivity(i);
        }

        if(view.getId() == R.id.Logout)
        {
            final Dialog dialoglogout = new Dialog(SettingsActivity.this);
            dialoglogout.setTitle("");

            dialoglogout.setContentView(R.layout.logoutalert);
            dialoglogout.show();



            Button cdt = (Button)dialoglogout.findViewById(R.id.cancel_logout);
            Button sdt = (Button)dialoglogout.findViewById(R.id.ok_logout);

            cdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialoglogout.cancel();
                }
            });


            sdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ParseUser.logOut();
                    //ParseUser user = ParseUser.getCurrentUser();
                    dialoglogout.cancel();
                    Intent i = new Intent(SettingsActivity.this, SettingsActivity.class);
                    SettingsActivity.this.startActivity(i);
                }
            });



        }
    }

    public void button_LoginOnClick(View view)
    {
        if(view.getId() == R.id.Login)
        {
            Intent i = new Intent(SettingsActivity.this,LoginActivity.class);
            //startActivity(i);
            SettingsActivity.this.startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.restart){
            if (researcher == 1)
            {
        query0.whereEqualTo("User_ID",userid);
        //query.whereEqualTo("Date",today);
        //query0.setLimit(1);
        query0.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e ==null){
                try {
                   for(int i = 0;i<objects.size();i++){
                       objects.get(i).delete();
                   }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                    Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                    msg.show();
                }}
                else
                {
                  Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                  msg.show();
                }
            }
        });

                query1.whereEqualTo("User_ID",userid);

                query1.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });


                query2.whereEqualTo("User_ID",userid);

                query2.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });

                query3.whereEqualTo("User_ID",userid);

                query3.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });


                query4.whereEqualTo("User_ID",userid);

                query4.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });


                query5.whereEqualTo("User_ID",userid);

                query5.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });


                query6.whereEqualTo("User_ID",userid);

                query6.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });


                query7.whereEqualTo("User_ID",userid);

                query7.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });

                query8.whereEqualTo("User_ID",userid);

                query8.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });


                query9.whereEqualTo("User_ID",userid);

                query9.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });

                query10.whereEqualTo("User_ID",userid);

                query10.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e ==null){
                            try {
                                for(int i = 0;i<objects.size();i++){
                                    objects.get(i).delete();
                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                                Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                                msg.show();
                            }}
                        else
                        {
                            Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser+"No data to be deleted!", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                });

                Toast msg = Toast.makeText(SettingsActivity.this,"Data cleared!", Toast.LENGTH_SHORT);
                msg.show();
            }
        }

}

}

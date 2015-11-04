package app.sleepdiary.com.sleepdiary;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.support.v7.app.ActionBarActivity;
import com.parse.ParseUser;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseAnalytics;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends ActionBarActivity {

   Button CreateID, Login;
    ImageView Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        CreateID = (Button)findViewById(R.id.CreateId);
        Login = (Button)findViewById(R.id.Login);
        Logout = (ImageView)findViewById(R.id.Logout);

        ParseUser currentUser = ParseUser.getCurrentUser();
//        Toast msg = Toast.makeText(SettingsActivity.this,"User"+ currentUser, Toast.LENGTH_SHORT);
//        msg.show();

        if (currentUser == null)
        {
            CreateID.setVisibility(View.VISIBLE);
            Login.setVisibility(View.VISIBLE);
            Logout.setVisibility(View.INVISIBLE);
        }

        else
        {
            CreateID.setVisibility(View.INVISIBLE);
            Login.setVisibility(View.INVISIBLE);
            Logout.setVisibility(View.VISIBLE);

        }




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
                    Intent i = new Intent(SettingsActivity.this, MainActivity.class);
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
}

package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.FindCallback;
import java.util.Calendar;
import java.util.List;
import android.util.Log;
import android.webkit.WebView;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Context;
/**
 * Created by ypl5142 on 10/25/15.
 */
public class MainActivity  extends ActionBarActivity {

    boolean M30 = false;
    boolean A_DOPA1= false;
    boolean A_DOPA = false;
    boolean E = false;

    boolean login_status = false;
//    int month = 0;
//    int date = 0;
//    int year = 0;
    //String yesterdaystr = "";
    ImageView finish_M30,finish_adi,finish_bdi,finish_E;
    String userid = "";

    ParseUser currentUser;

    final Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;
    int date = cal.get(Calendar.DATE);
    int year = cal.get(Calendar.YEAR);
    String today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);

    String lastpage = "";
    String endstr = "";
   // ParseObject TaskCheckList  = new ParseObject("TaskCheckList");
   ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TaskCheckList");
    ParseQuery<ParseObject> queryt = ParseQuery.getQuery("TaskCheckList");
    ParseObject TaskCheckList  = new ParseObject("TaskCheckList");

    String end = "";
    String end0 = "Thank you for completing Morning test! Please come back for Morning, After Drug-intake test.";
    String end1 = "Thank you for completing Morning, After Drug-intake test! Please come back for Afternoon, Before Drug-intake test.";
    String end2 = "Thank you for completing Afternoon, Before Drug-intake test! Please come back for Bed Time test.";
    String end3 = "Thank you for completing all the tests for today!";
    String end4 = "Thank you for completing the Nap test!";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();




//        if(Intent.ACTION_VIEW.equals(action)){
//            Uri uri = i_getvalue.getData();
//            if(uri != null){
//                String name = uri.getQueryParameter("name");
//                String age= uri.getQueryParameter("age");
//            }
//        }


        finish_M30 = (ImageView)findViewById(R.id.fmt);
        finish_adi = (ImageView)findViewById(R.id.fadi);
        finish_bdi = (ImageView)findViewById(R.id.fbdi);
        finish_E = (ImageView)findViewById(R.id.fbt);

        if (isNetworkAvailable()) {
            // Do your stuff here.
            currentUser = ParseUser.getCurrentUser();

            if(currentUser != null) {
                userid = ParseUser.getCurrentUser().getUsername();

                queryt.whereEqualTo("User_ID", userid);
                queryt.whereEqualTo("Date", today);


                queryt.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "create task list."+userid);
                            TaskCheckList.put("User_ID", userid);
                            TaskCheckList.put("Date", today);
                            TaskCheckList.put("Nap",0);
                            TaskCheckList.put("Nap_Braintest",0);
                            TaskCheckList.put("Nap_Movesleep",0);
                            TaskCheckList.saveInBackground();

                        }
                    }
                });

                query1.whereEqualTo("User_ID", userid);
                query1.whereEqualTo("Date", today);
                query1.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            Log.d("User_ID", "The getFirst request failed.");

                        } else {
                            //Log.d("score", "Retrieved the object.");
                            //if(object.getInt("MBraintest")== 0 || object.getInt("MBraintest") ==1)

                            if (object.getInt("M30_Sleepdiary") == 1 && object.getInt("M30_Movesleep") == 1&& object.getInt("M30_Braintest") == 1) {
                                M30 = true;
                                finish_M30.setVisibility(View.VISIBLE);
                            }

                            if ( object.getInt("MDOPA1_Movesleep") == 1&& object.getInt("MDOPA1_Braintest") == 1) {
                                A_DOPA1 = true;
                                finish_adi.setVisibility(View.VISIBLE);
                            }

                            if ( object.getInt("ADOPA_Movesleep") == 1&& object.getInt("ADOPA_Braintest") == 1) {
                                A_DOPA = true;
                                finish_bdi.setVisibility(View.VISIBLE);
//                            Toast pass = Toast.makeText(MainActivity.this,"ADOPA TRUE", Toast.LENGTH_SHORT);
//                            pass.show();
                            }

                            if (object.getInt("E_Movesleep") == 1&& object.getInt("E_Braintest") == 1) {
                                E = true;
                                finish_E.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }


        }
        else {
            Toast pass = Toast.makeText(MainActivity.this, "Network is not available, please check your network.", Toast.LENGTH_LONG);
            pass.show();
        }

        //login_status = getIntent().getBooleanExtra("loginstatus",false);



        //M30 = getIntent().getBooleanExtra("f3",false);


//        if(M30)
//        {
//            finish_M30.setVisibility(View.VISIBLE);
//        }
//        if(A_DOPA1)
//        {
//            finish_adi.setVisibility(View.VISIBLE);
//        }
//        if(A_DOPA)
//        {
//            finish_bdi.setVisibility(View.VISIBLE);
//        }
//        if(E)
//        {
//            finish_E.setVisibility(View.VISIBLE);
//        }
        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();
        endstr = i_getvalue.getStringExtra("endstr");

        if(endstr!=null)
        {

            if(endstr.equals("end0")){
                end=end0;
                Toast pass = Toast.makeText(MainActivity.this,end, Toast.LENGTH_LONG);
                for (int i  = 0 ; i< 2;i++)
                    pass.show();
            }
            else if(endstr.equals("end1")){
                end=end1;
                Toast pass = Toast.makeText(MainActivity.this,end, Toast.LENGTH_LONG);
                for (int i  = 0 ; i< 2;i++)
                    pass.show();
            }
            else if(endstr.equals("end2")){
                end=end2;
                Toast pass = Toast.makeText(MainActivity.this,end, Toast.LENGTH_LONG);
                for (int i  = 0 ; i< 2;i++)
                    pass.show();
            }
            else if(endstr.equals("end3")){
                end=end3;
                Toast pass = Toast.makeText(MainActivity.this,end, Toast.LENGTH_LONG);
                for (int i  = 0 ; i< 2;i++)
                    pass.show();
            }
            else if(endstr.equals("end4")){
                end=end4;
                Toast pass = Toast.makeText(MainActivity.this,end, Toast.LENGTH_LONG);
                for (int i  = 0 ; i< 2;i++)
                    pass.show();
            }


        }


    }

//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        if(url.startsWith("www.yuhanexample.com")) {
//            Intent MnRegister = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(MnRegister);
//        }
//        view.loadUrl(url);
//        return true;
//    }

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

            if (id == R.id.action_settings) {
                Intent i = new Intent(MainActivity.this,SettingsActivity.class);
                MainActivity.this.startActivity(i);

            }


        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    public void CoverOnClick(View view) {


        if (isNetworkAvailable()) {
            //if network is available
            if (view.getId() == R.id.mt||view.getId() == R.id.fmt)
            {
                currentUser = ParseUser.getCurrentUser();
                if(currentUser == null)
                {
                    Toast pass = Toast.makeText(MainActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                    pass.show();
                }

                else if (M30)
                {
                    Toast pass = Toast.makeText(MainActivity.this,"You have finished this part!", Toast.LENGTH_SHORT);
                    pass.show();
                }
                else
                {

                    Intent i = new Intent(MainActivity.this, SleepActivity.class);
                    lastpage = "M30";
                    i.putExtra("lastpage",lastpage);
                    MainActivity.this.startActivity(i);
                }
            }

            if (view.getId() == R.id.adi||view.getId() == R.id.fadi)
            {
                currentUser = ParseUser.getCurrentUser();
                if(currentUser == null)
                {
                    Toast pass = Toast.makeText(MainActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                    pass.show();
                }

//            else if (A_DOPA1)
//            {
//                Toast pass = Toast.makeText(MainActivity.this,"You have finished this part!", Toast.LENGTH_SHORT);
//                pass.show();
//            }
                else
                {
                    Intent i = new Intent(MainActivity.this, SleepActivity.class);
                    lastpage = "MDOPA1";
                    i.putExtra("lastpage",lastpage);
                    MainActivity.this.startActivity(i);
                }
            }

            if (view.getId() == R.id.bdi||view.getId() == R.id.fbdi)
            {
                currentUser = ParseUser.getCurrentUser();
                if(currentUser == null)
                {
                    Toast pass = Toast.makeText(MainActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                    pass.show();
                }

                else if (A_DOPA)
                {
                    Toast pass = Toast.makeText(MainActivity.this,"You have finished this part!", Toast.LENGTH_SHORT);
                    pass.show();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, SleepActivity.class);
                    lastpage = "A_DOPA";
                    i.putExtra("lastpage",lastpage);
                    MainActivity.this.startActivity(i);
                }
            }

            if (view.getId() == R.id.btt||view.getId() == R.id.fbt)
            {
                currentUser = ParseUser.getCurrentUser();
                if(currentUser == null)
                {
                    Toast pass = Toast.makeText(MainActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                    pass.show();
                }

                else if (E)
                {
                    Toast pass = Toast.makeText(MainActivity.this,"You have finished this part!", Toast.LENGTH_SHORT);
                    pass.show();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, SleepActivity.class);
                    lastpage = "E";
                    i.putExtra("lastpage",lastpage);
                    MainActivity.this.startActivity(i);
                }
            }

            if (view.getId() == R.id.naptitle)
            {
                currentUser = ParseUser.getCurrentUser();
                if(currentUser == null)
                {
                    Toast pass = Toast.makeText(MainActivity.this,"Please Login in first!", Toast.LENGTH_SHORT);
                    pass.show();
                }


                else
                {
                    Intent i = new Intent(MainActivity.this, SleepActivity.class);
                    lastpage = "Nap";
                    i.putExtra("lastpage",lastpage);
                    MainActivity.this.startActivity(i);
                }
            }
        }
        else {
            Toast pass = Toast.makeText(MainActivity.this, "Network is not available, please check your network.", Toast.LENGTH_LONG);
            pass.show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MainActivity.this,MainActivity.class);
        //i.putExtra("lastpage",lastpage);
        //startService(j);
        startActivity(i);

    }
}

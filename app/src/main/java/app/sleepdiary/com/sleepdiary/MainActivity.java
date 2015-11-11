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

   // ParseObject TaskCheckList  = new ParseObject("TaskCheckList");
   ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TaskCheckList");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();

//        if(Intent.ACTION_VIEW.equals(action)){
//            Uri uri = i_getvalue.getData();
//            if(uri != null){
//                String name = uri.getQueryParameter("name");
//                String age= uri.getQueryParameter("age");
//            }
//        }

       currentUser = ParseUser.getCurrentUser();

        if(currentUser != null) {
            userid = ParseUser.getCurrentUser().getUsername();
            query1.whereEqualTo("User_ID", userid);
            query1.whereEqualTo("Date", today);

            query1.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (object == null) {
                        Log.d("User_ID", "The getFirst request failed.");

                    } else {
                        //Log.d("score", "Retrieved the object.");
                        //if(object.getInt("MBraintest")== 0 || object.getInt("MBraintest") ==1)

                        if (object.getInt("MSleepdiary") == 1 && object.getInt("MMovesleep") == 1) {
                            M30 = true;
                            finish_M30.setVisibility(View.VISIBLE);
                        }

                        if (object.getInt("AdiBraintest") == 1 && object.getInt("AdiMovesleep") == 1) {
                            A_DOPA1 = true;
                            finish_adi.setVisibility(View.VISIBLE);
                        }

                        if (object.getInt("BdiBraintest") == 1 && object.getInt("BdiMovesleep") == 1) {
                            A_DOPA = true;
                            finish_bdi.setVisibility(View.VISIBLE);
                        }

                        if (object.getInt("BdiBraintest") == 1 && object.getInt("BdiMovesleep") == 1) {
                            E = true;
                            finish_E.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }

        finish_M30 = (ImageView)findViewById(R.id.fmt);
        finish_adi = (ImageView)findViewById(R.id.fadi);
        finish_bdi = (ImageView)findViewById(R.id.fbdi);
        finish_E = (ImageView)findViewById(R.id.fbt);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//          return true;
            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            MainActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void CoverOnClick(View view) {


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

            else if (A_DOPA1)
            {
                Toast pass = Toast.makeText(MainActivity.this,"You have finished this part!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
                Intent i = new Intent(MainActivity.this, Dopa1Activity.class);
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
                Intent i = new Intent(MainActivity.this, Dopa1Activity.class);
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

            else if (A_DOPA1)
            {
                Toast pass = Toast.makeText(MainActivity.this,"You have finished this part!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
                Intent i = new Intent(MainActivity.this, Dopa1Activity.class);
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

            else if (A_DOPA1)
            {
                Toast pass = Toast.makeText(MainActivity.this,"You have finished this part!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
                Intent i = new Intent(MainActivity.this, Dopa1Activity.class);
                MainActivity.this.startActivity(i);
            }
        }
    }

}

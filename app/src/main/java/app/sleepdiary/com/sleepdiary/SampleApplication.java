package app.sleepdiary.com.sleepdiary;

/**
 * Created by ypl5142 on 10/12/15.
 */

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Calendar;

public class SampleApplication extends Application {

    ParseObject TaskCheckList  = new ParseObject("TaskCheckList");
    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TaskCheckList");
    String userid = "";
    final Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;
    int date = cal.get(Calendar.DATE);
    int year = cal.get(Calendar.YEAR);
    String today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);
    //String yesterday =String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);
    ParseUser currentUser;

    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

        currentUser = ParseUser.getCurrentUser();

        if(currentUser != null) {
            userid = ParseUser.getCurrentUser().getUsername();
            query1.whereEqualTo("User_ID", userid);
            query1.whereEqualTo("Date", today);

            //object.getInt("A_DOPA1_Braintest") == 1 &&
            //object.getInt("A_DOPA_Braintest") == 1 &&
            //object.getInt("E_Braintest") == 1 &&

            query1.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (object == null) {
                        Log.d("User_ID", "The getFirst request failed.");
                        TaskCheckList.put("User_ID", ParseUser.getCurrentUser().getUsername());
                        TaskCheckList.put("Date", today);

                        TaskCheckList.put("M30_Briantest", 0);
                        TaskCheckList.put("M30_Sleepdiary", 0);
                        TaskCheckList.put("M30_Movesleep", 0);

                        TaskCheckList.put("MDOPA1_Briantest", 0);
                        TaskCheckList.put("MDOPA1_Movesleep", 0);

                        TaskCheckList.put("A_DOPA_Briantest", 0);
                        TaskCheckList.put("A_DOPA_Movesleep", 0);

                        TaskCheckList.put("E_Briantest", 0);
                        TaskCheckList.put("E_Movesleep", 0);

                        TaskCheckList.saveInBackground();

                    }
                }
            });
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
}
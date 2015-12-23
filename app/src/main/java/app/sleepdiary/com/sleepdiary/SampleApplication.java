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

    ParseObject Sleepdiary  = new ParseObject("Sleep_Diary");
    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Sleep_Diary");

    ParseObject M30_Movesleep  = new ParseObject("M30");
    ParseQuery<ParseObject> query3 = ParseQuery.getQuery("M30");

    ParseObject MDOPA1_MoveSleep  = new ParseObject("MDOPA1");
    ParseQuery<ParseObject> query4 = ParseQuery.getQuery("MDOPA1");

    ParseObject DOPA_Movesleep  = new ParseObject("ADOPA");
    ParseQuery<ParseObject> query5 = ParseQuery.getQuery("ADOPA");

    ParseObject E_Movesleep  = new ParseObject("E");
    ParseQuery<ParseObject> query6 = ParseQuery.getQuery("E");

    ParseObject Nap_Movesleep  = new ParseObject("Nap");
    ParseQuery<ParseObject> query7 = ParseQuery.getQuery("Nap");

    String userid = "";
    final Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;
    int date = cal.get(Calendar.DATE);
    int year = cal.get(Calendar.YEAR);
    String today = String.valueOf(month)+"/"+String.valueOf(date)+"/"+String.valueOf(year);

    String yesterday ="";
    ParseUser currentUser;

    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        //Parse.initialize(this);
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

        if (date == 1) {
            month = month - 1;
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                date = 31;
            } else {
                date = 30;
            }
        } else {
            date = date - 1;
        }

        yesterday = String.valueOf(month) + "/" + String.valueOf(date) + "/" + String.valueOf(year);
        currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            userid = ParseUser.getCurrentUser().getUsername();
            query1.whereEqualTo("User_ID", userid);
            query1.whereEqualTo("Date", today);

            //object.getInt("MDOPA1_Braintest") == 1 &&
            //object.getInt("ADOPA_Braintest") == 1 &&
            //object.getInt("E_Braintest") == 1 &&

            query1.getFirstInBackground(new GetCallback<ParseObject>() {
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
//
//
//            query2.whereEqualTo("User_ID", userid);
//            query2.whereEqualTo("Date", today);
//            query2.getFirstInBackground(new GetCallback<ParseObject>() {
//                public void done(ParseObject object, ParseException e) {
//                    if (object == null) {
//                        Log.d("User_ID", "create"+userid);
//                        Sleepdiary.put("User_ID", ParseUser.getCurrentUser().getUsername());
//                        Sleepdiary.put("Date", today);
//
//                        Sleepdiary.saveInBackground();
//
//                    }
//                }
//            });
//
//            query3.whereEqualTo("User_ID", userid);
//            query3.whereEqualTo("Date", today);
//            query3.getFirstInBackground(new GetCallback<ParseObject>() {
//                public void done(ParseObject object, ParseException e) {
//                    if (object == null) {
//                        //Log.d("User_ID", "The getFirst request failed.");
//                        M30_Movesleep.put("User_ID", ParseUser.getCurrentUser().getUsername());
//                        M30_Movesleep.put("Date", today);
//                        M30_Movesleep.saveInBackground();
//
//                    }
//                }
//            });
//
//            query4.whereEqualTo("User_ID", userid);
//            query4.whereEqualTo("Date", today);
//            query4.getFirstInBackground(new GetCallback<ParseObject>() {
//                public void done(ParseObject object, ParseException e) {
//                    if (object == null) {
//                        //Log.d("User_ID", "The getFirst request failed.");
//                        MDOPA1_MoveSleep.put("User_ID", ParseUser.getCurrentUser().getUsername());
//                        MDOPA1_MoveSleep.put("Date", today);
//                        MDOPA1_MoveSleep.saveInBackground();
//
//                    }
//                }
//            });
//
//            query5.whereEqualTo("User_ID", userid);
//            query5.whereEqualTo("Date", today);
//            query5.getFirstInBackground(new GetCallback<ParseObject>() {
//                public void done(ParseObject object, ParseException e) {
//                    if (object == null) {
//                        //Log.d("User_ID", "The getFirst request failed.");
//                        DOPA_Movesleep.put("User_ID", ParseUser.getCurrentUser().getUsername());
//                        DOPA_Movesleep.put("Date", today);
//                        DOPA_Movesleep.saveInBackground();
//
//                    }
//                }
//            });
//            query6.whereEqualTo("User_ID", userid);
//            query6.whereEqualTo("Date", today);
//            query6.getFirstInBackground(new GetCallback<ParseObject>() {
//                public void done(ParseObject object, ParseException e) {
//                    if (object == null) {
//                        //Log.d("User_ID", "The getFirst request failed.");
//                        E_Movesleep.put("User_ID", ParseUser.getCurrentUser().getUsername());
//                        E_Movesleep.put("Date", today);
//
//                        E_Movesleep.saveInBackground();
//
//                    }
//                }
//            });
//
//
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

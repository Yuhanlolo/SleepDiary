package app.sleepdiary.com.sleepdiary;

/**
 * Created by ypl5142 on 10/12/15.
 */

import android.app.Application;
import android.content.Intent;
import android.webkit.WebView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class SampleApplication extends Application {

    ParseObject TaskCheckList  = new ParseObject("TaskCheckList");
    ParseUser currentUser;
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

//        currentUser = ParseUser.getCurrentUser();
//
//        if(currentUser != null) {
//
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

}

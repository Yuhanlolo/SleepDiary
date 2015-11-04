package app.sleepdiary.com.sleepdiary;

/**
 * Created by ypl5142 on 10/12/15.
 */

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class SampleApplication extends Application {

    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

        //


    }

}

package app.sleepdiary.com.sleepdiary;

/**
 * Created by ypl5142 on 10/12/15.
 */

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class SampleApplication extends Application {

    public void OnCreate()
    {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "8wojV1DKT8Wc5mj3VG2J7LY3wHpN9zzuXmoGSRHm", "ygOecPEu6Umd4QIqWhp3qUW3hSzoe5kIxJ7r9Czc");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

    }
}

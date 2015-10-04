package app.sleepdiary.com.sleepdiary;

/**
 * Created by ypl5142 on 10/4/15.
 */
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class SelectingItem implements AdapterView.OnItemSelectedListener{

    public String value = "";
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

        Toast.makeText(parent.getContext(), "Selecting Item : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
    }

    public String onItem(AdapterView<?> parent, View view, int pos,long id) {
        value = parent.getItemAtPosition(pos).toString();
        //parent.getSelectedItem();
            return parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}

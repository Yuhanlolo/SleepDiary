package app.sleepdiary.com.sleepdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
/**
 * Created by apple on 8/31/15.
 */
public class CreateIdActivity extends ActionBarActivity {

    dataBaseHelper helper = new dataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createid);
    }

    public void button_CIdOnClick (View view)
    {
        if(view.getId()==R.id.createdId)
        {
            EditText userid = (EditText)findViewById(R.id.useridc);
            EditText pass1 = (EditText)findViewById(R.id.pwd);
            EditText pass2 = (EditText)findViewById(R.id.conpwd);

            String useridstr = userid.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();



           if(helper.findExistId(useridstr))
            {
                Toast pass = Toast.makeText(CreateIdActivity.this,"The ID has already existed!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else  if(!pass1str.equals(pass2str))
           {
               //popup msg
               Toast pass = Toast.makeText(CreateIdActivity.this,"Password don't match!", Toast.LENGTH_SHORT);
               pass.show();
           }
            else
            {
                Contact c = new Contact();
                c.setId(useridstr);
                c.setPwd(pass1str);

                helper.insertColumn(c);

                Intent i = new Intent(CreateIdActivity.this,SleepActivity.class);
                i.putExtra("userid",useridstr);
                CreateIdActivity.this.startActivity(i);

            }

        }

        if(view.getId() == R.id.cancel_c)
        {
            Intent i = new Intent(CreateIdActivity.this,MainActivity.class);
            CreateIdActivity.this.startActivity(i);
        }



    }
}
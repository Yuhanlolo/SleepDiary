package app.sleepdiary.com.sleepdiary;

/**
 * Created by apple on 9/13/15.
 */
public class Contact {

    String userid, pwd;

    public void setId(String id)
    {
        this.userid = id;
    }

    public String getId()
    {
        return userid;
    }

    public void setPwd(String pass)
    {
        this.pwd = pass;
    }

    public String getPwd()
    {
        return pwd;
    }
}

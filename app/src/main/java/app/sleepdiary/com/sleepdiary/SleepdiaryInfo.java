package app.sleepdiary.com.sleepdiary;

/**
 * Created by Yuhan on 9/29/15.
 */
public class SleepdiaryInfo {
    int no_coffee, no_tea, no_smoke, no_naptime, no_pill;
    int no_wake, sleepface, wakeface;
    String sleepdurationday, pillname, bedtime, sleeptime, woketime, uptime, sleepdurationnight;
    String urgemove, musclecramp, diffturninbed, pain, distressing, hallucination, diffbreath, passurine, environdistur;

    //yesterday
    public void setNo_coffee(int number)
    {
        this.no_coffee = number;
    }

    public int getNo_coffee()
    {
        return no_coffee;
    }

    public void setNo_tea(int number)
    {
        this.no_tea = number;
    }

    public int getNo_tea()
    {
        return no_tea;
    }

    public void setNo_smoke(int number)
    {
        this.no_smoke = number;
    }

    public int getNo_smoke()
    {
        return no_smoke;
    }

    public void setNo_naptime(int number)
    {
        this.no_naptime = number;
    }

    public int getNo_naptime()
    {
        return no_naptime;
    }

    public void setSleepdurationday(String duration)
    {
        this.sleepdurationday = duration;
    }

    public String getSleepdurationday()
    {
        return sleepdurationday;
    }


}

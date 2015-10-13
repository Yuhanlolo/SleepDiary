package app.sleepdiary.com.sleepdiary;

/**
 * Created by Yuhan on 9/29/15.
 */
public class SleepdiaryInfo {
    int day;
    int no_coffee, no_wine, no_smoke, no_naptime;
    String sleepdurationday, pilltime,pillname, bedtime, sleeptime, woketime, uptime;
    int no_wake, sleepface, wakeface;
    String urgemove, musclecramp, diffturninbed, pain, distressing, hallucination, diffbreath, passurine, environdistur;

    public void setDay (){}
    public int getDay(){return day;}

    //page1
    public void setNo_coffee(int number) {this.no_coffee = number;}

    public int getNo_coffee() {return no_coffee;}

    public void setNo_wine(int number) {this.no_wine = number;}

    public int getNo_wine() {return no_wine;}

    public void setNo_smoke(int number)
    {
        this.no_smoke = number;
    }

    public int getNo_smoke()
    {
        return no_smoke;
    }

    public void setNo_naptime(int number) {this.no_naptime = number;}

    public int getNo_naptime()
    {
        return no_naptime;
    }

    public void setSleepdurationday(String duration) {this.sleepdurationday = duration;}

    public String getSleepdurationday()
    {
        return sleepdurationday;
    }

    public void setPilltime(String pillt)
    {
        this.pilltime = pillt;
    }

    public String getPilltime()
    {
        return pilltime;
    }

    public void setPillname(String pilln) {this.pillname= pilln;}

    public String getPillname() {return pillname;}

    //page2
    public void setbedtime(String t){bedtime = t;}

    public String getbedtime (){return bedtime;}

    public void setsleeptime(String t){sleeptime = t;}

    public String getsleeptime (){return sleeptime;}

    public void setwoketime(String t){woketime = t;}

    public String getwoketime (){return woketime;}

    public void setuptime(String t){uptime = t;}

    public String getuptime (){return uptime;}

    public void setWaketime(int n){no_wake = n;}

    public int getWaketime(){return no_wake;}

    public void setSleepface(int face){sleepface = face;}

    public int getSleepface(){return sleepface;}

    public void setWakeface(int face){wakeface = face;}

    public int getWakeface(){return wakeface;}

    //page3
    public void setUrgemove(String answer){urgemove = answer;}

    public String getUrgemove(){return urgemove;}

    public void setMusclecramp(String answer){musclecramp = answer;}

    public String getMusclecramp(){return musclecramp;}

    public void setDiffturninbed(String answer){diffturninbed = answer;}

    public String getDiffturninbed(){return diffturninbed;}

    public void setPain(String answer){pain = answer;}

    public String getPain(){return pain;}

    public void setDistressing(String answer){distressing = answer;}

    public String getDistressing(){return distressing;}

    public void setHallucination(String answer){hallucination = answer;}

    public String getHallucination(){return hallucination;}

    public void setDiffbreath(String answer){diffbreath = answer;}

    public String getDiffbreath(){return diffbreath;}

    public void setPassurine(String answer){passurine = answer;}

    public String getPassurine(){return passurine;}

    public void setEnvirondistur(String answer){environdistur = answer;}

    public String getEnvirondistur(){return environdistur;}

}

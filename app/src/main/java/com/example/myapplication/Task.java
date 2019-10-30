package com.example.myapplication;
import java.util.Calendar;
import java.util.Date;

public class Task
{
    private String name;
    private String description;
    private int reqLevel;
    private boolean unlocked;
    private Idol slot[]; //
    private long startTime; //
    private long processTime;
    private int danceAff;
    private int singAff;
    private int charmAff;

    public Task(String name, String description, int reqLevel, boolean unlocked, long processTime, int danceAff, int singAff, int charmAff)
    {
//        SetName(name);
//        SetDescription(description);
//        SetReqLevel(reqLevel);
        this.name = name;
        this.description = description;
        this.reqLevel = reqLevel;
        this.unlocked = unlocked;
        this.processTime = processTime;
        this.danceAff = danceAff;
        this.singAff = singAff;
        this.charmAff = charmAff;

        startTime = 0;
    }

//    private void SetName(String name){ this.name = name; }
//    private void SetDescription(String description){ this.name = description; }
//    private void SetReqLevel(int reqLevel){ this.reqLevel = reqLevel; }

    private boolean startTask()
    {
        boolean ret;
        if (startTime == 0)
        {
            startTime = Calendar.getInstance().getTimeInMillis();
            ret = true;
        }
        else
        {
            ret = false;
        }
        return ret;
    }

    public boolean isUnlocked(int n)
    {
        //TODO
        return true;
    }

    private float calcAffinityMultiplier()
    {
        //TODO
        return 1f;
    }

    public boolean isDone()
    {
        return getRemainingTime() < 0;
    }

    private long getRemainingTime()
    {
        return processTime - (Calendar.getInstance().getTimeInMillis() - startTime);
    }

    public void setIdol(Idol slotIdol)
    {
        //TODO
    }

    public Idol getIdol(int id)
    {
        //TODO
        return new Idol();
    }
}

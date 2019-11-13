package com.example.myapplication;

import java.util.Calendar;

public class Workplace extends Task
{
    private long startTime;
    public long processTime;
    public int rewardCurrency;

    public int danceAff;
    public int singAff;
    public int charmAff;

    Workplace(String name, String description, int numSlots, int reqLevel, boolean unlocked, long processTime, int rewardCurrency, int danceAff, int singAff, int charmAff)
    {
        super(name, description, numSlots, reqLevel, unlocked);
        this.processTime = processTime; // in ms
        this.rewardCurrency = rewardCurrency;
        this.danceAff = danceAff;
        this.singAff = singAff;
        this.charmAff = charmAff;

        this.startTime = 0;
    }

    Workplace()
    {
        super();
        this.processTime = 1000; // in ms
        this.rewardCurrency = 1;
        this.danceAff = 1;
        this.singAff = 1;
        this.charmAff = 1;

        this.startTime = 0;
    }

    public boolean startTask()
    {
        if(getNumSlottedIdols() < 1)
        {
            return false;
        }

        if (this.startTime == 0)
        {
            this.startTime = Calendar.getInstance().getTimeInMillis();
            return true;
        }

        return false;
    }

    public void resetTask()
    {
        unsetAllIdols();
        this.startTime = 0;
    }

    public long getStartTime(){ return startTime; }

    public long getRemainingTime()
    {
        return this.processTime - (Calendar.getInstance().getTimeInMillis() - this.startTime);
    }

    public boolean isDone()
    {
        return getRemainingTime() < 0;
    }

    public float getAffinityMultiplier()
    {
        //TODO
        return 1f;
    }
}

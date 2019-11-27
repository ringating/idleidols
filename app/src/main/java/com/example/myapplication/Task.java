package com.example.myapplication;

import java.util.Calendar;

public enum Task
{
    BAR_LIVE("Bar Live",
            0,
            R.drawable.work_hollywoodbowl,
            1,
            1,
            500,
            50,
            200,
            0,
            1,
            0), //TODO ADD OVERRIDE METHODS HERE
    FLASH_MOB("Flash Mob",
            0,
            R.drawable.work_realitytv,
            3,
            1,
            500,
            50,
            200,
            1,
            1,
            0),
    STAND_UP("Stand Up Comedy",
            0,
            R.drawable.work_standup,
            1,
            1,
            500,
            50,
            200,
            0,
            0,
            1); //TODO Add the Override methods here!


    public interface Workplace
    {
        public boolean startTask();
        public long getStartTime();
        public long getRemainingTime();
        public boolean isDone();
        public float getMultiplier();
    }

    public interface Academy
    {
        public void unsetAllIdols();
        public boolean setIdol(int idolID, int slotIndex);
        public long getIdolTrainTime(int slotIndex);
        public float getIdolDanceGained(int slotIndex);
        public float getIdolSingGained(int slotIndex);
        public float getIdolCharmGained(int slotIndex);
    }


    public final String name;
    public final int type; //This determines whether it's a Workplace or an Academy
    public final int image;
    public final int numSlots; //number of slots available
    public final int reqLevel;
    public boolean unlocked;
    public final long processTime; //in milliseconds
    public final int cost;
    public final int rewardCurrency;
    public final float dance; //if Academy, this will be the growth rate, if Workplace, this will be the affinity determination.
    public final float sing;
    public final float charm;

    private int[] idolSlots;

    Task(String name, int type, int image, int numSlots, int reqLevel, long processTime, int cost, int rewardCurrency, float dance, float sing, float charm)
    {
        this.name = name;
        this.type = type;
        this.image = image;
        this.numSlots = numSlots;
        this.reqLevel = reqLevel;
        this.processTime = processTime;
        this.cost = cost;
        this.rewardCurrency = rewardCurrency;
        this.dance = dance;
        this.sing = sing;
        this.charm = charm;

        this.unlocked = false;
        this.idolSlots = new int[this.numSlots];
    }

    // check level against this task's required level
    public boolean isUnlocked(int agencyLevel)
    {
        return agencyLevel >= this.reqLevel;
    }

    public boolean setIdol(int idolID, int slotIndex)
    {
        if(slotIndex >= this.idolSlots.length || slotIndex < 0)
        {
            return false;
        }

        this.idolSlots[slotIndex] = idolID;
        return true;
    }

    public boolean unsetIdol(int slotIndex)
    {
        if(slotIndex >= this.idolSlots.length || slotIndex < 0)
        {
            return false;
        }

        idolSlots[slotIndex] = -1;
        return true;
    }

    public void unsetAllIdols()
    {
        for (int i = 0; i < this.idolSlots.length; ++i)
        {
            this.idolSlots[i] = -1;
        }
    }

    public int[] getIdolSlots()
    {
        return this.idolSlots.clone();
    }

    public int getIdolID(int slotIndex)
    {
        return this.idolSlots[slotIndex];
    }

    public int getNumSlottedIdols()
    {
        int count = 0;
        for (int i = 0; i < this.idolSlots.length; ++i)
        {
            if(this.idolSlots[i] >= 0)
            {
                count++;
            }
        }
        return count;
    }
}
/*
public class Workplace extends Task
{
    private long startTime;
    public long processTime;
    public int rewardCurrency;

    public int danceAff;
    public int singAff;
    public int charmAff;


    Workplace(String name, int numSlots, int reqLevel, boolean unlocked, long processTime, int rewardCurrency, int danceAff, int singAff, int charmAff)
    {
        super(name, numSlots, reqLevel, unlocked);
        this.processTime = processTime; // in ms
        this.rewardCurrency = rewardCurrency;
        this.danceAff = danceAff;
        this.singAff = singAff;
        this.charmAff = charmAff;

        this.startTime = 0;
    }
/*
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
*/

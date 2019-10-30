package com.example.myapplication;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Task
{
    public String name;
    public String description;
    public int reqLevel;
    public boolean unlocked;
    public int numSlots;
    public int[] idolSlots; //
    public long startTime; //
    public long processTime;
    public int danceAff;
    public int singAff;
    public int charmAff;

    public Task(String name, String description, int numSlots, int reqLevel, boolean unlocked, long processTime, int danceAff, int singAff, int charmAff)
    {
        this.name = name;
        this.description = description;
        this.numSlots = numSlots;
        this.reqLevel = reqLevel;
        this.unlocked = unlocked;
        this.processTime = processTime; // in milliseconds
        this.danceAff = danceAff;
        this.singAff = singAff;
        this.charmAff = charmAff;

        startTime = 0;

        idolSlots = new int[this.numSlots];
        unsetAllIdols();
    }

    public boolean startTask()
    {
        if(getNumSlottedIdols() < 1)
        {
            return false;
        }

        if (startTime == 0)
        {
            startTime = Calendar.getInstance().getTimeInMillis();
            return true;
        }

        return false;
    }

    // check level against this task's required level
    public boolean isUnlocked(int agencyLevel)
    {
        return agencyLevel >= reqLevel;
    }

    public float getAffinityMultiplier()
    {
        //TODO
        return 1f;
    }

    public boolean isDone()
    {
        return getRemainingTime() < 0;
    }

    public long getRemainingTime()
    {
        return processTime - (Calendar.getInstance().getTimeInMillis() - startTime);
    }
    
    public boolean setIdol(int idolID, int slotIndex)
    {
        if(slotIndex >= idolSlots.length || slotIndex < 0)
        {
            return false;
        }

        idolSlots[slotIndex] = idolID;
        return true;
    }

    public boolean unsetIdol(int slotIndex)
    {
        if(slotIndex >= idolSlots.length || slotIndex < 0)
        {
            return false;
        }

        idolSlots[slotIndex] = -1;
        return true;
    }

    public void unsetAllIdols()
    {
        for (int i = 0; i < idolSlots.length; ++i)
        {
            idolSlots[i] = -1;
        }
    }

    public int[] getIdolSlots(int id)
    {
        return idolSlots.clone();
    }

    public int getNumSlottedIdols()
    {
        int count = 0;
        for (int i = 0; i < idolSlots.length; ++i)
        {
            if(idolSlots[i] >= 0)
            {
                count++;
            }
        }
        return count;
    }
}

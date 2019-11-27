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


    public Task(String name, String description, int numSlots, int reqLevel, boolean unlocked)
    {
        this.name = name;
        this.description = description;
        this.numSlots = numSlots;
        this.reqLevel = reqLevel;
        this.unlocked = unlocked;

        this.idolSlots = new int[this.numSlots];
        unsetAllIdols();
    }

    Task()
    {
        this.name = "DefaultTaskName";
        this.description = "DefaultTaskDescription";
        this.numSlots = 4;
        this.reqLevel = 0;
        this.unlocked = true;

        this.idolSlots = new int[this.numSlots];
        unsetAllIdols();
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

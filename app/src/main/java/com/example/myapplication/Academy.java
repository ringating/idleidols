package com.example.myapplication;

import java.util.Calendar;
/*
public class Academy extends Task
{
    public float danceGrowthPerHour;
    public float singGrowthPerHour;
    public float charmGrowthPerHour;

    public long[] idolStartTimes;

    Academy(String name, int numSlots, int reqLevel, boolean unlocked, float danceGrowthPerHour, float singGrowthPerHour, float charmGrowthPerHour)
    {
        super(name, numSlots, reqLevel, unlocked);

        this.danceGrowthPerHour = danceGrowthPerHour;
        this.singGrowthPerHour = singGrowthPerHour;
        this.charmGrowthPerHour = charmGrowthPerHour;

        this.idolStartTimes = new long[this.numSlots];
    }

    Academy()
    {
        super();

        this.danceGrowthPerHour = 0.05f;
        this.singGrowthPerHour = 0.05f;
        this.charmGrowthPerHour = 0.05f;

        this.idolStartTimes = new long[this.numSlots];
    }

    public void unsetAllIdols()
    {
        super.unsetAllIdols();

        if(idolStartTimes != null)
        {
            for(int i = 0; i < this.idolStartTimes.length; ++i)
            {
                this.idolStartTimes[i] = 0;
            }
        }
    }

    public boolean setIdol(int idolID, int slotIndex)
    {
        if(!super.setIdol(idolID, slotIndex))
            return false;
        this.idolStartTimes[slotIndex] = Calendar.getInstance().getTimeInMillis();
        return true;
    }

    // returns how long the idol has been training (in ms)
    public long getIdolTrainTime(int slotIndex)
    {
        return Calendar.getInstance().getTimeInMillis() - this.idolStartTimes[slotIndex];
    }

    // returns how much dance stat has been gained by this idol since it began this training session
    public float getIdolDanceGained(int slotIndex)
    {
        return this.danceGrowthPerHour * (getIdolTrainTime(slotIndex) / 3600000f); // an hour in ms
    }

    // returns how much sing stat has been gained by this idol since it began this training session
    public float getIdolSingGained(int slotIndex)
    {
        return this.singGrowthPerHour * (getIdolTrainTime(slotIndex) / 3600000f); // an hour in ms
    }

    // returns how much charm stat has been gained by this idol since it began this training session
    public float getIdolCharmGained(int slotIndex)
    {
        return this.charmGrowthPerHour * (getIdolTrainTime(slotIndex) / 3600000f); // an hour in ms
    }
}
 */

package com.example.myapplication;

import java.io.Serializable;

public class DataForSaveLoad implements Serializable // serializable is the point of this class
{
    public int curCurrency;
    public int totalCurrency;
    public int curSeeds;
    public int totalSeeds;
    public int level;
    public String levelStr;
    public String agencyName;
    public int expNeededToLevelUp;
    public int curExp;
    public boolean firstTime;

    DataForSaveLoad(Agency agency)
    {
        curCurrency = agency.GetCurrentCurrency();
        totalCurrency = agency.GetTotalCurrency();
        curSeeds = agency.GetCurrentSeeds();
        totalSeeds = agency.GetTotalSeeds();
        level = Integer.parseInt(agency.GetLevel()); // this is jank
        levelStr = agency.GetLevel();
        agencyName = agency.GetName();
        expNeededToLevelUp = agency.GetExpNeededToLevel();
        curExp = agency.GetCurrentExp();
        firstTime = agency.getFirstTimeFlag();
    }

    public Agency getAgency()
    {
        Agency agency = new Agency();

        agency.SetCurrency(curCurrency);
        agency.SetTotalCurrency(totalCurrency);
        agency.SetSeeds(curSeeds);
        agency.SetTotalSeeds(totalSeeds);
        agency.SetLevel(level);
        agency.SetName(agencyName);
        agency.SetExpNeededToLevel(levelStr);
        agency.SetCurrentExp(curExp);
        agency.setFirstTimeFlag(firstTime);

        return agency;
    }
}

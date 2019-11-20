package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

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

    // idol isn't serializable, and doesnt seem to be able to be made serializable
    // so will have to make a struct or something for idol data and regenerate idols when loading them
//    private ArrayList<Idol> idols = new ArrayList<>();

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

//        for(int i = 0; i < agency.numberOfIdols(); ++i)
//            idols.add(agency.getIdol(i));
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

//        for(int i = 0; i < idols.size(); ++i)
//            agency.addIdol(idols.get(i));

        return agency;
    }

    public static void copyAgencyValues(Agency destination, Agency source)
    {
        destination.SetCurrency(source.GetCurrentCurrency());
        destination.SetTotalCurrency(source.GetTotalCurrency());
        destination.SetSeeds(source.GetCurrentSeeds());
        destination.SetTotalSeeds(source.GetTotalSeeds());
        destination.SetLevel(Integer.parseInt(source.GetLevel()));
        destination.SetName(source.GetName());
        destination.SetExpNeededToLevel(source.GetLevel());
        destination.SetCurrentExp(source.GetCurrentExp());
        destination.setFirstTimeFlag(source.getFirstTimeFlag());

//        while(destination.numberOfIdols() > 0)
//            destination.removeIdolAtIndex(0);
//
//        for(int i = 0; i < source.numberOfIdols(); ++i)
//            destination.addIdol(source.getIdol(i));
    }
}

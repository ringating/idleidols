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
    public boolean[] claimedAchievements = new boolean[Achievement.values().length];

    // idol isn't serializable, so using a new structure to hold idol data
    private ArrayList<IdolStruct> idols = new ArrayList<>();

    class IdolStruct implements Serializable
    {
        String idolName;
        String mainAffinity;
        float danceStat;
        float singStat;
        float charmStat;
        int rarity;
        int image;
        int merged;
        boolean isBeingUsed;

        IdolStruct(Idol idol)
        {
            this.idolName = idol.getIdolName();
            this.mainAffinity = idol.getMainAffinity();
            this.danceStat = idol.getDanceStat();
            this.singStat = idol.getSingStat();
            this.charmStat = idol.getCharmStat();
            this.rarity = idol.getRarity();
            this.image = idol.getImage();
            this.merged = idol.getMerged();
            this.isBeingUsed = false; //TODO, once relevant method exists
        }

        Idol toIdol()
        {
            Idol idol = new Idol(idolName,mainAffinity,danceStat,singStat,charmStat,rarity,image);
            idol.setMerged(merged); // for now, this cant be done in a constructor
            // TODO: also set isBeingUsed once relevant method/constructor exists
            return idol;
        }
    }

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

        for(int i = 0; i < agency.numberOfIdols(); ++i)
            idols.add(new IdolStruct(agency.getIdol(i)));

        for(int i = 0; i < agency.claimedAchievements.length; ++i)
            this.claimedAchievements[i] = agency.claimedAchievements[i];
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

        for(int i = 0; i < idols.size(); ++i)
            agency.addIdol(idols.get(i).toIdol());

        for(int i = 0; i < this.claimedAchievements.length; ++i)
            agency.claimedAchievements[i] = this.claimedAchievements[i];

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

        while(destination.numberOfIdols() > 0)
            destination.removeIdolAtIndex(0);

        for(int i = 0; i < source.numberOfIdols(); ++i)
            destination.addIdol(source.getIdol(i));

        for(int i = 0; i < source.claimedAchievements.length; ++i)
            destination.claimedAchievements[i] = source.claimedAchievements[i];
    }
}

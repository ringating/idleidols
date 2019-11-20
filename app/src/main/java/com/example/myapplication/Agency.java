package com.example.myapplication;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Agency extends Application {

    private int curCurrency;
    private int totalCurrency;
    private int curSeeds;
    private int totalSeeds;
    private int level;
    private String agencyName;
    private int expNeededToLevelUp;
    private int curExp;
    private boolean firstTime = true;

    private final int EXP_LEVEL_MODIFIER = 100;

    private ArrayList<Idol> idols = new ArrayList<>();

    public void SetCurrency(int currency)
    {
        this.curCurrency = currency;
    }

    public int GetCurrentCurrency()
    {
        return curCurrency;
    }

    public void SetTotalCurrency(int currency){this.totalCurrency = currency;}

    public int GetTotalCurrency(){return totalCurrency;}

    public void SetSeeds(int seeds)
    {
        this.curSeeds = seeds;
    }

    public int GetCurrentSeeds()
    {
        return curSeeds;
    }

    public void SetTotalSeeds(int seeds){this.totalSeeds = seeds;}

    public int GetTotalSeeds(){return totalSeeds;}

    public void SetLevel(int level)
    {
        this.level = level;
    }

    public String GetLevel()
    {
        String level = "";
        if(this.level > 0 && this.level < 10)
        {
            level += "0";
        }
        level += Integer.toString(this.level);
        return level;
    }

    public void SetName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    public String GetName()
    {
        return agencyName;
    }

    public ArrayList<Idol> GetIdols() { return this.idols; }

    public boolean noIdols()
    {
        return idols.isEmpty();
    }

    public Idol getIdol(int i)
    {
        return idols.get(i);
    }

    public void addIdol(Idol idol)
    {
        idols.add(idol);
    }

    public void removeIdol(Idol idol)
    {
        idols.remove(idol);
    }

    public void removeIdolAtIndex(int i)
    {
        idols.remove(i);
    }

    public void SetCurrentExp(int exp)
    {
        this.curExp = exp;
    }

    public int GetCurrentExp()
    {
        return this.curExp;
    }

    public void SetExpNeededToLevel(String level)
    {
        this.expNeededToLevelUp += Integer.parseInt(level) * EXP_LEVEL_MODIFIER;
    }

    public int GetExpNeededToLevel()
    {
        return this.expNeededToLevelUp;
    }

    public int numberOfIdols()
    {
        return idols.size();
    }

    public boolean getFirstTimeFlag()
    {
        return this.firstTime;
    }

    public void setFirstTimeFlag(boolean flag)
    {
        this.firstTime = flag;
    }

}

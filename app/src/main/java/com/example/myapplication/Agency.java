package com.example.myapplication;

import android.app.Application;

import java.util.ArrayList;

public class Agency extends Application {

    private int curCurrency;
    private int totalCurrency;
    private int curSeeds;
    private int totalSeeds;
    private int level;
    private String agencyName;
    private ArrayList<Idol> idols;
    private int curNumOfIdols;
    private int maxNumOfIdols;
    private int expNeededToLevelUp;
    private int curExp;

    private final int EXP_LEVEL_MODIFIER = 100;
    private final int LEVEL_INCREMENT = 5;
    private final int IDOL_NUM_INCREMENT = 3;
    private final int INITIAL_MAX_NUM_IDOL = 10;

    public Agency()
    {
        this.curCurrency = 0;
        this.totalCurrency = 0;
        this.curSeeds = 0;
        this.totalSeeds = 0;
        this.curExp = 0;
        this.level = 1;
        this.curNumOfIdols = 1;
        this.maxNumOfIdols = INITIAL_MAX_NUM_IDOL;
    }

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

    public int GetLevel()
    {
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

    public int GetCurNumOfIdols(ArrayList<Idol> idols)
    {
        return idols.size();
    }

    public void SetMaxNumOfIdols(int level)
    {
        if(level % LEVEL_INCREMENT == 0)
        {
            this.maxNumOfIdols += IDOL_NUM_INCREMENT;
        }
    }

    public int GetMaxNumOfIdols()
    {
        return this.maxNumOfIdols;
    }

    public void SetCurrentExp(int exp)
    {
        this.curExp = exp;
    }

    public int GetCurrentExp()
    {
        return this.curExp;
    }

    public void SetExpNeededToLevel(int level)
    {
        this.expNeededToLevelUp += level * EXP_LEVEL_MODIFIER;
    }

    public int GetExpNeededToLevel()
    {
        return this.expNeededToLevelUp;
    }

}

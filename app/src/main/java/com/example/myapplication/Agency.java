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
    private List<Idol> idols = new ArrayList<>();

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

    public int numberOfIdols()
    {
        return idols.size();
    }
}

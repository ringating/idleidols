package com.example.myapplication;

import android.app.Application;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Agency extends Application implements Serializable {

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

    public Idol combineIdols(int idolIndex1, int idolIndex2)
    {
        Idol originalIdol = getIdol(idolIndex1);
        Idol disappears = getIdol(idolIndex2);

        if(!originalIdol.getIdolName().equals(disappears.getIdolName()))
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Please select the same idol", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            originalIdol.setDanceStat(originalIdol.getDanceStat() + disappears.getDanceStat());
            originalIdol.setSingStat(originalIdol.getSingStat() + disappears.getSingStat());
            originalIdol.setCharmStat(originalIdol.getCharmStat() + disappears.getCharmStat());
            originalIdol.setMerged(originalIdol.getMerged() + 1);

            removeIdolAtIndex(idolIndex2);
        }

        return originalIdol;
    }

    public void levelUp()
    {
        if(this.curExp >= this.expNeededToLevelUp)
        {
            this.level += 1;
            this.curExp = this.curExp - this.expNeededToLevelUp;
            this.expNeededToLevelUp += this.level * 100;
        }
    }
}

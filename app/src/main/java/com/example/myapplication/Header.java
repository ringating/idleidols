package com.example.myapplication;

public class Header {

    int currency;
    int seeds;
    int level;
    String agencyName;

    public Header(int money, int tokens, int lvl, String name)
    {
        SetCurrency(money);
        SetSeeds(tokens);
        SetLevel(lvl);
        SetName(name);
    }

    public void SetCurrency(int money)
    {
        currency = money;
    }

    public int GetCurrency()
    {
        return currency;
    }

    public void SetSeeds(int tokens)
    {
        seeds = tokens;
    }

    public int GetSeeds()
    {
        return seeds;
    }

    public void SetLevel(int lvl)
    {
        level = lvl;
    }

    public int GetLevel()
    {
        return level;
    }

    public void SetName(String name)
    {
        agencyName = name;
    }

    public String GetName()
    {
        return agencyName;
    }
}

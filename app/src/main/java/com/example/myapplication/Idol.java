package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Idol implements Parcelable {
    private String idolName;
    private String mainAffinity;
    private float danceStat;
    private float singStat;
    private float charmStat;
    private int rarity;
    private int image;
    private boolean isBeingUsed;

    public Idol(String name, String affinity, int imgId, float dance, float sing, float charm, int rarity)
    {
        setIdolName(name);
        setMainAffinity(affinity);
        setDanceStat(dance);
        setSingStat(sing);
        setCharmStat(charm);
        setRarity(rarity);
        setImage(imgId);
    }

    public Idol()
    {
        generateStats(0);
        calculateRarity();
    }

    public Idol(Parcel in)
    {
        super();
        readFromParcel(in);
    }

    public void generateStats(float weight)
    {
        //TODO
        Random rand = new Random();
        switch (rand.nextInt(3) + 1) {
            case 1:
                setMainAffinity("Dance");
                break;
            case 2:
                setMainAffinity("Sing");
                break;
            case 3:
                setMainAffinity("Charm");
                break;
            default:
                setMainAffinity("Unknown");
        }

        switch (rand.nextInt(2)){
            case 0:
                setImage(R.drawable.onion);
                setIdolName("Onion");
                break;
            case 1:
                setImage(R.drawable.tomato);
                setIdolName("Tamto");
                break;
            default:
                setImage(R.drawable.tempidol);
                setIdolName("huh?");
        }

        this.danceStat = rand.nextFloat();
        this.singStat = rand.nextFloat();
        this.charmStat = rand.nextFloat();
    }

    public void calculateRarity()
    {
        //TODO
        this.rarity = (int)(this.danceStat * 100 + this.charmStat * 100 + this.singStat * 100);
    }

    public Idol combineIdol(Idol other)
    {
        //TODO
        return null;
    }

    public void setIdolName(String name)
    {
        this.idolName = name;
    }

    public void setMainAffinity(String affinity)
    {
        this.mainAffinity = affinity;
    }

    public void setDanceStat(float stat)
    {
        this.danceStat = stat;
    }

    public void setSingStat(float stat)
    {
        this.singStat = stat;
    }

    public void setCharmStat(float stat)
    {
        this.charmStat = stat;
    }

    public void setRarity(int rarity)
    {
        this.rarity = rarity;
    }

    public void setImage(int imgId)
    {
        this.image = imgId;
    }

    public String getIdolName()
    {
        return this.idolName;
    }

    public String getMainAffinity()
    {
        return this.mainAffinity;
    }

    public float getDanceStat()
    {
        return this.danceStat;
    }

    public float getSingStat()
    {
        return this.singStat;
    }

    public float getCharmStat()
    {
        return this.charmStat;
    }

    public int getRarity()
    {
        return this.rarity;
    }

    public int getImage()
    {
        return this.image;
    }

    //Parcelable methods
    public static final Parcelable.Creator<Idol> IDOL_CREATOR = new Parcelable.Creator<Idol>()
    {
        public Idol createFromParcel(Parcel in)
        {
            return new Idol(in);
        }

        public Idol[] newArray(int size)
        {
            return new Idol[size];
        }
    };

    public void readFromParcel(Parcel in)
    {
        this.idolName = in.readString();
        this.mainAffinity = in.readString();
        this.image = in.readInt();
        this.danceStat = in.readFloat();
        this.singStat = in.readFloat();
        this.charmStat = in.readFloat();
        this.rarity = in.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.idolName);
        dest.writeString(this.mainAffinity);
        dest.writeInt(this.image);
        dest.writeFloat(this.danceStat);
        dest.writeFloat(this.singStat);
        dest.writeFloat(this.charmStat);
        dest.writeInt(this.rarity);
    }
}

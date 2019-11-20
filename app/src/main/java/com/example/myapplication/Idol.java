package com.example.myapplication;

import android.os.Parcelable;
import android.os.Parcel;
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

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Idol createFromParcel(Parcel in) {
            return new Idol(in);
        }

        public Idol[] newArray(int size) {
            return new Idol[size];
        }
    };

    public Idol(String name, String affinity, float dance, float sing, float charm, int rarity, int imgId)
    {
        setIdolName(name);
        setMainAffinity(affinity);
        setDanceStat(dance);
        setSingStat(sing);
        setCharmStat(charm);
        setRarity(rarity);
        setImage(imgId);
    }

    public Idol(Parcel in)
    {
        setIdolName(in.readString());
        setMainAffinity(in.readString());
        setDanceStat(in.readFloat());
        setSingStat(in.readFloat());
        setCharmStat(in.readFloat());
        setRarity(in.readInt());
        setImage(in.readInt());
    }

    public Idol()
    {
        generateStats(0);
        calculateRarity();
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.idolName);
        dest.writeString(this.mainAffinity);
        dest.writeFloat(this.danceStat);
        dest.writeFloat(this.singStat);
        dest.writeFloat(this.charmStat);
        dest.writeInt(this.rarity);
        dest.writeInt(this.image);
    }
}

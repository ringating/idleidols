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
    private int merged;
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
        setMerged(0);
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
        setMerged(in.readInt());
    }

    public Idol(String type)
    {
        calculateRarity(type);
        setMerged(0);
        generateStats(this.rarity);
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

    public void setMerged(int merge)
    {
        this.merged = merge;
    }

    public int getMerged()
    {
        return this.merged;
    }

    public void setStatus(boolean status)
    {
        this.isBeingUsed = status;
    }

    public boolean getStatus()
    {
        return this.isBeingUsed;
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

    //Gacha Rates OOGA BOOGA
    private void calculateRarity(String type)
    {
        Random rand = new Random();
        int randomInt = rand.nextInt(100 ) + 1;
        //setting rarity
        if(type.equals("normal"))
        {
            if(randomInt >= 1 && randomInt <= 50)
            {
                this.setRarity(3);
            }
            else if (randomInt > 50 && randomInt <= 90)
            {
                this.setRarity(4);
            }
            else if(randomInt > 90 && randomInt <= 100)
            {
                this.setRarity(5);
            }
        }
        else if(type.equals("special"))
        {
            if(randomInt >= 1 && randomInt <= 30)
            {
                this.setRarity(3);
            }
            else if (randomInt > 30 && randomInt <= 80)
            {
                this.setRarity(4);
            }
            else if(randomInt > 90 && randomInt <= 100)
            {
                this.setRarity(5);
            }
        }
    }

    private void generateStats(int rarity)
    {
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

        float highest = rand.nextFloat();
        float random1 = rand.nextFloat();
        float random2 = rand.nextFloat();
        switch(rarity){
            case 3:
                if(Float.compare(highest, 0.5f) > 0)
                {
                    highest -= 0.5f;
                }
                if(Float.compare(highest, random1) < 0)
                {
                    random1 -= highest;
                }
                if(Float.compare(highest, random2) < 0)
                {
                    random2 -= highest;
                }

                switch (getMainAffinity())
                {
                    case "Dance":
                        setDanceStat(highest);
                        setCharmStat(random1);
                        setSingStat(random2);
                        break;
                    case "Sing":
                        setSingStat(highest);
                        setDanceStat(random1);
                        setCharmStat(random2);
                        break;
                    case "Charm":
                        setCharmStat(highest);
                        setSingStat(random1);
                        setDanceStat(random2);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                if(Float.compare(highest, 0.8f) > 0)
                {
                    highest -= 0.8f;
                }
                if(Float.compare(highest, random1) < 0)
                {
                    random1 -= highest;
                }
                if(Float.compare(highest, random2) < 0)
                {
                    random2 -= highest;
                }

                switch (getMainAffinity())
                {
                    case "Dance":
                        setDanceStat(highest);
                        setCharmStat(random1);
                        setSingStat(random2);
                        break;
                    case "Sing":
                        setSingStat(highest);
                        setDanceStat(random1);
                        setCharmStat(random2);
                        break;
                    case "Charm":
                        setCharmStat(highest);
                        setSingStat(random1);
                        setDanceStat(random2);
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                if(Float.compare(highest, 0.2f) < 0)
                {
                    highest += 0.8f;
                }
                if(Float.compare(highest, random1) < 0)
                {
                    random1 -= highest;
                }
                if(Float.compare(highest, random2) < 0)
                {
                    random2 -= highest;
                }

                switch (getMainAffinity())
                {
                    case "Dance":
                        setDanceStat(highest);
                        setCharmStat(random1);
                        setSingStat(random2);
                        break;
                    case "Sing":
                        setSingStat(highest);
                        setDanceStat(random1);
                        setCharmStat(random2);
                        break;
                    case "Charm":
                        setCharmStat(highest);
                        setSingStat(random1);
                        setDanceStat(random2);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

    }
}

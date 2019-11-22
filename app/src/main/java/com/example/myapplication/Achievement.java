
package com.example.myapplication;  // Package and imports copy-pasted from Achievements

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

public enum Achievement     // Using the Java enum class
{
    // 5 sample achievements
    CREATE_AGENCY(
            "You made one!",
            "Create an Agency",
            R.drawable.achieve_locked,
            150,
            0,
            5
    ){
        @Override
        public boolean canBeClaimed(Object... args)
        {
            Agency agency = (Agency)args[0];
            if(agency != null)
            {
                this.SetCanClaim(true);
            }
            return this.GetCanClaim();
        }
    },

    NUMBER_OF_IDOLS(
            "Starting the Garden",
            "Collect a total of 5 seed tokens",
            R.drawable.achieve_locked,
            50,
            500,
            0
    ) {

        @Override
        public boolean canBeClaimed(Object... args)
        {
            Agency agency = (Agency)args[0];
            if(agency.GetTotalSeeds() == 5)
            {
                this.SetCanClaim(true);
            }

            return this.GetCanClaim();
        }
    },

    SAMPLE_3(
            "Sample 3",
            "This is a sample achievement",
            R.drawable.rename_image,
            250,
            145,
            501
    ) {

        @Override
        public boolean canBeClaimed(Object... args)
        {
            // Let's pretend this achievement requires a certain amount of coins
            int coins = ((Integer) args[0]).intValue(); // notice the unsafe cast
            return coins > 500;
        }
    },

    SAMPLE_4(
            "Sample 4",
            "This is a sample achievement",
            R.drawable.rename_image,
            409,
            397,
            201
    ) {

        @Override
        public boolean canBeClaimed(Object... args)
        {
            // Let's pretend this achievement requires a certain amount of coins
            int coins = ((Integer) args[0]).intValue(); // notice the unsafe cast
            return coins > 500;
        }
    },

    SAMPLE_5(
            "Sample 5",
            "This is a sample achievement",
            R.drawable.rename_image,
            520,
            5,
            32
    ) {

        @Override
        public boolean canBeClaimed(Object... args)
        {
            // Let's pretend this achievement requires a certain amount of coins
            int coins = ((Integer) args[0]).intValue(); // notice the unsafe cast
            return coins > 500;
        }
    };

    public final String title;
    public final String description;

    /**
     * Res ID for icon
     */
    public final int imageDrawable;

    /**
     * The amount of exp earned for unlocking this achievement
     */
    public final int exp;
    /**
     * The amount of money earned for unlocking this achievement
     */
    public final int money;
    /**
     * The amount of seeds earned for unlocking this achievement
     */
    public final int seeds;

    /**
     * This achievement can be unlocked
     */
    private boolean isClaimed;
    /**
     * This achievement has been unlocked
     */
    private boolean canClaim;

    Achievement(String title, String description, int imageDrawable, int exp, int money, int seeds)
    {
        this.title = title;
        this.description = description;
        this.imageDrawable = imageDrawable;
        this.exp = exp;
        this.money = money;
        this.seeds = seeds;
        this.isClaimed = false;
        this.canClaim = false;
    }

    /*
     * Friendly note from Jose:
     *  This is a generally dangerous way of trying to do this, but it allows flexibility
     *  in what it means for an achievement to be unlocked. However, validation for argument type
     *  and ordering needs to be done in each individual override!
     */


    /**
     * isUnlocked
     * @return true if achievement is unlocked
     */
    public abstract boolean canBeClaimed(Object... args);

    public void SetCanClaim(boolean newClaimState)
    {
        this.canClaim = newClaimState;
    }

    public boolean GetCanClaim()
    {
        return this.canClaim;
    }

    public void SetIsClaimed(boolean newClaimState)
    {
        this.isClaimed = newClaimState;
    }

    public boolean GetIsClaimed()
    {
        return this.isClaimed;
    }

    public void ClaimAchievement(Agency agency)
    {
        // TODO: Change this from a void to return something
        //  so we can set multiple states of the Achievement later.
        this.SetIsClaimed(true);
        agency.SetCurrency(agency.GetCurrentCurrency() + this.money);
        agency.SetTotalCurrency(agency.GetTotalCurrency() + this.money);
        agency.SetSeeds(agency.GetCurrentSeeds() + this.seeds);
        agency.SetTotalSeeds(agency.GetTotalSeeds() + this.seeds);
        agency.SetCurrentExp(agency.GetCurrentExp() + this.exp);

        //return this.isClaimed;
    }
}
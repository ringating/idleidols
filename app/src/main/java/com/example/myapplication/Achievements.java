package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Achievements extends AppCompatActivity
{
    Dialog myDialog;

    Agency agency;
    TextView currency;
    TextView seeds;
    TextView level;
    ProgressBar expBar;

    FragmentManager manager;
    Bundle sendAchievement;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idol_achievements);
        myDialog = new Dialog(this);

        manager = getSupportFragmentManager();

        //This is where the agency passes along data to the page.
        agency = (Agency) getApplicationContext();

        currency = (TextView)findViewById(R.id.currency);
        currency.setText(Integer.toString(agency.GetCurrentCurrency()));

        seeds = (TextView)findViewById(R.id.seed);
        seeds.setText(Integer.toString(agency.GetCurrentSeeds()));

        level = (TextView)findViewById(R.id.level);
        level.setText(agency.GetLevel());

        TextView name = findViewById(R.id.agencyName);
        name.setText(agency.GetName());

        expBar = (ProgressBar)findViewById(R.id.expBar);
        expBar.setMax(agency.GetExpNeededToLevel());
        expBar.setProgress(agency.GetCurrentExp());

        loadAchievements();
    }

    public void setHeaderText(Agency agency)
    {
        agency.levelUp();
        currency.setText(Integer.toString(agency.GetCurrentCurrency()));
        seeds.setText(Integer.toString(agency.GetCurrentSeeds()));
        level.setText(agency.GetLevel());
        expBar.setMax(agency.GetExpNeededToLevel());
        expBar.setProgress(agency.GetCurrentExp());
    }

    private void loadAchievements()
    {
        /*
         * For each enum value in Achievements:
         *      Create an instance of the achievement fragment using factory method;
         *      Add the achievement to the linear layout using begin transaction stuff.
         */
        for (Achievement value : Achievement.values())
        {
            FragmentTransaction transaction = manager.beginTransaction();
            sendAchievement = new Bundle();
            sendAchievement.putSerializable("Agency", agency);

            AchievementFragment fragment = AchievementFragment.createAchievementFragment(value, sendAchievement);
            transaction.add(R.id.achievements_list, fragment);
            transaction.commit();
        }
    }

    @Override
    protected void onDestroy()
    {
        // autosave upon leaving this activity
        super.onDestroy();
        SaveLoad.save(this.getApplicationContext(), new DataForSaveLoad((Agency) this.getApplicationContext()));
    }
}

package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

    Achievement createAgencyAchievement = Achievement.CREATE_AGENCY;
    Achievement get2Idols = Achievement.NUMBER_OF_IDOLS;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idol_achievements);
        myDialog = new Dialog(this);

        loadAchievements();

        //This is where the agency passes along data to the page.
        agency = (Agency) getApplicationContext();

        currency = (TextView)findViewById(R.id.currency);
        currency.setText(Integer.toString(agency.GetCurrentCurrency()));

        seeds = (TextView)findViewById(R.id.seed);
        seeds.setText(Integer.toString(agency.GetCurrentSeeds()));

        level = (TextView)findViewById(R.id.level);
        agency.SetLevel(01); // TODO: This is just a place holder; will change later!
        level.setText(Integer.toString(agency.GetLevel()));

        TextView name = findViewById(R.id.agencyName);
        name.setText(agency.GetName());

        expBar = (ProgressBar)findViewById(R.id.expBar);
        expBar.setMax(agency.GetExpNeededToLevel());
        expBar.setProgress(agency.GetCurrentExp());

        GoHome(); // goes to home screen on button click
        GoAcademies();
        GoWorkplace();
        GoScout();
        GoManagement();
        GoAchievements();

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
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            AchievementFragment fragment = AchievementFragment.createAchievementFragment(value);
            transaction.add(R.id.achievements_list, fragment);
            transaction.commit();

        }

    }

    public void ClaimAgencyAchievement(View v)
    {
        ImageView agencyAchievement = (ImageView) v;
        if(createAgencyAchievement.canBeClaimed(agency) && !createAgencyAchievement.GetIsClaimed())
        {
            Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
            agencyAchievement.startAnimation(shrink);
            createAgencyAchievement.ClaimAchievement(agency);
            seeds.setText(Integer.toString(agency.GetCurrentSeeds()));

            //This is the code for checking EXP
            if(agency.GetCurrentExp() >= agency.GetExpNeededToLevel())
            {
                agency.SetLevel(agency.GetLevel() + 1);
                level.setText(Integer.toString(agency.GetLevel()));
                expBar.setProgress(agency.GetCurrentExp() - agency.GetExpNeededToLevel());
                agency.SetCurrentExp(agency.GetCurrentExp() - agency.GetExpNeededToLevel());
                agency.SetExpNeededToLevel(agency.GetLevel());
                expBar.setMax(agency.GetExpNeededToLevel());

                //pops up the Level Up Card
                myDialog.setContentView(R.layout.level_up_card);
                TextView levelUpText = myDialog.findViewById(R.id.levelUpText);
                String newLevelUpText = levelUpText.getText().toString() + agency.GetLevel();
                levelUpText.setText(newLevelUpText);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
            else
            {
                expBar.setProgress(agency.GetCurrentExp());
                agency.SetCurrentExp(agency.GetCurrentExp());
            }
        }
        /*else
        {
            // TODO Change this to throw an error card obviously. Or maybe the progress?
            TextView exitButton;
            myDialog.setContentView(R.layout.workcard);
            exitButton = myDialog.findViewById(R.id.exitButton);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        }*/
    }

    public void ClaimNumberOfIdols (View v)
    {
        ImageView idolAchievement = (ImageView) v;
        if(get2Idols.canBeClaimed(agency) && !get2Idols.GetIsClaimed())
        {
            Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
            idolAchievement.startAnimation(shrink);
            get2Idols.ClaimAchievement(agency);
            currency.setText(Integer.toString(agency.GetCurrentCurrency()));

            if(agency.GetCurrentExp() >= agency.GetExpNeededToLevel())
            {
                agency.SetLevel(agency.GetLevel() + 1);
                level.setText(Integer.toString(agency.GetLevel()));
                expBar.setProgress(agency.GetCurrentExp() - agency.GetExpNeededToLevel());
                agency.SetCurrentExp(agency.GetCurrentExp() - agency.GetExpNeededToLevel());
                agency.SetExpNeededToLevel(agency.GetLevel());
                expBar.setMax(agency.GetExpNeededToLevel());

                //pops up the Level Up Card
                myDialog.setContentView(R.layout.level_up_card);
                TextView levelUpText = myDialog.findViewById(R.id.levelUpText);
                String newLevelUpText = levelUpText.getText().toString() + agency.GetLevel();
                levelUpText.setText(newLevelUpText);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
            else
            {
                expBar.setProgress(agency.GetCurrentExp());
                agency.SetCurrentExp(agency.GetCurrentExp());
            }
        }
        /*else
        {
            // TODO Change this to throw an error card obviously. Or maybe the progress?
            TextView exitButton;
            myDialog.setContentView(R.layout.workcard);
            exitButton = myDialog.findViewById(R.id.exitButton);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        }*/
    }

    //
    // The following methods are the bottom-screen buttons:
    //
    private void GoHome()
    {
        Button homeButton = (Button) findViewById(R.id.home_button4);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Achievements.this, MainActivity.class));
            }
        });
    }

    private void GoAcademies()
    {
        Button academies = (Button) findViewById(R.id.academies_button4);
        academies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Achievements.this, Train.class));
            }
        });
    }

    private void GoWorkplace()
    {
        Button workplace = (Button) findViewById(R.id.workplace_button4);
        workplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Achievements.this, Work.class));
            }
        });
    }

    private void GoScout()
    {
        Button scout = (Button) findViewById(R.id.scout_button4);
        scout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Achievements.this, Scout.class));
            }
        });
    }

    private void GoManagement()
    {
        Button management = (Button) findViewById(R.id.management_button4);
        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Achievements.this, Management.class));
            }
        });
    }

    private void GoAchievements()
    {
        final Button achievements = (Button) findViewById(R.id.achievements_button4);
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Achievements.this, Achievements.class)); //CHANGE THIS
            }
        });
    }
}

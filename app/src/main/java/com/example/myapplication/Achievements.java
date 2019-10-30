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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Achievements extends AppCompatActivity {

    Dialog myDialog;

    Agency agency;
    TextView curMoney;
    TextView curTokens;
    TextView curLevel;

    Achievement createAgencyAchievement = Achievement.CREATE_AGENCY;
    Achievement get2Idols = Achievement.NUMBER_OF_IDOLS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idol_achievements);
        myDialog = new Dialog(this);


        //This is where the agency passes along data to the page.
        agency = (Agency) getApplicationContext();

        curMoney = (TextView)findViewById(R.id.currency);
        curMoney.setText(Integer.toString(agency.GetCurrentCurrency()));

        curTokens = (TextView)findViewById(R.id.seed);
        curTokens.setText(Integer.toString(agency.GetCurrentSeeds()));

        curLevel = (TextView)findViewById(R.id.level);
        agency.SetLevel(01); //TODO this is just a place holder. Will change later!
        curLevel.setText(Integer.toString(agency.GetLevel()));

        TextView name = findViewById(R.id.agencyName);
        name.setText(agency.GetName());

        GoHome(); //goes to home screen on button click
        GoAcademies();
        GoWorkplace();
        GoScout();
        GoManagement();
        GoAchievements();

    }

    public void ClaimAgencyAchievement(View v)
    {
        ImageView agencyAchievement = (ImageView) v;
        if(createAgencyAchievement.canBeClaimed(agency) && !createAgencyAchievement.GetIsClaimed())
        {
            Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
            agencyAchievement.startAnimation(shrink);
            createAgencyAchievement.ClaimAchievement(agency);
            curTokens.setText(Integer.toString(agency.GetCurrentSeeds()));
        }
        else
        {
            //TODO Change this to throw an error card obviously. Or maybe the progress?
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
        }
    }

    public void ClaimNumberOfIdols (View v)
    {
        ImageView idolAchievement = (ImageView) v;
        if(get2Idols.canBeClaimed(agency) && !get2Idols.GetIsClaimed())
        {
            Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
            idolAchievement.startAnimation(shrink);
            get2Idols.ClaimAchievement(agency);
            curTokens.setText(Integer.toString(agency.GetCurrentSeeds()));
        }
        else
        {
            //TODO Change this to throw an error card obviously. Or maybe the progress?
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
        }
    }


    //The Following methods are for the bottom screen
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

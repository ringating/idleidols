package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Scout extends AppCompatActivity implements IdolIconListDialog.CreateDialog{
    Dialog myDialog;
    Agency agency;

    public static final DecimalFormat df =  new DecimalFormat("0.00");

    private static final String NORMAL_GACHA = "normal";
    private static final String SPECIAL_GACHA = "special";
    private static final int NORMAL_SEEDS = 3;
    private static final int SPECIAL_SEEDS = 5;

    public void showDialog(Bundle bundle)
    {
        IdolCardInfoDialog card = new IdolCardInfoDialog();
        card.setArguments(bundle);                                                         //Show the Idol Card with relevant information
        card.show(getSupportFragmentManager(), "IdolCardInfoDialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scout);
        myDialog = new Dialog(this);

        //This is where the agency passes along data to the page.
        agency = (Agency) getApplicationContext();

        TextView currency = findViewById(R.id.currency);
        currency.setText(Integer.toString(agency.GetCurrentCurrency()));

        TextView seeds = findViewById(R.id.seed);
        seeds.setText(Integer.toString(agency.GetCurrentSeeds()));

        TextView level = findViewById(R.id.level);
        level.setText(agency.GetLevel());

        TextView name = findViewById(R.id.agencyName);
        name.setText(agency.GetName());

//        GoHome(); //goes to home screen on button click
//        GoAcademies();
//        GoWorkplace();
//        GoScout();
//        GoManagement();
//        GoAchievements();
    }

    //Scouting Buttons
    public void Scout1(View v)
    {
        Button scout = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        scout.startAnimation(shrink);
        if (agency.GetCurrentSeeds() > NORMAL_SEEDS)
        {
            agency.SetSeeds(agency.GetCurrentSeeds() - NORMAL_SEEDS);
            TextView seeds = findViewById(R.id.seed);
            seeds.setText(Integer.toString(agency.GetCurrentSeeds()));
            Bundle bundle = new Bundle();

            Idol tempIdol = new Idol(NORMAL_GACHA);
            agency.addIdol(tempIdol);

            bundle.putParcelable("idol", tempIdol);

            IdolCardInfoDialog card = new IdolCardInfoDialog();
            card.setArguments(bundle);                                                         //Show the Idol Card with relevant information
            card.show(getSupportFragmentManager(), "IdolCardInfoDialog");
        }
    }

    public void Scout10(View v) {
        Button scout = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this, R.anim.button_press);
        scout.startAnimation(shrink);
        if (agency.GetCurrentSeeds() >= (NORMAL_SEEDS) * 10)
        {
            agency.SetSeeds(agency.GetCurrentSeeds() - (NORMAL_SEEDS) * 10);
            TextView seeds = findViewById(R.id.seed);
            seeds.setText(Integer.toString(agency.GetCurrentSeeds()));
            Bundle bundle = new Bundle();
            ArrayList<Idol> temp = new ArrayList<>();
            Idol tempIdol;

            for(int i = 0; i < 10; i++)
            {
                tempIdol = new Idol(NORMAL_GACHA);
                agency.addIdol(tempIdol);
                temp.add(tempIdol);
            }

            bundle.putParcelableArrayList("idol", temp);

            IdolIconListDialog card = new IdolIconListDialog();
            card.setArguments(bundle);                                                         //Show the Idol Card with relevant information
            card.show(getSupportFragmentManager(), "IdolIconListDialog");
        }
    }

    public void SpecialScout (View v)
    {
        Button scout = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        scout.startAnimation(shrink);
        if (agency.GetCurrentSeeds() > SPECIAL_SEEDS)
        {
            agency.SetSeeds(agency.GetCurrentSeeds() - SPECIAL_SEEDS);
            TextView seeds = findViewById(R.id.seed);
            seeds.setText(Integer.toString(agency.GetCurrentSeeds()));
            Bundle bundle = new Bundle();

            Idol tempIdol = new Idol(SPECIAL_GACHA);
            agency.addIdol(tempIdol);

            bundle.putParcelable("idol", tempIdol);

            IdolCardInfoDialog card = new IdolCardInfoDialog();
            card.setArguments(bundle);                                                         //Show the Idol Card with relevant information
            card.show(getSupportFragmentManager(), "IdolCardInfoDialog");
        }
    }

    //The Following methods are for the bottom screen
//    private void GoHome()
//    {
//        Button homeButton = (Button) findViewById(R.id.home_button3);
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Scout.this, MainActivity.class)); //CHANGE THIS. You need to renmae
//                // "Home" to whatever the class name of the Home screen is
//            }
//        });
//    }
//
//    private void GoAcademies()
//    {
//        Button academies = (Button) findViewById(R.id.academies_button3);
//        academies.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Scout.this, Train.class));
//            }
//        });
//    }
//
//    private void GoWorkplace()
//    {
//        Button workplace = (Button) findViewById(R.id.workplace_button3);
//        workplace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Scout.this, Work.class));
//            }
//        });
//    }
//
//    private void GoScout()
//    {
//        Button scout = (Button) findViewById(R.id.scout_button3);
//        scout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Scout.this, Scout.class));
//            }
//        });
//    }
//
//    private void GoManagement()
//    {
//        Button management = (Button) findViewById(R.id.management_button3);
//        management.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Scout.this, Management.class));
//            }
//        });
//    }
//
//    private void GoAchievements()
//    {
//        final Button achievements = (Button) findViewById(R.id.achievements_button3);
//        achievements.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Scout.this, Achievements.class)); //CHANGE THIS
//            }
//        });
//    }
}

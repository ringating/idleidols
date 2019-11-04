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

public class Work extends AppCompatActivity {

    Dialog myDialog;
    Agency agency;

    //temp stuff
    int numIdols = 0;
    boolean[] slotted = new boolean[4];
    Workplace testTask;
    boolean haveNotEarned = true;

    TextView curMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idol_work);
        myDialog = new Dialog(this);

        //This is where the agency passes along data to the page.
        agency = (Agency) getApplicationContext();

        TextView currency = findViewById(R.id.currency);
        currency.setText(Integer.toString(agency.GetCurrentCurrency()));

        TextView seeds = findViewById(R.id.seed);
        seeds.setText(Integer.toString(agency.GetCurrentSeeds()));

        TextView level = findViewById(R.id.level);
        level.setText(Integer.toString(agency.GetLevel()));

        TextView name = findViewById(R.id.agencyName);
        name.setText(agency.GetName());

        GoHome(); //goes to home screen on button click
        GoAcademies();
        GoWorkplace();
        GoScout();
        GoManagement();
        GoAchievements();

        testTask = new Workplace("Task Name", "Task Description",
                4, 1, false,
                0, 100, 1, 1, 1);



    }

    public void ShowCard(View v)
    {
        //temp stuff
        haveNotEarned = true;
        slotted = new boolean[4];

        //this is the animation for clicking on the idol image
        ImageView button = (ImageView) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        button.startAnimation(shrink);
        //this is in the window now
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

    public boolean TempAllSlotted()
    {
        return slotted[0] && slotted[1] && slotted[2] && slotted[3];
    }

    public void TempSetIdol1(View v)
    {
        // set idol in slot 1
        slotted[0] = true;
        ImageView idolFace = (ImageView) v;
        Idol tempIdol = new Idol();
        idolFace.setImageResource(tempIdol.getImage());

        if(TempAllSlotted() && haveNotEarned)
        {
            agency.SetCurrency(agency.GetCurrentCurrency() + testTask.rewardCurrency);
            curMoney = (TextView)findViewById(R.id.currency);
            curMoney.setText(Integer.toString(agency.GetCurrentCurrency()));
            haveNotEarned = false;
        }
    }

    public void TempSetIdol2(View v)
    {
        // set idol in slot 2
        slotted[1] = true;
        ImageView idolFace = (ImageView) v;
        Idol tempIdol = new Idol();
        idolFace.setImageResource(tempIdol.getImage());

        if(TempAllSlotted() && haveNotEarned)
        {
            agency.SetCurrency(agency.GetCurrentCurrency() + testTask.rewardCurrency);
            curMoney = (TextView)findViewById(R.id.currency);
            curMoney.setText(Integer.toString(agency.GetCurrentCurrency()));
            haveNotEarned = false;
        }
    }

    public void TempSetIdol3(View v)
    {
        // set idol in slot 3
        slotted[2] = true;
        ImageView idolFace = (ImageView) v;
        Idol tempIdol = new Idol();
        idolFace.setImageResource(tempIdol.getImage());

        if(TempAllSlotted() && haveNotEarned)
        {
            agency.SetCurrency(agency.GetCurrentCurrency() + testTask.rewardCurrency);
            curMoney = (TextView)findViewById(R.id.currency);
            curMoney.setText(Integer.toString(agency.GetCurrentCurrency()));
            haveNotEarned = false;
        }
    }

    public void TempSetIdol4(View v)
    {
        // set idol in slot 4
        slotted[3] = true;
        ImageView idolFace = (ImageView) v;
        Idol tempIdol = new Idol();
        idolFace.setImageResource(tempIdol.getImage());

        if(TempAllSlotted() && haveNotEarned)
        {
            agency.SetCurrency(agency.GetCurrentCurrency() + testTask.rewardCurrency);
            curMoney = (TextView)findViewById(R.id.currency);
            curMoney.setText(Integer.toString(agency.GetCurrentCurrency()));
            haveNotEarned = false;
        }
    }

    public void ReleaseButton(View v)
    {
        Button release = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        release.startAnimation(shrink);
    }

    //The Following methods are for the bottom screen
    private void GoHome()
    {
        Button homeButton = (Button) findViewById(R.id.home_button4);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Work.this, MainActivity.class));
            }
        });
    }

    private void GoAcademies()
    {
        Button academies = (Button) findViewById(R.id.academies_button4);
        academies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Work.this, Train.class));
            }
        });
    }

    private void GoWorkplace()
    {
        Button workplace = (Button) findViewById(R.id.workplace_button4);
        workplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Work.this, Work.class));
            }
        });
    }

    private void GoScout()
    {
        Button scout = (Button) findViewById(R.id.scout_button4);
        scout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Work.this, Scout.class));
            }
        });
    }

    private void GoManagement()
    {
        Button management = (Button) findViewById(R.id.management_button4);
        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Work.this, Management.class));
            }
        });
    }

    private void GoAchievements()
    {
        final Button achievements = (Button) findViewById(R.id.achievements_button4);
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Work.this, Achievements.class)); //CHANGE THIS
            }
        });
    }
}

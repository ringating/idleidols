package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Management extends AppCompatActivity {

    Dialog myDialog;
    Agency agency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management);
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

        //The button menu button functions
        GoHome();
        GoAcademies();
        GoWorkplace();
        GoScout();
        GoManagement();
        GoAchievements();

    }

    public void ShowIdolCard(View v)
    {
        //this is the animation for clicking on the idol image
        ImageView button = (ImageView) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        button.startAnimation(shrink);
        //this is in the window now
        TextView exitButton;
        myDialog.setContentView(R.layout.idolcard);
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

    public void CombineButton(View v) //combine method goes here
    {
        Button combine = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        combine.startAnimation(shrink);
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
        Button homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Management.this, MainActivity.class)); //CHANGE THIS. You need to renmae
                // "Home" to whatever the class name of the Home screen is
            }
        });
    }

    private void GoAcademies()
    {
        Button academies = (Button) findViewById(R.id.academies_button);
        academies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Management.this, Train.class));
            }
        });
    }

    private void GoWorkplace()
    {
        Button workplace = (Button) findViewById(R.id.workplace_button);
        workplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Management.this, Work.class));
            }
        });
    }

    private void GoScout()
    {
        Button scout = (Button) findViewById(R.id.scout_button);
        scout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Management.this, Scout.class));
            }
        });
    }

    private void GoManagement()
    {
        Button management = (Button) findViewById(R.id.management_button);
        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Management.this, Management.class));
            }
        });
    }

    private void GoAchievements()
    {
        final Button achievements = (Button) findViewById(R.id.achievements_button);
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Management.this, Achievements.class)); //CHANGE THIS
            }
        });
    }
}

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

public class Train extends AppCompatActivity {

    Dialog myDialog;
    Agency agency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idol_train);
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

        GoHome(); //goes to home screen on button click
        GoAcademies();
        GoWorkplace();
        GoScout();
        GoManagement();
        GoAchievements();

        Academy testTask = new Academy("Task Name", "Task Description",
                4, 1, false,
                0.1f, 0.2f, 0.3f);

        TextView title1 = findViewById(R.id.textView2);
        title1.setText(testTask.name);

        TextView description1 = findViewById(R.id.textView4);
        description1.setText(testTask.description);

        TextView slotCount1 = findViewById(R.id.textView5);
        slotCount1.setText("Occupied Slots: " + testTask.getNumSlottedIdols() + "/" + testTask.numSlots);
    }

    public void ShowCard(View v)
    {
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

    public void ReleaseButton(View v)
    {
        Button release = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        release.startAnimation(shrink);
    }

    //The Following methods are for the bottom screen
    private void GoHome()
    {
        Button homeButton = (Button) findViewById(R.id.home_button2);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Train.this, MainActivity.class));
            }
        });
    }

    private void GoAcademies()
    {
        Button academies = (Button) findViewById(R.id.academies_button2);
        academies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Train.this, Train.class));//SAME THING CHANGE THIS
            }
        });
    }

    private void GoWorkplace()
    {
        Button workplace = (Button) findViewById(R.id.workplace_button2);
        workplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Train.this, Work.class));
            }
        });
    }

    private void GoScout()
    {
        Button scout = (Button) findViewById(R.id.scout_button2);
        scout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Train.this, Scout.class));
            }
        });
    }

    private void GoManagement()
    {
        Button management = (Button) findViewById(R.id.management_button2);
        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Train.this, Management.class));
            }
        });
    }

    private void GoAchievements()
    {
        final Button achievements = (Button) findViewById(R.id.achievements_button2);
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Train.this, Achievements.class)); //CHANGE THIS
            }
        });
    }
}

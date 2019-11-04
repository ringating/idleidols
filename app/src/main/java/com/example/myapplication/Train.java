package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Train extends AppCompatActivity {

    Dialog myDialog;
    Agency agency;

    ArrayList<Idol> idols;      // placeholder array of idols
    public static final DecimalFormat df =  new DecimalFormat("0.00");

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
        level.setText(Integer.toString(agency.GetLevel()));

        TextView name = findViewById(R.id.agencyName);
        name.setText(agency.GetName());

        ProgressBar expBar = findViewById(R.id.expBar);
        agency.SetExpNeededToLevel(agency.GetLevel());
        expBar.setMax(agency.GetExpNeededToLevel());
        expBar.setProgress(agency.GetCurrentExp());

        idols = agency.GetIdols();

        GoHome(); //goes to home screen on button click
        GoAcademies();
        GoWorkplace();
        GoScout();
        GoManagement();
        GoAchievements();

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

    public void ShowIdolList(View v)
    {
        ImageView idolIcon = (ImageView) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        idolIcon.startAnimation(shrink);
        //opens the window
        myDialog.setContentView(R.layout.idol_list_menu);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

        displayList();
    }

    private void displayList()
    {
        TableLayout table = findViewById(R.id.idolViewMenu);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        final TableRow.LayoutParams bp = new TableRow.LayoutParams(300,300);
        bp.leftMargin = 15;
        bp.rightMargin = 15;
        bp.bottomMargin = 30;
        bp.gravity = Gravity.CENTER;

        int count = 0;

        for (int i = 0; i <= (idols.size() / 4); i++)
        {
            TableRow row = new TableRow(this);  //Generates a new row ever four row elements
            row.setLayoutParams(lp);
            for(int j = 0; j < 4 && count < idols.size(); j++)
            {
                ImageView icon = new ImageView(this);
                icon.setId(idols.get(count).getIdolId());
                icon.setLayoutParams(bp);
                icon.setBackgroundResource(idols.get(count).getImage());
                row.addView(icon);

                count++;
            }

            table.addView(row, i);

        }
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

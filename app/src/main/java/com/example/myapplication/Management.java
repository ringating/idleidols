package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Management extends AppCompatActivity {

    Dialog myDialog;
    Agency agency;
    ArrayList<Idol> idols;      // placeholder array of idols

    public static final DecimalFormat df =  new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management);
        myDialog = new Dialog(this);
        idols = new ArrayList<>();

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

        populateArray();        // placeholder, generates a random list of random idols

        generateList();         // dynamically creates a table layout based on number of idols

    }

    public void populateArray()
    {
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(50); i++)
        {
            idols.add(new Idol());
        }
    }

    public void generateList()
    {
        TableLayout table = findViewById(R.id.table);
        table.removeAllViews();
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        final TableRow.LayoutParams bp = new TableRow.LayoutParams(300,300);
        bp.leftMargin = 15;
        bp.rightMargin = 15;
        bp.bottomMargin = 30;
        bp.gravity = Gravity.CENTER;

        int id = 0;

        for (int i = 0; i <= (idols.size() / 4); i++)
        {
            TableRow row = new TableRow(this);  //Generates a new row ever four row elements
            row.setLayoutParams(lp);

            for (int n = 0; n < 4 && id < idols.size(); n++) {
                ImageView button = new ImageView(this);         //Four buttons are generated each row
                button.setId(id);                                       //Each button is set a unique id
                button.setLayoutParams(bp);
                button.setBackgroundResource(idols.get(id).getImage()); //The image of the button is taken from the index of the Idol array
                row.addView(button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView button = (ImageView) v;                                                        //
                        Animation shrink = AnimationUtils.loadAnimation( getBaseContext(), R.anim.button_press); // Adds some animation to the tapping, makes it look like I know what I'm doing (I don't)
                        button.startAnimation(shrink);                                                           //

                        Bundle bundle = new Bundle();

                        bundle.putString("name", idols.get(v.getId()).getIdolName());
                        bundle.putString("rarity", Integer.toString(idols.get(v.getId()).getRarity()));
                        bundle.putString("dance", df.format(idols.get(v.getId()).getDanceStat()));
                        bundle.putString("sing", df.format(idols.get(v.getId()).getSingStat()));
                        bundle.putString("charm", df.format(idols.get(v.getId()).getCharmStat()));         //Send the data of a specific idol at index "id" to the Card Fragment to be displayed
                        bundle.putInt("image", idols.get(v.getId()).getImage());

                        IdolCardDialog card = new IdolCardDialog();
                        card.setArguments(bundle);                                                         //Show the Idol Card with relevant information
                        card.show(getSupportFragmentManager(), "IdolCardDialog");
                    }
                });
                id++;
            }

            table.addView(row, i);

        }
    }

    public void CombineButton(View v) //combine method goes here
    {
        Button combine = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        combine.startAnimation(shrink);
        idols.add(new Idol());
        generateList();
    }

    public void ReleaseButton(View v)
    {
        Button release = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        release.startAnimation(shrink);
        if (!idols.isEmpty()) {
            idols.remove(idols.size() - 1);
        }
        generateList();
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

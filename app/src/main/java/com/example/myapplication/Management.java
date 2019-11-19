package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Management extends AppCompatActivity implements WarningDialog.Sender{

    Dialog myDialog;
    Agency agency;
    int mode = 0;
    int idolsIterate = 0;
    int[] idols = new int[2]; // array of idol indexes to be combined together in matrimony
    boolean accept = false;

    public static final DecimalFormat df =  new DecimalFormat("0.00");

    public void Send(boolean accept, int extra, int extra2, int extra3) //This is called when the "ok" button is pressed in WarningDialog
    {
        if (extra3 == 1)
        {
            agency.removeIdolAtIndex(extra);
            generateList();
        }
        else if (extra3 == 2)
        {
            agency.combineIdols(extra, extra2);
            generateList();
        }
    }

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

        generateList();         // dynamically creates a table layout based on number of idols

    }

    public void generateList()
    {
        TableLayout table = findViewById(R.id.table);
        table.removeAllViews();
        TableRow.LayoutParams rowLayout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);    // Row Layout
        rowLayout.gravity = Gravity.CENTER_HORIZONTAL;                                                      //
        final TableRow.LayoutParams buttonLayout = new TableRow.LayoutParams(300,300);   //
        buttonLayout.leftMargin = 15;                                                           //
        buttonLayout.rightMargin = 15;                                                          // Button Layout
        buttonLayout.bottomMargin = 30;                                                         //
        buttonLayout.gravity = Gravity.CENTER;                                                  //

        int id = 0;  // ID for each button

        for (int i = 0; i <= (agency.numberOfIdols() / 4); i++)
        {
            TableRow row = new TableRow(this);  //Generates a new row every four row elements
            row.setLayoutParams(rowLayout);

            for (int n = 0; n < 4 && id < agency.numberOfIdols(); n++) {
                ImageView button = new ImageView(this);         //Four buttons are generated each row
                button.setId(id);                                       //Each button is set a unique id
                button.setLayoutParams(buttonLayout);                   //The layout is set for the button
                button.setBackgroundResource(agency.getIdol(id).getImage()); //The image of the button is taken from the index of the Idol array
                row.addView(button);                                         //The button is finally added to the row
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView button = (ImageView) v;                                                        //
                        Animation shrink = AnimationUtils.loadAnimation( getBaseContext(), R.anim.button_press); // Adds some animation to the tapping, makes it look like I know what I'm doing (I don't)
                        button.startAnimation(shrink);                                                           //

                        Bundle bundle;

                        switch (mode)   //The button will do different things depending on what "mode" management is in
                        {
                            case 0:
                                bundle = new Bundle();

                                ArrayList<Idol> temp = new ArrayList<>();

                                temp.add(agency.getIdol(v.getId()));

                                bundle.putParcelableArrayList("idol", temp);

                                IdolCardDialog card = new IdolCardDialog();
                                card.setArguments(bundle);                                                         //Show the Idol Card with relevant information
                                card.show(getSupportFragmentManager(), "IdolCardDialog");
                                break;
                            case 2:
                                bundle = new Bundle();

                                bundle.putInt("index1", v.getId());  //Sends the index of the idol being affected for the interface
                                bundle.putInt("option", 1);
                                bundle.putString("message", "Are you sure you want to remove " + agency.getIdol(v.getId()).getIdolName() + " from existence?"); //Custom message for WarningDialog

                                WarningDialog warning = new WarningDialog();
                                warning.setArguments(bundle);
                                warning.show(getSupportFragmentManager(), "WarningDialog"); //Show warning
                                break;
                            case 3:
                                idols[idolsIterate] = v.getId();
                                idolsIterate++;
                                if (idolsIterate == 2) {
                                    bundle = new Bundle();

                                    bundle.putInt("index1", idols[0]);  //Sends the index of the idol being affected for the interface
                                    bundle.putInt("index2", idols[1]);
                                    bundle.putInt("option", 2);
                                    bundle.putString("message", "Are you sure you want to combine " + agency.getIdol(idols[0]).getIdolName() + " and " + agency.getIdol(idols[1]).getIdolName()); //Custom message for WarningDialog

                                    WarningDialog ask = new WarningDialog();
                                    ask.setArguments(bundle);
                                    ask.show(getSupportFragmentManager(), "WarningDialog"); //Show warning
                                }
                                mode = 0;
                                idolsIterate = 0;
                                ImageView border = findViewById(R.id.warningBorder);
                                border.setVisibility(View.INVISIBLE);
                                break;
                            default:
                        }
                    }
                });
                id++;
            }

            table.addView(row, i);  //The row is added to the table

        }
    }

    public void CombineButton(View v) //combine method goes here
    {
        Button combine = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        combine.startAnimation(shrink);
        ImageView border = findViewById(R.id.warningBorder);
        if (mode == 3)
        {
            mode = 0;
            idolsIterate = 0;
            border.setVisibility(View.INVISIBLE);
        }
        else if (agency.numberOfIdols() > 0)
        {
            border.setVisibility(View.VISIBLE);
            mode = 3;
        }
    }

    public void ReleaseButton(View v)
    {
        Button release = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        release.startAnimation(shrink);
        ImageView border = findViewById(R.id.warningBorder);
        if (mode == 2)
        {
            mode = 0;
            border.setVisibility(View.INVISIBLE);
        }
        else if (agency.numberOfIdols() > 0)
        {

            border.setVisibility(View.VISIBLE);
            mode = 2;
        }
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

package com.example.myapplication;
// package com.example.idleidol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SettingsDialogFragment.EditNameDialogListener, NewAgencyFragment.NameAgencyDialogListener {

    static final int GET_RENAME_AGENCY = 1;

    Agency agency;

    //these are the variables regarding the header.
    TextView curMoney;
    TextView curTokens;
    TextView curLevel;
    TextView agencyName;
    ProgressBar expBar;

    private static boolean firstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) // Some android stuff, probably important
    {
        Log.d("idleidol", "hello from MainActivity's OnCreate method");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agency = (Agency) getApplicationContext();

        DataForSaveLoad load = SaveLoad.load(getApplicationContext());
        if(firstRun && load != null)
        {
            Log.d("idleIdol", "loading data from file (should only happen once per app run!)");
            // overwrite values in app context Agency with the values from the loaded data
            DataForSaveLoad.copyAgencyValues(agency, load.getAgency());
        }
        firstRun = false;

        final FragmentManager fragmentManager = this.getSupportFragmentManager();

        if(agency.getFirstTimeFlag())
        {
            NewAgencyFragment agencyFragment = new NewAgencyFragment();
            agencyFragment.show(fragmentManager, "NewAgency");
        }

        //Displays the information on the header.
        curMoney = (TextView)findViewById(R.id.currency);
        curMoney.setText(Integer.toString(agency.GetCurrentCurrency()));

        curTokens = (TextView)findViewById(R.id.seed);
        curTokens.setText(Integer.toString(agency.GetCurrentSeeds()));

        curLevel = (TextView)findViewById(R.id.level);
        curLevel.setText(agency.GetLevel());

        agencyName = (TextView)findViewById(R.id.agencyName);
        agencyName.setText(agency.GetName());

        expBar = (ProgressBar)findViewById(R.id.expBar);
        agency.SetExpNeededToLevel(agency.GetLevel());
        expBar.setMax(agency.GetExpNeededToLevel());
        expBar.setProgress(agency.GetCurrentExp());

        ImageView settings = findViewById(R.id.settingsButton);
        Animation shrink = AnimationUtils.loadAnimation(this, R.anim.button_press);
        settings.startAnimation(shrink);
        final SettingsDialogFragment settingsMenu = new SettingsDialogFragment();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsMenu.show(fragmentManager, "SettingsMenu");
            }
        });

    }

    //Renames the agency
    @Override
    public void onFinishEditDialog(String newName)
    {
        if(!newName.equals(agency.GetName()) && !newName.equals(""))
        {
            agency.SetName(newName);
            agencyName.setText(agency.GetName());
        }
    }

    //Names the agency at the start of the game
    @Override
    public void onFinishNameDialog(String name)
    {
        agency.SetName(name);
        agencyName.setText(agency.GetName());

        agency.SetLevel(1);
        curLevel.setText(agency.GetLevel());

        agency.SetTotalCurrency(0);
        agency.SetCurrency(0);
        curMoney.setText(Integer.toString(agency.GetCurrentCurrency()));

        agency.SetSeeds(0);
        agency.SetTotalSeeds(0);
        curTokens.setText(Integer.toString(agency.GetCurrentSeeds()));

        agency.SetCurrentExp(0);
        agency.SetExpNeededToLevel(agency.GetLevel());
        expBar.setMax(agency.GetExpNeededToLevel());
        expBar.setProgress(agency.GetCurrentExp());

        agency.setFirstTimeFlag(false);
    }

    public void idolDoThing(View v) //When the idol is pressed, it does this
    {
        final int NORMAL_CLICK = 10;

        ImageView button = (ImageView) v;                                                   //
        Animation shrink = AnimationUtils.loadAnimation(this, R.anim.button_press); // This animation does something different because it is special

        button.startAnimation(shrink);                                                      //

        button.startAnimation(shrink);//

        //increasing by clicking normally.
        //We can later add a graphic or a text view of +10 or whatever to have a visual showing of the increase instead of just the number going up.
        agency.SetCurrency(agency.GetCurrentCurrency() + NORMAL_CLICK); //this will store the currency value in case we need to trade screens.
        agency.SetTotalCurrency(agency.GetTotalCurrency() + NORMAL_CLICK); //store the total collected currency.
        curMoney.setText(Integer.toString(agency.GetCurrentCurrency()));

    }
}

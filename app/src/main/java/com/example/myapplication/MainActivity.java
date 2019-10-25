package com.example.myapplication;
// package com.example.idleidol;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Dialog myDialog;
    private final int NORMAL_CLICK = 10;
    Agency agency;

    //these are the variables regarding the header.
    private int currency;
    TextView curMoney;
    private int seeds;
    TextView curTokens;
    private int level;
    TextView curLevel;
    private String name;
    TextView agencyName;


    @Override
    protected void onCreate(Bundle savedInstanceState) // Some android stuff, probably important
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);
        //Creates the Agency object in the context of the application instead of just the page.
        agency = (Agency) getApplicationContext();

        //Displays the information on the header.
        curMoney = (TextView)findViewById(R.id.currency);
        currency = agency.GetCurrentCurrency();
        curMoney.setText(Integer.toString(currency));

        curTokens = (TextView)findViewById(R.id.seed);
        seeds = agency.GetCurrentSeeds();
        curTokens.setText(Integer.toString(seeds));

        curLevel = (TextView)findViewById(R.id.level);
        agency.SetLevel(01); //TODO this is just a place holder. Will change later!
        level = agency.GetLevel();
        curLevel.setText(Integer.toString(level));

        agencyName = (TextView)findViewById(R.id.agencyName);
        agency.SetName("AgencyName"); //TODO this is just a place holder!
        name = agency.GetName();
        agencyName.setText(name);
    }

    public void openAchievements(View v) // When the achievements button is pressed, it does this
                                         // All methods are currently called from the activity_main.xml by android:onClick
    {
        ImageView button = (ImageView) v;                                                   //
        Animation shrink = AnimationUtils.loadAnimation(this, R.anim.button_press); // Adds some animation to the tapping, makes it look like I know what I'm doing (I don't)

        button.startAnimation(shrink);                                                      //
        startActivity(new Intent(MainActivity.this, Achievements.class));

        button.startAnimation(shrink);//
        curMoney = (TextView) findViewById(R.id.currency);

        Intent i = new Intent(MainActivity.this, Achievements.class);
        startActivity(i);

    }

    public void openManagement(View v) //When the management button is pressed, it does this
    {
        ImageView button = (ImageView) v;                                                   //
        Animation shrink = AnimationUtils.loadAnimation(this, R.anim.button_press); // These three lines are everywhere
        button.startAnimation(shrink);                                                      //

        startActivity(new Intent(MainActivity.this, Management.class));
    }

    public void openRecruitment(View v) //When the recruitment button is pressed, it does this
    {
        ImageView button = (ImageView) v;
        Animation shrink = AnimationUtils.loadAnimation(this, R.anim.button_press);
        button.startAnimation(shrink);

        startActivity(new Intent(MainActivity.this, Scout.class));
    }

    public void openSettings(View v) //Opens a popup window, will probably change in the future
    {
        ImageView button = (ImageView) v;
        Animation shrink = AnimationUtils.loadAnimation(this, R.anim.button_press);
        button.startAnimation(shrink);
        ImageView buttonClose;
        myDialog.setContentView(R.layout.settings_menu);
        buttonClose = myDialog.findViewById(R.id.closeButton);
        buttonClose.setOnClickListener(new View.OnClickListener()   //
        {                                                           //
            @Override                                               //
            public void onClick(View v)                             // Makes sure you're not stuck on the setting screen forever until you die
            {                                                       // by having the X button do what it should
                myDialog.dismiss();                                 // Note that the rename input box is totally working as expected and is ready for production
            }                                                       //
        });                                                         //

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void idolDoThing(View v) //When the idol is pressed, it does this
    {
        ImageView button = (ImageView) v;                                                   //
        Animation shrink = AnimationUtils.loadAnimation(this, R.anim.button_press); // This animation does something different because it is special

        button.startAnimation(shrink);                                                      //

        button.startAnimation(shrink);//

        //increasing by clicking normally.
        //We can later add a graphic or a text view of +10 or whatever to have a visual showing of the increase instead of just the number going up.
        currency += NORMAL_CLICK;
        agency.SetCurrency(currency); //this will store the currency value in case we need to trade screens.
        agency.SetTotalCurrency(agency.GetTotalCurrency() + currency); //store the total collected currency.
        curMoney.setText(Integer.toString(currency));

    }

    public void openWork(View v) //When the work button is pressed, it does this
    {
        ImageView button = (ImageView) v;
        Animation move = AnimationUtils.loadAnimation(this, R.anim.another_another_button_press);
        button.startAnimation(move);

        startActivity(new Intent(MainActivity.this, Work.class));
    }

    public void openAcademy(View v) //When the academy button is pressed, it does this
    {
        ImageView button = (ImageView) v;
        Animation move = AnimationUtils.loadAnimation(this, R.anim.another_button_press);
        button.startAnimation(move);

        startActivity(new Intent(MainActivity.this, Train.class));
    }
}

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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityWork extends AppCompatActivity {

    private static final String TAG = "Workplace";

    Dialog myDialog;
    Agency agency;

    //temp stuff
    int numIdols = 0;
    boolean[] slotted = new boolean[4];
    boolean haveNotEarned = true;

    TextView curMoney;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idol_work);
        manager = getSupportFragmentManager();
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

        ProgressBar expBar = findViewById(R.id.expBar);
        expBar.setMax(agency.GetExpNeededToLevel());
        expBar.setProgress(agency.GetCurrentExp());


        loadWorkFragments();

    }

    private void loadWorkFragments()
    {
        for (Task value : Task.values())
        {
            FragmentTransaction transaction = manager.beginTransaction();
            Bundle sendToWorkFrag = new Bundle();
            sendToWorkFrag.putSerializable("Agency", agency);

            if(value.type == 0)
            {
                WorkplaceFragment fragment = WorkplaceFragment.createWorkplaceFragment(value, sendToWorkFrag);
                transaction.add(R.id.workplace_list, fragment);
                transaction.commit();
            }
        }
    }

    public boolean TempAllSlotted()
    {
        return slotted[0] && slotted[1] && slotted[2] && slotted[3];
    }

    public void ReleaseButton(View v)
    {
        Button release = (Button) v;
        Animation shrink = AnimationUtils.loadAnimation(this,R.anim.button_press);
        release.startAnimation(shrink);
    }

    @Override
    protected void onStop()
    {
        // autosave upon leaving this activity
        super.onStop();
        SaveLoad.save(this.getApplicationContext(), new DataForSaveLoad((Agency) this.getApplicationContext()));
    }
}
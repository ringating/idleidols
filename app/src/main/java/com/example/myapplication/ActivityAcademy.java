package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
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

public class ActivityAcademy extends AppCompatActivity {

    Dialog myDialog;
    Agency agency;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idol_train);
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

        loadAcademyFragments();
    }

    private void loadAcademyFragments()
    {
        for (Task value : Task.values())
        {
            FragmentTransaction transaction = manager.beginTransaction();
            Bundle sendToWorkFrag = new Bundle();
            sendToWorkFrag.putSerializable("Agency", agency);

            if(value.type == 1)
            {
                AcademyFragment fragment = AcademyFragment.createAcademyFragment(value, sendToWorkFrag);
                transaction.add(R.id.workplace_list, fragment);
                transaction.commit();
            }
        }
    }


    @Override
    protected void onStop()
    {
        // autosave upon leaving this activity
        super.onStop();
        SaveLoad.save(this.getApplicationContext(), new DataForSaveLoad((Agency) this.getApplicationContext()));
    }
}

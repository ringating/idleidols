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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Work extends AppCompatActivity {

    private static final String TAG = "Workplace";

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
        level.setText(agency.GetLevel());

        TextView name = findViewById(R.id.agencyName);
        name.setText(agency.GetName());

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

        FragmentManager taskCard = getSupportFragmentManager();
        //Sends information to IdolListMenuDialog
        TaskIdolCard taskIdolCard = new TaskIdolCard();
        Bundle sendToIdolList = new Bundle();
        sendToIdolList.putParcelableArrayList("IdolArrayList", agency.GetIdols());
        taskIdolCard.setArguments(sendToIdolList);

        //Opens the TaskIdolCard
        taskIdolCard.show(taskCard, "TaskCard");
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
}

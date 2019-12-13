package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class WorkplaceFragment extends Fragment
{
    public Task workplace;
    private static int SLOTTED_IDOLS = 1;

    public Agency agency;

    public WorkplaceFragment()
    {
        //empty constructor
    }

    //Creates the workplace
    public static WorkplaceFragment createWorkplaceFragment(Task workplace, Bundle agency)
    {
        WorkplaceFragment fragment = new WorkplaceFragment();
        fragment.agency = (Agency)agency.getSerializable("Agency");
        fragment.workplace = workplace;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.workplace_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        workplace.setStartTime(agency.taskStartTimes[workplace.ordinal()]); // load task's saved start time from agency

        //TODO: Put the actual dynamic icon showing here along with the onClick function.
        if(Integer.parseInt(agency.GetLevel()) >= workplace.reqLevel)
        {
            workplace.unlocked = true;
        }
        final RelativeLayout fragmentLayout = view.findViewById(R.id.workplaceFragment);
        fragmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(workplace.unlocked)
                {
                    Animation shrink = AnimationUtils.loadAnimation(getActivity(), R.anim.button_press);
                    fragmentLayout.startAnimation(shrink);
                    ShowCard();
                }
                else
                {
                    Toast toast = Toast.makeText(getContext(), "Facility not yet Unlocked", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        ImageView icon = view.findViewById(R.id.workplace_icon);
        icon.setImageResource(workplace.image);

        ImageView danceIcon = view.findViewById(R.id.workplace_dance);
        if(workplace.dance != 1)
        {
            danceIcon.setVisibility(View.INVISIBLE);
        }

        ImageView singIcon = view.findViewById(R.id.workplace_sing);
        if(workplace.sing != 1)
        {
            singIcon.setVisibility(View.INVISIBLE);
        }

        ImageView charmIcon = view.findViewById(R.id.workplace_charm);
        if(workplace.charm != 1)
        {
            charmIcon.setVisibility(View.INVISIBLE);
        }

        TextView name = view.findViewById(R.id.workplace_name);
        name.setText(workplace.name);

        TextView cost = view.findViewById(R.id.workplace_cost_content);
        cost.setText(Integer.toString(workplace.cost));

        TextView profit = view.findViewById(R.id.workplace_profit_content);
        profit.setText(Integer.toString(workplace.rewardCurrency));

        TextView duration = view.findViewById(R.id.workplace_duration_content);
        int processTime = (int) workplace.processTime/1000;
        String durationText = processTime + " seconds"; //TODO Probably want to change this so it tells you how many hours and stuff too.
        duration.setText(durationText);
    }

    private void ShowCard()
    {
        FragmentManager taskCard = getFragmentManager();
        //Sends information to IdolListMenuDialog
        TaskIdolCard taskIdolCard = new TaskIdolCard();
        taskIdolCard.setTargetFragment(this, SLOTTED_IDOLS);

        Bundle sendToIdolList = new Bundle();
        sendToIdolList.putParcelableArrayList("IdolArrayList", agency.GetIdols());
        sendToIdolList.putInt("numberOfSlots", workplace.numSlots);
        sendToIdolList.putParcelableArray("IdolSlot", workplace.idolSlots);
        sendToIdolList.putInt("cost", workplace.cost);
        sendToIdolList.putInt("profit", workplace.rewardCurrency);
        sendToIdolList.putLong("duration", workplace.processTime);
        sendToIdolList.putFloat("dance", workplace.dance);
        sendToIdolList.putFloat("sing", workplace.sing);
        sendToIdolList.putFloat("charm", workplace.charm);
        sendToIdolList.putBoolean("started", workplace.started);
        taskIdolCard.setArguments(sendToIdolList);
        sendToIdolList.putInt("taskOrdinal", workplace.ordinal());

        //Opens the TaskIdolCard
        if(taskCard != null)
        {
            taskIdolCard.show(taskCard, "TaskCard");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode == SLOTTED_IDOLS)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Idol[] getSlottedIdols = (Idol[]) intent.getParcelableArrayExtra("slottedIdols");
                workplace.idolSlots = getSlottedIdols;
            }
            else if(resultCode == 2)
            {
                workplace.started = intent.getBooleanExtra("startedTask", false);
            }
        }
    }
}

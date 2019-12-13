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

public class AcademyFragment extends Fragment {

    private Task academy;
    private static int SLOTTED_IDOLS = 1;

    private Agency agency;

    public AcademyFragment()
    {
        //empty constructor
    }

    //Creates the workplace
    public static AcademyFragment createAcademyFragment(Task academy, Bundle agency)
    {
        AcademyFragment fragment = new AcademyFragment();
        fragment.agency = (Agency)agency.getSerializable("Agency");
        fragment.academy = academy;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.academy_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if(Integer.parseInt(agency.GetLevel()) >= academy.reqLevel)
        {
            academy.unlocked = true;
        }
        final RelativeLayout fragmentLayout = view.findViewById(R.id.academy_fragment);
        fragmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(academy.unlocked)
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

        ImageView icon = view.findViewById(R.id.academy_icon);
        icon.setImageResource(academy.image);

        ImageView danceIcon = view.findViewById(R.id.academy_dance);
        if(academy.dance == 0)
        {
            danceIcon.setVisibility(View.INVISIBLE);
        }

        ImageView singIcon = view.findViewById(R.id.academy_sing);
        if(academy.sing == 0)
        {
            singIcon.setVisibility(View.INVISIBLE);
        }

        ImageView charmIcon = view.findViewById(R.id.academy_charm);
        if(academy.charm == 0)
        {
            charmIcon.setVisibility(View.INVISIBLE);
        }

        TextView name = view.findViewById(R.id.academy_name);
        name.setText(academy.name);

        TextView level = view.findViewById(R.id.academyLevelContent);
        level.setText(Integer.toString(academy.level));

        TextView occupied = view.findViewById(R.id.academy_occupied_slots_content);
        occupied.setText(Integer.toString(academy.numOfIdols));

        TextView totalSlots = view.findViewById(R.id.academy_total_slots_content);
        totalSlots.setText(Integer.toString(academy.numSlots));
    }

    private void ShowCard()
    {
        FragmentManager taskCard = getFragmentManager();
        //Sends information to IdolListMenuDialog
        AcademyIdolCard taskIdolCard = new AcademyIdolCard(); //TODO CHANGE THE CARD LOAD OUT HERE
        taskIdolCard.setTargetFragment(this, SLOTTED_IDOLS);

        Bundle sendToIdolList = new Bundle();
        sendToIdolList.putParcelableArrayList("IdolArrayList", agency.GetIdols());
        sendToIdolList.putInt("numberOfSlots", academy.numSlots);
        sendToIdolList.putParcelableArray("IdolSlot", academy.idolSlots);
        sendToIdolList.putInt("level", academy.level);
        sendToIdolList.putInt("cost", academy.cost);
        sendToIdolList.putFloat("dance", academy.dance);
        sendToIdolList.putFloat("sing", academy.sing);
        sendToIdolList.putFloat("charm", academy.charm);
        taskIdolCard.setArguments(sendToIdolList);

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
                academy.idolSlots = getSlottedIdols;
            }
        }
    }
}

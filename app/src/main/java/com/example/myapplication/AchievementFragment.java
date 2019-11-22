package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringWriter;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment
{
    private Achievement achievement;
    private static final String TAG = "AchievementFragment";

    private Agency agency;
    ImageView iconView;

    public AchievementFragment()
    {
        // Required empty public constructor
    }

    // This method is a static factory method.
    public static AchievementFragment createAchievementFragment(Achievement achievement, Bundle bundle)
    {
        AchievementFragment fragment = new AchievementFragment();
        fragment.achievement = achievement;
        fragment.agency = (Agency) bundle.getSerializable("Agency");

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if(agency == null)
        {
            Log.e(TAG, "Agency was not passed properly");
        }
        else
        {
            switch(achievement)
            {
                case CREATE_AGENCY:
                case NUMBER_OF_IDOLS:
                case EARN_MONEY:
                    achievement.canBeClaimed(agency);
                    break;
            }
        }
        iconView = view.findViewById(R.id.achievement_icon);
        if(achievement.GetCanClaim() && !achievement.GetIsClaimed())
        {
            iconView.setImageResource(achievement.iconUnlocked);
        }
        else if(achievement.GetIsClaimed())
        {
            iconView.setImageResource(achievement.iconClaimed);
        }
        else
        {
            iconView.setImageResource(achievement.iconLocked);
        }

        TextView titleView = view.findViewById(R.id.achievement_name);
        titleView.setText(achievement.title);

        TextView descriptionView = view.findViewById(R.id.achievement_description);
        descriptionView.setText(achievement.description);

        TextView rewardView = view.findViewById(R.id.achievement_reward_content);
        String rewardOutput = "";
        if(achievement.money != 0)
        {
            rewardOutput += " Money: " + achievement.money;
        }
        if(achievement.seeds != 0)
        {
            rewardOutput += " Seeds: " + achievement.seeds;
        }
        if(achievement.exp != 0)
        {
            rewardOutput += " EXP: " + achievement.exp;
        }
        rewardView.setText(rewardOutput);

        final RelativeLayout achievementCard = view.findViewById(R.id.achievementFragment);
        achievementCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation shrink = AnimationUtils.loadAnimation(getActivity(), R.anim.button_press);
                achievementCard.startAnimation(shrink);
                if(achievement.GetCanClaim() && !achievement.GetIsClaimed())
                {
                    achievement.ClaimAchievement(agency);
                    Toast toast = Toast.makeText(getActivity(), "Claimed Achievement!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(!achievement.GetCanClaim())
                {
                    Toast toast = Toast.makeText(getActivity(), "Must complete Achievement", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (achievement.GetIsClaimed())
                {
                    Toast toast = Toast.makeText(getActivity(), "Already Claimed", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }

}

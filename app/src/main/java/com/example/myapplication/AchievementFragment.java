package com.example.myapplication;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.StringWriter;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment
{
    private Achievement achievement;

    public AchievementFragment()
    {
        // Required empty public constructor
    }

    // This method is a static factory method.
    public static AchievementFragment createAchievementFragment(Achievement achievement)
    {
        AchievementFragment fragment = new AchievementFragment();
        fragment.achievement = achievement;
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

        final ImageView iconView = view.findViewById(R.id.achievement_icon);
        iconView.setImageResource(achievement.imageDrawable);

        final TextView titleView = view.findViewById(R.id.achievement_name);
        titleView.setText(achievement.title);

        final TextView descriptionView = view.findViewById(R.id.achievement_description);
        descriptionView.setText(achievement.description);

        final TextView rewardView = view.findViewById(R.id.achievement_reward);
        String rewardOutput = "Money: $" + achievement.money
                + "; Seeds: " + achievement.seeds;
        rewardView.setText(rewardOutput);
    }
}

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NavigationFragment extends Fragment {
    private ImageView home;
    private ImageView academies;
    private ImageView workplace;
    private ImageView recruit;
    private ImageView management;
    private ImageView achievements;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.navigation_fragment, container, false);

        home = view.findViewById(R.id.homeButton);
        academies = view.findViewById(R.id.academyButton);
        workplace = view.findViewById(R.id.workplaceButton);
        recruit = view.findViewById(R.id.recruitButton);
        management = view.findViewById(R.id.managementButton);
        achievements = view.findViewById(R.id.achievementsButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        academies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Train.class));
            }
        });

        workplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Work.class));
            }
        });

        recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Scout.class));
            }
        });

        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Management.class));
            }
        });

        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Achievements.class));
            }
        });

        return view;
    }
}

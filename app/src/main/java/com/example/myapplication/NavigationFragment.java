package com.example.myapplication;

import android.annotation.SuppressLint;
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
    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.navigation_fragment, container, false);

        home = view.findViewById(R.id.homeButton);
        academies = view.findViewById(R.id.academyButton);
        workplace = view.findViewById(R.id.workplaceButton);
        recruit = view.findViewById(R.id.recruitButton);
        management = view.findViewById(R.id.managementButton);
        achievements = view.findViewById(R.id.achievementsButton);

        expandButton();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().overridePendingTransition(0, 0);
            }
        });

        academies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivityAcademy.class));
                getActivity().overridePendingTransition(0, 0);
            }
        });

        workplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivityWork.class));
                getActivity().overridePendingTransition(0, 0);
            }
        });

        recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Scout.class));
                getActivity().overridePendingTransition(0, 0);
            }
        });

        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Management.class));
                getActivity().overridePendingTransition(0, 0);
            }
        });

        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Achievements.class));
                getActivity().overridePendingTransition(0, 0);
            }
        });

        return view;
    }

    private void expandButton()
    {
        if (getActivity() != null) {
            if (getActivity().getClass() == MainActivity.class) {
                ViewGroup.LayoutParams params = home.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                home.setBackgroundResource(R.drawable.nav_icon_background_expanded);
                home.setLayoutParams(params);
            } else if (getActivity().getClass() == ActivityAcademy.class) {
                ViewGroup.LayoutParams params = academies.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                academies.setBackgroundResource(R.drawable.nav_icon_background_expanded);
                academies.setLayoutParams(params);
            } else if (getActivity().getClass() == ActivityWork.class) {
                ViewGroup.LayoutParams params = workplace.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                workplace.setBackgroundResource(R.drawable.nav_icon_background_expanded);
                workplace.setLayoutParams(params);
            } else if (getActivity().getClass() == Scout.class) {
                ViewGroup.LayoutParams params = recruit.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                recruit.setBackgroundResource(R.drawable.nav_icon_background_expanded);
                recruit.setLayoutParams(params);
            } else if (getActivity().getClass() == Management.class) {
                ViewGroup.LayoutParams params = management.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                management.setBackgroundResource(R.drawable.nav_icon_background_expanded);
                management.setLayoutParams(params);
            } else if (getActivity().getClass() == Achievements.class) {
                ViewGroup.LayoutParams params = achievements.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                achievements.setBackgroundResource(R.drawable.nav_icon_background_expanded);
                achievements.setLayoutParams(params);
            }
        }
    }
}

package com.example.myapplication;

import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WorkplaceFragment extends Fragment
{
    private Workplace workplace;
    private static final String TAG = "Workplace Fragment";

    public WorkplaceFragment()
    {
        //empty constructor
    }

    //Creates the workplace
    public static WorkplaceFragment createWorkplaceFragment(Workplace workplace)
    {
        WorkplaceFragment fragment = new WorkplaceFragment();
        fragment.workplace = workplace;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //TODO: Put the actual dynamic icon showing here along with the onClick function.

    }
}

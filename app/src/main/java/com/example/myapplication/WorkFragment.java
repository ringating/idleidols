package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.StringWriter;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link WorkFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link WorkFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class WorkFragment extends Fragment
{
    private Work work;

    public WorkFragment()
    {
        // Required empty public constructor
    }

    // This method is a static factory method.
    public static WorkFragment createWorkFragment(Work work)
    {
        WorkFragment fragment = new WorkFragment();
        fragment.work = work;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // This is where the agency passes along data to the page.

        final TextView currency = view.findViewById(R.id.currency);
        currency.setText(Integer.toString(work.agency.GetCurrentCurrency()));

        final TextView seeds = view.findViewById(R.id.seed);
        seeds.setText(Integer.toString(work.agency.GetCurrentSeeds()));

        final TextView level = view.findViewById(R.id.level);
        level.setText(Integer.toString(work.agency.GetLevel()));

        final TextView name = view.findViewById(R.id.agencyName);
        name.setText(work.agency.GetName());
    }

}

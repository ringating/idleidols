package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IdolCardFragment extends Fragment {

    private TextView exit;

    private TextView idolName;
    private TextView rarity;
    private TextView dance;
    private TextView sing;
    private TextView charm;

    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.idolcard, container, false);

        idolName = view.findViewById(R.id.name);
        rarity = view.findViewById(R.id.rarity);
        dance = view.findViewById(R.id.dance);
        sing = view.findViewById(R.id.sing);
        charm = view.findViewById(R.id.charm);
        image = view.findViewById(R.id.idolImage);

        exit = view.findViewById(R.id.exitButton);

        Idol temp = getArguments().getParcelable("idol");

        idolName.setText(temp.getIdolName());
        rarity.setText(String.format(Locale.US, "%d", temp.getRarity()));
        dance.setText(String.format(Locale.US, "%.2f", temp.getDanceStat()));
        sing.setText(String.format(Locale.US, "%.2f", temp.getSingStat()));
        charm.setText(String.format(Locale.US, "%.2f", temp.getCharmStat()));
        image.setBackgroundResource(temp.getImage());

        return view;
    }
}

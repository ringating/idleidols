package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class IdolCardDialog extends DialogFragment {
    private static final String TAG = "IdolCardDialog";

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.idol_card_scroll_container, container, false);

        ArrayList<Idol> idols = getArguments().getParcelable("idols");

        for (Idol idol : idols)
        {

        }

        return view;
    }
}

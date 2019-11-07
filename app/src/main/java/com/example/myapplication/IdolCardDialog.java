package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class IdolCardDialog extends DialogFragment {
    private static final String TAG = "IdolCardDialog";

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.idol_card_scroll_container, container, false);

        ArrayList<Idol> idols = getArguments().getParcelableArrayList("idol");

        LinearLayout fragmentContainer = view.findViewById(R.id.cardLayout);

        int id = 0;
        for (Idol idol : idols)
        {
            LinearLayout fragment = new LinearLayout(getContext());
            fragment.setId(id);

            Bundle bundle = new Bundle();
            bundle.putParcelable("idol", idol);
            IdolCardFragment idolCard = new IdolCardFragment();

            idolCard.setArguments(bundle);

            getFragmentManager().beginTransaction().add(fragment.getId(), idolCard, "tag").commit();

            fragmentContainer.addView(fragment);

            id++;
        }

        return view;
    }
}

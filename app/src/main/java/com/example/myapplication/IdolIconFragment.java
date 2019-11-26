package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IdolIconFragment extends Fragment {
    private ImageView image;
    private ImageView affinityImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.idol_icon, container, false);

        image = view.findViewById(R.id.idolIcon);
        affinityImage = view.findViewById(R.id.affinity);

        Idol temp = getArguments().getParcelable("idol");

        image.setBackgroundResource(temp.getImage());

        switch (temp.getMainAffinity()) {
            case "Dance":
                affinityImage.setImageResource(R.drawable.affinity_dance);
                affinityImage.setBackgroundResource(R.drawable.dance_affinity_background);
                break;
            case "Sing":
                affinityImage.setImageResource(R.drawable.affinity_sing);
                affinityImage.setBackgroundResource(R.drawable.sing_affinity_background);
                break;
            case "Charm":
                affinityImage.setImageResource(R.drawable.affinity_act);
                affinityImage.setBackgroundResource(R.drawable.charm_affinity_background);
                break;
            default:
        }

        return view;
    }
}

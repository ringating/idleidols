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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.idol_icon, container, false);

        image = view.findViewById(R.id.idolIcon);

        Idol temp = getArguments().getParcelable("idol");

        image.setBackgroundResource(temp.getImage());

        return view;
    }
}

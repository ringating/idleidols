package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class IdolCardDialog extends DialogFragment {

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.idol_card_scroll_container, container, false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

        ArrayList<Idol> idols = getArguments().getParcelableArrayList("idol");

        LinearLayout idolListContainer = view.findViewById(R.id.cardLayout);

        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.bottomMargin = 40;

        for (Idol idol: idols)
        {
            LinearLayout fragmentContainer = new LinearLayout(getActivity());

            fragmentContainer.setLayoutParams(layout);

            fragmentContainer.setOrientation(LinearLayout.VERTICAL);
            fragmentContainer.setId(View.generateViewId());

            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putParcelable("idol", idol);

            IdolCardFragment fragment = new IdolCardFragment();
            fragment.setArguments(bundle);

            fragmentTransaction.add(fragmentContainer.getId(), fragment);
            fragmentTransaction.commit();

            idolListContainer.addView(fragmentContainer);
        }

        return view;
    }
}

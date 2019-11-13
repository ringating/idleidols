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

        for (int i = 0; i < idols.size(); i++)
        {
            LinearLayout linearDummy = new LinearLayout(getActivity());
            linearDummy.setOrientation(LinearLayout.VERTICAL);
            linearDummy.setId(i);

            Bundle bundle = new Bundle();
            bundle.putParcelable("idol", idols.get(i));
            IdolCardFragment idolCard = new IdolCardFragment();

            idolCard.setArguments(bundle);

            getFragmentManager().beginTransaction().add(linearDummy.getId(), idolCard, "tag" + i).commit();

            fragmentContainer.addView(linearDummy);
        }

        return view;
    }
}

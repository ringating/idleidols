package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class AcademyIdolCard extends DialogFragment
{
    private static final String TAG = "AcademyTaskCard";
    static final int GET_IDOL = 1;
    static final int STARTED = 2;

    private Idol[] idolForTask;
    private ImageView[] idolIcons;
    private int numOfSlots;
    private int curIndex;
    private int selectedIndex;

    private ImageView selected;

    private final IdolListMenuDialog idolListMenu = new IdolListMenuDialog();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.academy_idol_card, container, false);

        numOfSlots = getArguments().getInt("numberOfSlots");
        idolForTask = (Idol[]) getArguments().getParcelableArray("IdolSlot");
        idolIcons = new ImageView[numOfSlots];

        TextView exitButton = (TextView)view.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFragment(idolForTask);
                dismiss();
            }
        });

        final FragmentManager idolListDialog = getActivity().getSupportFragmentManager();

        idolListMenu.setTargetFragment(this, GET_IDOL);

        //This whole nice section is because, well.... you can't just pass it directly from the fragment,
        //so you gotta pass it first through this fragment, AND THEN to the display menu. D:
        ArrayList<Idol> idolArrayList = getArguments().getParcelableArrayList("IdolArrayList");
        if(idolArrayList == null)
        {
            Log.e(TAG, "Idol List is null");
        }
        else
        {
            Bundle sendToIdolList = new Bundle();
            sendToIdolList.putParcelableArrayList("IdolList", idolArrayList);
            idolListMenu.setArguments(sendToIdolList);
        }

        TextView level = view.findViewById(R.id.academyCardLevelContent);
        level.setText(Integer.toString(getArguments().getInt("level")));

        TextView cost = view.findViewById(R.id.academyUpgradeCostContent);
        cost.setText(Integer.toString(getArguments().getInt("cost")));

        TextView dance = view.findViewById(R.id.academyCardDanceContent);
        dance.setText(Float.toString(getArguments().getFloat("dance")));

        TextView sing = view.findViewById(R.id.academyCardSingContent);
        sing.setText(Float.toString(getArguments().getFloat("sing")));

        TextView charm = view.findViewById(R.id.academyCardCharmContent);
        charm.setText(Float.toString(getArguments().getFloat("dance")));


        //TODO THIS IS WHERE THE IMAGES ARE LOADED
        LinearLayout equippedIdols = view.findViewById(R.id.idolImageContainer);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(225, 200);
        curIndex = 0;
        for(int i = 0; i < numOfSlots; i++)
        {
            final ImageView idolImage = new ImageView(getContext());
            if(idolForTask[i] != null)
            {
                Log.d(TAG, idolForTask[i].getIdolName());
                idolImage.setImageResource(idolForTask[i].getImage());

            }
            else
            {
                idolImage.setImageResource(R.drawable.no_idol);
            }
            idolIcons[i] = idolImage;
            equippedIdols.addView(idolImage, imageParams);
            idolImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idolListMenu.show(idolListDialog, "IdolListMenu");
                    selected = idolImage;
                    selectedIndex = curIndex;
                }
            });
        }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode == GET_IDOL)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Idol getIdol = intent.getParcelableExtra("IdolIcon");
                selected.setImageResource(getIdol.getImage());
                selected = null; //resets selected image.
                idolForTask[selectedIndex] = getIdol;
            }
        }
    }

    private void saveToFragment(Idol[] idolsInTask)
    {
        Intent toWorkplaceFrag = new Intent();
        toWorkplaceFrag.putExtra("slottedIdols", idolsInTask);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, toWorkplaceFrag);
    }

    private void sendStartedState(boolean started)
    {
        Intent toWorkplaceFrag = new Intent();
        toWorkplaceFrag.putExtra("startedTask", started);
        getTargetFragment().onActivityResult(getTargetRequestCode(), STARTED, toWorkplaceFrag);
    }
}
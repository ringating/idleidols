package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class TaskIdolCard extends DialogFragment {


    private static final String TAG = "TaskCard";
    static final int GET_IDOL = 1;

    private Idol[] idolForTask;
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
        View view = inflater.inflate(R.layout.work_card, container, false);

        numOfSlots = getArguments().getInt("numberOfSlots");
        idolForTask = (Idol[]) getArguments().getParcelableArray("IdolSlot");

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

        TextView cost = view.findViewById(R.id.workCardCostContent);
        cost.setText(Integer.toString(getArguments().getInt("cost")));

        TextView profit = view.findViewById(R.id.workCardProfitContent);
        profit.setText(Integer.toString(getArguments().getInt("profit")));

        TextView duration = view.findViewById(R.id.workCardDurationContent);
        String durationContent = "";
        durationContent += getArguments().getLong("duration")/100 + " seconds"; //TODO later change such that it says days, hours, minutes, seconds, etc.
        duration.setText(durationContent);

        ImageView danceIcon = view.findViewById(R.id.workCardDance);
        if(getArguments().getFloat("dance") != 1)
        {
            danceIcon.setVisibility(View.INVISIBLE);
        }

        ImageView singIcon = view.findViewById(R.id.workCardSing);
        if(getArguments().getFloat("sing") != 1)
        {
            singIcon.setVisibility(View.INVISIBLE);
        }

        ImageView charmIcon = view.findViewById(R.id.workCardCharm);
        if(getArguments().getFloat("charm") != 1)
        {
            charmIcon.setVisibility(View.INVISIBLE);
        }

        //TODO THIS IS WHERE THE IMAGES ARE LOADED
        LinearLayout equippedIdols = view.findViewById(R.id.idolImageContainer);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(225, 200);
        for(int i = 0; i < numOfSlots; i++)
        {
            final ImageView idolImage = new ImageView(getContext());
            curIndex = i;
            if(idolForTask[curIndex] != null)
            {
                Log.d(TAG, idolForTask[curIndex].getIdolName());
                idolImage.setImageResource(idolForTask[curIndex].getImage());

            }
            else
            {
                Log.d(TAG, "No Idol Added");
                idolImage.setImageResource(R.drawable.no_idol);
            }
            equippedIdols.addView(idolImage, imageParams);
            idolImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(idolListDialog != null)
                    {
                        selected = idolImage;
                        selectedIndex = curIndex;
                        idolListMenu.show(idolListDialog, "IdolListMenu");
                    }
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
}

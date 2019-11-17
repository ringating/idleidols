package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class TaskIdolCard extends DialogFragment {

    private boolean[] slotted = new boolean[4];
    private static final String TAG = "TaskCard";
    static final int GET_IDOL = 1;

    private ImageView idolSlot1;
    private ImageView idolSlot2;
    private ImageView idolSlot3;
    private ImageView idolSlot4;

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
        View view = inflater.inflate(R.layout.work_card_fragment, container, false);
        TextView exitButton = (TextView)view.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        idolSlot1 = (ImageView)view.findViewById(R.id.idolSlot1);
        idolSlot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!slotted[0])
                {
                    slotted[0] = true;
                }
                if(idolListDialog != null)
                {
                    Log.d(TAG, "Opening Idol List Dialog");
                    idolListMenu.show(idolListDialog, "IdolListMenu");
                }
            }
        });

        idolSlot2 = (ImageView)view.findViewById(R.id.idolSlot2);
        idolSlot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!slotted[0])
                {
                    slotted[0] = true;
                }
                if(idolListDialog != null)
                {
                    Log.d(TAG, "Opening Idol List Dialog");
                    idolListMenu.show(idolListDialog, "IdolListMenu");
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode == GET_IDOL)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                //TODO THIS ONLY CHANGES THE FIRST SLOT!!!!!!
                Log.d(TAG,"IT WORKS!!!");
                Idol getIdol = intent.getParcelableExtra("IdolIcon");
                idolSlot1.setImageResource(getIdol.getImage());
            }
        }
    }
}

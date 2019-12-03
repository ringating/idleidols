package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.w3c.dom.Text;

public class WorkplaceFragment extends Fragment
{
    private Task workplace;
    private static final String TAG = "Workplace Fragment";

    private Agency agency;

    public WorkplaceFragment()
    {
        //empty constructor
    }

    //Creates the workplace
    public static WorkplaceFragment createWorkplaceFragment(Task workplace, Bundle agency)
    {
        WorkplaceFragment fragment = new WorkplaceFragment();
        fragment.agency = (Agency)agency.getSerializable("Agency");
        fragment.workplace = workplace;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.workplace_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //TODO: Put the actual dynamic icon showing here along with the onClick function.
        final RelativeLayout fragmentLayout = view.findViewById(R.id.workplaceFragment);
        fragmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Animation shrink = AnimationUtils.loadAnimation(getActivity(), R.anim.button_press);
               fragmentLayout.startAnimation(shrink);
               ShowCard();
            }
        });

        ImageView icon = view.findViewById(R.id.workplace_icon);
        icon.setImageResource(workplace.image);

        ImageView danceIcon = view.findViewById(R.id.workplace_dance);
        if(workplace.dance != 1)
        {
            danceIcon.setVisibility(View.INVISIBLE);
        }

        ImageView singIcon = view.findViewById(R.id.workplace_sing);
        if(workplace.sing != 1)
        {
            singIcon.setVisibility(View.INVISIBLE);
        }

        ImageView charmIcon = view.findViewById(R.id.workplace_charm);
        if(workplace.charm != 1)
        {
            charmIcon.setVisibility(View.INVISIBLE);
        }

        TextView name = view.findViewById(R.id.workplace_name);
        name.setText(workplace.name);

        TextView cost = view.findViewById(R.id.workplace_cost_content);
        cost.setText(Integer.toString(workplace.cost));

        TextView profit = view.findViewById(R.id.workplace_profit_content);
        profit.setText(Integer.toString(workplace.rewardCurrency));

        TextView duration = view.findViewById(R.id.workplace_duration_content);
        int processTime = (int) workplace.processTime/100;
        String durationText = processTime + " seconds"; //TODO Probably want to change this so it tells you how many hours and stuff too.
        duration.setText(durationText);
    }

    private void ShowCard()
    {
        FragmentManager taskCard = getFragmentManager();
        //Sends information to IdolListMenuDialog
        TaskIdolCard taskIdolCard = new TaskIdolCard();
        Bundle sendToIdolList = new Bundle();
        sendToIdolList.putParcelableArrayList("IdolArrayList", agency.GetIdols());
        taskIdolCard.setArguments(sendToIdolList);

        //Opens the TaskIdolCard
        if(taskCard != null)
        {
            taskIdolCard.show(taskCard, "TaskCard");
        }
    }
}

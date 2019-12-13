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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TaskIdolCard extends DialogFragment {


    private static final String TAG = "TaskCard";
    static final int GET_IDOL = 1;
    static final int STARTED = 2;

    private Idol[] idolForTask;
    private int numOfSlots;
    private int curIndex;
    private int selectedIndex;
    private boolean started;

    private ImageView selected;

    private Agency agency;
    private int taskOrdinal;

    private final IdolListMenuDialog idolListMenu = new IdolListMenuDialog();

    private TextView timeTextContent;
    private TextView timeText;
    private ProgressBar timeDuration;
    private Button startButton;

    private int remainingTime = -1;
    private int remainingTimeMs = -1;

    Timer timer = new Timer();
    TimerTask t = new TimerTask()
    {
        @Override
        public void run()
        {
            getActivity().runOnUiThread(Timer_Tick);
        }
    };

    private Runnable Timer_Tick = new Runnable()
    {
        public void run()
        {
            // update UI in response to passing time here!

            remainingTimeMs = Math.max(0, (int)(getArguments().getLong("duration") - (Calendar.getInstance().getTimeInMillis() - agency.taskStartTimes[taskOrdinal])));
            remainingTime = Math.max(0, Math.round((getArguments().getLong("duration") - (Calendar.getInstance().getTimeInMillis() - agency.taskStartTimes[taskOrdinal])) / 1000f));
            if (remainingTime == 0)
            {
                timeDuration.setVisibility(View.INVISIBLE);
                timeText.setVisibility(View.INVISIBLE);
                timeTextContent.setText(Math.round(getArguments().getLong("duration") / 1000f) + " sec");
                timeTextContent.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
                startButton.setEnabled(true);
            }
            else
            {
                timeTextContent.setText("" + remainingTime + " sec");
                timeDuration.setMax((int) getArguments().getLong("duration"));
                timeDuration.setProgress((int) getArguments().getLong("duration") - remainingTimeMs);
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);
        agency = (Agency) getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.work_card, container, false);

        taskOrdinal = getArguments().getInt("taskOrdinal");

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

        final TextView duration = view.findViewById(R.id.workCardDurationContent);
        String durationContent = "";
        durationContent += getArguments().getLong("duration")/1000f + " seconds"; //TODO later change such that it says days, hours, minutes, seconds, etc.
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

        //TODO THIS IS WHERE THE START BUTTON TO PROGRESS IS SHOWN
        startButton = view.findViewById(R.id.workCardStartButton);
        timeDuration = view.findViewById(R.id.workCardProgress);
        timeText = view.findViewById(R.id.workCardTime);
        timeTextContent = view.findViewById(R.id.workCardTimeContent);
        started = getArguments().getBoolean("started");
        if(started)
        {
            timeDuration.setVisibility(View.VISIBLE);
            timeText.setVisibility(View.VISIBLE);
            timeTextContent.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            startButton.setEnabled(false);
            //TODO NEED TO INCLUDE PROGRESS BAR STATUS
        }
        else
        {
            timeDuration.setVisibility(View.INVISIBLE);
            timeText.setVisibility(View.INVISIBLE);
            timeTextContent.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            startButton.setEnabled(true);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendStartedState(true);
                    timeDuration.setVisibility(View.VISIBLE);
                    timeText.setVisibility(View.VISIBLE);
                    timeTextContent.setVisibility(View.VISIBLE);
                    startButton.setVisibility(View.INVISIBLE);
                    startButton.setEnabled(false);
                    Animation shrink = AnimationUtils.loadAnimation(getActivity(), R.anim.button_press);
                    startButton.startAnimation(shrink);
                    //TODO START PROGRESS HERE :D
                    agency.taskStartTimes[taskOrdinal] = Calendar.getInstance().getTimeInMillis();
                }
            });
        }

        timer.scheduleAtFixedRate(t,1,333);

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

    @Override
    public void onStop()
    {
        super.onStop();
        timer.cancel();
    }
}

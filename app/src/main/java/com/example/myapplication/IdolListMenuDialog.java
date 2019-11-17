package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;



import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;

public class IdolListMenuDialog extends DialogFragment {

    private static final String TAG = "IdolListMenu";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.idol_list_card, container, false);
        final ArrayList<Idol> idolArrayList = getArguments().getParcelableArrayList("IdolList");
        if(idolArrayList == null)
        {
            Log.e(TAG, "Idol List is not passed properly");
        }
         GridView idolList = (GridView) view.findViewById(R.id.idolViewMenu);
        //Which adapter is being used.
        IdolListGridAdapter adapter = new IdolListGridAdapter(getActivity(), idolArrayList);
        idolList.setAdapter(adapter);

        if(getTargetFragment() == null)
        {
            Log.e(TAG, "Did not set Target Fragment");
        }

            //TODO I still need to figure out how to determine which slot is being changed.
            idolList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(idolArrayList.get(position) != null)
                    {
                        SendIdol(idolArrayList.get(position));
                        getDialog().dismiss();
                    }
                }
            });

        //Set title of this Dialog
        getDialog().setTitle("IdolListMenu");

        return view;
    }

    private void SendIdol(Idol idolToSend)
    {
        Intent sendToActivityCard = new Intent();
        sendToActivityCard.putExtra("IdolIcon", idolToSend);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, sendToActivityCard);
    }

}

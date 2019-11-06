package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class IdolListMenuDialog extends DialogFragment {

    public interface Sender
    {
        void Send(boolean accept, int extra);
    }

    private Sender data;
    private Bundle bundle;
    GridView idolList;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            this.data = (Sender) context;
        }
        catch (final ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " must implement Send");
        }
    }

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
        bundle = getArguments();
        idolList = (GridView) view.findViewById(R.id.idolViewMenu);
        ArrayList<Idol> idolArrayList = bundle.getParcelableArrayList("IdolArrayList");

        //Set title of this Dialog
        getDialog().setTitle("IdolListMenu");

        //Which adapter is being used.
        IdolListGridAdapter adapter = new IdolListGridAdapter(getActivity(), idolArrayList);

        return view;
    }

    public void listIdols()
    {

    }
}

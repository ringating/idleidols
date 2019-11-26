package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class WarningDialog extends DialogFragment {

    public interface Sender {
        void Send(boolean accept, int extra, int extra2, int extra3);
    }

    private Sender data;

    @Override
    public void onAttach(Context context) { //Ensures that Send is implemented in the parent activity
        super.onAttach(context);
        try {
            this.data = (Sender) context;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Send");
        }
    }

    private TextView exit;
    private TextView accept;
    private TextView message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {         //
        super.onCreate(savedInstanceState);                             // Removes the annoying white corners from the rounded warning dialog
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);      //
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.warning, container, false);

        exit = view.findViewById(R.id.decline);
        accept = view.findViewById(R.id.accept);
        message = view.findViewById(R.id.message);

        message.setText(getArguments().getString("message"));

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.Send(false, -1, -1, -1);
                getDialog().dismiss();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int option = getArguments().getInt("option");

                if (option == 1) {
                    data.Send(true, getArguments().getInt("index1"), -1, option);
                }
                else
                {
                    data.Send(true, getArguments().getInt("index1"), getArguments().getInt("index2"), option);
                }
                getDialog().dismiss();
            }
        });

        return view;
    }
}

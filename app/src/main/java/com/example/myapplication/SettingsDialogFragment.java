package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SettingsDialogFragment extends DialogFragment
{

    public interface EditNameDialogListener
    {
        void onFinishEditDialog(String inputText);
    }

    private EditNameDialogListener listener;
    private static final String TAG = "SettingsMenu";
    private EditText newName;

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
        View view = inflater.inflate(R.layout.settings_menu, container, false);

        listener = (EditNameDialogListener) getActivity();

        newName = (EditText)view.findViewById(R.id.renameBox);
        Button acceptName = (Button)view.findViewById(R.id.acceptButton);
        acceptName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                listener.onFinishEditDialog(newName.getText().toString());
                newName.setText(null);
            }
        }
        );

        Button saveButton = (Button)view.findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: SAVE BUTTON
            }
        });

        ImageView exitButton = (ImageView)view.findViewById(R.id.closeButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
}

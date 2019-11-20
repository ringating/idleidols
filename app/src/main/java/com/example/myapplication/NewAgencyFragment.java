package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NewAgencyFragment extends DialogFragment {

    public interface NameAgencyDialogListener
    {
        void onFinishNameDialog(String inputText);
    }

    private static final String TAG = "NewAgency";
    private NameAgencyDialogListener listener;
    private EditText nameBox;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_agency_fragment, container, false);

        listener = (NameAgencyDialogListener) getActivity();

        nameBox = (EditText)view.findViewById(R.id.NameAgencyBox);

        Button acceptButton = (Button)view.findViewById(R.id.AcceptNameButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFinishNameDialog(nameBox.getText().toString());
                dismiss();
            }
        });

        return view;
    }
}

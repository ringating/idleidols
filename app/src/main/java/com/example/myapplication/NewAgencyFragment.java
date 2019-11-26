package com.example.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);

        listener = (NameAgencyDialogListener) getActivity();

        nameBox = (EditText)view.findViewById(R.id.NameAgencyBox);

        Button acceptButton = (Button)view.findViewById(R.id.AcceptNameButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameBox.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    listener.onFinishNameDialog(nameBox.getText().toString());
                    dismiss();
                }
            }
        });

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Window window = getDialog().getWindow();

        final float scale = getResources().getDisplayMetrics().density;
        int width = (int) (350 * scale);
        int height = (int) (300 * scale);

        window.setLayout(width, height);
        window.setGravity(Gravity.CENTER);
    }
}

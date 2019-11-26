package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class IdolIconListDialog extends DialogFragment {

    public interface CreateDialog {
        void showDialog(Bundle bundle);
    }

    private CreateDialog data;

    @Override
    public void onAttach(Context context) { //Ensures that CreateDialog is implemented in the parent activity
        super.onAttach(context);
        try {
            this.data = (CreateDialog) context;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement CreateDialog");
        }
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.idol_card_scroll_container, container, false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

        final ArrayList<Idol> idols = getArguments().getParcelableArrayList("idol");

        TableLayout idolListContainer = view.findViewById(R.id.iconIconContainer);
        idolListContainer.removeAllViews();

        TableRow.LayoutParams rowLayout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);    // Row Layout
        rowLayout.gravity = Gravity.CENTER_HORIZONTAL;                                                      //
        final TableRow.LayoutParams iconLayout = new TableRow.LayoutParams(700,700);   //
        iconLayout.bottomMargin = 30;                                                         //

        int id = 0;

        for (int i = 0; idols != null && i <= (idols.size() / 2); i++)
        {
            TableRow row = new TableRow(getActivity());  //Generates a new row every four row elements
            row.setLayoutParams(rowLayout);

            for (int n = 0; n < 2 && id < idols.size(); n++) {
                LinearLayout fragmentContainer = new LinearLayout(getActivity());

                fragmentContainer.setLayoutParams(iconLayout);

                fragmentContainer.setOrientation(LinearLayout.VERTICAL);
                fragmentContainer.setId(id + 1);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putParcelable("idol", idols.get(id));

                IdolIconFragment fragment = new IdolIconFragment();
                fragment.setArguments(bundle);

                fragmentTransaction.add(fragmentContainer.getId(), fragment);
                fragmentTransaction.commit();

                row.addView(fragmentContainer);

                fragmentContainer.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        LinearLayout button = (LinearLayout) v;
                        Animation shrink = AnimationUtils.loadAnimation(getActivity(), R.anim.button_press);           // Adds some animation to the tapping, makes it look like I know what I'm doing (I don't)
                        button.startAnimation(shrink);

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("idol", idols.get((v.getId()) - 1));

                        data.showDialog(bundle);
                    }
                });

                id++;
            }
            idolListContainer.addView(row, i);
        }

        return view;
    }
}

package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class IdolListGridAdapter extends BaseAdapter {
    
    Context context;
    ArrayList<Idol> idols;

    public IdolListGridAdapter(Context context, ArrayList<Idol> idols) {
        this.context = context;
        this.idols = idols;
    }

    @Override
    public int getCount() {
        return idols.size();
    }

    @Override
    public Object getItem(int position) {
        return idols.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.idol_list_icon, null);
        }

        //Get's View elements
        ImageView idolIcon = (ImageView)convertView.findViewById(R.id.idolIcon);

        //Assigns the data to view elements
        idolIcon.setImageResource(idols.get(position).getImage());


        return convertView;
    }
}

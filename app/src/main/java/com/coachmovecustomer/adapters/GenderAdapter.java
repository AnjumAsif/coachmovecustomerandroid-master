package com.coachmovecustomer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coachmovecustomer.R;

public class GenderAdapter extends BaseAdapter {

    Context context;
    String[] genderNames;
    LayoutInflater inflter;

    public GenderAdapter(Context applicationContext, String[] genderNames) {
        this.context = applicationContext;
        this.genderNames = genderNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return genderNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflter.inflate(R.layout.select_gender, null);
        TextView names = convertView.findViewById(R.id.textview_gender);
        names.setText(genderNames[position]);
        return convertView;

    }
}
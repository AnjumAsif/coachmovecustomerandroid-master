package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.coachmovecustomer.R;
import com.coachmovecustomer.data.AddModalitiesData;

import java.util.ArrayList;

public class ModalityAdapter extends ArrayAdapter {

    Context context;
    ArrayList<AddModalitiesData> selectModalityList = new ArrayList<>();
    LayoutInflater inflter;

    public ModalityAdapter(@NonNull Context context, int resource, ArrayList<AddModalitiesData> selectModalityList) {
        super(context, resource);
        this.context = context;
        this.selectModalityList = selectModalityList;
        inflter = (LayoutInflater.from(context));
    }

    /*public ModalityAdapter(Context applicationContext, ArrayList<AddModalitiesData> selectModalityList) {
            this.context =applicationContext;
        this.selectModalityList =selectModalityList;
    inflter =(LayoutInflater.from(applicationContext));
}*/

    @Override
    public int getCount() {
        return selectModalityList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return inflter.inflate(R.layout.genderadapter, null);

    }

        /* @Override
public View getView(int position,View convertView,ViewGroup parent){
        *//**//*    convertView = inflter.inflate(R.layout.genderadapter, null);
        TextView names=convertView.findViewById(R.id.textview_gender);
        AddModalitiesData addModalitiesData=selectModalityList.get(position);
        names.setText(addModalitiesData.modality);*//**//*
        return convertView;

        }*/

    @Override
    public View getDropDownView(final int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.genderadapter, null);
        AddModalitiesData addModalitiesData = selectModalityList.get(position);
  /*      final TextView names = convertView.findViewById(R.id.textview_gender);
        names.setText(addModalitiesData.modality);*/

  /*      names.setTag(position);
        names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = position;

                Log.e("addModalitiesData", names.getText().toString() + " ");
            }
        });
*/


        return convertView;
    }
}
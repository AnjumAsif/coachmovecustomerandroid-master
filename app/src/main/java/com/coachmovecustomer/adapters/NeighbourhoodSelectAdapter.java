package com.coachmovecustomer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.customDialog.NeighbourhoodSelectDialog;
import com.coachmovecustomer.data.NeighbourhoodData;

import java.util.ArrayList;
import java.util.List;

public class NeighbourhoodSelectAdapter extends RecyclerView.Adapter<NeighbourhoodSelectAdapter.MyViewHolder> {

    private ArrayList<NeighbourhoodData> neighbourhoodDataList = new ArrayList<>();
    private ArrayList<NeighbourhoodData> arrayList = new ArrayList<>();
    LayoutInflater inflater;
    private BaseActivity baseActivity;
    private NeighbourhoodSelectDialog neighbourhoodSelectDialog;


    public NeighbourhoodSelectAdapter(BaseActivity baseActivity, NeighbourhoodSelectDialog neighbourhoodSelectDialog, ArrayList<NeighbourhoodData> neighbourhoodDataList) {
        this.baseActivity = baseActivity;
        this.neighbourhoodDataList = neighbourhoodDataList;
        this.arrayList = neighbourhoodDataList;
        this.neighbourhoodSelectDialog = neighbourhoodSelectDialog;
        inflater = LayoutInflater.from(baseActivity);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_neighbourhood_select, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NeighbourhoodData neighbourhoodData = neighbourhoodDataList.get(position);
        holder.schoolNameTV.setText(neighbourhoodData.neighbourhood);
        holder.tickIV.setVisibility(View.GONE);


        holder.parentRL.setTag(position);

        holder.parentRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();

                ((NeighbourhoodSelectDialog) neighbourhoodSelectDialog).selctedschool(neighbourhoodDataList.get(pos).neighbourhood, neighbourhoodDataList.get(pos).id + "");

//                itemListDatas.get(pos).isSelected = !itemListDatas.get(pos).isSelected;
//                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return neighbourhoodDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private  ImageView tickIV;
        private TextView schoolNameTV;
        private  RelativeLayout parentRL;

        public MyViewHolder(View view) {
            super(view);
            tickIV = view.findViewById(R.id.tickIV);
            schoolNameTV = view.findViewById(R.id.schoolNameTV);
            parentRL = view.findViewById(R.id.parentRL);


        }
    }

    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List tempFliteredDataList = new ArrayList();

                if (constraint == null || constraint.toString().trim().length() == 0) {
                    results.values = arrayList;
                } else {
                    String constrainString = constraint.toString().toLowerCase();
                    for (NeighbourhoodData arrayList : arrayList) {
                        if (arrayList.neighbourhood.toLowerCase().contains(constrainString)) {
                            tempFliteredDataList.add(arrayList);
                        }
                    }
                    results.values = tempFliteredDataList;
                  /*  for (String arrayList : arrayList) {
                        if (arrayList.toLowerCase().contains(constrainString)) {
                            tempFliteredDataList.add(arrayList);
                        }
                    }
                     results.values = tempFliteredDataList;
                 */

                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                try {
                    if (results.values != null) {
                        neighbourhoodDataList = (ArrayList) results.values;
                        notifyDataSetChanged();


                    } else {
                        neighbourhoodDataList = arrayList;
                        notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        return filter;
    }


}
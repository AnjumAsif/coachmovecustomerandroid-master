package com.coachmovecustomer.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.DietDetailData;
import com.coachmovecustomer.fragments.DietFragment;
import com.coachmovecustomer.myInterface.onClickAdd;

import java.util.ArrayList;

public class DietDetailAdapter extends RecyclerView.Adapter<DietDetailAdapter.MyViewHolder> {

    private onClickAdd clickDiet;
//    Fragment fragment;
    private ArrayList<DietDetailData> dietDetailList = new ArrayList<>();
    BaseActivity baseActivity;

    public DietDetailAdapter(BaseActivity baseActivity/*, DietFragment dietFragment*/, ArrayList<DietDetailData> dietDetailList) {
        this.baseActivity = baseActivity;
//        this.fragment = dietFragment;
        this.dietDetailList = dietDetailList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dietTV, dietDateTV , dietCancelTV;
        private LinearLayout dietLL;
        private ImageView arrowIV ;

        public MyViewHolder(View view) {
            super(view);
            dietTV = view.findViewById(R.id.diet_TV);
            dietDateTV = view.findViewById(R.id.dietDate_TV);
            dietLL = view.findViewById(R.id.dietLL);
            arrowIV  = view.findViewById(R.id.arrowIV);
            dietCancelTV  = view.findViewById(R.id.dietCancelTV);
        }
    }


    @Override
    public DietDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_diet, parent, false);

        return new DietDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DietDetailAdapter.MyViewHolder holder, final int position) {
        final DietDetailData dietDetailData = dietDetailList.get(position);
        holder.dietTV.setText(dietDetailData.objective);
        holder.dietDateTV.setText(baseActivity.convertDateFormatLocale(dietDetailData.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd MMM, yyyy"));


        if (dietDetailData.status==4){
            holder.arrowIV.setVisibility(View.GONE);
            holder.dietCancelTV.setVisibility(View.VISIBLE);
        }else {
            holder.arrowIV.setVisibility(View.VISIBLE);
            holder.dietCancelTV.setVisibility(View.GONE);

        }




        holder.dietLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = position;
                    if (clickDiet != null) {
                        clickDiet.onClick(dietDetailList.get(pos), pos);

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return dietDetailList.size();
    }


    public void setOnNewClick(onClickAdd click) {
        this.clickDiet = click;
    }

}
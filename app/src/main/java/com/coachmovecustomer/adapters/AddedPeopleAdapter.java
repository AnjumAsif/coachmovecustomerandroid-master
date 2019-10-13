package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.PeopleForAddData;
import com.coachmovecustomer.fragments.WorkoutFragment;
import com.coachmovecustomer.myInterface.onClickDelete;
import com.coachmovecustomer.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class AddedPeopleAdapter extends RecyclerView.Adapter<AddedPeopleAdapter.MyViewHolder> {

    private onClickDelete clickDelete;
    private List<PeopleForAddData> addedPeopleDataList;
    Context mContext;
    private BaseActivity baseActivity;
    private Fragment fragment;

    public AddedPeopleAdapter(BaseActivity baseActivity, WorkoutFragment workoutFragment
            , ArrayList<PeopleForAddData> addedPeopleDataList) {
        this.baseActivity = baseActivity;
        this.addedPeopleDataList = addedPeopleDataList;
        this.fragment = workoutFragment;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName_TV;
        ImageView profileImg, crossIV;
        RelativeLayout top_RL;

        public MyViewHolder(View view) {
            super(view);
            profileImg = view.findViewById(R.id.profileImg);
            crossIV = view.findViewById(R.id.crossIV);
            userName_TV = view.findViewById(R.id.userName_TV);
            top_RL = view.findViewById(R.id.top_RL);
        }
    }


    @Override
    public AddedPeopleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_added_people, parent, false);

        return new AddedPeopleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddedPeopleAdapter.MyViewHolder holder, int position) {
        PeopleForAddData addedPeopleData = addedPeopleDataList.get(position);
        holder.userName_TV.setText(addedPeopleData.firstName);
        if (addedPeopleData.profilePicPath != null && !addedPeopleData.profilePicPath.isEmpty())
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + addedPeopleData.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profileImg);


        holder.top_RL.setTag(position);
        holder.crossIV.setTag(position);
        holder.crossIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickDelete != null) {
                    clickDelete.onClick(addedPeopleDataList.get(pos), pos);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return addedPeopleDataList.size();
    }


    public void setOnNewClick(onClickDelete click) {
        this.clickDelete = click;
    }


}
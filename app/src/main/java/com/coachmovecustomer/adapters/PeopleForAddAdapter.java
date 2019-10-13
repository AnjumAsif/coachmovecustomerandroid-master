package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.data.NeighbourhoodData;
import com.coachmovecustomer.data.PeopleForAddData;
import com.coachmovecustomer.fragments.AddPeopleFragment;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.myInterface.onClickDelete;
import com.coachmovecustomer.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class PeopleForAddAdapter extends RecyclerView.Adapter<PeopleForAddAdapter.MyViewHolder> {

    private onClickAdd clickAdd;
    private ArrayList<PeopleForAddData> addPeopleDataList = new ArrayList<>();
    private ArrayList<PeopleForAddData> arrayList = new ArrayList<>();
    Context mContext;
    private BaseActivity baseActivity;
    private Fragment fragment;

    public PeopleForAddAdapter(BaseActivity baseActivity, AddPeopleFragment addPeopleFragment
            , ArrayList<PeopleForAddData> addPeopleDataList) {
        this.baseActivity = baseActivity;
        this.addPeopleDataList = addPeopleDataList;
//        this.fragment = addPeopleFragment;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userName_TV, userID_TV;
        private  ImageView profile_Img;
        private RelativeLayout top_RL;

        public MyViewHolder(View view) {
            super(view);
            profile_Img = view.findViewById(R.id.profile_Img);
            userName_TV = view.findViewById(R.id.userName_TV);
            userID_TV = view.findViewById(R.id.userID_TV);
            top_RL = view.findViewById(R.id.top_RL);
        }
    }


    @Override
    public PeopleForAddAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_people_for_add, parent, false);

        return new PeopleForAddAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PeopleForAddAdapter.MyViewHolder holder, int position) {
        PeopleForAddData peopleForAddData = addPeopleDataList.get(position);
        holder.userName_TV.setText(peopleForAddData.firstName);
        holder.userID_TV.setText(String.format(" (%s)", peopleForAddData.accountId));

        Log.e("imagePath", "" + "" + peopleForAddData.profilePicPath);
        if (peopleForAddData.profilePicPath != null && !peopleForAddData.profilePicPath.isEmpty())
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + peopleForAddData.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profile_Img);

        holder.top_RL.setTag(position);
        holder.top_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickAdd != null) {
                    clickAdd.onClick(addPeopleDataList.get(pos), pos);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return addPeopleDataList.size();
    }


    public void setOnNewClick(onClickAdd click) {
        this.clickAdd = click;
    }


}
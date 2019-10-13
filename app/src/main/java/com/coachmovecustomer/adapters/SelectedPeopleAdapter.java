package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.PeopleForAddData;
import com.coachmovecustomer.data.SelectedPeopleData;
import com.coachmovecustomer.fragments.AddPeopleFragment;
import com.coachmovecustomer.fragments.WorkoutFragment;
import com.coachmovecustomer.myInterface.onClickDelete;
import com.coachmovecustomer.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class SelectedPeopleAdapter extends RecyclerView.Adapter<SelectedPeopleAdapter.MyViewHolder> {

    private List<PeopleForAddData> addPeopleDataList = new ArrayList<>();
    Context mContext;
    private BaseActivity baseActivity;
    private Fragment fragment;
    private onClickDelete clickDelete;


    public SelectedPeopleAdapter(BaseActivity baseActivity, AddPeopleFragment addPeopleFragmnet
            , ArrayList<PeopleForAddData> addPeopleDataList) {
        this.baseActivity = baseActivity;
        this.addPeopleDataList = addPeopleDataList;
//        this.fragment = addPeopleFragmnet;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userName_TV;
        private ImageView profile_Img, removeIV;
        private LinearLayout top_LL;

        public MyViewHolder(View view) {
            super(view);
            profile_Img = view.findViewById(R.id.profile_Img);
            userName_TV = view.findViewById(R.id.userName_TV);
//            top_LL = view.findViewById(R.id.top_LL);
            removeIV = view.findViewById(R.id.removeIV);
        }
    }


    @Override
    public SelectedPeopleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_selected_people, parent, false);

        return new SelectedPeopleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SelectedPeopleAdapter.MyViewHolder holder, int position) {
        PeopleForAddData peopleForAddData = addPeopleDataList.get(position);
        holder.userName_TV.setText(peopleForAddData.firstName);
        Log.e("imagePath", "" + "" + peopleForAddData.profilePicPath);
        if (peopleForAddData.profilePicPath != null && !peopleForAddData.profilePicPath.isEmpty())
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + peopleForAddData.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profile_Img);


        holder.removeIV.setTag(position);
        holder.removeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
          /*      addPeopleDataList.remove(pos);
                notifyDataSetChanged();*/
                if (clickDelete != null) {
                    clickDelete.onClick(addPeopleDataList.get(pos), pos);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return addPeopleDataList.size();
    }

    public void setOnClickDelete(onClickDelete click) {
        this.clickDelete = click;
    }

}
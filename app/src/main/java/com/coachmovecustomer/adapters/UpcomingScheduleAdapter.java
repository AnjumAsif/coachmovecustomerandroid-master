package com.coachmovecustomer.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.AllScheduleData;
import com.coachmovecustomer.fragments.ScheduleFragment;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.myInterface.onClickDelete;
import com.coachmovecustomer.utils.Const;

import java.util.ArrayList;

public class UpcomingScheduleAdapter extends RecyclerView.Adapter<UpcomingScheduleAdapter.MyViewHolder> {

    private onClickAdd clickChat;
    private onClickDelete clickCancel;
    private ArrayList<AllScheduleData> upcomingScheduleDataList;
    BaseActivity baseActivity;
    Fragment fragment;

    public UpcomingScheduleAdapter(BaseActivity baseActivity, ScheduleFragment scheduleFragment, ArrayList<AllScheduleData> upcomingScheduleDataList) {
        this.baseActivity = baseActivity;
        this.upcomingScheduleDataList = upcomingScheduleDataList;
        this.fragment = scheduleFragment;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userName_TV, session_TV, address_TV, date_TV, time_TV, cancelTV;
        private ImageView profile_Img, message_IV;

        public MyViewHolder(View view) {
            super(view);
            profile_Img = view.findViewById(R.id.profile_Img);
            message_IV = view.findViewById(R.id.message_IV);
            userName_TV = view.findViewById(R.id.userName_TV);
            session_TV = view.findViewById(R.id.session_TV);
            address_TV = view.findViewById(R.id.address_TV);
            date_TV = view.findViewById(R.id.date_TV);
            time_TV = view.findViewById(R.id.time_TV);
            cancelTV = view.findViewById(R.id.cancelTV);
        }
    }


    @Override
    public UpcomingScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_upcoming_schedule, parent, false);

        return new UpcomingScheduleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UpcomingScheduleAdapter.MyViewHolder holder, int position) {
//        AllScheduleData allScheduleData = upcomingScheduleDataList.get(position);
        if (upcomingScheduleDataList.get(position).requestTo.profilePicPath != null || !upcomingScheduleDataList.get(position).requestTo.profilePicPath.isEmpty()) {
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + upcomingScheduleDataList.get(position).requestTo.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profile_Img);
        }
        holder.userName_TV.setText(upcomingScheduleDataList.get(position).requestTo.firstName);
        holder.address_TV.setText(upcomingScheduleDataList.get(position).address);
//        holder.date_TV.setText(baseActivity.convertDateFormat(upcomingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd MMM, yyyy"));
//        holder.time_TV.setText(baseActivity.convertDateFormat(upcomingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "HH:mm"));

        holder.date_TV.setText(baseActivity.convertDateFormatLocale(upcomingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd MMMM, yyyy"));
        holder.time_TV.setText(baseActivity.convertDateFormatLocale(upcomingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "HH:mm"));



        holder.session_TV.setText(upcomingScheduleDataList.get(position).modality.modalityBr);

       /* if (baseActivity.store.getLanguage() == "en") {
            holder.session_TV.setText(upcomingScheduleDataList.get(position).modality.modality);
        } else {
            holder.session_TV.setText(upcomingScheduleDataList.get(position).modality.modalityBr);
        }*/


        //        holder.time_TV.setText(baseActivity.convertDateFormat(upcomingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "hh:mm aa"));


       /* holder.date_TV.setText(baseActivity.convertDateFormat(allScheduleData.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ","dd,MMM yyyy"));
        holder.time_TV.setText(baseActivity.convertDateFormat(allScheduleData.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "hh:mm aa"));
       */
//        holder.cancelTV.setText("Cancel");


        holder.message_IV.setImageResource(R.drawable.chat_blue);


        holder.message_IV.setTag(position);
        holder.message_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickChat != null) {
                    clickChat.onClick(upcomingScheduleDataList.get(pos), pos);
                }
            }
        });


        holder.cancelTV.setTag(position);
        holder.cancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickCancel != null) {
                    clickCancel.onClick(upcomingScheduleDataList.get(pos), pos);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return upcomingScheduleDataList.size();
    }

    public void setOnClickChat(onClickAdd click) {
        this.clickChat = click;
    }


    public void setOnClickCancel(onClickDelete click) {
        this.clickCancel = click;
    }

}
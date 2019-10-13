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
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.AllScheduleData;
import com.coachmovecustomer.fragments.ScheduleFragment;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;

public class CompleteScheduleAdapter extends RecyclerView.Adapter<CompleteScheduleAdapter.MyViewHolder> {

    private onClickAdd clickFeedback;
    private ArrayList<AllScheduleData> completeScheduleDataList;
    Context mContext;
    private BaseActivity baseActivity;
    private Fragment fragment;

    public CompleteScheduleAdapter(BaseActivity baseActivity, ScheduleFragment scheduleFragmnet
            , ArrayList<AllScheduleData> completeScheduleDataList) {
        this.baseActivity = baseActivity;
        this.completeScheduleDataList = completeScheduleDataList;
        this.fragment = scheduleFragmnet;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView header_TV, userName_TV, userID_TV, address_TV, date_TV, time_TV, comment_TV, calenderdateTV, calDayTV, feedback_btn;
        private ImageView profile_Img;
        private RatingBar ratingBar;
        private View view_Line;
        private LinearLayout rating_ll;

        public MyViewHolder(View view) {
            super(view);


            header_TV = view.findViewById(R.id.header_TV);
            userName_TV = view.findViewById(R.id.userName_TV);
            userID_TV = view.findViewById(R.id.userID_TV);
            address_TV = view.findViewById(R.id.address_TV);
            date_TV = view.findViewById(R.id.date_TV);
            time_TV = view.findViewById(R.id.time_TV);
            comment_TV = view.findViewById(R.id.comment_TV);
            ratingBar = view.findViewById(R.id.ratingBar);
            profile_Img = view.findViewById(R.id.profile_Img);
            calenderdateTV = view.findViewById(R.id.calenderdateTV);
            calDayTV = view.findViewById(R.id.calDayTV);
            rating_ll = view.findViewById(R.id.rating_ll);
            feedback_btn = view.findViewById(R.id.feedback_btn);
        }
    }


    @Override
    public CompleteScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_complete_schedule, parent, false);

        return new CompleteScheduleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompleteScheduleAdapter.MyViewHolder holder, int position) {

      /*  if (baseActivity.store.getLanguage() == "en") {
            holder.header_TV.setText(completeScheduleDataList.get(position).modality.modality);
        } else {
            holder.header_TV.setText(completeScheduleDataList.get(position).modality.modalityBr);
        }*/

        holder.header_TV.setText(completeScheduleDataList.get(position).modality.modalityBr);

        holder.userName_TV.setText(completeScheduleDataList.get(position).requestTo.firstName);
        holder.userID_TV.setText(" (" + completeScheduleDataList.get(position).requestTo.accountId + ")");
        holder.address_TV.setText(completeScheduleDataList.get(position).address);
        /*holder.date_TV.setText(baseActivity.convertDateFormat(completeScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd MMM, yyyy"));
        holder.time_TV.setText(baseActivity.convertDateFormat(completeScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "HH:mm"));*/

        holder.date_TV.setText(baseActivity.convertDateFormatLocale(completeScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd MMMM, yyyy"));
        holder.time_TV.setText(baseActivity.convertDateFormatLocale(completeScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "HH:mm"));
        holder.calenderdateTV.setText(baseActivity.convertDateFormatLocale(completeScheduleDataList.get(position).lastUpdatedAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd"));
        holder.calDayTV.setText(baseActivity.convertDateFormatLocale(completeScheduleDataList.get(position).lastUpdatedAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "EEE"));


       /* holder.calenderdateTV.setText(baseActivity.convertDateFormat(completeScheduleDataList.get(position).lastUpdatedAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd"));
        holder.calDayTV.setText(baseActivity.convertDateFormat(completeScheduleDataList.get(position).lastUpdatedAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "EEE"));
*/
        if (completeScheduleDataList.get(position).requestTo.profilePicPath != null || !completeScheduleDataList.get(position).requestTo.profilePicPath.isEmpty()) {
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + completeScheduleDataList.get(position).requestTo.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profile_Img);
        }

        holder.rating_ll.setTag(position);
        holder.feedback_btn.setTag(position);
        Log.e("position", position + "");

        if (completeScheduleDataList.get(position).customerRating != null) {
            holder.rating_ll.setVisibility(View.VISIBLE);
            holder.feedback_btn.setVisibility(View.GONE);
            holder.ratingBar.setRating(Float.parseFloat(completeScheduleDataList.get(position).customerRating.rating + ""));

            String toServerUnicodeEncoded = StringEscapeUtils.unescapeJava(completeScheduleDataList.get(position).customerRating.comment);
            holder.comment_TV.setText(toServerUnicodeEncoded);

//            holder.comment_TV.setText(completeScheduleDataList.get(position).customerRating.comment);

        } else {
            holder.rating_ll.setVisibility(View.GONE);
            holder.feedback_btn.setVisibility(View.VISIBLE);
        }


        holder.feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickFeedback != null) {
                    clickFeedback.onClick(completeScheduleDataList.get(pos), pos);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return completeScheduleDataList.size();
    }


    public void setOnNewClick(onClickAdd click) {
        this.clickFeedback = click;
    }

}

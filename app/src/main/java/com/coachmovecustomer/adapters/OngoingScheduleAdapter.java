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
import com.coachmovecustomer.utils.Const;

import java.util.ArrayList;

public class OngoingScheduleAdapter extends RecyclerView.Adapter<OngoingScheduleAdapter.MyViewHolder> {

    private ArrayList<AllScheduleData> ongoingScheduleDataList;
    BaseActivity baseActivity;
    Fragment fragment;

    public OngoingScheduleAdapter(BaseActivity baseActivity, ScheduleFragment scheduleFragment, ArrayList<AllScheduleData> ongoingScheduleDataList) {
        this.baseActivity = baseActivity;
        this.fragment = scheduleFragment;
        this.ongoingScheduleDataList = ongoingScheduleDataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userName_TV, session_TV, address_TV, date_TV, time_TV, Ongoing_TV;
        private ImageView profile_Img;

        public MyViewHolder(View view) {
            super(view);
            profile_Img = view.findViewById(R.id.profile_Img);
            userName_TV = view.findViewById(R.id.userName_TV);
            session_TV = view.findViewById(R.id.session_TV);
            address_TV = view.findViewById(R.id.address_TV);
            date_TV = view.findViewById(R.id.date_TV);
            time_TV = view.findViewById(R.id.time_TV);
            Ongoing_TV = view.findViewById(R.id.Ongoing_TV);
        }
    }


    @Override
    public OngoingScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_outgoing_schedule, parent, false);

        return new OngoingScheduleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OngoingScheduleAdapter.MyViewHolder holder, int position) {
//        AllScheduleData ongoingScheduleData = ongoingScheduleDataList.get(position);

        if (ongoingScheduleDataList.get(position).requestTo.profilePicPath != null || !ongoingScheduleDataList.get(position).requestTo.profilePicPath.isEmpty()) {
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + ongoingScheduleDataList.get(position).requestTo.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profile_Img);
        }

        holder.userName_TV.setText(ongoingScheduleDataList.get(position).requestTo.firstName);
        holder.address_TV.setText(ongoingScheduleDataList.get(position).address);
    /*    holder.date_TV.setText(baseActivity.convertDateFormat(ongoingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ","dd MMM, yyyy"));
        holder.time_TV.setText(baseActivity.convertDateFormat(ongoingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "HH:mm"));*/

        holder.date_TV.setText(baseActivity.convertDateFormatLocale(ongoingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd MMMM, yyyy"));
        holder.time_TV.setText(baseActivity.convertDateFormatLocale(ongoingScheduleDataList.get(position).timeSlot.start, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "HH:mm"));
        holder.session_TV.setText(ongoingScheduleDataList.get(position).modality.modalityBr);


    /*    if (baseActivity.store.getLanguage() == "en") {
            holder.session_TV.setText(ongoingScheduleDataList.get(position).modality.modality);
        } else {
            holder.session_TV.setText(ongoingScheduleDataList.get(position).modality.modalityBr);
        }
*/

        holder.Ongoing_TV.setText(baseActivity.getResources().getString(R.string.ongoingProcess));

//        baseActivity.showToast(ongoingScheduleDataList.get(position).status + "", false);


    }

    @Override
    public int getItemCount() {
        return ongoingScheduleDataList.size();
    }
}
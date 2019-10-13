package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.ChatData;
import com.coachmovecustomer.data.NotificationData;
import com.coachmovecustomer.fragments.NotificationFragment;
import com.coachmovecustomer.myInterface.onClickAdd;

import java.util.ArrayList;
import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private onClickAdd clickNotificationOpen;
    private ArrayList<NotificationData.Notification> notificationDataList;
    private BaseActivity baseActivity;
    Fragment fragment;
    Context mContext;

    public NotificationAdapter(BaseActivity baseActivity, NotificationFragment notificationFragment, ArrayList<NotificationData.Notification> notificationDataList) {
        this.baseActivity = baseActivity;
        this.fragment = notificationFragment;
        this.notificationDataList = notificationDataList;
    }

    public void notifyAdapter(ArrayList<NotificationData.Notification> notificationDataList) {
        this.notificationDataList = notificationDataList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userName_txt, notification_txt, messageTime_TV;
        private LinearLayout parentLL;

        public MyViewHolder(View view) {
            super(view);
            userName_txt = view.findViewById(R.id.userName_txt);
            notification_txt = view.findViewById(R.id.notification_txt);
            messageTime_TV = view.findViewById(R.id.messageTime_TV);
            parentLL = view.findViewById(R.id.parentLL);
        }
    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notification, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.MyViewHolder holder, int position) {
        NotificationData.Notification notificationData = notificationDataList.get(position);

//        if (baseActivity.store.getLanguage().equalsIgnoreCase("en")) {
//            holder.userName_txt.setText((Html.fromHtml(notificationData.message)));
//        } else {
//            holder.userName_txt.setText((Html.fromHtml(notificationData.messagePt)));
//        }

        holder.userName_txt.setText((Html.fromHtml(notificationData.messagePt)));
        if (notificationData.createdAt != null) {
            holder.messageTime_TV.setText(baseActivity.changeDate(notificationData.createdAt + "", "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        }
        holder.parentLL.setTag(position);
        holder.parentLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickNotificationOpen != null) {
                    clickNotificationOpen.onClick(notificationDataList.get(pos), pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    public void setOnNotificationClick(onClickAdd click) {
        this.clickNotificationOpen = click;
    }
}

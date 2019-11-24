package com.coachmovecustomer.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
import com.coachmovecustomer.data.MessageData;
import com.coachmovecustomer.data.NearbyCoachesData;
import com.coachmovecustomer.fragments.MessageFragment;
import com.coachmovecustomer.fragments.SearchChatUserFragment;
import com.coachmovecustomer.myInterface.OnClickListener;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchChatMessageAdapter extends RecyclerView.Adapter<SearchChatMessageAdapter.MyViewHolder> {

    private BaseActivity baseActivity;
    private Fragment fragment;
    private ArrayList<MessageData> messagesDataList = new ArrayList<>();
    private ArrayList<MessageData> arrayList = new ArrayList<>();


    private OnClickListener mOnClickListener;

    public SearchChatMessageAdapter(BaseActivity baseActivity, SearchChatUserFragment messagesFragment
            , ArrayList<MessageData> messagesDataList,OnClickListener onClickListener) {
        this.baseActivity = baseActivity;
        this.messagesDataList = messagesDataList;
        this.fragment = messagesFragment;
        this.mOnClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_messages, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SearchChatMessageAdapter.MyViewHolder holder, final int position) {
        MessageData messageData = messagesDataList.get(position);
        holder.nameTV.setText(messageData.receiver.firstName);
        String toServerUnicodeEncoded = StringEscapeUtils.unescapeJava(messageData.message.message);
        holder.msgTV.setText(toServerUnicodeEncoded);
        holder.timeTV.setText(baseActivity.changeDate(messageData.message.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        if (messageData.receiver.profilePicPath != null && !messageData.receiver.profilePicPath.isEmpty())
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + messageData.receiver.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.userImage_IV);
        else {
            Glide.with(baseActivity)
                    .load(R.drawable.placeholder)
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.userImage_IV);
        }


        holder.parentRL.setTag(position);

        holder.parentRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (mOnClickListener != null) mOnClickListener.onClick(pos);
            }
        });

    }

    public void filterList(ArrayList<MessageData> filterdNames) {
        this.messagesDataList = filterdNames;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return messagesDataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV, msgTV, timeTV;
        private ImageView userImage_IV;
        private RelativeLayout parentRL;

        public MyViewHolder(View view) {
            super(view);
            userImage_IV = view.findViewById(R.id.userImage_IV);
            nameTV = view.findViewById(R.id.nameTV);
            msgTV = view.findViewById(R.id.msgTV);
            timeTV = view.findViewById(R.id.timeTV);
            parentRL = view.findViewById(R.id.parentRL);
        }
    }

//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults results = new FilterResults();
//                List tempFliteredDataList = new ArrayList();
//
//                if (constraint == null || constraint.toString().trim().length() == 0) {
//                    results.values = arrayList;
//                } else {
//                    String constrainString = constraint.toString().toLowerCase();
//                    for (MessageData arrayList : arrayList) {
//                        if (arrayList.receiver.firstName.toLowerCase().contains(constrainString)) {
//                            tempFliteredDataList.add(arrayList);
//                        }
//                    }
//                    results.values = tempFliteredDataList;
//          /*          for (String arrayList : arrayList) {
//                        if (arrayList.toLowerCase().contains(constrainString)) {
//                            tempFliteredDataList.add(arrayList);
//                        }
//                    }
//                    results.values = tempFliteredDataList;*/
//
//
//                }
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                try {
//                    if (results.values != null) {
//                        messagesDataList = (ArrayList) results.values;
//                        notifyDataSetChanged();
//
//
//                    } else {
//                        messagesDataList = arrayList;
//                        notifyDataSetChanged();
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//
//        return filter;
//    }
}
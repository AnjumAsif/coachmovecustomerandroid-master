package com.coachmovecustomer.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.ChatData;
import com.coachmovecustomer.data.ProfileData;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private BaseActivity baseActivity;
    private Fragment fragment;
    private List<ChatData.ChatMessage> chatDataList = new ArrayList<>();
    ProfileData profileData = new ProfileData();
    private char d;


    public ChatAdapter(BaseActivity baseActivity, List<ChatData.ChatMessage> chatDataList) {
        this.baseActivity = baseActivity;
        this.chatDataList = chatDataList;
        profileData = baseActivity.store.getProfileData();
    }

    public void notifyAdapter(ArrayList<ChatData.ChatMessage> chatDataList) {
        this.chatDataList = chatDataList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_single_chat, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChatData.ChatMessage chatData = chatDataList.get(position);
        if (chatDataList.get(position).senderBy.id == profileData.id) {

            String toServerUnicodeEncoded = StringEscapeUtils.unescapeJava(chatData.message);
            holder.myMessage_TV.setText(toServerUnicodeEncoded);
            holder.myTime_TV.setText(baseActivity.changeDate(chatData.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            holder.friendChatLL.setVisibility(View.GONE);
            holder.myChatLL.setVisibility(View.VISIBLE);
        } else {
            String fromServerUnicodeDecoded = StringEscapeUtils.unescapeJava(chatData.message);
            holder.senderNameTV.setText(fromServerUnicodeDecoded);
            holder.senderTimeTV.setText(baseActivity.changeDate(chatData.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            holder.friendChatLL.setVisibility(View.VISIBLE);
            holder.myChatLL.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return chatDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout myChatLL, friendChatLL;
        private LinearLayout parentFriendChatLL, parentmyChatLL;
        private  TextView senderTimeTV, senderNameTV, myTime_TV, myMessage_TV;

        public MyViewHolder(View view) {
            super(view);
            friendChatLL = view.findViewById(R.id.friendChatLL);
            myChatLL = view.findViewById(R.id.myChatLL);
            parentFriendChatLL = view.findViewById(R.id.parentFriendChatLL);
            parentmyChatLL = view.findViewById(R.id.parentmyChatLL);
            myMessage_TV = view.findViewById(R.id.myMessage_TV);
            myTime_TV = view.findViewById(R.id.myTime_TV);
            senderTimeTV = view.findViewById(R.id.senderTimeTV);
            senderNameTV = view.findViewById(R.id.senderNameTV);
        }
    }
}

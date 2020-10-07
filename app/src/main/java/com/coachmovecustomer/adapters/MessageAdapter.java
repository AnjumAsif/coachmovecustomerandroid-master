package com.coachmovecustomer.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.coachmovecustomer.data.MessageData;
import com.coachmovecustomer.fragments.MessageFragment;
import com.coachmovecustomer.myInterface.OnClickListener;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private onClickAdd clickChatOpen;
    private BaseActivity baseActivity;
    private Fragment fragment;
    private ArrayList<MessageData> messagesDataList = new ArrayList<>();

    private OnClickListener mOnClickListener;

    public MessageAdapter(BaseActivity baseActivity, MessageFragment messagesFragment
            , ArrayList<MessageData> messagesDataList, OnClickListener onClickListener) {
        this.baseActivity = baseActivity;
        this.messagesDataList = messagesDataList;
        this.fragment = messagesFragment;
        mOnClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_messages, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MessageAdapter.MyViewHolder holder, final int position) {
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
        holder.parentRL.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (messagesDataList.get(position).mBlock)
                    openBlockDAlertDialog(position, "Deseja desbloquear esta pessoa?", "desbloquear");
                else
                    openBlockDAlertDialog(position, "Você gostertaria de bloquear esta pessoa?", "bloquear");
                return true;
            }
        });

        holder.parentRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickChatOpen != null) {
                    clickChatOpen.onClick(messagesDataList.get(pos), pos);
                }

//                ((MessageFragment) baseActivity).gotoChatFragment(pos);
/*
                fragment.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutMain, new SingleChatFragment())
                        .addToBackStack(null)
                        .commit();*/

            }
        });

    }

    private void openBlockDAlertDialog(final int position, String message, String buttonName) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(baseActivity);
        //Setting message manually and performing action on button click
        mBuilder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonName, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mOnClickListener.onClick(position);
                    }
                })
                .setNegativeButton("não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        //Creating dialog box
        AlertDialog alert = mBuilder.create();
        //Setting the title manually
        alert.setTitle("Alerta");
        alert.show();

    }

    @Override
    public int getItemCount() {
        return messagesDataList.size();
    }

    public void setOnChatClick(onClickAdd click) {
        this.clickChatOpen = click;
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
}
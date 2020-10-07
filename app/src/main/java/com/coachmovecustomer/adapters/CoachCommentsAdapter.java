package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.CoachCommentsData;
import com.coachmovecustomer.fragments.CoachCommentsFragment;
import com.coachmovecustomer.utils.Const;

import java.util.ArrayList;

public class CoachCommentsAdapter extends RecyclerView.Adapter<CoachCommentsAdapter.MyViewHolder> {

    private ArrayList<CoachCommentsData.Rating> coachCommentsDataList = new ArrayList<>();
    private BaseActivity baseActivity;
    private Fragment fragment;
    Context mContext;


    public CoachCommentsAdapter(BaseActivity baseActivity, CoachCommentsFragment coachCommentsFragment
            , ArrayList<CoachCommentsData.Rating> coachCommentsDataList) {
        this.baseActivity = baseActivity;
        this.coachCommentsDataList = coachCommentsDataList;
        this.fragment = coachCommentsFragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userName_TV, userID_TV, date_TV, comment_hint_TV;
        private ImageView profileImg;
        private RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            profileImg = view.findViewById(R.id.profileImg);
            userName_TV = view.findViewById(R.id.userName_TV);
            userID_TV = view.findViewById(R.id.userID_TV);
            date_TV = view.findViewById(R.id.date_TV);
            ratingBar = view.findViewById(R.id.ratingBar);
            comment_hint_TV = view.findViewById(R.id.comment_hint_TV);
        }
    }


    @Override
    public CoachCommentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coach_comments, parent, false);

        return new CoachCommentsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CoachCommentsAdapter.MyViewHolder holder, int position) {
        CoachCommentsData.Rating coachCommentsData = coachCommentsDataList.get(position);
        holder.userName_TV.setText(coachCommentsData.commentBy.firstName);
        holder.userID_TV.setText(" (" + coachCommentsData.commentBy.accountId + ") ");
        if (coachCommentsData.commentBy.profilePicPath != null && !coachCommentsData.commentBy.profilePicPath.isEmpty()) {
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + coachCommentsData.commentBy.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profileImg);
        }

        holder.ratingBar.setRating(coachCommentsData.rating);
        holder.comment_hint_TV.setText(coachCommentsData.comment);
        holder.date_TV.setText(baseActivity.convertDateFormat(coachCommentsData.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd MMM, yyyy"));

    }

    @Override
    public int getItemCount() {
        return coachCommentsDataList.size();
    }


}
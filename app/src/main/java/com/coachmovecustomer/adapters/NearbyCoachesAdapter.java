package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.NearbyCoachesData;
import com.coachmovecustomer.fragments.NearbyCoachFragment;
import com.coachmovecustomer.fragments.PaymentFragment;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class NearbyCoachesAdapter extends RecyclerView.Adapter<NearbyCoachesAdapter.MyViewHolder> {

    private onClickAdd clickComments;
    private onClickAdd clickPayment;
    private ArrayList<NearbyCoachesData> nearbyCoachesDataList = new ArrayList<>();
    private ArrayList<NearbyCoachesData> arrayList = new ArrayList<>();
    Context mContext;
    private BaseActivity baseActivity;
    private Fragment fragment;

    public NearbyCoachesAdapter(BaseActivity baseActivity, NearbyCoachFragment nearbyCoachFragment
            , ArrayList<NearbyCoachesData> nearbyCoachesDataList) {
        this.baseActivity = baseActivity;
        this.nearbyCoachesDataList = nearbyCoachesDataList;
        this.fragment = nearbyCoachFragment;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userName_TV, userID_TV, experience_TV, rating_TV, commentsTV, comment_hint_TV;
        private ImageView profile_Img;
        private RatingBar ratingBar;
        private RelativeLayout top_RL;

        public MyViewHolder(View view) {
            super(view);
            profile_Img = view.findViewById(R.id.profile_Img);
            userName_TV = view.findViewById(R.id.userName_TV);
            userID_TV = view.findViewById(R.id.userID_TV);
            experience_TV = view.findViewById(R.id.experience_TV);
            ratingBar = view.findViewById(R.id.ratingBar);
            rating_TV = view.findViewById(R.id.rating_TV);
            commentsTV = view.findViewById(R.id.commentsTV);
            comment_hint_TV = view.findViewById(R.id.comment_hint_TV);
            top_RL = view.findViewById(R.id.top_RL);
        }
    }


    @Override
    public NearbyCoachesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_nearby_coaches, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NearbyCoachesAdapter.MyViewHolder holder, int position) {
        NearbyCoachesData nearbyCoachesData = nearbyCoachesDataList.get(position);
        holder.userName_TV.setText(nearbyCoachesData.firstName);
        holder.userID_TV.setText(String.format(" (%s)", nearbyCoachesData.accountId));
        holder.experience_TV.setText(nearbyCoachesData.experience);
        holder.ratingBar.setRating(Float.parseFloat(nearbyCoachesData.avgRating + ""));

        if (nearbyCoachesData.avgRating == 0.0) {
            holder.rating_TV.setVisibility(View.GONE);
        } else {
            DecimalFormat twoDForm = new DecimalFormat("#.0");
            holder.rating_TV.setText(String.format("(%s)", twoDForm.format(nearbyCoachesData.avgRating)));
//            holder.rating_TV.setText(String.format("(%s)", nearbyCoachesData.avgRating));
        }

        holder.commentsTV.setText(String.format("%d %s", nearbyCoachesData.comments, baseActivity.getResources().getString(R.string.newComments)));

        if (nearbyCoachesData.profilePicPath != null && !nearbyCoachesData.profilePicPath.isEmpty())
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + nearbyCoachesData.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profile_Img);


        holder.commentsTV.setTag(position);
        holder.commentsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickComments != null) {
                    clickComments.onClick(nearbyCoachesDataList.get(pos), pos);
                }


            }
        });

        holder.top_RL.setTag(position);
        holder.top_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickPayment != null) {
                    clickPayment.onClick(nearbyCoachesDataList.get(pos), pos);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return nearbyCoachesDataList.size();
    }

    public void setOnCommentClick(onClickAdd click) {
        this.clickComments = click;
    }

    public void setOnPaymentClick(onClickAdd click) {
        this.clickPayment = click;
    }

    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List tempFliteredDataList = new ArrayList();

                if (constraint == null || constraint.toString().trim().length() == 0) {
                    results.values = arrayList;
                } else {
                    String constrainString = constraint.toString().toLowerCase();
                    for (NearbyCoachesData arrayList : arrayList) {
                        if (arrayList.firstName.toLowerCase().contains(constrainString)) {
                            tempFliteredDataList.add(arrayList);
                        }
                    }
                    results.values = tempFliteredDataList;
          /*          for (String arrayList : arrayList) {
                        if (arrayList.toLowerCase().contains(constrainString)) {
                            tempFliteredDataList.add(arrayList);
                        }
                    }
                    results.values = tempFliteredDataList;*/


                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                try {
                    if (results.values != null) {
                        nearbyCoachesDataList = (ArrayList) results.values;
                        notifyDataSetChanged();


                    } else {
                        nearbyCoachesDataList = arrayList;
                        notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        return filter;
    }

}
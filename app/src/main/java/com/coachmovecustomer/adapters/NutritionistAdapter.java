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
import com.coachmovecustomer.data.NutritionistData;
import com.coachmovecustomer.fragments.NutritionistFragment;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class NutritionistAdapter extends RecyclerView.Adapter<NutritionistAdapter.MyViewHolder> {

    private onClickAdd clickComments;
    private onClickAdd clickPayment;
    private ArrayList<NutritionistData> nutritionistDataList = new ArrayList<>();
    private ArrayList<NutritionistData> arrayList = new ArrayList<>();
    Context mContext;
    private BaseActivity baseActivity;
    private Fragment fragment;

    public NutritionistAdapter(BaseActivity baseActivity, NutritionistFragment nutritionistFragment
            , ArrayList<NutritionistData> nutritionistDataList) {
        this.baseActivity = baseActivity;
        this.nutritionistDataList = nutritionistDataList;
        this.fragment = nutritionistFragment;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userNameTV, userIDTV, ratingTV, commentsTV, comment_hint_TV;
        private ImageView profileImg;
        private RatingBar ratingBar;
        private RelativeLayout top_RL;

        public MyViewHolder(View view) {
            super(view);
            profileImg = view.findViewById(R.id.profileImg);
            userNameTV = view.findViewById(R.id.userNameTV);
            userIDTV = view.findViewById(R.id.userIDTV);
            ratingBar = view.findViewById(R.id.ratingBar);
            ratingTV = view.findViewById(R.id.ratingTV);
            commentsTV = view.findViewById(R.id.commentsTV);
            comment_hint_TV = view.findViewById(R.id.comment_hint_TV);
            top_RL = view.findViewById(R.id.top_RL);
        }
    }


    @Override
    public NutritionistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_nutritionist, parent, false);
        return new NutritionistAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NutritionistAdapter.MyViewHolder holder, int position) {
        NutritionistData nutritionistData = nutritionistDataList.get(position);

        holder.userNameTV.setText(nutritionistData.firstName);
        holder.userIDTV.setText(String.format(" (%s)", nutritionistData.accountId));
        holder.ratingBar.setRating(Float.parseFloat(nutritionistData.avgRating + ""));


        if (nutritionistData.avgRating == 0.0) {
            holder.ratingTV.setVisibility(View.GONE);
        } else {
            DecimalFormat twoDForm = new DecimalFormat("#.0");
            holder.ratingTV.setText(String.format("(%s)", twoDForm.format(nutritionistData.avgRating)));
//            holder.ratingTV.setText(" (" + nutritionistData.avgRating + ")");
        }


        holder.commentsTV.setText(nutritionistData.comments + " " + baseActivity.getResources().getString(R.string.commentss));
        if (nutritionistData.profilePicPath != null && !nutritionistData.profilePicPath.isEmpty()) {
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + nutritionistData.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder))
                    .into(holder.profileImg);
        }
//        holder.comment_hint_TV.setText(nutritionistData.getCommentHint());

        holder.commentsTV.setTag(position);
        holder.commentsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickComments != null) {
                    clickComments.onClick(nutritionistDataList.get(pos), pos);
                }

            }
        });

        holder.top_RL.setTag(position);
        holder.top_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (clickPayment != null) {
                    clickPayment.onClick(nutritionistDataList.get(pos), pos);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return nutritionistDataList.size();
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
                    for (NutritionistData arrayList : arrayList) {
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
                        nutritionistDataList = (ArrayList) results.values;
                        notifyDataSetChanged();


                    } else {
                        nutritionistDataList = arrayList;
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
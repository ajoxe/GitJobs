package com.example.android.gitjobs.views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.gitjobs.R;
import com.example.android.gitjobs.model.GitJobsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by BabiMaji on 1/13/18.
 */

public class GitJobsViewHolder extends RecyclerView.ViewHolder{

    GitJobsModel gitJobsModel;
    ImageView job_logo;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    Button apply_btn;
    Button save_btn;

    public GitJobsViewHolder(View itemView) {
        super(itemView);

        job_logo = itemView.findViewById(R.id.logo_image_view);
        tv1 = itemView.findViewById(R.id.git_jobs_title_text_view);
        tv2 = itemView.findViewById(R.id.git_jobs_company_text_view);
        tv3 = itemView.findViewById(R.id.git_jobs_created_at_text_view);
        tv4 = itemView.findViewById(R.id.git_jobs_location_text_view);
        apply_btn = itemView.findViewById(R.id.apply_btn);
        save_btn = itemView.findViewById(R.id.save_btn);


    }

    public void onBind(List<GitJobsModel> gitJobsModelList) {
        Log.d(TAG, "onBind" + gitJobsModelList);

        String title = String.format("Title: %s", gitJobsModel.getTitle());
        tv1.setText(title);

        String company = String.format("Company: %s", gitJobsModel.getCompany());
        tv2.setText(company);

        String created_at = String.format("Created at: %s", gitJobsModel.getCreated_at());
        tv3.setText(created_at);

        String location = String.format("Location: %s", gitJobsModel.getLocation());
        tv4.setText(location);

        Picasso.with(itemView.getContext()).load(gitJobsModel.getCompany_logo()).into(job_logo);

    }
}

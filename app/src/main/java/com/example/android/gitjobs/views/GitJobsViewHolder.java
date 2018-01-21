package com.example.android.gitjobs.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.gitjobs.R;
import com.example.android.gitjobs.model.GitJobsModel;


/**
 * Created by BabiMaji on 1/13/18.
 */

public class GitJobsViewHolder extends RecyclerView.ViewHolder{

    ImageView jobLogo;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    public Button saveBtn;
    public Button applyBtn;
    public LinearLayout linearLayout;


    //TODO extend recyclerview, implent methods.
    //TODO add item view

    public GitJobsViewHolder(View itemView) {
        super(itemView);

        jobLogo = itemView.findViewById(R.id.logo_image_view);
        tv1 = itemView.findViewById(R.id.git_jobs_title_text_view);
        tv2 = itemView.findViewById(R.id.git_jobs_company_text_view);
        tv3 = itemView.findViewById(R.id.git_jobs_created_at_text_view);
        tv4 = itemView.findViewById(R.id.git_jobs_location_text_view);
        saveBtn = itemView.findViewById(R.id.save_btn);
        applyBtn = itemView.findViewById(R.id.apply_btn);
        linearLayout = itemView.findViewById(R.id.job_item_layout);
    }

    public void onBind(GitJobsModel gitJobsModel) {
        Log.d("onBind", "onBind: ");
        tv1.setText(gitJobsModel.getTitle());
        tv2.setText(gitJobsModel.getCompany());
        tv3.setText(gitJobsModel.getCreated_at());
        tv4.setText(gitJobsModel.getLocation());
        linearLayout.setTag(gitJobsModel.getId());
        saveBtn.setTag(gitJobsModel.getId());
        applyBtn.setTag(gitJobsModel.getId());
    }
}

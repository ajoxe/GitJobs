package com.example.android.gitjobs.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.gitjobs.R;
import com.example.android.gitjobs.model.GitJobsModel;
import com.example.android.gitjobs.views.GitJobsViewHolder;

import java.util.List;

/**
 * Created by BabiMaji on 1/13/18.
 */

public class GitJobsAdapter extends RecyclerView.Adapter<GitJobsViewHolder>{

    //TODO extend recyclerview.adapter
    //TODO implement methods

    List<GitJobsModel> jobsList;
    Context context;
    View.OnClickListener searchListOnClick;
    View.OnClickListener saveButtonOnClick;
    View.OnClickListener applyButtonOnClick;

    public GitJobsAdapter(List<GitJobsModel> jobsList, Context context, View.OnClickListener searchListOnClick, View.OnClickListener saveButtonOnClick, View.OnClickListener applyButtonOnClick) {
        this.jobsList = jobsList;
        this.context = context;
        this.searchListOnClick = searchListOnClick;
        this.saveButtonOnClick = saveButtonOnClick;
        this.applyButtonOnClick = applyButtonOnClick;
    }

    @Override
    public GitJobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.git_jobs_item_view, parent, false);
        return new GitJobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GitJobsViewHolder holder, int position) {
        GitJobsModel job = jobsList.get(position);
        holder.onBind(job);
        holder.setImageView(context, job);
        /*holder.itemView.setTag(job.getId());
        holder.itemView.setOnClickListener(searchListOnClick);*/
        holder.linearLayout.setOnClickListener(searchListOnClick);
        holder.applyBtn.setOnClickListener(applyButtonOnClick);
        holder.saveBtn.setOnClickListener(saveButtonOnClick);
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }
}

package com.example.android.gitjobs.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.gitjobs.GitJobsDBHelper;
import com.example.android.gitjobs.GitJobsListings;
import com.example.android.gitjobs.R;
import com.example.android.gitjobs.model.GitJobsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GitJobsDetailFragment extends Fragment {
    TextView company, jobCreated, title, location, type, description, apply;
    Button url;
    ImageView logo;
    String job_id;
    String result;
    List<GitJobsModel> jobs = new ArrayList<>();
    Context context;

    GitJobsModel job = new GitJobsModel();

    public GitJobsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_git_jobs_detail, container, false);
        company = rootView.findViewById(R.id.job_company);
        jobCreated = rootView.findViewById(R.id.job_created);
        title = rootView.findViewById(R.id.job_title);
        location = rootView.findViewById(R.id.job_location);
        type = rootView.findViewById(R.id.job_type);
        description = rootView.findViewById(R.id.job_description);
        apply = rootView.findViewById(R.id.job_apply);
        url = rootView.findViewById(R.id.comp_url);
        logo = rootView.findViewById(R.id.company_logo);

        context = getActivity().getApplicationContext();

        company.setText(job.getCompany());
        jobCreated.setText(job.getCreated_at());
        title.setText(job.getTitle());
        location.setText(job.getLocation());
        type.setText(job.getType());
        description.setText(Html.fromHtml(job.getDescription()));
        apply.setText(Html.fromHtml(job.getHow_to_apply()));
        url.setText(job.getCompany_url());

        Picasso.with(context).load(job.getCompany_logo()).into(logo);

        return rootView;
    }
    public void updateId(Bundle bundle){
        job_id = bundle.getString("job_id");
        Log.d("jobs id detail", job_id);

        result = bundle.getString("result");
        GitJobsListings gitutil = new GitJobsListings(result);
        jobs.addAll(gitutil.getGitJobsModelList());
        Log.d("jobs list", String.valueOf(jobs.size()));
        job = gitutil.getJobFromListById(job_id);

       /* GitJobsDBHelper db = new GitJobsDBHelper(context);
        db.getJobById(job_id);*/

    }



}

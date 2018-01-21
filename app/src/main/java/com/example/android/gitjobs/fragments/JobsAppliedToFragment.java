package com.example.android.gitjobs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.gitjobs.GitJobsDBHelper;
import com.example.android.gitjobs.R;
import com.example.android.gitjobs.model.GitJobsModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobsAppliedToFragment extends Fragment {
    View rootView;
    List<GitJobsModel> appliedJobsList;
    GitJobsDBHelper db;

    public JobsAppliedToFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_jobs_applied_to, container, false);
        db = new GitJobsDBHelper(getActivity());
        appliedJobsList = db.getAppliedJobsList();
        Log.d("applied fragment", "list size : " + appliedJobsList.size());
        return rootView;
    }

}

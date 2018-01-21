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
public class SavedJobsFragment extends Fragment {
    View rootView;
    List<GitJobsModel> savedJobsList;
    GitJobsDBHelper db;


    public SavedJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_saved_jobs, container, false);
        db = new GitJobsDBHelper(getActivity());
        savedJobsList = db.getSavedJobsList();
        Log.d("saved fragment", "list size : " + savedJobsList.size());

        return rootView;
    }

}

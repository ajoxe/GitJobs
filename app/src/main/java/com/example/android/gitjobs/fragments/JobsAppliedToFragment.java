package com.example.android.gitjobs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.gitjobs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobsAppliedToFragment extends Fragment {


    public JobsAppliedToFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jobs_applied_to, container, false);
    }

}

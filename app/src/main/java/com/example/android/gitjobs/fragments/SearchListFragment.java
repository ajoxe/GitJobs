package com.example.android.gitjobs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.gitjobs.R;
import com.example.android.gitjobs.controller.GitJobsAdapter;
import com.example.android.gitjobs.model.GitJobsModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListFragment extends Fragment {
    View rootView;
    RecyclerView searchListRecyclerView;
    GitJobsAdapter gitJobsAdapter;
    List<GitJobsModel> gitJobsList;
    LinearLayoutManager linearLayoutManager;

    public SearchListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search_list, container, false);
        setRecyclerView();
        return rootView;
    }

    public void setRecyclerView(){
        searchListRecyclerView = (RecyclerView) rootView.findViewById(R.id.search_list_recyclerview);
        gitJobsAdapter = new GitJobsAdapter();
        linearLayoutManager = new LinearLayoutManager(getContext());
        //searchListRecyclerView.setAdapter(gitJobsAdapter);
        searchListRecyclerView.setLayoutManager(linearLayoutManager);
    }

}

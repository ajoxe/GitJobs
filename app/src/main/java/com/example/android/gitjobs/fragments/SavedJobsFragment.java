package com.example.android.gitjobs.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.gitjobs.GitJobsDBHelper;
import com.example.android.gitjobs.R;
import com.example.android.gitjobs.controller.GitJobsAdapter;
import com.example.android.gitjobs.model.GitJobsModel;

import java.util.List;

import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry._STATUS_APPLIED;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry._STATUS_SAVED;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry._STATUS_SEARCHED;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedJobsFragment extends Fragment {
    View rootView;
    List<GitJobsModel> savedJobsList;
    GitJobsDBHelper db;
    RecyclerView savedRecyclerView;
    GitJobsAdapter savedAdapter;
    LinearLayoutManager linearLayoutManager;
    Context context;
    View.OnClickListener searchListButtonClick;
    View.OnClickListener saveButtonClick;
    View.OnClickListener applyButtonClick;


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
        db.close();
        Log.d("saved fragment", "list size : " + savedJobsList.size());
        context = getContext().getApplicationContext();
        setUpRecyclerView();


        return rootView;
    }

    public void setUpRecyclerView(){
        setApplyButton();
        setSaveButton();
        setSearchListButton();
        savedRecyclerView = (RecyclerView) rootView.findViewById(R.id.saved_list_recyclerview);
        savedAdapter = new GitJobsAdapter(savedJobsList, context, searchListButtonClick,saveButtonClick,applyButtonClick);
        linearLayoutManager = new LinearLayoutManager(context);
        savedRecyclerView.setLayoutManager(linearLayoutManager);
        savedRecyclerView.setAdapter(savedAdapter);
    }

    public void setApplyButton(){
        applyButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobId = v.getTag().toString();
                GitJobsModel job = db.getJobById(jobId);
                Intent applyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getUrl()));
                startActivity(applyIntent);
                Toast.makeText(getActivity(), "Good Luck!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void setSaveButton(){
        saveButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobId = v.getTag().toString();

                Toast.makeText(getActivity(), "This job is saved to your 'saved' list!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void setSearchListButton(){
        searchListButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobId = v.getTag().toString();
                GitJobsDetailFragment detailFragment = new GitJobsDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("job_id", jobId);
                detailFragment.updateId(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                fragmentTransaction.replace(R.id.main_fragment_container, detailFragment);
                fragmentTransaction.addToBackStack("next");
                fragmentTransaction.commit();
            }
        };
    }

}

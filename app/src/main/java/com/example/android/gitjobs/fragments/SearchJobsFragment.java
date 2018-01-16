package com.example.android.gitjobs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.gitjobs.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchJobsFragment extends Fragment {
    View rootView;
    EditText keywordSearch;
    EditText locationSearch;
    CheckBox fulltimeCheck;
    Button searchButton;


    public SearchJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_search_jobs, container, false);
        keywordSearch = (EditText) rootView.findViewById(R.id.keyword_search_edittext);
        locationSearch = (EditText) rootView.findViewById(R.id.location_search_edittext);
        fulltimeCheck = (CheckBox) rootView.findViewById(R.id.full_time_checkbox);
        searchButton = (Button) rootView.findViewById(R.id.search_jobs_button);
        searchButtonOnClick();
        return rootView;

    }

    public void searchButtonOnClick() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String keyword = keywordSearch.getText().toString();
                String location = locationSearch.getText().toString();
                boolean isFullTime = fulltimeCheck.isChecked();
                Bundle searchBundle = new Bundle();
                searchBundle.putString("keyword", keyword);
                searchBundle.putString("location", location);
                searchBundle.putBoolean("isFullTime", isFullTime);
                //SearchListFragment searchListFragment = (SearchListFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.search_list_fragment_container);
                SearchListFragment searchListFragment = new SearchListFragment();
                searchListFragment.updateSearchList(searchBundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_container, searchListFragment);
                fragmentTransaction.addToBackStack("next");
                fragmentTransaction.commit();
            }
        });

    }
}

package com.example.android.gitjobs;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.gitjobs.fragments.SearchJobsFragment;
import com.example.android.gitjobs.fragments.SearchListFragment;

public class MainActivity extends AppCompatActivity {
    SearchJobsFragment searchJobsFragment;
    SearchListFragment searchListFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragments();
        //Networking using okhttp:
        //TODO add okhttp dependency
        //TODO add internet permissions to manifest
        //TODO build url string
        //TODO add okhttp request method
        //TODO parse result string into list
        //TODO add list into adapter
    }

    public void setFragments(){
        searchJobsFragment = new SearchJobsFragment();
        searchListFragment = new SearchListFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.search_fragment_container, searchJobsFragment);
        fragmentTransaction.replace(R.id.search_list_fragment_container, searchListFragment);
        fragmentTransaction.commit();
    }
}

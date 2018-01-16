package com.example.android.gitjobs;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.gitjobs.fragments.SearchJobsFragment;
import com.example.android.gitjobs.fragments.SearchListFragment;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
        fragmentTransaction.replace(R.id.main_fragment_container, searchJobsFragment);
        fragmentTransaction.addToBackStack("next");
        fragmentTransaction.commit();
    }

}

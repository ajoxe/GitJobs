package com.example.android.gitjobs;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        fragmentTransaction.replace(R.id.main_fragment_container, searchJobsFragment);
        fragmentTransaction.addToBackStack("next");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_menu:
                fragmentManager.beginTransaction().replace(R.id.main_fragment_container, searchJobsFragment).commit();
        }
        return true;
    }
}

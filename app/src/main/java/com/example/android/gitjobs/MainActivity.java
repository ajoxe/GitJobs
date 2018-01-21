package com.example.android.gitjobs;


import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.gitjobs.fragments.JobsAppliedToFragment;
import com.example.android.gitjobs.fragments.SavedJobsFragment;
import com.example.android.gitjobs.model.GitJobsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.gitjobs.fragments.SearchJobsFragment;
import com.example.android.gitjobs.fragments.SearchListFragment;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


public class MainActivity extends AppCompatActivity {
    SearchJobsFragment searchJobsFragment;
    SearchListFragment searchListFragment;
    SavedJobsFragment savedJobsFragment;
    JobsAppliedToFragment jobsAppliedToFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    List<GitJobsModel> jobsList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragments();
        /*GitJobsDBHelper dbHelper = new GitJobsDBHelper(getApplicationContext());
        dbHelper.deleteTable();
        long size = dbHelper.getReadableDatabase().getPageSize();
        Log.d("size", String.valueOf(size));
        dbHelper.close();*/
        //String faux ="gitjob";
        //db.insertJob(new GitJobsModel(faux,faux,faux,faux,faux, faux, faux, faux, faux, faux, faux), "search");
    }

//    public void okhttpConnection(){
//
//        final String url = "https://jobs.github.com/positions.json?description=python&location=new+york";
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request urlRequest = new Request.Builder()
//                .url(url)
//                .build();
//
//        okHttpClient.newCall(urlRequest).enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Log.d("Failed", "onFailure: ");
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String test = response.body().string();
//                Log.d("Connected", "onResponse: Success" + test);
//
//
//                try {
//                    JSONArray jsonArray= new JSONArray(test);
//                    Log.d("RESponse==", "onResponse: "+jsonArray.length());
//                    Log.d("RESponse==", "onResponse: "+jsonArray.getJSONObject(0).get("id"));
//
//                    for (int i = 0; i <jsonArray.length() ; i++) {
//
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//
//                        jobsList.add(new GitJobsModel(jsonObject.get("id"), jsonObject.get("created_at"),
//                                jsonObject.get("title"), jsonObject.get("location"), jsonObject.get("type"),
//                                jsonObject.get("description"), jsonObject.get("how_to_apply"), jsonObject.get("company"), jsonObject.get("company_url"),
//                                jsonObject.get("company_logo"), jsonObject.get("url")));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

    public void setFragments(){
        searchJobsFragment = new SearchJobsFragment();
        searchListFragment = new SearchListFragment();
        savedJobsFragment = new SavedJobsFragment();
        jobsAppliedToFragment = new JobsAppliedToFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
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
                break;
            case R.id.saved_menu:
                fragmentManager.beginTransaction().replace(R.id.main_fragment_container, savedJobsFragment).commit();
                break;
            case R.id.applied_menu:
                fragmentManager.beginTransaction().replace(R.id.main_fragment_container, jobsAppliedToFragment).commit();
                break;
        }
        return true;
    }
}

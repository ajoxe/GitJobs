package com.example.android.gitjobs.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.gitjobs.GitJobsDBHelper;
import com.example.android.gitjobs.GitJobsListings;
import com.example.android.gitjobs.MainActivity;
import com.example.android.gitjobs.R;
import com.example.android.gitjobs.controller.GitJobsAdapter;
import com.example.android.gitjobs.model.GitJobsModel;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry._STATUS_APPLIED;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry._STATUS_SAVED;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry._STATUS_SEARCHED;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListFragment extends Fragment {
    View rootView;
    private static final String SHARED_PREFS_KEY = "gitJobsJson";
    private SharedPreferences gitJobsJson;
    TextView searchTerm;
    TextView listings;
    ImageView company_logo;
    RecyclerView searchListRecyclerView;
    GitJobsAdapter gitJobsAdapter;
    List<GitJobsModel> gitJobsList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    String url = "https://jobs.github.com/positions.json";
    String keywordPref = "?description=";
    String locationPref ="&location=";
    String fullTime = "&full_time=true";
    String keyword;
    String location;
    boolean isFullTime;
    RecyclerView recyclerView;
    View.OnClickListener searchListButtonClick;
    View.OnClickListener saveButtonClick;
    View.OnClickListener applyButtonClick;
    Context context;
    GitJobsListings gitList;
    String jsonResult;


    public SearchListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search_list, container, false);
        gitJobsJson = getActivity().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        searchTerm = rootView.findViewById(R.id.search_term_textview);
        listings = rootView.findViewById(R.id.listings_textview);
        recyclerView = rootView.findViewById(R.id.search_list_recyclerview);
        setRecyclerView();
        context = getContext().getApplicationContext();
        return rootView;
    }

    public void setRecyclerView(){
        searchListRecyclerView = rootView.findViewById(R.id.search_list_recyclerview);
        setSearchListButton();
        setSaveButton();
        setApplyButton();
        gitJobsAdapter = new GitJobsAdapter(gitJobsList, getContext(), searchListButtonClick, saveButtonClick,applyButtonClick);
        linearLayoutManager = new LinearLayoutManager(getContext());
        //cant set or update the adapter until the class is built
        searchListRecyclerView.setAdapter(gitJobsAdapter);
        searchListRecyclerView.setLayoutManager(linearLayoutManager);
    }

    //called when 'search' button is clicked in SearchJobsFragment.
    //this method takes the bundle added from the search button and gets the keyword, location, and fulltime boolean.
    //it then calls the buildUrlString method (below) which builds the url string for the okhttp request (below)
    public void updateSearchList(Bundle bundle){
        keyword = bundle.getString("keyword");
        location = bundle.getString("location");
        isFullTime = bundle.getBoolean("isFullTime");
        makeRequestWithOkHttp(buildUrlString());
        Log.d("updateSearch method", keyword + location);
    }

    public void setApplyButton(){
        applyButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobId = v.getTag().toString();
                GitJobsModel job = gitList.getJobFromListById(jobId);
                GitJobsDBHelper db = new GitJobsDBHelper(context.getApplicationContext());
                db.insertJob(job, _STATUS_APPLIED);
                db.close();
                Intent applyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getUrl()));
                startActivity(applyIntent);
                Toast.makeText(getActivity(), "This job is saved to your 'applied' list!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void setSaveButton(){
        saveButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobId = v.getTag().toString();
                GitJobsModel job = gitList.getJobFromListById(jobId);
                GitJobsDBHelper db = new GitJobsDBHelper(context.getApplicationContext());
                db.insertJob(job, _STATUS_SAVED);
                db.close();
                Toast.makeText(getActivity(), "This job is saved to your 'saved' list!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void setSearchListButton(){
        searchListButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobId = v.getTag().toString();
                GitJobsModel job = gitList.getJobFromListById(jobId);
                GitJobsDBHelper db = new GitJobsDBHelper(context.getApplicationContext());
                db.insertJob(job, _STATUS_SEARCHED);
                db.close();
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



    //uses a string builder to add keyword, location, and fultime queries to the url string.
    //if no info is provided, all jobs will be returned.
    public String buildUrlString(){

        StringBuilder sb = new StringBuilder(url);
        if(keyword != null) {
            try {
                //URL encoder, encodes the queries so the url string is formed properly.
                keyword = URLEncoder.encode(keyword, "UTF-8");
                sb.append(keywordPref);
                sb.append(keyword);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (location != null) {
            try {
                location = URLEncoder.encode(location, "UTF-8");
                sb.append(locationPref);
                sb.append(location);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        if (isFullTime){
            sb.append(fullTime);
        }

        url = sb.toString();

        Log.d("buildUrl", url);
        return url;
    }

    //This method first makes a request with the url. Then onResponse, it sends the string result to the list builder class,
    //which parses the json string. it adds the list to the gitJobs list in this class, and also adds the result
    //to the shared preferences with the keyword and location as the key (because each search request will be different)
    //it also notifies the adapter on the main thread.
    private void makeRequestWithOkHttp(String url) {
        OkHttpClient client = new OkHttpClient();   // 1
        Request request = new Request.Builder().url(url).build();  // 2

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) { // 3
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();  // 4

                //Log.d("result", result);
                jsonResult = result;
                //sends result to gitjobslisting class which parses the json string.
                gitList = new GitJobsListings(result);
                //adds the parsed jobs to the list.
                gitJobsList.addAll(gitList.getGitJobsModelList());
//

                //adds the result string to shared preferences
                /*SharedPreferences.Editor editor = gitJobsJson.edit();
                editor.putString(keyword + location, result);
                editor.apply();*/
                //logs the size
                //Log.d("gitJobsList: ", String.valueOf(gitJobsList.size()));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //updates the text views with info from bundle and okhttprequest
                            searchTerm.setText("Search Term: " + keyword);
                            listings.setText("Listings: " + gitJobsList.size());
                            gitJobsAdapter.notifyDataSetChanged();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


}

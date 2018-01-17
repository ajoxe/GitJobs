package com.example.android.gitjobs;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.gitjobs.backend.GitJobService;
import com.example.android.gitjobs.controller.GitJobsAdapter;
import com.example.android.gitjobs.model.GitJobsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY = "jobs";
    private GitJobsAdapter gitJobsAdapter;
    private ArrayList<GitJobsModel> gitJobsModelList;
    private GitJobsModel gitJobsModel;
    EditText keyword;
    EditText location;
    EditText type;
    Button search_term_btn; // This button should have intent to the Search Term Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyword = findViewById(R.id.key_word_et);
        location = findViewById(R.id.location_et);
        type = findViewById(R.id.type_et);
        search_term_btn = findViewById(R.id.search_term_intent_btn);

        final RecyclerView recyclerView = findViewById(R.id.git_jobs_recycler_view);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GitJobService service = retrofit.create(GitJobService.class);
        service.getJobs();

        Call<ArrayList<GitJobsModel>> jobService = service.getJobs();

        jobService.enqueue(new Callback<ArrayList<GitJobsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GitJobsModel>> call, Response<ArrayList<GitJobsModel>> response) {
                if (response.isSuccessful()) {
                    gitJobsModelList = response.body();
                    gitJobsAdapter = new GitJobsAdapter(gitJobsModelList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setAdapter(gitJobsAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    Log.d(TAG, "onResponse: " + gitJobsModel.getTitle());
                    gitJobsAdapter.notifyDataSetChanged();

//                    int listSize = gitJobsModelList.size();
//                    Random random = new Random();
//                    int n = random.nextInt(listSize);
//
//                    gitJobsModel = gitJobsModelList.get(n);
//                    gitJobsAdapter.setHasStableIds(true);
//                    gitJobsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GitJobsModel>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

    }

//    public void jobLinkIntent(String link){
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
//        startActivity(intent);
//    }
//
//    public void allJobs(){
//        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//        intent.putParcelableArrayListExtra(KEY, (ArrayList<? extends Parcelable>) gitJobsModelList);
//        startActivity(intent);
//    }

}

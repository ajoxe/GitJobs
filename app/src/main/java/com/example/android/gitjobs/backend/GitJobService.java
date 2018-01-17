package com.example.android.gitjobs.backend;

import com.example.android.gitjobs.model.GitJobsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BabiMaji on 1/16/18.
 */

public interface GitJobService {
    @GET("positions.json")
    Call<ArrayList<GitJobsModel>> getJobs();
}

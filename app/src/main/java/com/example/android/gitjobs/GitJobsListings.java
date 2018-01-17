package com.example.android.gitjobs;

import android.app.job.JobInfo;

import com.example.android.gitjobs.model.GitJobsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 1/15/18.
 */

public class GitJobsListings {
    private List<GitJobsModel> gitJobsModelList;
    private HashMap<String, GitJobsModel> gitJobsModelHashMap;

    public GitJobsListings(String gitJobsJson){
        buildJobsList(gitJobsJson);
        buildJobsMap();
    }

    public void buildJobsList(String gitJobsJson){
        Type collectionType = new TypeToken<Collection<GitJobsModel>>() {
        }.getType();
        Gson gs = new Gson();
        Collection<GitJobsModel> jobs = null;
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(gitJobsJson.getBytes(StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(is);
        jobs = gs.fromJson(isr, collectionType);
        gitJobsModelList = new ArrayList<>();
        gitJobsModelList.addAll(jobs);
    }

    public void buildJobsMap(){
        gitJobsModelHashMap = new HashMap<>();
        for (GitJobsModel job: gitJobsModelList){
            gitJobsModelHashMap.put(job.getId(),job);
        }
    }

    public List<GitJobsModel> getGitJobsModelList() {
        return gitJobsModelList;
    }

    public HashMap<String, GitJobsModel> getGitJobsModelHashMap() {
        return gitJobsModelHashMap;
    }
}

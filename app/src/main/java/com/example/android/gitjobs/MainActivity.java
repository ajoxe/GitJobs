package com.example.android.gitjobs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.gitjobs.model.GitJobsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    List<GitJobsModel> jobsList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Networking using okhttp:
        //TODO add okhttp dependency (DONE)
        //TODO add internet permissions to manifest (DONE)
        //TODO build url string
        //TODO add okhttp request method
        //TODO parse result string into list
        //TODO add list into adapter

        okhttpConnection();

    }

    public void okhttpConnection(){

        final String url = "https://jobs.github.com/positions.json?description=python&location=new+york";

        OkHttpClient okHttpClient = new OkHttpClient();
        Request urlRequest = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(urlRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("Failed", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String test = response.body().string();
                Log.d("Connected", "onResponse: Success" + test);


                try {
                    JSONArray jsonArray= new JSONArray(test);
                    Log.d("RESponse==", "onResponse: "+jsonArray.length());
                    Log.d("RESponse==", "onResponse: "+jsonArray.getJSONObject(0).get("id"));

                    for (int i = 0; i <jsonArray.length() ; i++) {

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        jobsList.add(new GitJobsModel(jsonObject.get("id"), jsonObject.get("created_at"),
                                jsonObject.get("title"), jsonObject.get("location"), jsonObject.get("type"),
                                jsonObject.get("description"), jsonObject.get("how_to_apply"), jsonObject.get("company"), jsonObject.get("company_url"),
                                jsonObject.get("company_logo"), jsonObject.get("url")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}

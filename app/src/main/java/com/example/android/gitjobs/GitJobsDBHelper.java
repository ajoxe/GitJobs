package com.example.android.gitjobs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.gitjobs.model.GitJobsModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_COMPANY;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_COMPANY_LOGO;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_COMPANY_URL;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_CREATED_AT;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_DESCRIPTION;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_HOW_TO_APPLY;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_JOB_ID;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_LOCATION;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_STATUS;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_TITLE;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_TYPE;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.COLUMN_NAME_URL;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry.TABLE_NAME;
import static com.example.android.gitjobs.GitJobsDBContract.GitJobsentry._ID;

/**
 * Created by amirahoxendine on 1/20/18.
 */

public class GitJobsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gitjobs.db";
    private static final int SCHEMA_VERSION = 1;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_STATUS + " TEXT, " +
                    COLUMN_NAME_JOB_ID + " TEXT, " +
                    COLUMN_NAME_CREATED_AT + " TEXT, " +
                    COLUMN_NAME_TITLE + " TEXT, " +
                    COLUMN_NAME_LOCATION + " TEXT, " +
                    COLUMN_NAME_TYPE + " TEXT, " +
                    COLUMN_NAME_DESCRIPTION + " TEXT, " +
                    COLUMN_NAME_HOW_TO_APPLY + " TEXT, " +
                    COLUMN_NAME_COMPANY + " TEXT, " +
                    COLUMN_NAME_COMPANY_URL + " TEXT, " +
                    COLUMN_NAME_COMPANY_LOGO + " TEXT, " +
                    COLUMN_NAME_URL + " TEXT);";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public GitJobsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public boolean insertJob(GitJobsModel job, String status) {

        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
       /* getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
                "(" + COLUMN_NAME_STATUS + ", " +
                COLUMN_NAME_JOB_ID + ", " +
                COLUMN_NAME_CREATED_AT + ", " +
                COLUMN_NAME_TITLE + ", " +
                COLUMN_NAME_LOCATION + ", " +
                COLUMN_NAME_TYPE + ", " +
                COLUMN_NAME_DESCRIPTION+ ", " +
                COLUMN_NAME_HOW_TO_APPLY+ ", " +
                COLUMN_NAME_COMPANY+ ", " +
                COLUMN_NAME_COMPANY_URL+ ", " +
                COLUMN_NAME_COMPANY_LOGO+ ", " +
                COLUMN_NAME_URL + ") VALUES ('" +
                status + "', '" +
               job.getId() + "', '" +
                job.getCreated_at() + "', " +
                DatabaseUtils.sqlEscapeString(job.getTitle()) + ", " +
                DatabaseUtils.sqlEscapeString(job.getLocation()) + ", " +
                DatabaseUtils.sqlEscapeString(job.getType()) + ", " +
                DatabaseUtils.sqlEscapeString(job.getDescription()) + ", " +
                DatabaseUtils.sqlEscapeString(job.getHow_to_apply()) + ", " +
                DatabaseUtils.sqlEscapeString(job.getCompany()) + ", '" +
                job.getCompany_url() + "', '" +
                job.getCompany_logo() + "', '" +
                job.getUrl() + "');");*/
       Log.d("insertInto jobID", job.getId());
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME_STATUS, status);
            contentValues.put(COLUMN_NAME_JOB_ID, job.getId());
            contentValues.put(COLUMN_NAME_CREATED_AT, job.getCreated_at());
            contentValues.put(COLUMN_NAME_TITLE, job.getTitle());
            contentValues.put(COLUMN_NAME_LOCATION, job.getLocation());
            contentValues.put(COLUMN_NAME_TYPE, job.getType());
            contentValues.put(COLUMN_NAME_DESCRIPTION, job.getDescription());
            contentValues.put(COLUMN_NAME_HOW_TO_APPLY, job.getHow_to_apply());
            contentValues.put(COLUMN_NAME_COMPANY, job.getCompany());
            contentValues.put(COLUMN_NAME_COMPANY_URL, job.getCompany_url());
            contentValues.put(COLUMN_NAME_COMPANY_LOGO, job.getCompany_logo());
            contentValues.put(COLUMN_NAME_URL, job.getUrl());
            long rowid = db.insert(TABLE_NAME, null, contentValues);
            Log.d("row id", String.valueOf(rowid));
            GitJobsModel insertJob = getJobById(job.getId());
            Log.d("insertedJob", job.getId());

        return true;
    }

    public List<GitJobsModel> getSavedJobsList() {
        List<GitJobsModel> gitSavedJobs = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_STATUS + " = 'saved';", null);
        if (cursor.moveToFirst()) {
            do {
                GitJobsModel job = new GitJobsModel(
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_JOB_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CREATED_AT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LOCATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_HOW_TO_APPLY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY_LOGO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY_URL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_URL)));
                gitSavedJobs.add(job);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return gitSavedJobs;
    }

    public List<GitJobsModel> getAppliedJobsList() {
        List<GitJobsModel> gitSavedJobs = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_STATUS + " ='applied';", null);
        if (cursor.moveToFirst()) {
            do {
                GitJobsModel job = new GitJobsModel(
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_JOB_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CREATED_AT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LOCATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_HOW_TO_APPLY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY_LOGO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY_URL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_URL)));
                gitSavedJobs.add(job);
            } while (cursor.moveToNext());
        }
        return gitSavedJobs;

    }

    public GitJobsModel getJobById(String id){

        Log.d("job id", id);
        GitJobsModel job = new GitJobsModel();
        Cursor cursor = this.getReadableDatabase()
                .rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_STATUS + " ='search';", null);
        if (cursor.moveToFirst()) {
            do {

                job = new GitJobsModel(
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_JOB_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CREATED_AT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LOCATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_HOW_TO_APPLY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY_LOGO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COMPANY_URL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME_URL)));
            } while (cursor.moveToNext());
        }
                        cursor.close();
        Log.d("job title", job.getTitle());
                return job;
    }

}


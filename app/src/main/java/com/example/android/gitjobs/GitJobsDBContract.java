package com.example.android.gitjobs;

import android.provider.BaseColumns;

/**
 * Created by amirahoxendine on 1/20/18.
 */

public class GitJobsDBContract {
    private GitJobsDBContract(){

    }

    public static class GitJobsentry implements BaseColumns {
        public static final String TABLE_NAME = "gitjobs";
        public static final String COLUMN_NAME_JOB_ID = "job_id";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_HOW_TO_APPLY = "how_to_apply";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_COMPANY_URL = "company_url";
        public static final String COLUMN_NAME_COMPANY_LOGO = "company_logo";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String _ID = "_id";
    }
}

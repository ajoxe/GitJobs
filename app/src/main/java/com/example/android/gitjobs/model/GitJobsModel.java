package com.example.android.gitjobs.model;

/**
 * Created by BabiMaji on 1/13/18.
 */

public class GitJobsModel {

    String id;
    String created_at;
    String title;
    String location;
    String type;
    String description;
    String how_to_apply;
    String company;
    String company_url;
    String company_logo;
    String url;


    //This is the retrofit constructor, not necessary for okhttp.
    /*public GitJobsModel(String id, String created_at, String title, String location, String type, String description, String how_to_apply, String company, String company_url, String company_logo, String url) {
        this.id = id;
        this.created_at = created_at;
        this.title = title;
        this.location = location;
        this.type = type;
        this.description = description;
        this.how_to_apply = how_to_apply;
        this.company = company;
        this.company_url = company_url;
        this.company_logo = company_logo;
        this.url = url;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHow_to_apply() {
        return how_to_apply;
    }

    public void setHow_to_apply(String how_to_apply) {
        this.how_to_apply = how_to_apply;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_url() {
        return company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

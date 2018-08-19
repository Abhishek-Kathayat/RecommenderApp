package com.recom.www.recommenderapp.Models;

import com.google.gson.annotations.SerializedName;
import com.recom.www.recommenderapp.Models.Nearby_Model;

import java.util.List;


public class ApiResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Nearby_Model> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Nearby_Model> getResults() {
        return results;
    }

    public void setResults(List<Nearby_Model> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
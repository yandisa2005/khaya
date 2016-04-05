package com.sb.intro.model;

import com.sb.intro.entities.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by livious on 2015/07/06.
 */
public class ClientResults implements Serializable {

    private Integer displayedResults;
    private Long totalResults;

    private List<Client> results = new ArrayList<>();

    public Integer getDisplayedResults() {
        return displayedResults;
    }

    public void setDisplayedResults(Integer displayedResults) {
        this.displayedResults = displayedResults;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public List<Client> getResults() {
        return results;
    }

    public void setResults(List<Client> results) {
        this.results = results;
    }
}
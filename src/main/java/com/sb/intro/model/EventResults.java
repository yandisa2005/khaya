package com.sb.intro.model;

import com.sb.intro.entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by livious on 2015/07/06.
 */
public class EventResults implements Serializable {

    private Integer displayedResults;
    private Long totalResults;

    private List<com.sb.intro.entities.Event> results = new ArrayList<>();

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

    public List<com.sb.intro.entities.Event> getResults() {
        return results;
    }

    public void setResults(List<com.sb.intro.entities.Event> results) {
        this.results = results;
    }
}
package com.sb.intro.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Results<T> implements JsonToStringable {

    private Integer displayedResults;
    private Long totalResults;

    private List<T> results = new ArrayList<>();

    public Results() {
    }

    public Results(final List<T> results, final Long totalResults) {
        this.results = results;
        this.displayedResults = results.size();
        this.totalResults = totalResults;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(final Long totalResults) {
        this.totalResults = totalResults;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(final List<T> results) {
        this.results = results;
    }

    public Integer getDisplayedResults() {
        return displayedResults;
    }

    public void setDisplayedResults(final Integer displayedResults) {
        this.displayedResults = displayedResults;
    }

    @Override
    public String toString() {
        return toJsonString();
    }
}

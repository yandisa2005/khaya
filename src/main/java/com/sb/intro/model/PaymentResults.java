package com.sb.intro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by livious on 2015/07/09.
 */
public class PaymentResults implements Serializable {

    private Integer displayedResults;
    private Long totalResults;

    private List<com.sb.intro.entities.Payment> results = new ArrayList<>();

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

    public List<com.sb.intro.entities.Payment> getResults() {
        return results;
    }

    public void setResults(List<com.sb.intro.entities.Payment> results) {
        this.results = results;
    }
}
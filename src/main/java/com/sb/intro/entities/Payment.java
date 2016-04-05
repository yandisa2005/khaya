package com.sb.intro.entities;

import com.sb.intro.model.JsonToStringable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by livious on 2015/07/03.
 */
@Entity
public class Payment extends IdEntiity implements JsonToStringable {

    private long amount;
    private Event event;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

package com.sb.intro.entities;

import com.sb.intro.model.JsonToStringable;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by livious on 2015/07/02.
 */

@Entity
public class Event extends IdEntiity implements JsonToStringable {

    private String uuid = UUID.randomUUID().toString();
    private String eventName;
    private String venue;
    private Date eventDate;
    private double amount;
    private double totalExpenditure;
    private double taxCharge;
    private String status;
//    private String createdBy;
//    private Person contact;
//    private List<Person> personnal;

    public Event() {
    }

    public Event(final String eventName, final String venue, final Date eventDate,
                 final double amount, final double totalExpenditure, final double taxCharge,  final String createdBy, final String status) {
        this.eventName = eventName;
        this.venue = venue;
        this.eventDate = eventDate;
        this.amount = amount;
        this.totalExpenditure = totalExpenditure;
        this.taxCharge = taxCharge;
        this.status = status;
        this.setCreatedBy(createdBy);
//        this.createdBy = createdBy;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public double getTaxCharge() {
        return taxCharge;
    }

    public void setTaxCharge(double taxCharge) {
        this.taxCharge = taxCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //    @Override
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    @Override
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }

//    public Person getContact() {
//        return contact;
//    }
//
//    public void setContact(Person contact) {
//        this.contact = contact;
//    }

//    public List<Person> getPersonnal() {
//        return personnal;
//    }
//
//    public void setPersonnal(List<Person> personnal) {
//        this.personnal = personnal;
//    }
}

package com.sb.intro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by livious on 2015/07/06.
 */
public class Event implements Serializable {

    private String eventName;
    private String venue;
    private Date eventDate;
    private double amount;
    private double totalExpenditure;
    private double taxCharge;
    private String createdBy;
    private Person contact;
    private String status;
    private List<Person> personnal;

    public Event(String eventName, String venue, Date eventDate, Double amount, Double totalExpenditure, Double taxCharge, String  createdBy, String status) {
        this.eventName = eventName;
        this.venue = venue;
        this.eventDate = eventDate;
        this.amount = amount;
        this.totalExpenditure = totalExpenditure;
        this.taxCharge = taxCharge;
        this.status = status;
        this.createdBy = createdBy;
//        this.contact = contact;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getContact() {
        return contact;
    }

    public void setContact(Person contact) {
        this.contact = contact;
    }

    public List<Person> getPersonnal() {
        return personnal;
    }

    public void setPersonnal(List<Person> personnal) {
        this.personnal = personnal;
    }
}

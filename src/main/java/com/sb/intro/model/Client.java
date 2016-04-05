package com.sb.intro.model;

import com.sb.intro.model.Person;

import java.io.Serializable;

/**
 * Created by livious on 2015/07/06.
 */
public class Client implements Serializable{

    private long id;
    private String name;
    private String cretaedBy;
//    private Person contact;

    public Client(String name, String createdBy) {
        this.name = name;
        this.cretaedBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCretaedBy() {
        return cretaedBy;
    }

    public void setCretaedBy(String cretaedBy) {
        this.cretaedBy = cretaedBy;
    }

    //    public Person getContact() {
//        return contact;
//    }
//
//    public void setContact(Person contact) {
//        this.contact = contact;
//    }
}

package com.sb.intro.entities;

import com.sb.intro.model.JsonToStringable;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by livious on 2015/07/02.
 */
@Entity
public class Client extends IdEntiity implements JsonToStringable, Serializable {

    private String uuid = UUID.randomUUID().toString();
    private String name;
//    private Person contact;

    public Client() {
    }

    public Client(final String name, final String createdBy) {
        this.name = name;
        this.setCreatedBy(createdBy);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Person getContact() {
//        return contact;
//    }
//
//    public void setContact(Person contact) {
//        this.contact = contact;
//    }

    @Override
    public String toString() {
        return toJsonString();
    }
}

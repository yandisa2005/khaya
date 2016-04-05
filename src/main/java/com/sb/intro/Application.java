package com.sb.intro;

import com.sb.intro.entities.Client;
import com.sb.intro.entities.Event;
import com.sb.intro.entities.User;
import com.sb.intro.repositories.ClientRepository;
import com.sb.intro.repositories.EventRepository;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
//@EnableJms
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


//    @Bean
//    InitializingBean intData(ClientRepository clientRepository, EventRepository eventRepository) {
//        return () -> {
//            User u1 = new User();
//
//            Client c1 = new Client("Jones", "System");
//            Client c2 = new Client("Smiths", "System");
//            Client c3 = new Client("Dube", "System");
//
//            clientRepository.save(c1);
//            clientRepository.save(c2);
//            clientRepository.save(c3);
//
//            Event v1 = new Event( "Durban July", "Durban", new Date(), 50000, 15000, 7000, "System");
//            Event v2 = new Event( "Jazz Festival", "Joburg", new Date(), 50000, 3500, 788, "System");
//            Event v3 = new Event( "Zoo Lake", "Zoo Lake", new Date(), 49000, 7004, 784, "System");
//
//            eventRepository.save(v1);
//            eventRepository.save(v2);
//            eventRepository.save(v3);
//        };
//    }
}


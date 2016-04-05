package com.sb.intro.specifications;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;

public class MessageSpecification {

//    private static final Logger log = LoggerFactory.getLogger(MessageSpecification.class);
//
//    String message;
//    private Date fromDate, toDate;
//
//    public MessageSpecification(String message, Date fromDate, Date toDate) {
//        this.message = message;
//        this.fromDate = fromDate;
//        this.toDate = toDate;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//        Predicate predicate = null;
//        Predicate msgPredicate = null;
//
//        if(message != null ) {
//            msgPredicate = cb.and(cb.like(root.get(Message_.message), makeLikeable(message)));
//            if(fromDate == null && toDate == null) {
//                return msgPredicate;
//            }
//        }
//        if(msgPredicate != null) {
//            if (fromDate != null && toDate != null) {
//                predicate = cb.and(msgPredicate, cb.between(root.get(Message_.addedOn), fromDate, toDate));
//            } else {
//                if (fromDate != null) {
//                    predicate = cb.and(msgPredicate, cb.greaterThanOrEqualTo(root.get(Message_.addedOn), fromDate));
//                } else if (toDate != null) {
//                    predicate = cb.and(msgPredicate, cb.lessThanOrEqualTo(root.get(Message_.addedOn), toDate));
//                }
//            }
//        }else {
//            if (fromDate != null && toDate != null) {
//                predicate = cb.and(cb.between(root.get(Message_.addedOn), fromDate, toDate));
//            } else {
//                if (fromDate != null) {
//                    predicate = cb.and(cb.greaterThanOrEqualTo(root.get(Message_.addedOn), fromDate));
//                } else if (toDate != null) {
//                    predicate = cb.and(cb.lessThanOrEqualTo(root.get(Message_.addedOn), toDate));
//                }
//            }
//        }
//
//        return predicate;
//    }
//
//    protected String makeLikeable(final String value) {
//        String likeable = format("%%%s%%", fromNullable(value).or(""));
//
//        log.trace("Made this value likeable: {} -> {}", value, likeable);
//        return likeable;
//    }
}

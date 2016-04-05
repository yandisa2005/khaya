package com.sb.intro.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

public interface JsonToStringable extends Serializable {

    default public String toJsonString() {
        String toString;
        try {
            toString = new String(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(this));
        } catch (JsonProcessingException e) {
            toString = ToStringBuilder.reflectionToString(this, MULTI_LINE_STYLE);
        }

        return toString;
    }
}

package com.sb.intro.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;
import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

@MappedSuperclass
public abstract class IdEntiity extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof IdEntiity)) {
            return false;
        }

        return new EqualsBuilder().append(id, ((IdEntiity) obj).getId()).isEquals();
    }

    @Override
    public String toString() {
        String toString;
        try {
            toString = new String(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(this));
        } catch (JsonProcessingException e) {
            toString = ToStringBuilder.reflectionToString(this, MULTI_LINE_STYLE);
        }

        return toString;
    }
}

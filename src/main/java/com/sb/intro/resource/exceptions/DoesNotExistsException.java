package com.sb.intro.resource.exceptions;

import com.sb.intro.exceptions.RuntimeFormattedException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Created by livious on 2015/12/09.
 */

@ResponseStatus(NO_CONTENT)
public class DoesNotExistsException extends RuntimeFormattedException {

    public DoesNotExistsException(final Class<? extends Serializable> entity) {
        super(format("This %s does not exist", entity.getSimpleName()));
    }

    public DoesNotExistsException(final Class<? extends Serializable> entity, final String message, final Object... args) {
        super("%s", format("This %s does not exist: %s", entity.getSimpleName(), format(message, args)));
    }
}


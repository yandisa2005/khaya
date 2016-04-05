package com.sb.intro.resource.exceptions;

import com.sb.intro.entities.IdEntiity;
import com.sb.intro.exceptions.RuntimeFormattedException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * Created by livious on 2015/12/09.
 */
@ResponseStatus(CONFLICT)
public class AlreadyExistsException extends RuntimeFormattedException {

    public AlreadyExistsException(final Class<? extends IdEntiity> entity) {
        super(format("This %s already exists", entity.getSimpleName()));
    }

    public AlreadyExistsException(final Class<? extends IdEntiity> entity, final String message, final Object... args) {
        super("%s", format("This %s already exists: %s", entity.getSimpleName(), format(message, args)));
    }
}

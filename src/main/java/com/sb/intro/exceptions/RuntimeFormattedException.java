package com.sb.intro.exceptions;

import static java.lang.String.format;

/**
 * Created by livious on 2015/12/09.
 */
public class RuntimeFormattedException extends RuntimeException {

    public RuntimeFormattedException(final String message) {
        super(message);
    }

    public RuntimeFormattedException(final String message, final Object... args) {
        this(format(message, args));
    }
}

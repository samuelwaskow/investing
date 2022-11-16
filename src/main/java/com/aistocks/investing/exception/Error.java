package com.aistocks.investing.exception;

import java.util.Date;

public final class Error {

    private final Date timestamp;
    private final String message;
    private final String details;

    /**
     * Constructor
     * @param timestamp
     * @param message
     * @param details
     */
    public Error(final Date timestamp, final String message, final String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    /* Gets */
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
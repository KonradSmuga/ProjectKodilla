package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private String toCc;

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getToCc() {
        return toCc;
    }
}

package com.test.employees.other.eventbus;

public enum Status {
    SUCCESS(200),
    USER_ERROR(422),
    NOT_FOUND(404),
    INTERNET_NOT_AVAILABLE(800),
    SERVER_NOT_AVAILABLE(801);

    int code;
    Status(int code) {
        this.code = code;
    }
}

package com.test.employees.other.eventbus;

public class GetDataEvent {
    public Status status;

    public GetDataEvent(Status status) {
        this.status = status;
    }
}

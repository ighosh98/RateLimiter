package org.example;

public class RequestData {
    int numberOfRequests;
    long timeWindow;
    RequestData(int numberOfRequests, long timeWindow) {
        this.timeWindow = timeWindow;
        this.numberOfRequests = numberOfRequests;
    }
}

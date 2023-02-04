package com.example.ex4.Listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionListener implements HttpSessionListener {

    private final AtomicInteger activeSessions;
    public SessionListener() {
        super();
        activeSessions = new AtomicInteger();
    }
    public int getTotalActiveSession() {
        return activeSessions.get();
    }
    public void sessionCreated(final HttpSessionEvent event)
    {
        System.out.println("active sessions = "+activeSessions.get());
        activeSessions.incrementAndGet();
    }
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
    }
}

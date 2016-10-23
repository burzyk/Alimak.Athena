package com.jpbnetsoftware.alimak.athena;

/**
 * Created by pburzynski on 23/10/2016.
 */
public interface ControlServer {
    void start();

    void stop() throws InterruptedException;
}

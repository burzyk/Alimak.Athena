package com.jpbnetsoftware.alimak.athena;

/**
 * Created by pburzynski on 23/10/2016.
 */
public interface Log {
    void info(String message);

    void error(String message, Exception ex);
}

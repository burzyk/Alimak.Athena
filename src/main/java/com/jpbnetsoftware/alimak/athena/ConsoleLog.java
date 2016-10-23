package com.jpbnetsoftware.alimak.athena;

import java.util.Calendar;

/**
 * Created by pburzynski on 23/10/2016.
 */
public class ConsoleLog implements Log {
    @Override
    public void info(String message) {
        System.out.println(this.getTime() + " INFO: " + message);
    }

    @Override
    public void error(String message, Exception ex) {
        System.out.println(this.getTime() + " ERROR: " + message);
    }

    private String getTime() {
        return Calendar.getInstance().getTime().toString();
    }
}

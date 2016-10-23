package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.Log;
import com.jpbnetsoftware.alimak.athena.PinManager;

/**
 * Created by pburzynski on 09/10/2016.
 */
public class ConsolePinManager implements PinManager {

    private final Log log;

    public ConsolePinManager(Log log) {
        this.log = log;
    }

    @Override
    public void setPwm(int channel, float value) {
        this.log.info(String.format("%d - %f", channel, value));
    }

    @Override
    public void shutdownPwm(int channel) {
        this.log.info(String.format("Shutting down channel: %d", channel));
    }
}

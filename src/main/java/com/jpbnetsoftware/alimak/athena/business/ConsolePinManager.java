package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.PinManager;

/**
 * Created by pburzynski on 09/10/2016.
 */
public class ConsolePinManager implements PinManager {

    @Override
    public void setPwm(int channel, float value) {
        System.out.println(String.format("%d - %f", channel, value));
    }
}

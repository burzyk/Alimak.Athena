package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.PinManager;
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pburzynski on 09/10/2016.
 * 
 * For pin configuration see: https://projects.drogon.net/raspberry-pi/wiringpi/pins/ 
 * physical pins 9, 11, 12, 13, 15
 */
public class PiPinManager implements PinManager {

    private int[] pinConfiguration;

    private boolean isInitialized;

    private boolean isShutdown;

    public PiPinManager() {
        this.pinConfiguration = new int[]{0, 1, 2, 3};
    }

    @Override
    public synchronized void setPwm(int channel, float value) {
        if (channel > this.pinConfiguration.length) {
            throw new IllegalArgumentException("channel must be {0, 1}");
        }

        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("value must be in range [0, 1]");
        }

        if (this.isShutdown) {
            throw new IllegalStateException("manager has been shutdown");
        }

        if (!this.isInitialized) {
            Gpio.wiringPiSetup();

            for (int i : this.pinConfiguration) {
                SoftPwm.softPwmCreate(i, 0, 100);
            }

            this.isInitialized = true;
        }

        SoftPwm.softPwmWrite(this.pinConfiguration[channel], (int) (value * 100));
    }

    @Override
    public synchronized void shutdownPwm(int channel) {
        this.isShutdown = true;

        if (!this.isInitialized) {
            return;
        }

        for (int i : this.pinConfiguration) {
            SoftPwm.softPwmStop(i);
        }
    }
}

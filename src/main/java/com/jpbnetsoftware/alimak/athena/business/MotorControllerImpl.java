package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.MotorController;
import com.jpbnetsoftware.alimak.athena.PinManager;

/**
 * Created by pburzynski on 09/10/2016.
 */
public class MotorControllerImpl implements MotorController {

    private float value;

    private int duration;

    private int pwmChannel;

    private PinManager pinManager;

    public MotorControllerImpl(int pwmChannel, PinManager pinManager) {
        this.pwmChannel = pwmChannel;
        this.pinManager = pinManager;
    }

    public synchronized void setValue(float value, int duration) {
        if (Math.abs(value) > 1) {
            throw new IllegalArgumentException();
        }

        if (value < 0) {
            throw new IllegalArgumentException("negative values are not implemented");
        }

        this.duration = duration;
        this.value = value;
        this.refresh();
    }

    public synchronized void decrementDuration() {
        this.duration -= 1;
        this.refresh();
    }

    private void refresh() {
        this.pinManager.setPwm(this.pwmChannel, this.duration > 0 ? this.value : 0);
    }
}

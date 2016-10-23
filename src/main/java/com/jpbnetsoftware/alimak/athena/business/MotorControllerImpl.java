package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.MotorController;
import com.jpbnetsoftware.alimak.athena.PinManager;

/**
 * Created by pburzynski on 09/10/2016.
 */
public class MotorControllerImpl implements MotorController {

    private final int pwmChannelLeft;

    private final int pwmChannelRight;

    private final PinManager pinManager;

    private float value;

    private int duration;

    public MotorControllerImpl(int pwmChannelLeft, int pwmChannelRight, PinManager pinManager) {
        this.pwmChannelLeft = pwmChannelLeft;
        this.pwmChannelRight = pwmChannelRight;
        this.pinManager = pinManager;
    }

    public synchronized void setValue(float value, int duration) {
        if (Math.abs(value) > 1) {
            throw new IllegalArgumentException();
        }

        this.duration = duration;
        this.value = value;
    }

    public synchronized void refresh() {

        if (this.duration <= 0) {
            this.pinManager.setPwm(this.pwmChannelLeft, 0);
            this.pinManager.setPwm(this.pwmChannelRight, 0);

            return;
        }

        this.pinManager.setPwm(this.pwmChannelLeft, this.value > 0 ? this.value : 0);
        this.pinManager.setPwm(this.pwmChannelRight, this.value < 0 ? -this.value : 0);
        this.duration -= 1;
    }

    @Override
    public synchronized void shutdown() {
        this.pinManager.shutdownPwm(this.pwmChannelLeft);
        this.pinManager.shutdownPwm(this.pwmChannelRight);
    }
}

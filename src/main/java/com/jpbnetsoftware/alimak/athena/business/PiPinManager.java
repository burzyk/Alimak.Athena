package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.PinManager;
import com.pi4j.io.gpio.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pburzynski on 09/10/2016.
 */
public class PiPinManager implements PinManager {

    private Map<Integer, GpioPinPwmOutput> pwmChannels = new HashMap<>();

    @Override
    public synchronized void setPwm(int channel, float value) {
        if (channel != 0 && channel != 1) {
            throw new IllegalArgumentException("channel must be {0, 1}");
        }

        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("value must be in range [0, 1]");
        }

        if (!this.pwmChannels.containsKey(channel)) {
            Pin pinId = channel == 0 ? RaspiPin.GPIO_01 : RaspiPin.GPIO_23;
            GpioPinPwmOutput pin = GpioFactory.getInstance().provisionPwmOutputPin(pinId);
            pin.setShutdownOptions(true, PinState.LOW);
            this.pwmChannels.put(channel, pin);
        }

        GpioPinPwmOutput pin = this.pwmChannels.get(channel);
        pin.setPwm((int) (value * 1024));
    }
}

package com.jpbnetsoftware.alimak.athena;

/**
 * Created by pburzynski on 09/10/2016.
 */
public interface MotorController {
    void setValue(float value, int duration);

    void refresh();

    void shutdown();
}

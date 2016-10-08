package com.jpbnetsoftware.alimak.athena;

/**
 * Created by pburzynski on 08/10/2016.
 */
public class DefaultCarManager implements CarManager {
    @Override
    public void drive(float speed, int lifespan) {
        if (Math.abs(speed) > 1) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void turn(float turnPercentage, int lifespan) {
        if (Math.abs(turnPercentage) > 1) {
            throw new IllegalArgumentException();
        }
    }
}

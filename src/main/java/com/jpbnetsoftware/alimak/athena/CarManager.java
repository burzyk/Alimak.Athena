package com.jpbnetsoftware.alimak.athena;

/**
 * Created by pburzynski on 08/10/2016.
 */
public interface CarManager {
    void drive(float speed, int lifespan);

    void turn(float turnPercentage, int lifespan);
}

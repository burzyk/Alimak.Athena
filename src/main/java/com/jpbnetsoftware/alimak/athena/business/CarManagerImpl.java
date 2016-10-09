package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.CarManager;
import com.jpbnetsoftware.alimak.athena.MotorController;

/**
 * Created by pburzynski on 08/10/2016.
 */
public class CarManagerImpl implements CarManager {

    private Thread mainThread;

    private MotorController driveController;

    private MotorController turnController;

    public CarManagerImpl(MotorController driveController, MotorController turnController) {
        this.mainThread = new Thread();
        this.driveController = driveController;
        this.turnController = turnController;
    }

    @Override
    public void drive(float speed, int duration) {
        this.init();
        this.driveController.setValue(speed, duration);
    }

    @Override
    public void turn(float turnPercentage, int duration) {
        this.init();
        this.turnController.setValue(turnPercentage, duration);
    }

    private void init() {
        this.mainThread = new Thread(() -> {
            while (true) {
                driveController.decrementDuration();
                turnController.decrementDuration();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        this.mainThread.start();
    }
}

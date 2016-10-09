package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.CarManager;
import com.jpbnetsoftware.alimak.athena.MotorController;

/**
 * Created by pburzynski on 08/10/2016.
 */
public class CarManagerImpl implements CarManager {

    private final int RESOLUTION = 10;

    private Thread mainThread;

    private MotorController driveController;

    private MotorController turnController;

    public CarManagerImpl(MotorController driveController, MotorController turnController) {
        this.driveController = driveController;
        this.turnController = turnController;
    }

    @Override
    public void drive(float speed, int duration) {
        this.init();
        this.driveController.setValue(speed, duration * RESOLUTION);
    }

    @Override
    public void turn(float turnPercentage, int duration) {
        this.init();
        this.turnController.setValue(turnPercentage, duration * RESOLUTION);
    }

    private void init() {
        if(this.mainThread != null) {
            return;
        }

        this.mainThread = new Thread(() -> {
            while (true) {
                driveController.refresh();
                turnController.refresh();

                try {
                    Thread.sleep(1000 / RESOLUTION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        this.mainThread.start();
    }
}

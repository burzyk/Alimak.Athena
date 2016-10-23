package com.jpbnetsoftware.alimak.athena.business;

import com.jpbnetsoftware.alimak.athena.CarManager;
import com.jpbnetsoftware.alimak.athena.MotorController;

/**
 * Created by pburzynski on 08/10/2016.
 */
public class CarManagerImpl implements CarManager {

    private final int RESOLUTION = 10;

    private final MotorController driveController;

    private final MotorController turnController;

    private Thread mainThread;

    public CarManagerImpl(MotorController driveController, MotorController turnController) {
        this.driveController = driveController;
        this.turnController = turnController;
    }

    @Override
    public synchronized void drive(float speed, int duration) {
        this.init();
        this.driveController.setValue(speed, duration / RESOLUTION);
    }

    @Override
    public synchronized void turn(float turnPercentage, int duration) {
        this.init();
        this.turnController.setValue(turnPercentage, duration / RESOLUTION);
    }

    @Override
    public synchronized void shutdown() {
        this.driveController.shutdown();
        this.turnController.shutdown();
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
                    Thread.sleep(RESOLUTION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        this.mainThread.start();
    }
}

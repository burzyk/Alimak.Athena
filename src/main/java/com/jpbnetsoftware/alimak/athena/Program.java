package com.jpbnetsoftware.alimak.athena;

import com.jpbnetsoftware.alimak.athena.business.CarManagerImpl;
import com.jpbnetsoftware.alimak.athena.business.ConsolePinManager;
import com.jpbnetsoftware.alimak.athena.business.MotorControllerImpl;
import com.jpbnetsoftware.alimak.athena.business.PiPinManager;
import com.jpbnetsoftware.alimak.athena.server.UdpServer;

import java.io.IOException;

/**
 * Created by pburzynski on 08/10/2016.
 */
public class Program {
    public static void main(String[] arguments) throws InterruptedException, IOException {
        Log log = new ConsoleLog();
        PinManager pinManager = new ConsolePinManager(log);
        CarManager carManager = new CarManagerImpl(new MotorControllerImpl(0, 1, pinManager), new MotorControllerImpl(2, 3, pinManager));
        ControlServer server = new UdpServer(carManager, log);

        server.start();
        System.in.read();
        server.stop();
    }
}

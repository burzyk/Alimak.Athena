package com.jpbnetsoftware.alimak.athena;

import com.jpbnetsoftware.alimak.athena.business.CarManagerImpl;
import com.jpbnetsoftware.alimak.athena.business.ConsolePinManager;
import com.jpbnetsoftware.alimak.athena.business.MotorControllerImpl;
import com.jpbnetsoftware.alimak.athena.business.PiPinManager;
import com.jpbnetsoftware.alimak.athena.server.UdpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * Created by pburzynski on 08/10/2016.
 */
@SpringBootApplication
public class Program {
    public static void main(String[] arguments) throws InterruptedException, IOException {

        /*
        Log log = new ConsoleLog();
        PinManager pinManager = createPinManager(log);
        CarManager carManager = new CarManagerImpl(new MotorControllerImpl(0, 1, pinManager), new MotorControllerImpl(2, 3, pinManager));
        ControlServer server = new UdpServer(carManager, log);

        server.start();
        System.in.read();
        server.stop();
        */

        SpringApplication.run(Program.class, arguments);
    }

    @Bean
    public Log log() {
        return new ConsoleLog();
    }

    @Bean
    public CarManager carManager() {
        PinManager pinManager = new PiPinManager();
        //PinManager pinManager = new ConsolePinManager(this.log());
        return new CarManagerImpl(new MotorControllerImpl(0, 1, pinManager), new MotorControllerImpl(2, 3, pinManager));
    }

    private static PinManager createPinManager(Log log) {
        String pinManager = System.getProperty("PinManager");

        if (pinManager != null && pinManager.equals("local")) {
            log.info("Using local pin manager");
            return new ConsolePinManager(log);
        } else {
            log.info("Using raspberry pin manager");
            return new ConsolePinManager(log);
        }
    }
}

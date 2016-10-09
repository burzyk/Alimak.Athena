package com.jpbnetsoftware.alimak.athena;

import com.jpbnetsoftware.alimak.athena.business.CarManagerImpl;
import com.jpbnetsoftware.alimak.athena.business.ConsolePinManager;
import com.jpbnetsoftware.alimak.athena.business.MotorControllerImpl;
import com.jpbnetsoftware.alimak.athena.business.PiPinManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by pburzynski on 08/10/2016.
 */
@SpringBootApplication
public class Program {
    public static void main(String[] arguments) throws InterruptedException {
        SpringApplication.run(Program.class, arguments);
    }

    @Bean
    public CarManager carManager() {
        // PinManager pinManager = new PiPinManager();
        PinManager pinManager = new ConsolePinManager();
        return new CarManagerImpl(new MotorControllerImpl(0, pinManager), new MotorControllerImpl(1, pinManager));
    }
}

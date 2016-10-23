package com.jpbnetsoftware.alimak.athena;

import com.jpbnetsoftware.alimak.athena.business.CarManagerImpl;
import com.jpbnetsoftware.alimak.athena.business.ConsolePinManager;
import com.jpbnetsoftware.alimak.athena.business.MotorControllerImpl;
import com.jpbnetsoftware.alimak.athena.business.PiPinManager;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.io.IOException;

/**
 * Created by pburzynski on 08/10/2016.
 */
@SpringBootApplication
public class Program {
    public static void main(String[] arguments) throws InterruptedException, IOException {

        /*

        System.out.println("Starting");


        Gpio.wiringPiSetup();
        SoftPwm.softPwmCreate(0, 0, 100);
        SoftPwm.softPwmCreate(1, 0, 100);
        SoftPwm.softPwmCreate(2, 0, 100);
        SoftPwm.softPwmCreate(3, 0, 100);


        SoftPwm.softPwmWrite(0, 25);
        SoftPwm.softPwmWrite(1, 50);
        SoftPwm.softPwmWrite(2, 75);
        SoftPwm.softPwmWrite(3, 100);

        System.in.read();

        SoftPwm.softPwmStop(0);
        SoftPwm.softPwmStop(1);
        SoftPwm.softPwmStop(2);
        SoftPwm.softPwmStop(3);
        */
        SpringApplication.run(Program.class, arguments);
    }

    @Bean
    public CarManager carManager() {
        PinManager pinManager = new PiPinManager();
        //PinManager pinManager = new ConsolePinManager();
        return new CarManagerImpl(new MotorControllerImpl(0, 1, pinManager), new MotorControllerImpl(2, 3, pinManager));
    }
}

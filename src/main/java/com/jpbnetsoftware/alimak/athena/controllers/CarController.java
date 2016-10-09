package com.jpbnetsoftware.alimak.athena.controllers;

import com.jpbnetsoftware.alimak.athena.CarManager;
import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pburzynski on 08/10/2016.
 */
@RestController
public class CarController {

    @Autowired
    private CarManager carManager;

    @RequestMapping("api/drive")
    public void drive(@RequestParam float speed, @RequestParam int duration) {
        carManager.drive(speed, duration);
    }

    @RequestMapping("api/turn")
    public void turn(@RequestParam float turnPercentage, @RequestParam int duration) {
        carManager.turn(turnPercentage, duration);
    }
}

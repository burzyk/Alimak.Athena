package com.jpbnetsoftware.alimak.athena.controllers;

import com.jpbnetsoftware.alimak.athena.CarManager;
import com.jpbnetsoftware.alimak.athena.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pburzynski on 20/11/2016.
 */
@RestController
public class CarController {

    @Autowired
    private CarManager carManager;

    @Autowired
    private Log log;

    @RequestMapping("api/drive")
    public void drive(@RequestParam float speed, @RequestParam int duration) {
        log.info("drive: speed:"  + speed + " duration: " + duration);
        carManager.drive(speed, duration);
    }

    @RequestMapping("api/turn")
    public void turn(@RequestParam float turnPercentage, @RequestParam int duration) {
        log.info("turn: turnPercentage: " + turnPercentage + " duration: " + duration);
        carManager.turn(turnPercentage, duration);
    }
}

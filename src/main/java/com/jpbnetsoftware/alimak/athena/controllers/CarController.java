package com.jpbnetsoftware.alimak.athena.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pburzynski on 08/10/2016.
 */
@RestController
public class CarController {

    @RequestMapping("api/test")
    public String testMetiod() {
        return "ala ma kota";
    }
}

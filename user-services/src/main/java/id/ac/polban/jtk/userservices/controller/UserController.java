/*
 * Copyright April - 2020
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package id.ac.polban.jtk.userservices.controller;

import id.ac.polban.jtk.userservices.model.User;
import id.ac.polban.jtk.userservices.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.Resource;

/**
 *
 * @author ASUS
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices services;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Resource<String>> addUser(@RequestParam String userId,
            @RequestParam String name, @RequestParam String contact) {
        LOGGER.debug("masuk add");
        Resource<String> userRes;

        try {
            User user = new User(name, userId, contact);
            services.saveUser(user);
            userRes = new Resource<>("PASS");
        } catch (Exception e) {
            LOGGER.debug(e.toString());
            userRes = new Resource<>("FAIL");
        }
        System.out.println("hehe");
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @RequestMapping("/findByName")
    public void findByName(@RequestParam(defaultValue = "0") String addend1, @RequestParam(defaultValue = "0") String addend2) {
        LOGGER.debug("masuk serach1");
    }

    @RequestMapping("/findByUserId")
    public void findByUserId(@RequestParam(defaultValue = "0") String addend1, @RequestParam(defaultValue = "0") String addend2) {
        LOGGER.debug("masuk serach2");
    }

    @RequestMapping("/findByContact")
    public void findByContact(@RequestParam(defaultValue = "0") String addend1, @RequestParam(defaultValue = "0") String addend2) {
        LOGGER.debug("masuk serach3");
    }
}

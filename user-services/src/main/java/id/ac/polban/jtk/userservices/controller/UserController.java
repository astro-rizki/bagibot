/*
 * Copyright April - 2020
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package id.ac.polban.jtk.userservices.controller;

import id.ac.polban.jtk.userservices.UserServicesApplication;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServicesApplication.class);

    @Autowired
    private UserServices services;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Resource<String>> addUser(@RequestParam String userId,
            @RequestParam String name, @RequestParam String contact) {
        LOGGER.debug("masuk add");
        Resource<String> userRes;

        try {
            User user = new User(userId, name, contact);
            LOGGER.debug(user.toString());
            services.saveUser(user);
            userRes = new Resource<>("PASS");
        } catch (Exception e) {
            LOGGER.debug(e.toString());
            userRes = new Resource<>("FAIL");
        }
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public ResponseEntity<Resource<User>> findByName(@RequestParam String name) {
        LOGGER.debug("masuk serach by name\n");
        Resource<User> userRes;

        try {
            LOGGER.debug("searching user for name {}\n", name);
            User user = services.findUserByName(name);
            System.out.print(user.toString());
            userRes = new Resource<>(user);

        } catch (Exception e) {
            System.out.print(e.toString());
            return null;
        }
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
    public ResponseEntity<Resource<User>> findByUserId(@RequestParam String userId) {
        Resource<User> userRes;
        LOGGER.debug("masuk serach by userId\n");
        try {
            LOGGER.debug("searching user for userId : {}\n", userId);
            User user = services.findUserByUserId(userId);
            LOGGER.debug(user.toString());
            userRes = new Resource<>(user);

        } catch (Exception e) {
            LOGGER.debug(e.toString());
            return null;
        }
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByContact", method = RequestMethod.GET)
    public ResponseEntity<Resource<User>> findByContact(@RequestParam String contact) {
        Resource<User> userRes;

        LOGGER.debug("masuk serach by contact\n");
        try {
            LOGGER.debug("searching user for userId : {}\n", contact);
            User user = services.findUserByContact(contact);
            LOGGER.debug(user.toString());
            userRes = new Resource<>(user);
        } catch (Exception e) {
            LOGGER.debug(e.toString());
            return null;
        }
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Resource<String>> deleteUser(@RequestParam(required = false) String userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String contact) {
        Resource<String> msg;
        User user = null;

        LOGGER.debug("masuk serach by contact\n");

        if (userId != null) {
            user = services.findUserByUserId(userId);
        } else if (name != null) {
            user = services.findUserByName(name);
        } else if (contact != null) {
            user = services.findUserByContact(contact);
        }
        LOGGER.debug("deleting user " + user.toString());
        if (services.deleteUser(user)) {
            msg = new Resource<>("Success deleting user : " + user.toString());
        } else {
            msg = new Resource<>("Failed deleting user : " + user.toString());
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @RequestMapping(value = "/block", method = RequestMethod.POST)
    public ResponseEntity<Resource<String>> blockUser(@RequestParam String userId) {
        LOGGER.debug("masuk block");
        Resource<String> msg;

        LOGGER.debug(userId);
        if (services.blockUser(userId)) {
            msg = new Resource<>("Successfully block user with id : " + userId);
        } else {
            msg = new Resource<>("fail to block user with id : " + userId);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @RequestMapping(value = "/unblock", method = RequestMethod.POST)
    public ResponseEntity<Resource<String>> unblockUser(@RequestParam String userId) {
        LOGGER.debug("masuk unblock");
        Resource<String> msg;

        try {
            LOGGER.debug(userId);
            services.unblockUser(userId);
            msg = new Resource<>("Successfully unblock user with id : " + userId);
        } catch (Exception e) {
            LOGGER.debug(e.toString());
            msg = new Resource<>("fail to unblock user");
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}

/*
 * Copyright April - 2020
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package id.ac.polban.jtk.userservices.service;

import id.ac.polban.jtk.userservices.UserServicesApplication;
import id.ac.polban.jtk.userservices.repository.UserRepository;
import id.ac.polban.jtk.userservices.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class UserServicesImplementation implements UserServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServicesApplication.class);

    @Autowired
    private UserRepository repo;

    @Override
    public void saveUser(User user) {
        repo.save(user);
    }

    @Override
    public boolean editUser(User userDest, User userTarget) {
        try {
            repo.findById(userTarget.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try {
            repo.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User findUser(User userTarget) {
        //iterable search, may deprecated in future
        List<User> users = (List<User>) repo.findAll();
        for (User user : users) {
            if (user.equals(userTarget)) {
                return user;
            }
        }
        //kasih throw klo ga ketemu, there must be a better way
        return null;
    }

    @Override
    public User findUserByName(String name) {
        return repo.findUserByName(name);
    }

    @Override
    public User findUserByUserId(String userId) {
        return repo.findUserByUserId(userId);
    }

    @Override
    public User findUserByContact(String contact) {
        return repo.findUserByContact(contact);
    }

    @Override
    public boolean blockUser(String userId) {
        try {
            User u = repo.findUserByUserId(userId);
            u.block();
            System.out.println(u.toString());
            repo.save(u);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean unblockUser(String userId) {
        try {
            User u = repo.findUserByUserId(userId);
            u.unblock();
            repo.save(u);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

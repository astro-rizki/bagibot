/*
 * Copyright April - 2020
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package id.ac.polban.jtk.userservices.service;

import id.ac.polban.jtk.userservices.model.User;

/**
 *
 * @author ASUS
 */
public interface UserServices {
      
    //add
    public void saveUser(User user);
    //edit
    public boolean editUser(User userDest, User userTarget);
    //delete
    public boolean deleteUser(User user);
    //search
    public User findUser(User user);
    public User findUserByName(String name);
    public User findUserByUserId(String userId);
    public User findUserByContact(String contact);
    //block
    public boolean blockUser(String userId);
    //unblock
    public boolean unblockUser(String userId);
}

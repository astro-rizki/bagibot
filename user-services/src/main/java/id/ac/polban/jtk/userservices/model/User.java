/*
 * Copyright April - 2020
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package id.ac.polban.jtk.userservices.model;

import id.ac.polban.jtk.userservices.controller.UserController;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.util.Objects;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.TypeDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name="user_info")
@NamedQuery(name = "User.findUserByUserId", query = "select u from User u where u.userId = ?1")
@NamedQuery(name = "User.findUserByName", query = "select u from User u where u.name = ?1")
@NamedQuery(name = "User.findUSerByContact", query = "select u from User u where u.contact = ?1")
public class User {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    
    @Column(name = "userId", nullable = false, unique=true)
    private String userId;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "contact", nullable = false, unique=true)
    private String contact;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "current_status", nullable = false)
    private Status current_status;
    
    @Column(name = "firstContact", nullable = false)
    private LocalDateTime firstContact;

    private enum Status {
        active, blocked
    }
      
    public User(String userId, String name, String contact){
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.current_status = Status.active;
        this.firstContact = now();
    }
    
    public User(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentStatus() {
        return current_status.name();
    }
    public void setCurrentStatus(String status) {
        Status stat =  Status.valueOf(status.toLowerCase());
        this.current_status = stat;
    }
    public void block(){
        setCurrentStatus("blocked");
    }
    public void unblock(){
        setCurrentStatus("active");
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public LocalDateTime getFirstContact() {
        return firstContact;
    }
    public void setFirstContact(LocalDateTime firstContact) {
        this.firstContact = firstContact;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (contact == null) {
            if (other.contact != null) {
                return false;
            }
        } else if (!contact.equals(other.contact)) {
            return false;
        }
        if (current_status == null) {
            if (other.current_status != null) {
                return false;
            }
        } else if (!current_status.equals(other.current_status)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + Objects.hashCode(this.userId);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.contact);
        hash = 41 * hash + Objects.hashCode(this.current_status);
        return hash;
    }
    
    @Override
    public String toString(){
        return "Id : " + id +
                "  userId : " + userId +
                "  name : " + name +
                "  contact : " + contact +
                "  first contact : " + firstContact +
                "  status : " + current_status;
    }

    
}



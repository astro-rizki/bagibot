/*
 * Copyright April - 2020
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package id.ac.polban.jtk.userservices.repository;

import id.ac.polban.jtk.userservices.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    List<User> findByName( String name );
}

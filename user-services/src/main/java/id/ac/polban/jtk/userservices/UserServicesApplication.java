package id.ac.polban.jtk.userservices;

import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages="id.ac.polban.jtk.userservices.repository")
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableAutoConfiguration
//@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class UserServicesApplication{
       
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "user-services");
        SpringApplication.run(UserServicesApplication.class, args);
    }
}

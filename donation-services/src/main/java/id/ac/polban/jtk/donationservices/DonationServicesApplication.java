package id.ac.polban.jtk.donationservices;



import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class DonationServicesApplication {
    protected static final Logger logger = Logger.getLogger(DonationServicesApplication.class.getName());
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "donation-services");
        SpringApplication.run(DonationServicesApplication.class, args);
    }
}
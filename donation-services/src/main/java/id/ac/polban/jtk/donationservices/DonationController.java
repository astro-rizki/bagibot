/*
 * Copyright April - 2020
 * JTK POLBAN.
 * Made in Ciwaruga.
 */
package id.ac.polban.jtk.donationservices;

/**
 *
 * @author ASUS
 */
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationController {
    protected Logger logger = Logger.getLogger(DonationController.class.getName());
    
    @RequestMapping("/add")
    public void doAdd(@RequestParam(defaultValue="0") String addend1,@RequestParam(defaultValue="0") String addend2) {
        int augend1 = Integer.valueOf(addend1);
        int augend2 = Integer.valueOf(addend2);
        int sum = augend1 + augend2;
        
    }
}

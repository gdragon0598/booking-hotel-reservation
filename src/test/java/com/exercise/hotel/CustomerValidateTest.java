package com.exercise.hotel;
import com.exercise.hotel.util.CustomerValidate;
import org.junit.jupiter.api.Test;

public class CustomerValidateTest {
    @Test
    void testEmailValidate() {
        String email = "_leminhhuan72@gmail.com";
        if(CustomerValidate.emailValidate(email)) {
            System.out.println("Email is valid");
        } else {
            System.out.println("Email is invalid");
        }
    }
}

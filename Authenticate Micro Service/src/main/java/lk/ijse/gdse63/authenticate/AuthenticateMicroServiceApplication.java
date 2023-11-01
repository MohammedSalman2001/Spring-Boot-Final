package lk.ijse.gdse63.authenticate;

import lk.ijse.gdse63.authenticate.entity.Admin;
import lk.ijse.gdse63.authenticate.repo.AdminRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class AuthenticateMicroServiceApplication{

    AdminRepo adminRepo;

    public static void main(String[] args) {
        SpringApplication.run(AuthenticateMicroServiceApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }


}

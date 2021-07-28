package com.example.demo.config;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User rosi = new User(null, "Rosi Zila", "rosi@gmail.com");
        User alex = new User(null, "Alex Bombs", "alex@gmail.com");
        User neno = new User(null, "Neno Grey", "neno@gmail.com");

        userRepository.saveAll(Arrays.asList(rosi, alex, neno)); 
        /*
        Aqui, tive o mesmo problema que outras pessoas do curso tiveram: em vez de
        'userRepository.save(Arrays.asList(rosi, alex, neno));'
        tive que usar
        'userRepository.saveAll(Arrays.asList(rosi, alex, neno));'
         */

    }
}

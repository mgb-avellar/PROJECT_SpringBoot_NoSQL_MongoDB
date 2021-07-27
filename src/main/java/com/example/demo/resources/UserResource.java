package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    // Aula 347: conectando ao mongodb service
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {

        /*  Comentado na aula 347 para refatoração
        User maria = new User("1", "Maria Brown", "maria@gmail.com");
        User alex = new User("2", "Alex Green", "alex@gmail.com");

        List<User> list = new ArrayList<>();

        list.addAll(Arrays.asList(maria, alex));
         */

        List<User> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }
}

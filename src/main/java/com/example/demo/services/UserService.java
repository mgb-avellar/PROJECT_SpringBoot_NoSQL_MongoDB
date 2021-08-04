package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {

        return repo.findAll();
    }

    public User findById(String id) {

        //User user = repo.findOne(id); // Não funciona por causa nas mudanças no Spring Boot 1 -> 2
        Optional<User> user = repo.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj) {

        return repo.insert(obj);
    }

    public User fromDTO(UserDTO objDto) {

        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

    public void delete(String id) {

        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        User newObj = repo.findById(obj.getId()).get();
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj) {

        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

}

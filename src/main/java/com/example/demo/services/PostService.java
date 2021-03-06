package com.example.demo.services;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {

        Optional<Post> post = repo.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    // Criando um método de busca (aula 361)

    public List<Post> findByTitle(String text) {

        // return repo.findByTitleContainingIgnoreCase(text); // Comentado na aula 362
        return repo.searchTitle(text);
    }

    // Aula 363

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {

        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); // Macete para pegar a data correta, considerando o último dia mais 24 horas (em milissegundos)
        return repo.fullSearch(text, minDate, maxDate);
    }
}

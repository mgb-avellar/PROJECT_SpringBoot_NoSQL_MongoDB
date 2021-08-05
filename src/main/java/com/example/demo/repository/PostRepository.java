package com.example.demo.repository;

import com.example.demo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    /* Aula 361
    Consulta para buscar posts contendo um dado string no título.
     */

    List<Post> findByTitleContainingIgnoreCase(String text);
}

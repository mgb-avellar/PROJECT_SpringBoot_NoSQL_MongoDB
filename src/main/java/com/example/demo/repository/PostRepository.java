package com.example.demo.repository;

import com.example.demo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    /* Aula 361
    Consulta para buscar posts contendo um dado string no título.
     */

    List<Post> findByTitleContainingIgnoreCase(String text);

    // Método alternativo da aula 362 (ver modificação no arquivo PostService)
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text); // Ao contrário do anterior, aqui vai o nome que eu quiser

    // Aula 363: ver documentação do mongodb em 'logical query operators'

    @Query("{ $and: [ { date: {$gte: ?1} }, { date: {$lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}

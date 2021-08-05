package com.example.demo.resources;

import com.example.demo.domain.Post;
import com.example.demo.resources.util.URL;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {

        Post obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    // Endpoint para fazer a busca (aula 361)

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {

        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }

    // Endpoint de fullSearch

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L)); // Data default de 1970
        Date max = URL.convertDate(maxDate, new Date()); // Data atual do sistema

        List<Post> list = service.fullSearch(text, min, max);

        return ResponseEntity.ok().body(list);
    }
}

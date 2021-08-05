package com.example.demo.config;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    // Para salvar os usuários no banco de dados, preciso injetar o UserRepository aqui.
    @Autowired
    private UserRepository userRepository;

    // Para salvar os posts no banco de dados, preciso injetar o PostRepository aqui também.
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        // Para a aula 354: criando uma entidade Post
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));


        // Para limpar qualquer coisa do banco para testarmos
        userRepository.deleteAll();
        postRepository.deleteAll();

        User rosi = new User(null, "Rosi Zila", "rosi@gmail.com");
        User alex = new User(null, "Alex Bombs", "alex@gmail.com");
        User neno = new User(null, "Neno Grey", "neno@gmail.com");

        // Salvando no banco
        userRepository.saveAll(Arrays.asList(rosi, alex, neno));
        /*
        Aqui, tive o mesmo problema que outras pessoas do curso tiveram: em vez de
        'userRepository.save(Arrays.asList(rosi, alex, neno));'
        tive que usar
        'userRepository.saveAll(Arrays.asList(rosi, alex, neno));'
         */

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(rosi));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(rosi));
        // new AuthorDTO(rosi) na aula 356

        // Instanciando logo abaixo alguns comentários da aula 360 (entre esta linha e a linha do '// Salvando no banco')

        CommentDTO c1 = new CommentDTO("Boa viagem, Zi!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite, Zi!", sdf.parse("22/03/2018"), new AuthorDTO(neno));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

        // Próximo passo é associar os comentários acima com os respectivos posts
        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        // Salvando no banco
        postRepository.saveAll(Arrays.asList(post1, post2));

        // aula 357
        rosi.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(rosi);
    }
}

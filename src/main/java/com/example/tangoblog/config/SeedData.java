package com.example.tangoblog.config;

import com.example.tangoblog.models.Post;
import com.example.tangoblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts =  postService.getAll();

        if (posts.isEmpty()){
            Post post1 = new Post();
            post1.setTitle("Title number 1");
            post1.setBody("Body of post number 1");

            Post post2 = new Post();
            post2.setTitle("Title number 2");
            post2.setBody("Body of post number 2");

            postService.save(post1);
            postService.save(post2);
        }
    }
}
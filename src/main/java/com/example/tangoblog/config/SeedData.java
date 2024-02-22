package com.example.tangoblog.config;

import com.example.tangoblog.models.Account;
import com.example.tangoblog.models.Post;
import com.example.tangoblog.services.AccountService;
import com.example.tangoblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts =  postService.getAll();

        if (posts.isEmpty()){
            Account account1 = new Account();
            account1.setFirstName("Bob");
            account1.setLastName("Bo");
            account1.setEmail("bob@domain.com");
            account1.setPassword("strong");

            Account account2 = new Account();
            account2.setFirstName("Alice");
            account2.setLastName("Ali");
            account2.setEmail("alice@domain.com");
            account2.setPassword("weak");

            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("Title number 1");
            post1.setBody("Body of post number 1");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("Title number 2");
            post2.setBody("Body of post number 2");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);
        }
    }
}

package com.example.tangoblog.config;

import com.example.tangoblog.models.Account;
import com.example.tangoblog.models.Authority;
import com.example.tangoblog.models.Post;
import com.example.tangoblog.repositories.AuthorityRepository;
import com.example.tangoblog.services.AccountService;
import com.example.tangoblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts =  postService.getAll();

        if (posts.isEmpty()){
            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            account1.setFirstName("Bob");
            account1.setLastName("Bo");
            account1.setEmail("bob@domain.com");
            account1.setPassword("strong");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            Account account2 = new Account();
            account2.setFirstName("Alice");
            account2.setLastName("Ali");
            account2.setEmail("alice@domain.com");
            account2.setPassword("weak");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

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

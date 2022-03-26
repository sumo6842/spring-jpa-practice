package com.ductlmse.learning_guru_spring.config;

import com.ductlmse.learning_guru_spring.domain.Author;
import com.ductlmse.learning_guru_spring.domain.Book;
import com.ductlmse.learning_guru_spring.domain.Publisher;
import com.ductlmse.learning_guru_spring.repositories.AuthorRepositories;
import com.ductlmse.learning_guru_spring.repositories.BookRepository;
import com.ductlmse.learning_guru_spring.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BootstrapData implements ApplicationRunner {

    private final AuthorRepositories AuthorRepositories;
    public final BookRepository bookRepository;
    public final PublisherRepository publisherRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book_1 = new Book("Effective Java");
        Book book_2 = new Book("Async in java");

        Publisher pub = new Publisher("Nxb Kim Dong");

        pub.addBook(book_1);
        pub.addBook(book_2);

        publisherRepository.save(pub);


    }
}

package com.rebirth.flapdoodletest;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.rebirth.flapdoodletest.entity.Book;
import com.rebirth.flapdoodletest.repository.BookRepository;

class FlapdoodleApplicationTests extends AbstractDatabaseTest {

    @Test
    void example2(@Autowired final BookRepository bookRepository) {
        Assertions.assertNotNull(bookRepository);
    }

    @Test
    void example3(@Autowired final BookRepository bookRepository) {
        Book book = bookGenerator();
        bookRepository.insert(book);
        Assertions.assertFalse(bookRepository.findAll().isEmpty());
    }

}

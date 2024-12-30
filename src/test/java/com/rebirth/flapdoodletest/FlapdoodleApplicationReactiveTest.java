package com.rebirth.flapdoodletest;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.rebirth.flapdoodletest.entity.Book;

public class FlapdoodleApplicationReactiveTest extends AbstractDatabaseTest {

    @Test
    void exampleReactive1(@Autowired final ReactiveMongoTemplate reactiveMongoTemplate) {
        Book book = bookGenerator();
        Book mono = reactiveMongoTemplate.insert(Book.class)
            .one(book)
            .block();
        Assertions.assertNotNull(mono);
        List<Book> bookList = reactiveMongoTemplate.findAll(Book.class).collectList().block();
        Assertions.assertNotNull(bookList);
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertEquals(book, bookList.getFirst());
    }

    @Test
    void exampleReactive2(@Autowired final ReactiveMongoTemplate reactiveMongoTemplate) {

        List<Book> bookToSave = IntStream.range(0, 10)
            .mapToObj(i -> bookGenerator())
            .toList();

        List<Book> bookThatWasSaved = reactiveMongoTemplate.insert(Book.class)
            .all(bookToSave)
            .collectList()
            .block();

        Assertions.assertNotNull(bookThatWasSaved);
        List<Book> bookList = reactiveMongoTemplate.findAll(Book.class).collectList().block();
        Assertions.assertNotNull(bookList);
        Assertions.assertFalse(bookList.isEmpty());
    }

}

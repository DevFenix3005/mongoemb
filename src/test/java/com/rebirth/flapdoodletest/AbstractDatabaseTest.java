package com.rebirth.flapdoodletest;

import java.util.Random;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rebirth.flapdoodletest.entity.Book;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

@DataMongoTest()
@ExtendWith(SpringExtension.class)
public abstract class AbstractDatabaseTest {
    private static final Lorem LOREM = LoremIpsum.getInstance();

    private static final Random RANDOM = new Random();

    protected Book bookGenerator() {
        int year = 1900 + RANDOM.nextInt(119);
        String language = RANDOM.nextBoolean() ? "Spanish" : "English";

        Book book = new Book();
        book.setAuthor(LOREM.getName());
        book.setGenre(LOREM.getCountry());
        book.setPublisher(LOREM.getWords(2));
        book.setYear(year);
        book.setLanguage(language);
        return book;
    }

}

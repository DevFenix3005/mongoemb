package com.rebirth.flapdoodletest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rebirth.flapdoodletest.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}

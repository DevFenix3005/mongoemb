package com.rebirth.flapdoodletest.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "books")
public class Book implements Serializable {

    @Id
    private String id;

    private String name;

    private String author;

    private String genre;

    private String publisher;

    private int year;

    private String language;

}

package com.example.datamongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Manufacturer {
    @Id private String id;
    private String name;
}

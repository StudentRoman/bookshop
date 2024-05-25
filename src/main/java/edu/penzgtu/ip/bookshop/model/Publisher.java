package edu.penzgtu.ip.bookshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Publisher {
    @Id
    private String id;

    private String name;

    private Long year;
}

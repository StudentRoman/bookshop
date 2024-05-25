package edu.penzgtu.ip.bookshop.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document()
@Data
@Builder
public class Book {
  @Id
  private String id;

  private String name;

  private String ISBN;

  @DBRef
  private List<Author> author;

  @DBRef
  private List<Publisher> publisher;

  private Long price;

  private String category;
}

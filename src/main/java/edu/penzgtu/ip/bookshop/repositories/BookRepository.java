package edu.penzgtu.ip.bookshop.repositories;

import edu.penzgtu.ip.bookshop.model.Book;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByName(String bookName);
}
package edu.penzgtu.ip.bookshop;

import edu.penzgtu.ip.api.BookApi;
import edu.penzgtu.ip.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController implements BookApi {

    @GetMapping("/{bookId}")
    public ResponseEntity<List<Book>> getBookById(@PathVariable int bookId) {
        return ResponseEntity.ok(new ArrayList<>());
    }
}

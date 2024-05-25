package edu.penzgtu.ip.bookshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.penzgtu.ip.bookshop.exceptions.ResourceNotFoundException;
import edu.penzgtu.ip.bookshop.mappers.BookMapper;
import edu.penzgtu.ip.bookshop.model.Book;
import edu.penzgtu.ip.bookshop.repositories.BookRepository;
import edu.penzgtu.ip.model.BookDTO;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookMapper bookMapper;

	public BookDTO getBookByName(String bookName) {
		Optional<Book> book = bookRepository.findByName(bookName);

		if (!book.isPresent()) {
			throw new ResourceNotFoundException("Книга не найдена");
		} else {
			return bookMapper.mapToDto(book.get());
		}
	}

	public BookDTO getBookById(String bookId) {
		Optional<Book> book = bookRepository.findById(bookId);

		if (!book.isPresent()) {
			throw new ResourceNotFoundException("Книга не найдена");
		} else {
			return bookMapper.mapToDto(book.get());
		}
	}

	public BookDTO addBook(BookDTO newBook) {
		Optional<Book> book = bookRepository.findByName(newBook.getName());

		if (book.isPresent()) {
			throw new ResourceNotFoundException("Книга уже существует");
		}

		Book bookModel = bookMapper.mapToModel(newBook);
		Book addedBook = bookRepository.insert(bookModel);

		return bookMapper.mapToDto(addedBook);
	}

	public BookDTO updateBook(BookDTO updatedBook) {
		Optional<Book> foundedBook = bookRepository.findById(updatedBook.getId());

		if (foundedBook.isPresent()) {
			throw new ResourceNotFoundException("Книга не найдена");
		}

		Book bookModel = bookMapper.mapToModel(updatedBook);
		Book book = bookRepository.save(bookModel);

		return bookMapper.mapToDto(book);
	}

	public boolean deleteBook(String bookId) {
		boolean isDeleted = bookRepository.existsById(bookId);

		if (isDeleted) {
			throw new ResourceNotFoundException("Книга не найдена");
		}

		return isDeleted;
	}
}

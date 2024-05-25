package edu.penzgtu.ip.bookshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import edu.penzgtu.ip.bookshop.exceptions.ResourceNotFoundException;
import edu.penzgtu.ip.bookshop.mappers.BookMapper;
import edu.penzgtu.ip.bookshop.model.Book;
import edu.penzgtu.ip.bookshop.repositories.BookRepository;
import edu.penzgtu.ip.bookshop.services.BookService;
import edu.penzgtu.ip.model.BookDTO;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {
	@Mock
	private BookRepository bookRepository;

	@Spy
	private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

	@InjectMocks
	private BookService bookService;

	private Book book;

	@BeforeEach
	public void setup() {
		book = Book.builder()
				.id("12345")
				.name("Book #1")
				.ISBN("1234-3123-32131")
				.price(1L)
				.build();
	}

	@DisplayName("Test for addBook method")
	@Test
	public void givenBookObject_whenSaveBook_thenReturnBookObject() {
		given(bookRepository.findByName(book.getName())).willReturn(Optional.empty());
		given(bookRepository.insert(any(Book.class))).willReturn(book);

		BookDTO newBook = new BookDTO(null, "Book #2", null, null, 2L);
		BookDTO addedBook = bookService.addBook(newBook);

		assertThat(addedBook).isNotNull();
		verify(bookRepository).insert(any(Book.class));
	}

	@DisplayName("Test for addBook method which throws exception")
	@Test
	public void givenExistingName_whenSaveBook_thenThrowsException() {
		given(bookRepository.findByName(book.getName())).willReturn(Optional.of(book));

		BookDTO newBook = new BookDTO(null, "Book #1", null, null, 2L);

		assertThrows(ResourceNotFoundException.class, () -> {
			bookService.addBook(newBook);
		});

		verify(bookRepository, never()).save(any(Book.class));
	}

	@DisplayName("Test for getBookByName method")
	@Test
	public void givenBookName_whenGetBookByName_thenReturnBookObject() {
		given(bookRepository.findById(book.getName())).willReturn(Optional.of(book));

		BookDTO returnedBook = bookService.getBookById(book.getName());

		assertEquals(book.getName(), returnedBook.getName());
		verify(bookRepository).findById(book.getName());
	}

	@DisplayName("Test for getBookById method")
	@Test
	public void givenBookId_whenGetBookById_thenReturnBookObject() {
		given(bookRepository.findById(book.getId())).willReturn(Optional.of(book));

		BookDTO returnedBook = bookService.getBookById(book.getId());

		assertEquals(book.getId(), returnedBook.getId());
		verify(bookRepository).findById(book.getId());
	}

	@DisplayName("Test for updateBook method")
	@Test
	public void givenBookObject_whenUpdateBook_thenReturnUpdatedBook() {
		given(bookRepository.save(book)).willReturn(book);

		book.setName("Test Book");
		book.setPrice(3L);

		BookDTO updatedBook = bookService.updateBook(bookMapper.mapToDto(book));

		assertThat(updatedBook.getName()).isEqualTo("Test Book");
		assertThat(updatedBook.getPrice()).isEqualTo(3L);
	}

	@DisplayName("Test for deleteBook method")
	@Test
	public void givenBookId_whenDeleteBook_thenNothing() {
		String bookId = book.getId();

		willDoNothing().given(bookRepository).deleteById(bookId);

		bookService.deleteBook(bookId);

		verify(bookRepository, times(1)).existsById(bookId);
	}
}

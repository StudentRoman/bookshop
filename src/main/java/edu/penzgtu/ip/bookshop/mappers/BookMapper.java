package edu.penzgtu.ip.bookshop.mappers;

import org.mapstruct.Mapper;

import edu.penzgtu.ip.bookshop.model.Book;
import edu.penzgtu.ip.model.BookDTO;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book mapToModel(BookDTO dto);

    BookDTO mapToDto(Book model);
}

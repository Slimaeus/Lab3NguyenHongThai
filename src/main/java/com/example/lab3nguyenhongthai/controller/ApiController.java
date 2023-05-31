package com.example.lab3nguyenhongthai.controller;

import com.example.lab3nguyenhongthai.dto.BookDto;
import com.example.lab3nguyenhongthai.entity.Book;
import com.example.lab3nguyenhongthai.service.BookService;
import com.example.lab3nguyenhongthai.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/books")
public class ApiController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    private BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPrice(book.getPrice());
        bookDto.setCategoryName(categoryService.getCategoryById(book.getCategory().getId()).getName());
        return bookDto;
    }

    @GetMapping
    @ResponseBody
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(this::convertToBookDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BookDto getBookById(@PathVariable Long id) {
        return convertToBookDto(bookService.getBookById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteBook(@PathVariable Long id) {
        if (bookService.getBookById(id) != null)
            bookService.deleteBook(id);
    }
}

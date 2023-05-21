package com.example.lab3nguyenhongthai.controller;

import com.example.lab3nguyenhongthai.entity.Book;
import com.example.lab3nguyenhongthai.service.BookService;
import com.example.lab3nguyenhongthai.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView listBooks() {
        List<Book> books = bookService.getAllBooks();
        var modelAndView = new ModelAndView("book/list");
        modelAndView.addObject("books", books);
        modelAndView.addObject("title", "Book list");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addBookForm() {
        var modelAndView = new ModelAndView("book/add");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book editBook = bookService.getBookById(id);
        if (editBook != null) {
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        return "not-found";
    }

    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") Book updateBook, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        bookService.updateBook(updateBook);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}
package com.itheima.chapter07.controller;

import com.itheima.chapter07.entity.Book;
import com.itheima.chapter07.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping("list")
    public String findList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book_list";
    }
    
    @GetMapping("admin/manag")
    public String findManagList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book_manag";
    }
    
    @GetMapping("admin/add")
    public String showAddForm() {
        return "book_add";
    }
    
    @PostMapping("admin/add")
    public String addBook(Book book) {
        bookService.addBook(book);
        return "redirect:/book/admin/manag";
    }
    
    @GetMapping("admin/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book_edit";
    }
    
    @PostMapping("admin/edit")
    public String updateBook(Book book) {
        bookService.updateBook(book);
        return "redirect:/book/admin/manag";
    }
    
    @GetMapping("admin/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return "redirect:/book/admin/manag";
    }
} 
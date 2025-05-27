package com.itheima.chapter07.service.impl;

import com.itheima.chapter07.entity.Book;
import com.itheima.chapter07.mapper.BookMapper;
import com.itheima.chapter07.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public boolean addBook(Book book) {
        return bookMapper.insert(book) > 0;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookMapper.update(book) > 0;
    }

    @Override
    public boolean deleteBook(Integer id) {
        return bookMapper.deleteById(id) > 0;
    }
} 
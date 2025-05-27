package com.itheima.chapter07.service;

import com.itheima.chapter07.entity.Book;
import java.util.List;

public interface BookService {
    
    // 查询所有图书
    List<Book> findAll();
    
    // 根据ID查询图书
    Book findById(Integer id);
    
    // 添加图书
    boolean addBook(Book book);
    
    // 更新图书
    boolean updateBook(Book book);
    
    // 删除图书
    boolean deleteBook(Integer id);
} 
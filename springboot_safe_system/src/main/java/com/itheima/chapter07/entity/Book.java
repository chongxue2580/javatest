package com.itheima.chapter07.entity;

import lombok.Data;

@Data
public class Book {
    private Integer id;          // 图书编号
    private String name;         // 图书名称
    private String author;       // 作者
    private String publisher;    // 出版社
    private Double price;        // 价格
    private String description;  // 描述
    private Integer stock;       // 库存
    
    // 构造方法
    public Book() {}
    
    public Book(Integer id, String name, String author, String publisher, Double price, String description, Integer stock) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }
} 
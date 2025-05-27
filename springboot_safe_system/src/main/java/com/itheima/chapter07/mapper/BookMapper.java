package com.itheima.chapter07.mapper;

import com.itheima.chapter07.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    
    // 查询所有图书
    @Select("SELECT * FROM book")
    List<Book> findAll();
    
    // 根据ID查询图书
    @Select("SELECT * FROM book WHERE id = #{id}")
    Book findById(Integer id);
    
    // 添加图书
    @Insert("INSERT INTO book(name, author, publisher, price, description, stock) " +
            "VALUES(#{name}, #{author}, #{publisher}, #{price}, #{description}, #{stock})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Book book);
    
    // 更新图书
    @Update("UPDATE book SET name = #{name}, author = #{author}, publisher = #{publisher}, " +
            "price = #{price}, description = #{description}, stock = #{stock} WHERE id = #{id}")
    int update(Book book);
    
    // 删除图书
    @Delete("DELETE FROM book WHERE id = #{id}")
    int deleteById(Integer id);
} 
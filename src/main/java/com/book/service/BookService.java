package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;

import java.util.List;
import java.util.Map;

public interface BookService {
    // 获取书籍列表
    List<Book> getActiveBookList();
    // 删除书籍
    void deleteBook(int bid);
    // 添加书籍
    void addBook(String title, String desc, double price);
    // 添加借阅信息
    void addBorrow(int sid, int bid);
    // 删除借阅信息
    void returnBook(String id);
    // 查询借阅信息
    List<Borrow> getBorrowList();
    // 获取被借书籍列表
    Map<Book, Boolean> getBookList();
    // 获取学生列表
    List<Student> getStudentList();
}


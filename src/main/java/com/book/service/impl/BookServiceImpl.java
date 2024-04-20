package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.dao.StudentMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    // 查询所有书籍
    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try(SqlSession sqlSession = MybatisUtil.getSession()){
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBookList()
                    .stream()
                    .filter(book -> !set.contains(book.getBid()))
                    .collect(Collectors.toList());
        }
    }

    // 删除书籍
    @Override
    public void deleteBook(int bid) {
        try (SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        }
    }

    // 添加书籍
    @Override
    public void addBook(String title, String desc, double price) {
        try (SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.addBook(title, desc, price);
        }
    }

    // 查询借阅记录
    @Override
    public void addBorrow(int sid, int bid) {
        try (SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.addBorrow(sid, bid);
        }
    }

    // 删除借阅记录
    @Override
    public void returnBook(String id) {
        try (SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.deleteBorrow(id);
        }
    }

    // 查询借阅记录
    @Override
    public List<Borrow> getBorrowList() {
        try (SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBorrowList();
        }
    }

    // 获取被借书籍列表
    @Override
    public Map<Book, Boolean> getBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try (SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper((BookMapper.class));
            Map<Book, Boolean> map = new LinkedHashMap<>();
            mapper.getBookList().forEach((book -> map.put(book, set.contains(book.getBid()))));
            return map;
        }
    }

    // 查询学生列表
    @Override
    public List<Student> getStudentList() {
        try (SqlSession session = MybatisUtil.getSession()){
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            return mapper.getStudentList();
        }
    }

}

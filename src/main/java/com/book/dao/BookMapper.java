package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    // 获取书籍列表
    @Select("select * from book")
    List<Book> getBookList();
    // 删除书籍
    @Delete("delete from book where bid = #{bid}")
    void deleteBook(int bid);
    // 添加书籍
    @Insert("insert into book(title, `desc`, price) values(#{title}, #{desc}, #{price})")
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);
    // 添加借阅信息
    @Insert("insert into borrow(sid, bid) values (#{sid},#{bid})")
    void addBorrow(@Param("sid") int sid,@Param("bid") int bid);
    // 删除借阅信息
    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(String id);
    // 查询借阅信息
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "bid",property = "book_id"),
            @Result(column = "title",property = "book_name"),
            @Result(column = "time",property = "time"),
            @Result(column = "name",property = "student_name"),
            @Result(column = "sid",property = "student_id"),
    })
    @Select("select * from borrow,book,student where borrow.sid = student.sid and borrow.bid = book.bid")
    List<Borrow> getBorrowList();
}

package com.book.entity;

import lombok.Data;

@Data
public class Borrow {
    int id;
    int book_id;
    String book_name;
    int stduent_id;
    String stduent_name;
}

package com.book.service;

import jakarta.servlet.http.HttpSession;

public interface UesrService {
    boolean auth(String username, String password, HttpSession session);
}
